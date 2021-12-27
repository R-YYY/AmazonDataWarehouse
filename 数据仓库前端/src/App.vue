<template>
  <div id="app">
    <router-view></router-view>
    <div id="main"></div>
  </div>
</template>

<script>

export default {
  name: 'Home',
  mounted() {
    window.sessionStorage.setItem("hive", "1");
  }
}
</script>

<style>
html,body,title{
  background-color: #d3d3d3;

}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  height: 100%;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
