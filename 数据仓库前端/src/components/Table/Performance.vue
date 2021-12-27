<template>
  <div>
    <p style="margin-left: 10px"><b>查询效率显示</b></p>
    <el-card class="performance">
      <el-container>
        <div>
          <el-table class="timesInfo" :data="new Array(times)">
            <el-table-column prop="mysql" label="MySQL"></el-table-column>
            <el-table-column prop="neo4j" label="Neo4j"></el-table-column>
            <el-table-column prop="hive" label="Hive"></el-table-column>
          </el-table>
        </div>
        <div class="main" :id="id">
        </div>
      </el-container>
    </el-card>
  </div>
</template>

<script>
import * as echarts from 'echarts';
export default {
  name: "Performance",
  props:{
    id:String,
    times:Object
  },
  methods:{
    setChart(){
      let chartDom = document.getElementById(this.id);
      let myChart = echarts.init(chartDom);
      let option;
      option = {
        xAxis: {
          type: 'category',
          data: ["MySQL","Neo4j","Hive"]
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [this.times.mysql,this.times.neo4j,this.times.hive],
            type: 'bar'
          }
        ]
      };
      option && myChart.setOption(option);
    }
  },
  mounted() {
    this.setChart()
  },
  watch:{
    times:{
      handler(){
        this.setChart()
      },
      deep: true,
    }
  }
}
</script>

<style scoped>
.performance{
  width: 60%;
  margin-left: 20%;
  margin-bottom: 20px;
}

.timesInfo{
  margin-top: 40px;
  margin-left: 80px;
  width: 300px;
}

.main{
  margin-left: 60px;
  width: 300px;
  height: 200px;
}
</style>