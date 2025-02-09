<template>
  <nav class="header">
    <Header />
  </nav>
  <main class="container">
    <!-- Display HistoryCards for the current page -->
    <div v-for="card in paginatedCards" :key="card.id">
      <HistoryCard
        :name="card.name"
        :email="card.email"
        :phone="card.phone"
        :address="card.address"
      />
    </div>

    <!-- Pagination Controls -->
    <div class="pagination">
      <button @click="prevPage" :disabled="currentPage === 1">Previous</button>
      <span>Page {{ currentPage }}/{{ totalPages }}</span>
      <button @click="nextPage" :disabled="currentPage === totalPages">
        Next
      </button>
    </div>
  </main>
</template>

<script setup lang="ts">
import { ref, computed, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import Header from "@/components/Header.vue";
import HistoryCard from "@/components/HistoryCard.vue";

// mock data
const cards = Array.from({ length: 50 }, (_, i) => ({
  id: i + 1,
  name: `User ${i + 1}`,
  email: `user${i + 1}@example.com`,
  phone: `+1 (555) 555-${String(i + 1).padStart(4, "0")}`,
  address: `${i + 1} Main St, City`,
}));

const itemsPerPage = 10;
const currentPage = ref(1);

const route = useRoute();
const router = useRouter();

const totalPages = computed(() => Math.ceil(cards.length / itemsPerPage));

// Read the `page` query parameter from the URL
const pageQuery = parseInt(route.query.page as string, 10);
if (!isNaN(pageQuery) && pageQuery >= 1 && pageQuery <= totalPages.value) {
  currentPage.value = pageQuery;
}

const paginatedCards = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage;
  const end = start + itemsPerPage;
  return cards.slice(start, end);
});

function nextPage() {
  if (currentPage.value < totalPages.value) {
    currentPage.value++;
    updateUrl();
    scrollToTop();
  }
}

function prevPage() {
  if (currentPage.value > 1) {
    currentPage.value--;
    updateUrl();
    scrollToTop();
  }
}

function updateUrl() {
  router.push({ query: { page: currentPage.value } });
}

function scrollToTop() {
  window.scrollTo({
    top: 0,
    behavior: "smooth",
  });
}

// Watch for changes in the `page` query parameter
watch(
  () => route.query.page,
  (newPage) => {
    const page = parseInt(newPage as string, 10);
    if (!isNaN(page) && page >= 1 && page <= totalPages.value) {
      currentPage.value = page;
    }
  }
);
</script>

<style scoped>
.container {
  width: 70%;
  padding: 2rem;
  margin: 2rem 4rem;
  background-color: #e6e6e6;
  min-width: 80%;
  min-height: 80vh;
  height: 100%;
  justify-self: center;
  border-radius: 10px;
}

.header {
  position: sticky;
  top: 0;
}

.pagination {
  margin-top: 2rem;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 1rem;
}

.pagination button {
  padding: 0.5rem 1rem;
  border: none;
  background-color: #3b82f6;
  color: white;
  border-radius: 4px;
  cursor: pointer;
}

.pagination button:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}
</style>
