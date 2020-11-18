<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </div>
    <router-view/>
  </div>
</template>

<style>
/*GLOBAL COLOURS*/
:root {
  --primary-color: #2c3e50;
  --secondary-color: #42b983;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: var(--primary-color);
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: var(--primary-color);
}

#nav a.router-link-exact-active {
  color: var(--secondary-color);
}


</style>
