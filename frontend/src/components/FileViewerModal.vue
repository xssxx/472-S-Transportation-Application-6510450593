<template>
    <div class="modal-overlay" v-if="isVisible" @click.self="close">
      <div class="modal-content">
        <iframe :src="fileUrl" frameborder="0" class="file-viewer"></iframe>
        <button @click="close" class="close-button">Close</button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      isVisible: Boolean,
      fileUrl: String
    },
    methods: {
      close() {
        this.$emit("close");
      }
    }
  };
  </script>
  
  <style>
  .modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .modal-content {
    width: 80%;
    height: 80%;
    background: white;
    padding: 10px;
    position: relative;
  }
  .file-viewer {
    width: 100%;
    height: 100%;
  }
  .close-button {
    position: absolute;
    right: 50px;
    width: 50%;
    bottom: 10px;
  }
  </style>