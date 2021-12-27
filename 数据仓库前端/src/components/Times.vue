<template>
  <div>
    <div align="left" class="timeInput">
      <el-select class="timeChoice" v-model="year" clearable placeholder="请选择年份" style="width: 120px">
        <el-option
            v-for="item in years"
            :key="item.year"
            :label="item.label"
            :value="item.year">
        </el-option>
      </el-select>
      <el-select class="timeChoice" v-model="month" clearable placeholder="请选择月份" style="width: 120px">
        <el-option
            v-for="item in months"
            :key="item.month"
            :label="item.label"
            :value="item.month">
        </el-option>
      </el-select>
      <el-select class="timeChoice" v-model="day" clearable placeholder="请选择日期" style="width: 120px">
        <el-option
            v-for="item in days()"
            :key="item.day"
            :label="item.label"
            :value="item.day">
        </el-option>
      </el-select>
      <el-select class="timeChoice" v-model="season" clearable placeholder="请选择季度" style="width: 120px">
        <el-option
            v-for="item in seasons"
            :key="item.season"
            :label="item.label"
            :value="item.season">
        </el-option>
      </el-select>
      <el-button @click="searchByDate">查询</el-button>
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
  name: "Time",
  components: {Performance, MovieInfo},
  data(){
    return {
      name:"time",
      year:null,
      years:[],
      month:null,
      months:[{
        month:null,
        label:"不选择"
      }],
      day:null,
      season:null,
      seasons:[{
        season:null,
        label:"不选择"
      }],
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
    days(){
      let totalDays = 31;
      if(this.month === 4 || this.month === 6 || this.month === 9 || this.month === 11){
        totalDays = 30;
      } else if(this.year % 4 === 0 && this.month === 2){
        totalDays = 29;
      } else if(this.year % 4 !== 0 && this.month === 2){
        totalDays = 28;
      }
      let result =[{
        day:null,
        label:"不选择"
      }]
      for (let i = 1; i <= totalDays; i++) {
        result.push({
          day:i,
          label:i + " 日"
        })
      }
      return result
    },

    async searchByDate(){
      if(this.year === null || this.year === ""){
        this.$message.error("请选择年份！");
        return ;
      }
      this.isLoad=true
      this.isTimeLoad=true

      //MySQL
      await this.$axios({
        url:"/mysql/movie/getbydate",
        method:"post",
        data:{
          year:this.year,
          month:this.month,
          day:this.day,
          season:this.season
        }
      }).then((res)=>{
        this.movieInfo = res.data.data["movies"]
        this.times.mysql=res.data.data["timeCost"]
      }).catch()

      this.isLoad=false

      //neo4j
      this.$axios({
        url:"/neo4j/movie/getbydate",
        method:"post",
        data:{
          year:this.year,
          month:this.month,
          day:this.day,
          season:this.season
        }
      }).then((res)=>{
        this.times.neo4j=res.data["timeCost"]
      }).catch(()=>{})

      //hive
      if(window.sessionStorage.getItem("hive") === "1"){
        await this.$axios({
          url:"/hive/movie/getbydate",
          method:"post",
          data:{
            year:this.year,
            month:this.month,
            day:this.day,
            season:this.season
          }
        }).then((res)=>{
          console.log(res.data)
          this.times.hive=res.data.data["timeCost"]
        }).catch(()=>{})
      }

      this.isTimeLoad=false
    },
  },
  mounted() {
    for (let i = 1952; i < 2022; i++) {
      this.years.push({
        year:i,
        label:i + " 年"
      })
    }
    for (let i = 1; i < 13; i++) {
      this.months.push({
        month:i,
        label:i + " 月"
      })
    }
    for (let i = 1; i <= 4; i++) {
      this.seasons.push({
        season:i,
        label:(i*3-2) + " ~ " + i*3 +" 月"
      })
    }
  }
}

</script>

<style scoped>
.timeChoice{
  margin-right: 20px;
}

.timeInput{
  margin-top: 10px;
}
</style>