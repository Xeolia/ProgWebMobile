<template>
    <div>
        <base-header type="gradient-theme" class="pb-6 pt-5 pt-md-8">
        </base-header>

        <div class="container-fluid mt--7">
            <div class="row">
                <div class="col">
                    <div class="card shadow">
                        <div class="card-header bg-transparent">
                            <h3 class="mb-0">Proposer un vote</h3>
                        </div>

                        <div class="card-body">
                            <div class="row icon-examples">
                                <div class="col-lg-3 col-md-6"
                                     v-for="value in sondages" v-bind:key="value.id">
                                    <button type="button"
                                            :title="value.name"
                                            class="btn-icon-clipboard" data-clipboard-text="air-baloon" v-bind:id="value.id">
                                        <div class="row">
                                            <p><span class="font-weight-800">Nom :</span> {{value.name}}</p>
                                        </div>
                                        <div class="row">
                                            <p><span class="font-weight-800">Date :</span> {{value.date}}</p>
                                        </div>
                                        <div class="row">
                                            <p><span class="font-weight-800">Lieu :</span> {{value.lieu}}</p>
                                        </div>
                                        <div class="row">
                                            <p><span class="font-weight-800">Ville :</span> {{value.ville}}</p>
                                        </div>
                                        <div class="row">
                                            <p><span class="font-weight-800">Description :</span> {{value.description}}</p>
                                        </div>
                                    </button>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </div>

    </div>

</template>
<script>
    import Vue from 'vue'
    import VueClipboard from 'vue-clipboard2'
    import VueCookies from 'vue-cookies'
    Vue.use(VueCookies);
    Vue.use(VueClipboard);

    export default {
        name:'vote',

        data() {
            return {
                sondages: '',
                model: {
                    vote_nom: '',
                    vote_date: '',
                    vote_lieu: '',
                    vote_ville: '',
                    vote_description: ''
                }
            }
        },
        created () {
            // fetch the data when the view is created and the data is
            // already being observed
            this.getSondage()
        },
        methods: {
            getSondage(){
                fetch('http://127.0.0.1:8085/sondage/get', {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "Bearer "+this.$cookies.get('token')
                    },

                })
                    .then(response => {
                        response.json().then(data => {
                            if(response.status===200){
                                console.log(data);
                                this.sondages = data;

                                alert("Nous affichons la liste des sondages");
                            }
                        })
                    })
            },
        }
    };




</script>
<style></style>
