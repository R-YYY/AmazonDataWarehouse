<template>
  <div>
<!--    <div>-->
<!--      <el-container>-->
<!--        <span>请选择评分：</span>-->
<!--        <el-slider-->
<!--            class="scoreInput"-->
<!--            v-model="score"-->
<!--            range-->
<!--            :format-tooltip="formatTooltip"-->
<!--            :max="50">-->
<!--        </el-slider>-->
<!--        <el-button @click="searchByScore">查询</el-button>-->
<!--      </el-container>-->
<!--    </div>-->
    <div>
      <el-input class="scoreInput" v-model="inputScore" placeholder="请输入最低评分"></el-input>
      <el-button @click="searchByScore">查询</el-button>
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
  name: "Score",
  components: {MovieInfo, Performance},
  data(){
    return {
      name:"score",
      isLoad:false,
      isTimeLoad:false,
      movieInfo:[],
      inputScore:"",
      times:{
        mysql:0.0,
        neo4j:0.0,
        hive:0.0
      }
    }
  },
  methods:{
    async searchByScore(){
      this.isLoad=true
      this.isTimeLoad=true

      //MySQL
      await this.$axios({
        url:"/mysql/movie/getbyscore",
        method:"get",
        params:{
          score:this.inputScore
        }
      }).then((res)=>{
        this.movieInfo = res.data.data["movies"]
        this.times.mysql=res.data.data["timeCost"]
      }).catch(()=>{})

      this.isLoad=false

      //neo4j
      this.$axios({
        url:"/neo4j/movie/getbyscore",
        method:"get",
        params:{
          score:this.inputScore
        }
      }).then((res)=>{
        this.times.neo4j=res.data["timeCost"]
      }).catch(()=>{})

      //hive
      if(window.sessionStorage.getItem("hive") === "1"){
        await this.$axios({
          url:"/hive/movie/getbyscore",
          method:"get",
          params:{
            score:this.inputScore
          }
        }).then((res)=>{
          this.times.hive=res.data.data["timeCost"]
        }).catch(()=>{})
      }

      this.isTimeLoad=false
    },
  }
}
</script>

<style scoped>
.scoreInput{
  width: 200px;
  margin-top: 10px;
  margin-right: 30px;
}
</style>