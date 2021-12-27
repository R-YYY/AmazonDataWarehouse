<template>
  <div>
    <div>
      <el-input class="directorInput" v-model="inputDirector" placeholder="请输入导演名称"></el-input>
      <el-button @click="searchMovieByDirector">查询</el-button>
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
  name: "Direct",
  components: {MovieInfo, Performance, MovieScore},
  data() {
    return {
      name:"direct",
      inputDirector:"",
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

    async searchMovieByDirector(){
      if(this.strip(this.inputDirector) === ""){
        this.$message.error("输入不能为空！");
        return ;
      }
      this.isLoad=true
      this.isTimeLoad=true

      //MySQL
      await this.$axios({
        url:"/mysql/movie/getbydirector",
        method:"get",
        params:{
          name:this.strip(this.inputDirector)
        }
      }).then((res)=>{
        this.movieInfo = res.data.data["movies"]
        this.times.mysql=res.data.data["timeCost"]
      }).catch(()=>{
      })

      this.isLoad=false

      //neo4j
      this.$axios({
        url:"/neo4j/movie/getbydirector",
        method:"get",
        params:{
          name:this.strip(this.inputDirector)
        }
      }).then((res)=>{
        this.times.neo4j=res.data["timeCost"]
      }).catch(()=>{})

      //hive
      if(window.sessionStorage.getItem("hive") === "1"){
        await this.$axios({
          url:"/hive/movie/getbydirector",
          method:"get",
          params:{
            name:this.strip(this.inputDirector)
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
.directorInput{
  width: 200px;
  margin-top: 10px;
  margin-right: 30px;
}
</style>