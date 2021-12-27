<template>
  <div>
    <div>
      <el-input class="movieInput" v-model="inputMovie" placeholder="请输入电影名称"></el-input>
      <el-button @click="searchDirectorByMovie">查询</el-button>
    </div>
    <div v-loading="isTimeLoad">
      <performance :id="name" :times="times"></performance>
    </div>
    <div v-loading="isLoad">
      <p style="margin-left: 10px"><b>查询到{{directorInfo.length}}条结果</b></p>
      <el-table class="directorInfo" :data="directorInfo">
        <el-table-column type="index" label="序号" width="300px"></el-table-column>
        <el-table-column prop="directorName" label="导演名称"></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import Performance from "@/components/Table/Performance";
export default {
  name: "Director",
  components: {Performance},
  data(){
    return {
      name:"director",
      isLoad:false,
      isTimeLoad:false,
      inputMovie:"",
      directorInfo:[],
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

    async searchDirectorByMovie(){
      if(this.strip(this.inputMovie) === ""){
        this.$message.error("输入不能为空！");
        return ;
      }

      this.isLoad=true
      this.isTimeLoad=true
      //MySQL
      await this.$axios({
        url:"/mysql/direct/getbytitle",
        method:"get",
        params:{
          title:this.strip(this.inputMovie)
        }
      }).then((res)=>{
        console.log(res.data)
        if(res.data.data!=null){
          this.directorInfo=res.data.data["directors"]
        }
        this.times.mysql=res.data.data["timeCost"]
      })

      this.isLoad=false

      //neo4j
      this.$axios({
        url:"/neo4j/direct/getbytitle",
        method:"get",
        params:{
          title:this.strip(this.inputMovie)
        }
      }).then((res)=>{
        this.times.neo4j=res.data["timeCost"]
      })

      //hive
      if(window.sessionStorage.getItem("hive") === "1"){
        await this.$axios({
          url:"/hive/direct/getbytitle",
          method:"get",
          params:{
            title:this.strip(this.inputMovie)
          }
        }).then((res)=>{
          this.times.hive=res.data.data["timeCost"]
        })
      }
      this.isTimeLoad=false
    }
  }
}
</script>

<style scoped>
.movieInput{
  width: 200px;
  margin-top: 10px;
  margin-right: 30px;
}

.directorInfo{
  width: 50%;
  margin-left: 25%;
}
</style>