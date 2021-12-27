<template>
  <div class="home">
    <el-tabs class="panel" type="border-card" v-model="activeName">
      <el-tab-pane label="电影名称查询" name="movie">
        <Movie></Movie>
      </el-tab-pane>
      <el-tab-pane label="上映时间查询" name="time">
        <Times></Times>
      </el-tab-pane>
      <el-tab-pane label="电影类型查询" name="type">
        <Type></Type>
      </el-tab-pane>
      <el-tab-pane label="电影评分查询" name="score">
        <Score></Score>
      </el-tab-pane>
      <el-tab-pane label="导演查询电影" name="direct">
        <Direct></Direct>
      </el-tab-pane>
      <el-tab-pane label="演员查询电影" name="act">
        <Act></Act>
      </el-tab-pane>
      <el-tab-pane label="组合条件查询" name="add">
        <Combine></Combine>
      </el-tab-pane>
      <el-tab-pane label="电影查询导演" name="director">
        <Director></Director>
      </el-tab-pane>
      <el-tab-pane label="电影查询演员" name="actor">
        <Actor></Actor>
      </el-tab-pane>
      <el-tab-pane label="合作关系查询" name="relation">
        <Relation></Relation>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import Movie from "@/components/Movie";
import Direct from "@/components/Direct";
import Act from "@/components/Act";
import Times from "@/components/Times";
import Type from "@/components/Type";
import Score from "@/components/Score";
import Relation from "@/components/Relation";
import Director from "@/components/Director";
import Actor from "@/components/Actor";
import Combine from "@/components/Combine";
export default {
  name: 'Home',
  components: {Combine, Actor, Director, Relation, Score, Type, Act, Direct, Movie,Times},
  data(){
    return{
      activeName:'movie',
    }
  },
  methods:{
  }
}
</script>

<style>
.home{
  height: 100%;
}

.panel{
  margin-left: 10px;
  margin-top: 20px;
  margin-right: 10px;
  height: 100%;
}
</style>
