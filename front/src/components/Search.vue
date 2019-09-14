<template>
  <div class="container">
    <h2>Pretraga biljaka</h2>

    <form v-on:submit="onSubmit">
      <div class="form-row">
        <div class="form-group col-md-12">
          <label for="ime">Ime biljke</label>
          <input
            type="text"
            class="form-control"
            id="ime"
            v-model.trim="biljka.ime"
          />
        </div>
      
        <div class="form-group col-md-6">
          <label for="sadnja">Mesec sadnje</label>
          <select class="form-control form-control-sadnja" 
          id="sadnja"
          v-model="biljka.mesecSadnje"
          >
            <option value="">---</option>
            <option value="JAN">Januar</option>
            <option value="FEB">Februar</option>
            <option value="MAR">Mart</option>
            <option value="APR">April</option>
            <option value="MAJ">Maj</option>
            <option value="JUN">Jun</option>
            <option value="JUL">Jul</option>
            <option value="AVG">Avgust</option>
            <option value="SEP">Septembar</option>
            <option value="OKT">Oktobar</option>
            <option value="NOV">Novembar</option>
            <option value="DEC">Decembar</option>
          </select>
        </div>

        <div class="form-group col-md-6">
          <label for="berba">Mesec berbe</label>
          <select class="form-control" 
          id="berba"
          v-model="biljka.mesecBerbe"
          >
            <option value="">---</option>
            <option value="JAN">Januar</option>
            <option value="FEB">Februar</option>
            <option value="MAR">Mart</option>
            <option value="APR">April</option>
            <option value="MAJ">Maj</option>
            <option value="JUN">Jun</option>
            <option value="JUL">Jul</option>
            <option value="AVG">Avgust</option>
            <option value="SEP">Septembar</option>
            <option value="OKT">Oktobar</option>
            <option value="NOV">Novembar</option>
            <option value="DEC">Decembar</option>
          </select>
        </div>

        <div class="form-group col-md-6">
          <label for="mesto">Mesto sadnje</label>
          <select class="form-control" 
          id="mesto"
          v-model="biljka.mestoSadnje"
          >
            <option value="">---</option>
            <option value="basta">Bašta</option>
            <option value="rasad">Rasad</option>
          </select>
        </div>

        <div class="form-group col-md-6">
          <label for="dubina">Dubina sadnje [cm]</label>
          <input
            type="number"
            min="0"
            class="form-control"
            id="dubina"
            v-model.number="biljka.dubinaSadnje"
          />
        </div>

        <div class="form-group col-md-6">
          <label for="svetlo">Svetlo</label>
          <select class="form-control" 
          id="svetlo"
          v-model="biljka.svetlo"
          >
            <option value="">---</option>
            <option value="senka">Senka</option>
            <option value="polusenka">Polusenka</option>
            <option value="sunce">Sunce</option>
          </select>
        </div>

        <div class="form-group col-md-6">
          <label for="voda">Voda</label>
          <select class="form-control" 
          id="voda"
          v-model="biljka.voda"
          >
            <option value="">---</option>
            <option value="puno">Puno</option>
            <option value="srednje">Srednje</option>
            <option value="malo">Malo</option>
          </select>
        </div>

        <!-- <div class="form-group col-md-12">
          <label for="napomena">Napomena</label>
          <textarea
            class="form-control"
            id="napomena"
            rows="4"
            v-model.trim="biljka.napomena"
          />
        </div> -->

      </div>

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
</template>

<script>

import axios from 'axios';

export default {
  name: "Search",
  props: {
    msg: String
  },
  data: function() {
    return {
      biljka: {
        ime: '',
        mesecSadnje: '',
        mesecBerbe: '',
        mestoSadnje: '',
        dubinaSadnje: null,
        svetlo: '',
        voda: '',
        napomena: ''
      },
      res : null
    };
  },
  methods: {
    onSubmit: function(e) {
      e.preventDefault();

      console.log(this.$data.biljka);

      axios.post('http://localhost:8080/biljke/search', this.$data.biljka)
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
