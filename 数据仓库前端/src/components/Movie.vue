<template>
  <div>
    <div>
      <el-input class="movieInput" v-model="inputMovie" placeholder="请输入电影名称"></el-input>
      <el-button @click="searchMovieByName">查询</el-button>
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
  name: "Movie",
  components: {Performance, MovieInfo},
  data() {
    return {
      name:"movie",
      inputMovie:"",
      movieInfo:[],
      isLoad:false,
      isTimeLoad:false,
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

    async searchMovieByName(){
      if(this.strip(this.inputMovie) === ""){
        this.$message.error("输入不能为空！");
        return ;
      }
      this.isLoad=true
      this.isTimeLoad=true

      //MySQL
      await this.$axios({
        url:"/mysql/movie/getbytitle",
        method:"get",
        params:{
          title:this.strip(this.inputMovie)
        }
      }).then((res)=>{
        this.movieInfo = res.data.data["movies"]
        this.times.mysql=res.data.data["timeCost"]
      }).catch(()=>{})

      this.isLoad=false

      //neo4j
      this.$axios({
        url:"/neo4j/movie/getbytitle",
        method:"get",
        params:{
          title:this.strip(this.inputMovie)
        }
      }).then((res)=>{
        console.log(res.data)
        this.times.neo4j=res.data["timeCost"]
      }).catch(()=>{})

      //hive
      if(window.sessionStorage.getItem("hive") === "1"){
        await this.$axios({
          url:"/hive/movie/getbytitle",
          method:"get",
          params:{
            title:this.strip(this.inputMovie)
          }
        }).then((res)=>{
          this.times.hive=res.data.data["timeCost"]
        }).catch(()=>{})
      }

      this.isTimeLoad=false
    },
  },
  mounted() {
  }
}
</script>

<style scoped>
.movieInput{
  width: 200px;
  margin-top: 10px;
  margin-right: 30px;
}
</style>
