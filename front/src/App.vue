<template>
  <div id="app">
    <h1>Aplikacija za evidenciju poljoprivrednih biljaka</h1>

    <div id="nav">
      <router-link to="/">Prikaz svih biljaka</router-link> |
      <router-link to="/add">Dodavanje</router-link> |
      <router-link to="/about">Pretraga po parametrima</router-link> |
      <router-link to="/range">Pretraga po trajanju vegetacije</router-link>
    </div>
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
