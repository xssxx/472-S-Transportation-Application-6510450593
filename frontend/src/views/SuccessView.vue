<script setup>
// import router from "@/router";
import axios from "axios";
import { onMounted } from "vue";
import { useRoute } from "vue-router";
import Header from "@/components/Header.vue";

const route = useRoute();

onMounted(async () => {
  const orderId = route.query.id;
  if (orderId) {
    try {
      const res = await axios.post(
        `http://localhost:8080/orders/order-detail/${orderId}/change-status?status=UNCHECK`
      );
      // const res2 = await axios.post()
      console.log(res.data);
    } catch (error) {
      console.error(
        "Failed to update order status:",
        error.response ? error.response.data : error.message
      );
    }
  } else {
    console.error("Order ID not found in the URL");
  }
});

// const goToReceipt = () => {
//   router.push(`/receipt/${route.query.id}`);
// };
</script>

<template>
  <Header />
  <div class="container">
    <main class="main-content">
      <div class="order-details">
        <h1 class="title">Payment Successful</h1>
        <div>
          <img
            src="@/assets/success_icon.png"
            alt="Success Icon"
            class="success-icon"
          />
        </div>
        <p class="description">
          Thank you for your payment! Your order has been completed
          successfully.
        </p>
        <button @click="goToReceipt" class="receipt-button">
          Check your receipt!
        </button>
      </div>
    </main>
  </div>
</template>

<style scoped>
.container {
  display: flex;
}

.main-content {
  width: 100%;
  padding: 2rem;
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
  background-color: #f7fafc;
  min-height: calc(100vh - 62px);
  align-items: center;
  justify-content: center;
}

.order-details {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #fff;
  padding: 2rem;
  border-radius: 0.5rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  gap: 1.5rem;
}

.title {
  color: #48bb78;
  font-size: 2rem;
  font-weight: bold;
}

.success-icon {
  width: 5rem;
  height: 5rem;
}

.description {
  font-size: 1.125rem;
  color: #4a5568;
  text-align: center;
}

.receipt-button {
  margin-top: 1rem;
  background-color: #48bb78;
  color: #fff;
  padding: 0.5rem 1rem;
  border-radius: 0.375rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: background-color 0.2s ease;
}

.receipt-button:hover {
  background-color: #38a169;
}
</style>
