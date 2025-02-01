<template>
  <div>
    <Header></Header>
    <div class="image-grid">
      <div class="big-image">
        <div class="image-overlay"></div>
        <img src="../assets/storage.png" alt="Big Image"/>
        <h1 class="text big">Effortless transportation</h1>
      </div>
      <div class="small-images">
        <div class="small-image">
          <div class="image-overlay"></div>
          <div class="fade"></div>
          <img src="../assets/boat.png" alt="Small Image 1" />
          <h1 class="text title">Sea Freight</h1> 
          <span class="text">Our Transport Application provides a comprehensive range of services, including our Sea Shipping service, which delivers to ports worldwide. We cater to the diverse needs of our global client base.</span>
        </div>
        <div class="small-image">
          <div class="fade plane"></div>
          <div class="image-overlay"></div>
          <img src="../assets/plane.png" alt="Small Image 2" />
          <h1 class="text title">Air Freight</h1> 
          <span class="text">Our transportation application excels in delivering expert international air freight forwarding services and providing customized air freight logistics solutions tailored to meet your specific needs.

</span>
        </div>
        <div class="small-image truck-image">
          <div class="fade truck"></div>
          <div class="image-overlay"></div>
          <img src="../assets/truck.png" alt="Small Image 3" />
          <h1 class="text title cargo">Cargo Handling</h1> 
          <span class="text">Our transport application specializes in break bulk cargo services that accommodate all types of cargo, regardless of size or shape. We are committed to delivering an exceptional experience in project cargo handling, ensuring high-quality service tailored to your specific needs.</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import Header from "../components/Header.vue";

export default {
  components: {
    Header,
  },
  computed: {
    ...mapGetters(["userRole"]),
  },
};
</script>

<style scoped>
.image-grid {
  display: grid;
  grid-template-columns: 1fr;
  gap: 0px;
}

.big-image {
  grid-column: span 1;
  height: 606px;
  overflow: hidden;
  position: relative;
  box-shadow: 0 5px 20px rgba(0, 0, 0, 0.5);
  z-index: 2;
}

.small-images {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 0px;
  justify-content: center;
}

.small-image {
  overflow: hidden;
  height: 285px;
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
}

.text.title {
  margin-top: auto;
  margin-bottom: 10px;
  font-size: 20px;
  text-align: center;
}

.text {
  color: white;
  font-size: 14px;
  text-align: center;
  z-index: 2;
}


.image-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1;
}

.big-image img{
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: center;
  border-radius: 0px;
  z-index: 0;
}

.small-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: center 20%;
  border-radius: 0px;
  z-index: 0;
}

.truck-image img {
  object-position: center 80%;
}

.fade {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(to top, black 30%, transparent 100%);
  z-index: 1;
}

.truck {
  background: linear-gradient(to top, black 40%, transparent 100%);
}

.plane {
  background: linear-gradient(to top, black 15%, transparent 100%);
}

.big {
  position: absolute;
  bottom: 42%;
  left: 50%;
  transform: translateX(-50%);
  color: white;
  font-size: 100px;
  z-index: 2;
  text-align: center;
}

</style>
