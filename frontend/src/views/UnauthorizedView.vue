<template>
    <div class="unauthorized">
      <h1>Unauthorized Access</h1>
      <p>You do not have permission to view this page.</p>
      <button @click="goBack" class="link-button">Go Back</button>
    </div>
</template>
  
<script>
  export default {
    name: "UnauthorizedView",
    methods: {
        goBack() {
            window.history.back();
        },
    },
  };
</script>
  
<style scoped>
  .unauthorized {
    text-align: center;
    margin-top: 100px;
  }
  
  .unauthorized h1 {
    color: #ff0000;
  }
  
  .unauthorized p {
    font-size: 1.2em;
    color: #333;
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