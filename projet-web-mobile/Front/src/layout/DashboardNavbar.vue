<template>
    <base-nav class="navbar-top navbar-dark"
              id="navbar-main"
              :show-toggle-button="false"
              expand>
        <form class="navbar-search navbar-search-dark form-inline mr-3 d-none d-md-flex ml-lg-auto">
            <div class="form-group mb-0">
            </div>
        </form>
        <ul class="navbar-nav align-items-center d-none d-md-flex">
            <li class="nav-item dropdown">
                    <div class="media align-items-center" slot="title">
                        <div class="media-body ml-2 d-none d-lg-block">
                            <span class="mb-0 text-sm text-right  font-weight-bold">Votre compte</span>
                        </div>
                    </div>
            </li>
        </ul>
    </base-nav>
</template>
<script>
  export default {
    data() {
      return {
        activeNotifications: false,
        showMenu: false,
        searchQuery: ''
      };
    },
    methods: {
      toggleSidebar() {
        this.$sidebar.displaySidebar(!this.$sidebar.showSidebar);
      },
      hideSidebar() {
        this.$sidebar.displaySidebar(false);
      },
      toggleMenu() {
        this.showMenu = !this.showMenu;
      }
    }
  };
</script>
