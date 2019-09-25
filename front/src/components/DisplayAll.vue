<template>
    <div class="container">
        <h2>Prikaz svih biljaka</h2>

        <table v-if="res != null && res.length > 0" class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Ime</th>
                    <th scope="col">Mesec sadnje</th>
                    <th scope="col">Mesec berbe</th>
                    <th scope="col">Mesto sadnje</th>
                    <th scope="col">Dubina sadnje</th>
                    <th scope="col">Svetlo</th>
                    <th scope="col">Voda</th>
                    <th scope="col">Napomena</th>
                    <th scope="col">Obriši</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(obj,idx) in res" v-bind:key="obj">
                    <td>{{obj.ime}}</td>
                    <td>{{obj.mesecSadnje}}</td>
                    <td>{{obj.mesecBerbe}}</td>
                    <td>{{obj.mestoSadnje}}</td>
                    <td>{{obj.dubinaSadnje}} cm</td>
                    <td>{{obj.svetlo}}</td>
                    <td>{{obj.voda}}</td>
                    <td>{{obj.napomena}}</td>
                    <td><a href="void(0)" v-on:click="ukloniBiljku($event, idx)">x</a></td>
                </tr>
            </tbody>
        </table>

        <p v-else>Nijedna biljka nije dodata.</p>

    </div>
</template>

<script>

    import axios from 'axios';

    export default {
        name: "DisplayAll",
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
                cnt: 0,
                res: null
            }
        },
        methods: {
            beautifyMesto: function (mesto) {
                switch(mesto) {
                    case 'basta': mesto = 'Bašta'; break;
                    case 'rasad': mesto = 'Rasad'; break;
                    default: break;
                }
                return mesto;
            },
            beautifyMesec: function (mesec) {
                switch(mesec) {
                    case 'JAN': mesec = "Januar"; break;
                    case 'FEB': mesec = "Februar"; break;
                    case 'MAR': mesec = "Mart"; break;
                    case 'APR': mesec = "April"; break;
                    case 'MAJ': mesec = "Maj"; break;
                    case 'JUN': mesec = "Jun"; break;
                    case 'JUL': mesec = "Jul"; break;
                    case 'AVG': mesec = "Avgust"; break;
                    case 'SEP': mesec = "Septembar"; break;
                    case 'OKT': mesec = "Oktobar"; break;
                    case 'NOV': mesec = "Novembar"; break;
                    case 'DEC': mesec = "Decembar"; break;
                    default: break;
                }
                return mesec;
            },
            ucitajBiljke: function() {
                axios.post('http://localhost:8080/biljke/search', this.$data.biljka)
                    .then(response => {

                        let context = this;

                        response.data.forEach(function(item, idx) {
                            idx;
                            item.ime = item.ime.replace(/_/g, " ")
                            if (item.napomena != null && item.napomena != "") {
                                item.napomena = item.napomena.replace(/_/g, " ")
                            }
                            item.mesecSadnje = context.beautifyMesec(item.mesecSadnje)
                            item.mesecBerbe = context.beautifyMesec(item.mesecBerbe)
                            item.mestoSadnje = context.beautifyMesto(item.mestoSadnje)
                            item.svetlo = item.svetlo.charAt(0).toUpperCase() + item.svetlo.slice(1)
                            item.voda = item.voda.charAt(0).toUpperCase() + item.voda.slice(1)
                        })

                        this.$data.res = response.data;
                    })
                    .catch(e => {
                        e;
                    });
            },
            ukloniBiljku: function(e, idx) {
                e.preventDefault();

                axios.get('http://localhost:8080/biljke/delete/' + this.$data.res[idx].id)
                    .then(response => {
                        this.$data.res = null

                        response;

                        this.ucitajBiljke();
                    })
            }
        },
        beforeMount(){
            this.ucitajBiljke();
        }
    }
</script>

<style scoped>

</style>