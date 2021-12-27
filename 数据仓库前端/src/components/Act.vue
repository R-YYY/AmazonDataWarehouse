<template>
  <div>
    <div>
      <el-input class="actorInput" v-model="inputActor" placeholder="请输入演员名称"></el-input>
      <el-button @click="searchMovieByActor">查询</el-button>
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
import MovieScore from "@/components/Table/MovieScore";
import Performance from "@/components/Table/Performance";
import MovieInfo from "@/components/Table/MovieInfo";
export default {
  name: "Act",
  components: {MovieInfo, Performance, MovieScore},
  data() {
    return {
      name:"act",
      inputActor:"",
      movieInfo:[],
      isLoad:false,
      isTimeLoad:false,
      times:{
        mysql:0.0,
        neo4j:0.0,
        hive:0.0
      }
    }
  },
  methods:{
    strip(str){
      return str.replace(/(^[ \t\n\r]+)|([ \t\n\r]+$)/g, '').replace(/(^[\s]+)|([\s]+$)/g, '')
    },

    async searchMovieByActor(){
      if(this.strip(this.inputActor) === ""){
        this.$message.error("输入不能为空！");
        return ;
      }
      this.isLoad=true
      this.isTimeLoad=true

      //MySQL
      await this.$axios({
        url:"/mysql/movie/getbyactor",
        method:"get",
        params:{
          name:this.strip(this.inputActor)
        }
      }).then((res)=>{
        this.movieInfo = res.data.data["movies"]
        this.times.mysql=res.data.data["timeCost"]
      }).catch(()=>{
      })

      this.isLoad=false

      //neo4j
      this.$axios({
        url:"/neo4j/movie/getbyactor",
        method:"get",
        params:{
          name:this.strip(this.inputActor)
        }
      }).then((res)=>{
        this.times.neo4j=res.data["timeCost"]
      }).catch(()=>{})

      //hive
      if(window.sessionStorage.getItem("hive") === "1"){
        await this.$axios({
          url:"/hive/movie/getbyactor",
          method:"get",
          params:{
            name:this.strip(this.inputActor)
          }
        }).then((res)=>{
          this.times.hive=res.data.data["timeCost"]
        }).catch(()=>{})
      }

      this.isTimeLoad=false
    }
  }
}
</script>

<style scoped>
.actorInput{
  width: 200px;
  margin-top: 10px;
  margin-right: 30px;
}
</style>