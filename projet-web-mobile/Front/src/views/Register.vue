<template>
    <div class="row justify-content-center">
        <div class="col-lg-5 col-md-7">
            <div class="card bg-secondary shadow border-0">
                <div class="card-header bg-transparent pb-5">
                    <div class="text-muted text-center mt-2 mb-3"><small>Have an account ?</small></div>
                    <div class="btn-wrapper text-center">
                        <a href="#" class="btn btn-neutral btn-icon">
                            <span class="btn-inner--text"><router-link to="/login">Login</router-link></span>
                        </a>
                    </div>
                </div>
                <div class="card-body px-lg-5 py-lg-5">
                    <div class="text-center text-muted mb-4">
                        <small>Sign up</small>
                    </div>
                    <form role="form">

                        <base-input class="input-group-alternative mb-3"
                                    placeholder="Username"
                                    id="username_register"
                                    addon-left-icon="ni ni-hat-83"
                                    v-model="model.username">
                        </base-input>

                        <base-input class="input-group-alternative mb-3"
                                    placeholder="Name"
                                    id="name_register"
                                    addon-left-icon="ni ni-hat-3"
                                    v-model="model.name">
                        </base-input>

                        <base-input class="input-group-alternative mb-3"
                                    placeholder="Email"
                                    id="email_register"
                                    addon-left-icon="ni ni-email-83"
                                    v-model="model.email">
                        </base-input>

                        <base-input class="input-group-alternative"
                                    placeholder="Password"
                                    id="password_register"
                                    type="password"
                                    addon-left-icon="ni ni-lock-circle-open"
                                    v-model="model.password">
                        </base-input>

                        <div class="text-muted font-italic">
                            <small>Password strength: <span class="text-success font-weight-700">strong</span></small>
                        </div>

                        <div class="row my-4">
                            <div class="col-12">
                                <base-checkbox class="custom-control-alternative">
                                    <span class="text-muted">I agree with the <a href="#!">Privacy Policy</a></span>
                                </base-checkbox>
                            </div>
                        </div>
                        <div class="text-center">
                            <base-button type="primary" class="my-4" v-on:click="postSign()" value="postSign">Create account</base-button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>
<script>

  export default {
    name: 'register',
    data() {
      return {
        model: {
          name: '',
          email: '',
          password: ''
        }
      }
    },
      methods: {
          postSign() {
              var username = document.getElementById("username_register").value;
              var password = document.getElementById("password_register").value;
              var email = document.getElementById("email_register").value;
              var name = document.getElementById("name_register").value;
              console.log("username : " + username + "\npassword : " + "\nname : " + name + password + "\nname : " + name + '\nemail : ' + email);

              fetch('http://127.0.0.1:8085/user/registration', {
                  method: 'POST',
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  body: JSON.stringify({username: username, name: name, password: password, email: email})
              })
                  .then(response => {
                      response.json().then(data => {
                          console.log(data);

                          if(response.status === 200){
                              alert('Votre compte a bien été enregistré');
                              this.$router.push({ path: '/login'});
                          }
                          else{
                              alert("Erreur dans l'enregistrement");
                          }
                      })
                  });
          }
      }
  }
</script>
<style>
</style>
