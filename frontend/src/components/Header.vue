<template>
    <div class="header">
      <div class="header-container">
        <router-link to="/main" class="logo">Transport application</router-link>
        <nav class="nav">
          <router-link v-if="userRole === 'ADMIN'" to="/orders">Order</router-link>
          <router-link v-if="userRole === 'ADMIN'" to="/worker-list">Transport worker</router-link>
          <router-link v-if="userRole === 'ADMIN'" to="/user-list">User</router-link>
          <router-link v-if="userRole === 'ADMIN'" to="/create-user">Create User</router-link>
  
          <router-link v-if="userRole === 'USER'" to="/create-order">Create Order</router-link>
          <router-link v-if="userRole === 'USER' || userRole === 'WORKER'" to="/orders">My Order</router-link>
        </nav>
      </div>
      <div class="profile">
        <a href="#" @click="contact">Contact</a>
        <a href="#" @click="logout">Logout</a>
        <img src="https://via.placeholder.com/40" alt="Profile" class="profile-image" />
      </div>
    </div>
  </template>
  
  <script>
  import { mapGetters } from "vuex";
  
  export default {
    name: "Header",
    computed: {
      ...mapGetters(["userRole"]),
    },
    methods: {
      logout() {
        this.$store.dispatch("clearUserData");
        this.$router.push("/login");
      },
      contact() {
        alert("Tel: 089-***-****");
      },
    },
    created() {
      console.log("Header: ", this.userRole);
    }
  };
  </script>
  
  <style scoped>
  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #fff;
    padding: 10px 20px;
    border-bottom: 2px solid #e0e0e0;
  }
  
  .logo {
    text-decoration: none;
    font-size: 16px;
    color: #000;
    font-weight: bold;
    margin-right: 20px;
  }
  
  .header-container {
    display: flex;
    justify-content: flex-start;
    align-items: center;
  }
  
  .nav {
    margin-top: 10px;
    margin-left: 100px;
    display: flex;
    gap: 10px;
  }
  
  .nav a {
    text-decoration: none;
    font-size: 12px;
    color: #000;
    padding: 3px 6px;
    border-bottom: 1px solid transparent;
  }
  
  .nav a:hover,
  .nav a:focus {
    border-bottom: 1px solid black;
  }
  
  .profile {
    display: flex;
    align-items: center;
    gap: 10px;
  }
  
  .profile-image {
    width: 30px;
    height: 30px;
    border-radius: 50%;
  }
  </style>
  