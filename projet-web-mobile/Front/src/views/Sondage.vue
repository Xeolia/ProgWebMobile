<template>
    <div>
        <base-header type="gradient-theme" class="pb-6 pt-5 pt-md-8">
        </base-header>

        <div class="container-fluid mt--7">
            <div class="row">
                <div class="col">
                    <div class="card shadow">
                        <div class="card-header bg-transparent">
                            <h3 class="mb-0">Proposer un sondage</h3>
                        </div>
                        <div class="card-body">
                                <form role="form">
                                    <h6 class="heading-small text-muted mb-4">Formulaire sondage</h6>
                                    <div class="pl-lg-4">
                                        <div class="row">
                                                <div class="col-lg-6">
                                                    <base-input
                                                                id="sondage_nom"
                                                                label="Nom"
                                                                input-classes="form-control-alternative"
                                                                v-model="model.sondage_nom">
                                                    </base-input>
                                                </div>
                                                <div class="col-lg-6">
                                                    <base-input
                                                                id="sondage_date"
                                                                label="Date"
                                                                input-classes="form-control-alternative"
                                                                v-model="model.sondage_date">
                                                    </base-input>
                                                </div>
                                            </div>
                                        <div class="row">
                                            <div class="col-lg-6">
                                                <base-input
                                                            id="sondage_ville"
                                                            label="Ville"
                                                            input-classes="form-control-alternative"
                                                            v-model="model.sondage_ville">
                                                </base-input>
                                            </div>
                                            <div class="col-lg-6">
                                                <base-input
                                                            id="sondage_lieu"
                                                            label="Lieu"
                                                            input-classes="form-control-alternative"
                                                            v-model="model.sondage_lieu">
                                                </base-input>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <base-input
                                                            label="Description"
                                                            v-model="model.sondage_description">
                                                    <textarea id="sondage_description" rows="4" class="form-control form-control-alternative" placeholder="Description de votre réunion"></textarea>
                                                </base-input>
                                            </div>
                                        </div>

                                            <div id="visa">
                                                <h2>Liste des participants</h2>
                                                <form>
                                                    <br><br>
                                                    <button class="my-4" @click="addVisa">Ajout d'un participant</button>
                                                    <br>
                                                    <div class="previous"
                                                         v-for="(applicant, counter) in applicants"
                                                         v-bind:key="counter">
                                                        <span @click="deleteVisa(counter)">x</span>
                                                        <label for="duration">Username :</label>
                                                        <input type="text" v-model="applicant.previous" required>
                                                    </div>
                                                </form>
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
      name:'sondage',
      props: {
          msg: String
      },
      data() {
          return {
              applicants:[
                  {
                      previous: '',
                      expiration:''
                  }
              ],
              model: {
                  sondage_nom: '',
                  sondage_date: '',
                  sondage_lieu: '',
                  sondage_ville: '',
                  sondage_description: ''
              }
          }
      },
      methods: {
          addVisa(){
              this.applicants.push({
                  previous:'',
                  expiration: ''
              })
          },
          deleteVisa(counter){
              this.applicants.splice(counter,1);
          },
          postSondage() {
              console.log(this.$cookies.get('token'));
              var name = document.getElementById("sondage_nom").value;
              var date = document.getElementById("sondage_date").value;
              var lieu = document.getElementById("sondage_lieu").value;
              var ville = document.getElementById("sondage_ville").value;
              var description = document.getElementById("sondage_description").value;
              console.log("name : " + name + "date : " + date + "\n");

              fetch('http://127.0.0.1:8085/sondage/create', {
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
                            if(response.status === 200){
                                alert('Votre réunion à bien été enregistré');
                          } else{
                                alert('Une erreur s\'est produite. Veuillez réesayer ulterieurement');
                            }
                      })
                  })
          }
      }

  };
</script>
<style scoped>
    #visa {
        margin: 20px auto;
        max-width: 700px;
    }
    label{
        display: block;
        margin: 20px 0 10px;
    }
    input {
        font-size: 30px;
        border: 1px double rgb(102, 97, 96) ;
        border-radius: 4px;
    }
    button {
        font-size: 16px;
        background: rgb(64, 179, 140);
        padding: 0.4rem 1.3rem;
        text-align: center;
        color: white;
        border: none;
        cursor: pointer;
        border-radius: 4px;
        margin: 10px;
    }
    span{
        width: 30px;
        float: right;
        cursor: pointer;
    }
    span:hover{
        color: brown;
    }
    .previous{
        border: 1.5px solid;
        border-radius: 5px;
        padding:5px;
        margin-bottom: 10px;
    }
</style>
