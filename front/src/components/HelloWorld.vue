<template>
  <div class="container">
    <h2>Dodavanje nove biljke</h2>

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
            <option value="puno">Puno</option>
            <option value="srednje">Srednje</option>
            <option value="malo">Malo</option>
          </select>
        </div>

        <div class="form-group col-md-12">
          <label for="napomena">Napomena</label>
          <textarea
            class="form-control"
            id="napomena"
            rows="4"
            v-model.trim="biljka.napomena"
          />
        </div>

      </div>

      <button type="submit" class="btn btn-primary">Dodaj</button>
    </form>
  </div>
</template>

<script>

import axios from 'axios';

export default {
  name: "HelloWorld",
  props: {
    msg: String
  },
  data: function() {
    return {
      biljka: {
        ime: '',
        mesecSadnje: 'JAN',
        mesecBerbe: 'JAN',
        mestoSadnje: 'basta',
        dubinaSadnje: null,
        svetlo: 'senka',
        voda: 'puno',
        napomena: ''
      }
    };
  },
  methods: {
    onSubmit: function(e) {
      e.preventDefault();

      console.log(this.$data.biljka);

      axios.post('http://localhost:8080/biljke/add', this.$data.biljka)
      .then(response => {
        this.$toast.success({
          title:'Dodavanje biljke uspelo',
          message:'Biljka je uspesno dodata.'
        })
      })
      .catch(e => {
        this.$toast.error({
          title:'Dodavanje biljke nije uspelo',
          message:'Proverite ispravnost unetih podataka.'
        })
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
</style>
