<template>
  <div class="page-container">
    <Header></Header>

    <div class="main-container">
      <div class="order-container">
        <h2 class="order-title">Order</h2>
        <div v-if="userRole === 'ADMIN'">
          <label for="status">Filter by Status:</label>
          <select v-model="selectedStatus" @change="fetchOrdersFilter">
            <option value="all">All</option>
            <option value="uncheck">Unchecked</option>
            <option value="check">Checked</option>
            <option value="on-going">On-going</option>
            <option value="delivered">Delivered</option>
            <option value="uploaded">Uploaded</option>
            <option value="complete">Complete</option>
          </select>
        </div>
        <component
            v-for="order in orders"
            :key="order.id"
            :is="orderComponent"
            :orderId="order.id"
            :date="order.date || 'N/A'"
            :status="order.status"
            :customerName="order.customerName"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import Header from "../components/Header.vue";
import OrderAdminCard from "../components/OrderAdminCard.vue";
import OrderWorkerCard from "../components/OrderWorkerCard.vue";
import OrderCompanyCard from "../components/OrderCompanyCard.vue";
import axios from "axios";

export default {
  components: {
    Header,
    OrderAdminCard,
    OrderWorkerCard,
    OrderCompanyCard,
  },
  data() {
    return {
      orders: [],
      selectedStatus: "all",
    };
  },
  created() {
    const userId = this.id;
    console.log("User ID:", userId);
    if (userId) {
      this.fetchOrdersBasedOnRole(userId);
    } else {
      this.fetchOrders();
    }
  },
  computed: {
    ...mapGetters(["userRole", "id"]),
    orderComponent() {
      switch (this.userRole) {
        case "ADMIN":
          return OrderAdminCard;
        case "WORKER":
          return OrderWorkerCard;
        case "USER":
          return OrderCompanyCard;
        default:
          return null;
      }
    },
  },
  methods: {
    async fetchOrdersBasedOnRole(userId) {
      switch (this.userRole) {
        case "USER":
          await this.fetchOwnOrders(userId);
          break;
        case "ADMIN":
          await this.fetchOrders();
          break;
        case "WORKER":
          await this.fetchWorkerOrders(userId);
          break;
        default:
          console.error("Unknown user role");
      }
    },
    async fetchOrders() {
      try {
        const response = await axios.get("http://localhost:8080/orders/all-orders?fields=id,date,status,customerName");
        this.orders = response.data.map(order => ({
          id: order.id,
          date: order.date || "N/A",
          status: order.status,
          customerName: order.customerName
        })).sort((a, b) => new Date(b.date) - new Date(a.date));
      } catch (error) {
        console.error("Error fetching orders:", error);
      }
    },
    async fetchOrdersFilter() {
      let endpoint;
      switch (this.selectedStatus) {
        case "uncheck":
          endpoint = "http://localhost:8080/orders/uncheck-orders";
          break;
        case "check":
          endpoint = "http://localhost:8080/orders/check-orders";
          break;
        case "on-going":
          endpoint = "http://localhost:8080/orders/on-going-orders";
          break;
        case "delivered":
          endpoint = "http://localhost:8080/orders/delivered-orders";
          break;
        case "uploaded":
          endpoint = "http://localhost:8080/orders/uploaded-orders";
          break;
        case "complete":
          endpoint = "http://localhost:8080/orders/completed-orders";
          break;
        default:
          endpoint = "http://localhost:8080/orders/all-orders";
          break;
      }

      try {
        const response = await axios.get(endpoint);
        this.orders = response.data.map(order => ({
          id: order.id,
          date: order.date || "N/A",
          status: order.status,
          customerName: order.customerName,
        })).sort((a, b) => new Date(b.date) - new Date(a.date));
      } catch (error) {
        console.error("Error fetching filtered orders:", error);
      }
    },
    async fetchOwnOrders(userId) {
      try {
        const response = await axios.get(`http://localhost:8080/orders/user/${userId}`);
        this.orders = response.data.map(order => ({
          id: order.id,
          date: order.date || "N/A",
          status: order.status,
          customerName: order.customerName,
        })).sort((a, b) => new Date(b.date) - new Date(a.date));
      } catch (error) {
        console.error("Error fetching own orders:", error);
      }
    },
    async fetchWorkerOrders(workerId) {
      try {
        const response = await axios.get(`http://localhost:8080/orders/worker/${workerId}`);
        this.orders = response.data.map(order => ({
          id: order.id,
          date: order.date || "N/A",
          status: order.status,
          customerName: order.customerName,
        })).sort((a, b) => new Date(b.date) - new Date(a.date));
      } catch (error) {
        console.error("Error fetching worker orders:", error);
      }
    },
  },
};
</script>



<style scoped>
:root {
  --main-bg-color: #4a4a4a;
  --sub-bg-color: #eeeeee;
  --card-bg-color: #ffffff;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.page-container {
  background-color: var(--main-bg-color);
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.main-container {
  display: flex;
  justify-content: center;
  flex: 1;
}

.order-title {
  font-family: "Inter", sans-serif;
  font-size: 24px;
  font-weight: bold;
  color: #333;
  margin-bottom: 20px;
  text-align: left;
}

.order-container {
  background-color: #e6e6e6;
  padding: 20px;
  width: 80%;
  max-width: 1200px;
  overflow-y: auto;
  height: 100%;
}
</style>
