<template>
  <div>
    <p style="margin-left: 10px"><b>查询到{{movieScore.length}}条结果</b></p>
    <el-table class="scoreInfo" :data="movieScore">
      <el-table-column prop="title" label="电影名称"></el-table-column>
      <el-table-column prop="score" label="评分" sortable></el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "MovieScore",
  props: {
    movieScore: Array
  },
}
</script>

<style scoped>
.scoreInfo{
  margin-left: 20%;
  width: 60%;
}
</style>