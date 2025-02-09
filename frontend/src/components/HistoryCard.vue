<template>
  <div class="history-card">
    <div class="card-header">
      <h2 class="name">{{ name }}</h2>
      <p class="email">{{ email }}</p>
    </div>
    <div class="card-body">
      <p class="phone">{{ phone }}</p>
      <p class="address">{{ address }}</p>
    </div>
    <div class="card-footer">
      <span class="status rejected">Completed</span>
    </div>
  </div>
</template>

<script setup lang="ts">
interface Props {
  name?: string;
  email?: string;
  phone?: string;
  address?: string;
}

const props = withDefaults(defineProps<Props>(), {
  name: "Ada Evans",
  email: "jabinayuh@gmail.com",
  phone: "+1 (232) 479-2902",
  address: "32 St. Pierre & Miquelon",
});
</script>

<style scoped>
.history-card {
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  padding: 16px;
  margin: 8px;
  background-color: #ffffff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  min-height: 6rem;
}

.card-header {
  margin-bottom: 12px;
}

.name {
  font-size: 1.25rem;
  font-weight: bold;
  margin: 0;
}

.email {
  font-size: 0.875rem;
  color: #666666;
  margin: 4px 0 0;
}

.card-body {
  margin-bottom: 12px;
}

.phone,
.address {
  font-size: 0.875rem;
  color: #333333;
  margin: 4px 0;
}

.card-footer {
  text-align: right;
}

.status {
  font-size: 0.875rem;
  font-weight: bold;
  padding: 4px 8px;
  border-radius: 4px;
}

.rejected {
  color: #ffffff;
  background-color: #61be66;
}
</style>
