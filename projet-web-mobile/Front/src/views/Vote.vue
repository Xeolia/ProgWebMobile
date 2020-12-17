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
                            <form role="form">
                                <h6 class="heading-small text-muted mb-4">Formulaire vote</h6>
                                <div class="pl-lg-4">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <base-input
                                                    id="vote_nom"
                                                    label="Nom"
                                                    input-classes="form-control-alternative"
                                                    v-model="model.vote_nom">
                                            </base-input>
                                        </div>
                                        <div class="col-lg-6">
                                            <base-input
                                                    id="vote_date"
                                                    label="Date"
                                                    input-classes="form-control-alternative"
                                                    v-model="model.vote_date">
                                            </base-input>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <base-input
                                                    id="vote_ville"
                                                    label="Ville"
                                                    input-classes="form-control-alternative"
                                                    v-model="model.vote_ville">
                                            </base-input>
                                        </div>
                                        <div class="col-lg-6">
                                            <base-input
                                                    id="vote_lieu"
                                                    label="Lieu"
                                                    input-classes="form-control-alternative"
                                                    v-model="model.vote_lieu">
                                            </base-input>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <base-input
                                                    label="Description"
                                                    v-model="model.vote_description">
                                                <textarea id="vote_description" rows="4" class="form-control form-control-alternative" placeholder="Description de votre réunion"></textarea>
                                            </base-input>
                                        </div>
                                    </div>
                                    <div class="text-center">
                                        <base-button type="primary" class="my-4" v-on:click="postSondage()" value="postSondage">Enregistrer</base-button>
                                    </div>
                                </div>
                            </form>

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
                model: {
                    vote_nom: '',
                    vote_date: '',
                    vote_lieu: '',
                    vote_ville: '',
                    vote_description: ''
                }
            }
        },
        methods: {
            postSondage() {
                console.log(this.$cookies.get('token'));
                var name = document.getElementById("vote_nom").value;
                var date = document.getElementById("vote_date").value;
                var lieu = document.getElementById("vote_lieu").value;
                var ville = document.getElementById("vote_ville").value;
                var description = document.getElementById("vote_description").value;
                console.log("name : " + name + "date : " + date + "\n");

                fetch('http://127.0.0.1:8085/vote/create', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "Bearer "+this.$cookies.get('token')
                    },

                    body: JSON.stringify({name: name, date: date, lieu: lieu, ville: ville, description: description})
                })
                    .then(response => {
                        response.json().then(data => {
                            console.log(data);

                            if(response.status !== 200){
                                alert(response.headers["content-type"]);
                                alert("Wallah tes nul");
                            }
                        })
                    })
            }
        }
    };
</script>
<style></style>
