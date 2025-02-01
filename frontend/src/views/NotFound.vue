<template>
    <div class="not-found">
      <h1>404 - Page Not Found</h1>
      <p>The page you are looking for does not exist.</p>
      <button @click="goBack" class="link-button">Go Back</button>
    </div>
</template>
  
<script>
export default {
    name: "NotFound",
    methods: {
        goBack() {
            window.history.back();
        },
    },
};
</script>
  
<style scoped>
.not-found {
  text-align: center;
  margin-top: 100px;
}


.link-button {
  background: none;
  border: none;
  color: #007bff;
  cursor: pointer;
  font-size: 1em;
  padding: 0;
  text-decoration: underline;
}

.link-button:hover {
  color: #0056b3;
}
  </style>