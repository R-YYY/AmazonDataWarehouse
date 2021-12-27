<template>
  <div>
    <div>
      <el-input class="timesInput" v-model="inputDirector" placeholder="请输入导演名" clearable :disabled="inputActor!==''"></el-input>
      <el-input class="timesInput" v-model="inputActor" placeholder="请输入演员名" clearable :disabled="inputDirector!==''"></el-input>
      <el-input class="timesInput" v-model="inputTimes" placeholder="请输入最小合作次数"></el-input>
      <el-button @click="searchMovieByRelation">查询</el-button>
    </div>
    <div v-loading="isTimeLoad">
      <performance :id="name" :times="times"></performance>
    </div>
    <div v-loading="isLoad">
      <RelationInfo :relationInfo="relationInfo"></RelationInfo>
    </div>
  </div>
</template>

<script>
import Performance from "@/components/Table/Performance";
import RelationInfo from "@/components/Table/RelationInfo";
export default {
  name: "Relation",
  components: {RelationInfo, Performance},
  data(){
    return {
      name:"relation",
      inputDirector:"",
      inputActor:"",
      inputTimes:"",
      isLoad:false,
      isTimeLoad:false,
      relationInfo:[],
      times:{
        mysql:0.0,
        neo4j:0.0,
        hive:"无"
      }
    }
  },
  methods:{
    strip(str){
      return str.replace(/(^[ \t\n\r]+)|([ \t\n\r]+$)/g, '').replace(/(^[\s]+)|([\s]+$)/g, '')
    },

    async searchMovieByRelation(){
      if(this.strip(this.inputTimes) === "" && this.strip(this.inputDirector) === "" && this.strip(this.inputActor) === ""){
        this.$message.error("输入不能为空！");
        return ;
      }
      this.isLoad=true
      this.isTimeLoad=true

      //MySQL
      await this.$axios({
        url:"/mysql/movie/getbyrelation",
        method:"post",
        data:{
          directorName:this.strip(this.inputDirector),
          actorName:this.strip(this.inputActor),
          times:this.strip(this.inputTimes),
        }
      }).then((res)=>{
        console.log(res.data)
        this.relationInfo=res.data.data["relations"]
        this.times.mysql=res.data.data["timeCost"]
      }).catch(()=>{
      })

      this.isLoad=false

      //neo4j
      this.$axios({
        url:"/neo4j/act/getbydirector",
        method:"get",
        params:{
          director:this.strip(this.inputDirector),
        }
      }).then((res)=>{
        this.times.neo4j=res.data["timeCost"]
      }).catch(()=>{})

      this.isTimeLoad=false
    }
  }
}
</script>

<style scoped>
.timesInput{
  width: 170px;
  margin-top: 10px;
  margin-right: 30px;
}
</style>