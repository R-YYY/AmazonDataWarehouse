<template>
  <div>
    <p style="margin-left: 10px"><b>查询到{{movieInfo.length}}条结果</b></p>
    <el-table :data="movieInfo" :row-style="{ height: '30px' }" :cell-style="{ padding: '0' }">
      <el-table-column label="电影名称" prop="title" sortable></el-table-column>
      <el-table-column label="上映日期" width="110px" sortable>
        <template slot-scope="scope">{{scope.row.year + '/' + scope.row.month + '/' +scope.row.day}}
        </template>
      </el-table-column>
      <el-table-column label="电影类型" prop="type" width="150px" sortable>
        <template slot-scope="scope">{{scope.row.type.split(":")[scope.row.type.split(":").length-1]}}
        </template>
      </el-table-column>
      <el-table-column label="电影格式" prop="format" width="110px" sortable></el-table-column>
      <el-table-column label="电影评分" prop="score" width="110px" sortable></el-table-column>
      <el-table-column label="评论数量" prop="reviewNum" width="110px" sortable></el-table-column>
      <el-table-column label="导演数量" prop="directorNum" width="110px" sortable></el-table-column>
      <el-table-column label="演员数量" prop="actorNum" width="110px" sortable></el-table-column>
      <el-table-column label="信息来源" width="300px" sortable>
        <template slot-scope="scope">
          <el-link :href="go(scope.row.movieId)" target="_blank" type="primary">
            {{"https://www.amazon.com/dp/" + scope.row.movieId}}
          </el-link>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "MovieInfo",
  props: {
    movieInfo: Array
  },
  methods:{
    go(data){
      return "https://www.amazon.com/dp/" + data
    }
  }
}
</script>

<style scoped>

</style>