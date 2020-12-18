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
                                                <div class="col-lg-12">
                                                    <base-input
                                                                id="sondage_nom"
                                                                label="Nom"
                                                                input-classes="form-control-alternative"
                                                                v-model="model.sondage_nom">
                                                    </base-input>
                                                </div>
                                            </div>
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <base-input
                                                            id="sondage_ville"
                                                            label="Ville"
                                                            input-classes="form-control-alternative"
                                                            v-model="model.sondage_ville">
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
                                        <div>
                                            <h4>Proposition de lieux</h4>
                                            <form>
                                                <button @click="addLieu">Ajout d'un lieu</button>
                                                <br>
                                                <div class="previous"
                                                     v-for="(lieu, counter_lieu) in lieux"
                                                     v-bind:key="counter_lieu">
                                                    <span @click="deleteLieu(counter_lieu)">x</span>
                                                    <label for="lieu">Lieu :</label>
                                                    <label>
                                                        <input type="text" v-model="lieu.lieu" required>
                                                    </label>
                                                </div>
                                            </form>
                                        </div>
                                        <div>
                                            <h4>Proposition de date</h4>
                                            <form>
                                                <button @click="addDate">Ajout d'une date</button>
                                                <br>
                                                <div class="previous"
                                                     v-for="(date, counter_date) in dates"
                                                     v-bind:key="counter_date">
                                                    <span @click="deleteDate(counter_date)">x</span>
                                                    <label for="date">Date :</label>
                                                    <label>
                                                        <input type="text" v-model="date.date" required>
                                                    </label>
                                                </div>
                                            </form>
                                        </div>
                                        

                                        <div>
                                            <h4>Liste des participants</h4>
                                            <form>
                                                <button @click="addUsername">Ajout d'un participant</button>
                                                <br>
                                                <div class="previous"
                                                     v-for="(applicant, counter) in applicants"
                                                     v-bind:key="counter">
                                                    <span @click="deleteUsername(counter)">x</span>
                                                    <label for="username">Username :</label>
                                                    <div class="form-group">
                                                        <select v-model="applicant.username" class="form-control" required>
                                                            <option v-for="(username) in usernames" v-bind:key="username">{{username}}</option>
                                                        </select>
                                                    </div>
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
          msg: String,
          msg2: String,
          msg3: String
      },
      data() {
          return {
              applicants:[
                  {
                      username: '',
                  }
              ],
              lieux:[
                  {
                      lieu: '',
                  }
              ],
              dates:[
                  {
                      lieu: '',
                  }
              ],
              model: {
                  usernames: '',
                  sondage_nom: '',
                  sondage_date: '',
                  sondage_lieu: '',
                  sondage_ville: '',
                  sondage_description: ''
              }
          }
      },
      created() {
          this.usernames = this.loadUsernames();
      },
      methods: {
          loadUsernames(){
              fetch('http://127.0.0.1:8085/user/loadusernames', {
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
                              this.usernames = data;
                              return data;

                          }
                      })
                  })
          },
          addUsername(){
              this.applicants.push({
                  username:'',
              });

          },
          deleteUsername(counter){
              this.applicants.splice(counter,1);
          },
          addDate(){
              this.dates.push({
                  date:'',
              });

          },
          deleteDate(counter_date){
              this.dates.splice(counter_date,1);
          },
          addLieu(){
              this.lieux.push({
                  lieu:'',
              });

          },
          deleteLieu(counter_lieu){
              this.lieux.splice(counter_lieu,1);
          },
          postSondage() {
              console.log(this.$cookies.get('token'));
              var participants = "";
              var date = "";
              var lieu = "";
              var index = 0;
              this.applicants.forEach( function (element) {
                  if (index !== 0){
                      participants = participants +", "+element.username
                  } else {
                      participants = element.username
                  }
                index = index+1;
              });
              index = 0;
              this.dates.forEach( function (element) {
                      if (index !== 0){
                          date = date +", "+element.date
                      } else {
                          date = element.date
                      }
                      index = index+1;
                  }
              );
              index = 0;
              this.lieux.forEach( function (element) {
                  if (index !== 0){
                      lieu = lieu +", "+element.lieu
                  } else {
                      lieu = element.lieu
                  }
                  index = index+1;
              });
              var name = document.getElementById("sondage_nom").value;

              var ville = document.getElementById("sondage_ville").value;
              var description = document.getElementById("sondage_description").value;
              console.log("name : " + name + "date : " + date + "\n");

              fetch('http://127.0.0.1:8085/sondage/create', {
                  method: 'POST',
                  headers: {
                      'Content-Type': 'application/json',
                      'Authorization': "Bearer "+this.$cookies.get('token')
                  },

                  body: JSON.stringify({name: name, date: date, lieu: lieu, ville: ville, description: description, participants: participants})
              })
                  .then(response => {
                      response.json().then(data => {
                          console.log(data);
                            if(response.status === 200){
                                alert('Votre réunion à bien été enregistré');
                                this.$router.push({ path: '/vote'});
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
