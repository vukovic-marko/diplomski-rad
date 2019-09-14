<template>
  <div class="container">
    <h2>Pretraga biljaka po rasponu</h2>

    <form class="form-inline justify-content-center" v-on:submit="onSubmit">
      <input
        type="number"
        min="1"
        max="12"
        step="1"
        class="form-control"
        placeholder="Trajanje vegetacije"
        id="range"
        v-model.number="range"
      />
      &nbsp;
      <button type="submit" class="btn btn-primary">Pretrazi</button>
    </form>

    <br v-if="res">

    <div class="row">
      <div class="col-sm-6" v-for="obj in res" v-bind:key="obj"> 
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">{{obj.ime}}</h5>
            <p class="card-text">Mesec sadnje: {{obj.mesecSadnje}}</p>
            <p class="card-text">Mesec berbe: {{obj.mesecBerbe}}</p>
            <p class="card-text">Mesto sadnje: {{obj.mestoSadnje}}</p>
            <p class="card-text">Dubina sadnje: {{obj.dubinaSadnje}} cm</p>
            <p class="card-text">Svetlo: {{obj.svetlo}}</p>
            <p class="card-text">Voda: {{obj.voda}}</p>        
          </div>
          <div class="card-footer">
            <small class="text-muted" v-if="obj.napomena != null">{{obj.napomena}}</small>
            <small class="text-muted" v-else>-</small>
          </div>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>

import axios from 'axios';

export default {
  name: "SearchRange",
  props: {
    msg: String
  },
  data: function() {
    return {
      range: '',
      res: null
    };
  },
  methods: {
    onSubmit: function(e) {
      e.preventDefault();

      console.log(this.$data.biljka);

      axios.get('http://localhost:8080/biljke/search/' + this.$data.range)
      .then(response => {
        console.log(response.data);
        this.$data.res = response.data;
      })
      .catch(e => {
        console.log(e);
      });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.container {
  margin: auto;
  width: 50%;
}
.card-text {
  font-size: 14px;
  font-family: Arial, Helvetica, sans-serif;
  text-align: left;
}
p {
  margin-bottom: 0px;
}
.card {
  margin-bottom: 1em;
}
</style>
