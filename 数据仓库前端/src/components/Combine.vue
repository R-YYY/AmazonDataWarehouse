<template>
  <div>
    <div>
      <el-cascader
          class="typeInput"
          v-model="type"
          :options="options"
          :props="{ expandTrigger: 'hover' }"
          style="width: 500px">
      </el-cascader>
      <el-select class="timeChoice" v-model="year" clearable placeholder="请选择年份" style="width: 120px">
        <el-option
            v-for="item in years"
            :key="item.year"
            :label="item.label"
            :value="item.year">
        </el-option>
      </el-select>
      <el-input class="directorInput" v-model="inputDirector" placeholder="请输入导演名称"></el-input>
      <el-button @click="addSearch">查询</el-button>
    </div>
    <div v-loading="isTimeLoad">
      <performance :id="name" :times="times"></performance>
    </div>
    <div v-loading="isLoad">
      <MovieInfo :movie-info="movieInfo"></MovieInfo>
    </div>
  </div>
</template>

<script>
import Performance from "@/components/Table/Performance";
import MovieInfo from "@/components/Table/MovieInfo";
export default {
  name: "Combine",
  components: {MovieInfo, Performance},
  data(){
    return {
      name:"combine",
      isLoad:false,
      isTimeLoad:false,
      movieInfo:[],
      year:null,
      years:[],
      inputDirector:"",
      type:"",
      options:[],
      times:{
        mysql:0.0,
        neo4j:0.0,
        hive:0.0
      },
    }
  },
  methods:{
    strip(str){
      return str.replace(/(^[ \t\n\r]+)|([ \t\n\r]+$)/g, '').replace(/(^[\s]+)|([\s]+$)/g, '')
    },

    async addSearch(){
      if(this.year===null || this.strip(this.inputDirector) === "" || this.type===null){
        this.$message.error("输入不能为空！");
        return ;
      }
      this.isLoad=true
      this.isTimeLoad=true
      let type = this.type[0] + ":" + this.type[1]

      //MySQL
      await this.$axios({
        url:"/mysql/movie/getbycombine",
        method:"post",
        data:{
          year:this.year,
          type:type,
          director:this.inputDirector
        }
      }).then((res)=>{
        this.movieInfo=res.data.data["movies"]
        this.times.mysql=res.data.data["timeCost"]
      }).catch()

      this.isLoad=false

      //neo4j
      this.$axios({
        url:"/neo4j/movie/getbycombine",
        method:"post",
        data:{
          year:this.year,
          type:type,
          director:this.inputDirector
        }
      }).then((res)=>{
        this.times.neo4j=res.data["timeCost"]
      }).catch()

      //hive
      if(window.sessionStorage.getItem("hive") === "1"){
        await this.$axios({
          url:"/hive/movie/getbycombine",
          method:"post",
          data:{
            year:this.year,
            type:type,
            director:this.inputDirector
          }
        }).then((res)=>{
          this.times.hive=res.data.data["timeCost"]
        }).catch()
      }

      this.isTimeLoad=false
    }
  },
  mounted() {
    for (let i = 1952; i < 2022; i++) {
      this.years.push({
        year:i,
        label:i + " 年"
      })
    }
    let type = this.$content.split("\n")
    for (let i = 0; i < type.length; i++) {
      let result = []
      let tmp = type[i].split(";")
      for (let i = 1; i < tmp.length-1; i++) {
        result.push({
          value: tmp[i],
          label: tmp[i],
        })
      }
      this.options.push({
        value: tmp[0],
        label: tmp[0],
        children:result
      })
    }
  }
}
</script>

<style scoped>
.directorInput{
  width: 200px;
  margin-top: 10px;
  margin-left: 20px;
  margin-right: 30px;
}

.typeInput{
  margin-top: 10px;
  margin-right: 30px;
}
</style>