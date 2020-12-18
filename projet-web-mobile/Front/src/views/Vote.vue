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
                                            <p><span class="font-weight-800">Ville :</span> {{value.ville}}</p>
                                        </div>
                                        <div class="row">
                                            <p><span class="font-weight-800">Participants :</span> {{value.participants}}</p>
                                        </div>
                                        <div class="row">
                                            <p><span class="font-weight-800">Description :</span> {{value.description}}</p>
                                        </div>
                                        <h4>Choix du lieu :</h4>
                                        <div class="row" >
                                            <div class="col-md-12 pb-1" v-for="date in value.date.split(', ')" v-bind:key="date">
                                                <button v-on:click="postVote('Date',date,value.id)" class="btn btn-primary" style="width:100%" name="Lieu">
                                                    {{date}}
                                                </button>
                                            </div>
                                        </div>
                                        <h4>Choix des dates :</h4>
                                        <div class="row">
                                            <div class="col-md-12 pb-1" v-for="lieu in value.lieu.split(', ')" v-bind:key="lieu">
                                                <button v-on:click="postVote('Lieu',lieu ,value)" class="btn btn-primary" style="width:100%" name="Date">
                                                    {{lieu}}
                                                </button>
                                            </div>
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
                dates:'',
                lieux:'',
                sondages: '',
                sondage:'',
                model: {
                    vote_nom: '',
                    vote_date: '',
                    vote_lieu: '',
                    vote_ville: '',
                    vote_description: '',
                    vote_participants: ''
                }
            }
        },
        created () {
            this.getSondage();
        },
        methods: {
            getSondage(){
                fetch('http://127.0.0.1:8085/sondage/getyoursondages', {
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

                            }
                        })
                    })
            },
            postVote(type,choix,sondage) {
                fetch('http://127.0.0.1:8085/vote/create', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "Bearer "+this.$cookies.get('token')
                    },
                    body: JSON.stringify({type: type, choix: choix, reference: sondage})
                })
                    .then(response => {
                        response.json().then(data => {
                            console.log(sondage);

                            if(response.status === 200){
                                if(data ===true){
                                    alert('Le vote a bien été enregistré');
                                    this.$forceUpdate();
                                } else{
                                    alert("Vous avez déja effectué un vote pour ce sondage");
                                }
                            }
                            else{
                                alert("Une erreur s'est produite, veuillez réessayer ulterieurement ...")
                            }
                        })
                    });
            },
        }
    };




</script>
<style></style>
