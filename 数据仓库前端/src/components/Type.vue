<template>
  <div>
    <div>
      <el-cascader
          class="typeInput"
          v-model="type"
          :options="options"
          :props="{ expandTrigger: 'hover' }"
          style="width: 600px">
      </el-cascader>
      <el-button @click="searchByType">查询</el-button>
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
import MovieInfo from "@/components/Table/MovieInfo";
import Performance from "@/components/Table/Performance";
export default {
  name: "Type",
  components: {Performance, MovieInfo},
  data(){
    return {
      name:"type",
      type:null,
      isLoad:false,
      isTimeLoad:false,
      options:[],
      movieInfo:[],
      times:{
        mysql:0.0,
        neo4j:0.0,
        hive:0.0
      }
    }
  },
  methods:{
    async searchByType(){
      if(this.type == null){
        this.$message.error("请选择分类！");
        return ;
      }
      this.isLoad=true
      this.isTimeLoad=true
      let data = this.type[0] + ":" + this.type[1]

      //MySQL
      await this.$axios({
        url:"/mysql/movie/getbytype",
        method:"get",
        params:{
          type:data
        }
      }).then((res)=>{
        this.movieInfo = res.data.data["movies"]
        this.times.mysql=res.data.data["timeCost"]
      }).catch(()=>{})

      this.isLoad=false

      //neo4j
      this.$axios({
        url:"/neo4j/movie/getbytype",
        method:"get",
        params:{
          type:data
        }
      }).then((res)=>{
        this.times.neo4j=res.data["timeCost"]
      }).catch(()=>{})

      //hive
      if(window.sessionStorage.getItem("hive") === "1"){
        await this.$axios({
          url:"/hive/movie/getbytype",
          method:"get",
          params:{
            type:data
          }
        }).then((res)=>{
          this.times.hive=res.data.data["timeCost"]
        }).catch(()=>{})
      }

      this.isTimeLoad=false
    }
  },
  mounted() {
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
.typeInput{
  margin-top: 10px;
  margin-right: 30px;
}
</style>