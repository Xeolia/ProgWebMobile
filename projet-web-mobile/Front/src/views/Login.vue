<template>
        <div class="row justify-content-center">
            <div class="col-lg-5 col-md-7">
                <div class="card bg-secondary shadow border-0">
                    <div class="card-header bg-transparent pb-5">
                        <div class="text-muted text-center mt-2 mb-3"><small>Don't have an account ?</small></div>
                        <div class="btn-wrapper text-center">
                            <a href="#" class="btn btn-neutral btn-icon">
                                <span class="btn-inner--text"><router-link to="/register">Create new account</router-link></span>
                            </a>
                        </div>
                    </div>
                    <div class="card-body px-lg-5 py-lg-5">
                        <div class="text-center text-muted mb-4">
                            <small>Sign in</small>
                        </div>
                        <form role="form">
                            <base-input class="input-group-alternative mb-3"
                                        placeholder="Email"
                                        addon-left-icon="ni ni-email-83"
                                        id="username_login"
                                        v-model="model.username">
                            </base-input>

                            <base-input class="input-group-alternative"
                                        placeholder="Password"
                                        type="password"
                                        addon-left-icon="ni ni-lock-circle-open"
                                        id="password_login"
                                        v-model="model.password">

                            </base-input>

                            <base-checkbox class="custom-control-alternative">
                                <span class="text-muted">Remember me</span>
                            </base-checkbox>
                            <div class="text-center">
                                <base-button type="primary" class="my-4" v-on:click="postLog()" value="postLog">Sign in</base-button>
                            </div>
                            <br>
                        </form>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-6 text-left">
                        <a href="#" class="text-light"><small>Forgot password?</small></a>
                    </div>
                </div>
                <div class="mt-5">
                    <div class="justify-content-center">
                        <small class="text-center text-light">Copyright Tanguy Benard & Alexia Serrier</small>
                    </div>
                </div>
            </div>

        </div>
</template>
<script>
  export default {
    name: 'login',
    data() {
      return {
        model: {
          email: '',
          password: ''
        }
      }
    },
      methods: {
          postLog() {
              var username = document.getElementById("username_login").value;
              var password = document.getElementById("password_login").value;
              console.log("username : " + username + "\npassword : " + "\n");

              fetch('http://127.0.0.1:8085/user/authentification', {
                  method: 'POST',
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  body: JSON.stringify({username: username, password: password})
              })
                  .then(response => {
                      response.json().then(data => {
                          console.log(data);
                          if(response.status === 200){
                              this.$router.push({ path: '/icons'});
                          }else{
                              alert("Bad password");
                          }
                      })
                  })
          }
      }
  }
</script>
<style>
</style>
