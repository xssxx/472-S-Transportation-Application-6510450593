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
      <div class="profile" ref="profileContainer">
        <img
            src="https://via.placeholder.com/40"
            alt="Profile"
            class="profile-image"
            @click="toggleProfileMenu"
        />
        <div v-if="showProfileMenu" class="profile-menu">
          <a href="#" @click.prevent="editProfile">Edit Profile</a>
          <a href="#" @click.prevent="contact">Contact</a>
          <div class="menu-divider"></div>
          <a href="#" @click.prevent="logout" class="logout-link">Logout</a>
        </div>
      </div>
    </div>
  </template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "Header",
  data() {
    return {
      showProfileMenu: false
    };
  },
  computed: {
    ...mapGetters(["userRole"]),
  },
  methods: {
    toggleProfileMenu() {
      this.showProfileMenu = !this.showProfileMenu;
      if (this.showProfileMenu) {
        document.addEventListener('click', this.handleClickOutside);
      } else {
        document.removeEventListener('click', this.handleClickOutside);
      }
    },
    handleClickOutside(event) {
      if (!this.$refs.profileContainer?.contains(event.target)) {
        this.showProfileMenu = false;
        document.removeEventListener('click', this.handleClickOutside);
      }
    },
    editProfile() {
      this.$router.push('/edit-profile');
      this.showProfileMenu = false;
    },
    contact() {
      alert("Tel: 089-***-****");
      this.showProfileMenu = false;
    },
    logout() {
      this.$store.dispatch("clearUserData");
      this.$router.push("/login");
      this.showProfileMenu = false;
    }
  },
  beforeDestroy() {
    document.removeEventListener('click', this.handleClickOutside);
  }
};
</script>

<style scoped>
.profile {
  position: relative;
  display: flex;
  align-items: center;
  cursor: pointer;
}

.profile-menu {
  position: absolute;
  right: 0;
  top: 100%;
  background-color: white;
  border: 1px solid #e0e0e0;
  border-radius: 4px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  padding: 10px 0;
  z-index: 1000;
  margin-top: 5px;
  min-width: 150px;
}

.profile-menu a {
  display: block;
  padding: 8px 16px;
  text-decoration: none;
  color: #333;
  font-size: 14px;
  border-radius: 4px;
  transition: background-color 0.2s;
}

.profile-menu a:hover {
  background-color: #f5f5f5;
}

/* สไตล์เส้นคั่น */
.menu-divider {
  height: 1px;
  background-color: #e0e0e0;
  margin: 8px 0; /* เว้นระยะห่างด้านบนและล่าง */
}

/* สไตล์เฉพาะสำหรับ Logout */
.logout-link {
  margin-top: 8px; /* เว้นระยะห่างด้านบน */
  color: #ff4444; /* สีแดงเพื่อให้ดูโดดเด่น */
}

.logout-link:hover {
  background-color: #ffe5e5; /* สีพื้นหลังเมื่อ hover */
}

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

.profile-image {
  width: 30px;
  height: 30px;
  border-radius: 50%;
}

.picture-preview {
  width: 120px;
  height: 120px;
  margin: 0 auto;
  border-radius: 50%;
  overflow: hidden;
  border: 2px solid #ddd;
  display: flex;
  align-items: center;
  justify-content: center;
}

.picture-preview img {
  width: 100%;
  height: 100%;
  object-fit: cover; /* ทำให้รูปอยู่ในกรอบ */
  border-radius: 50%; /* ทำให้รูปภาพเป็นวงกลม */
}
</style>