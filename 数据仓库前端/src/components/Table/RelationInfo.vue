<template>
  <div>
    <p style="margin-left: 10px"><b>查询到{{relationInfo.length}}条结果</b></p>
    <el-table class="relationInfo" :data="relationInfo">
      <el-table-column prop="directorName" label="导演名称"></el-table-column>
      <el-table-column prop="actorName" label="演员名称"></el-table-column>
      <el-table-column prop="times" label="合作次数" sortable></el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "RelationInfo",
  props:{
    relationInfo:Array,
  }
}
</script>

<style scoped>
.relationInfo{
  margin-left: 20%;
  margin-right: 20%;
  width: 60%;
}
</style>