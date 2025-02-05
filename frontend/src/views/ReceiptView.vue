<script setup>
import { ref, onMounted } from 'vue'
import Header from "@/components/Header.vue";
import {useRoute} from "vue-router";

const o_id = ref('')

const date = ref('')
const status = ref('')
const cus_name = ref('')
const cus_address = ref('')
const worker = ref('')

const p_id = ref('');
const p_name = ref('')
const p_type = ref('')
const p_amount = ref('')

onMounted(async () => {
  try {
    const route = useRoute()
    const { data: responseOrder } = await receiptApi.getReceiptById(
        route.params.id
    )
    const { data: responseUser } = await receiptApi.getUserById(
        route.params.id
    )
    const { data: responseFood } = await receiptApi.getFoodById(
        route.params.id
    )

    b_total.value = responseOrder.data.total
    b_id.value = responseOrder.data.id
    created_at.value = responseOrder.data.createdAt
    username.value = responseUser.data.username
    foodList.value = responseFood.data.foods
  } catch (error) {
    console.error('Error fetching receipt:', error)
  }
})

const companyInfo = ref({
  name: "บริษัท ผู้ขายทดสอบ จำกัด (สำนักงานใหญ่)",
  address: "999 หมู่ 999 ถ.ทดสอบ 999 แขวงสีลม เขตบางรัก กรุงเทพมหานคร 10500",
  taxId: "1234567890999",
  phone: "912345678",
  email: "seller@test.com"
})

const customerInfo = ref({
  name: "kikiman pretty",
  address: "123 Selicon St.",
  status: "COMPLETE",
  date: "04/02/2025 16:09:44",
  worker: "N/A"
})

const invoiceInfo = ref({
  id: "TIV20210500001",
  date: "04/02/2025 16:09:44",
  items: [
    { id: "0a821fd5-5798-47cb-88c6-2546f86fc7fc", name: "สินค้ารายการแรก", type: "Lightweight", amount: "10" },
    { id: 2, name: "สินค้ารายการที่สอง", type: "Heavyweight", amount: "2" }
  ]
})

const printReceipt = () => {
  const printContent = document.getElementById('print-section').innerHTML
  const originalContent = document.body.innerHTML

  document.body.innerHTML = printContent
  window.print()
  document.body.innerHTML = originalContent
  window.location.reload()
}
</script>

<template>
  <Header></Header>
  <main class="container">
    <div id="print-section" class="receipt-container">
      <h2 class="title">ใบส่งของ/ใบกำกับภาษี</h2>
      <p class="subtitle">Delivery Order/Tax Invoice</p>

      <div class="info-section">
        <div class="company-info">
          <strong>{{ companyInfo.name }}</strong>
          <p>{{ companyInfo.address }}</p>
          <p>เลขประจำตัวผู้เสียภาษี: {{ companyInfo.taxId }}</p>
          <p>โทร: {{ companyInfo.phone }}</p>
          <p>อีเมล: {{ companyInfo.email }}</p>
        </div>
        <div class="invoice-details">
          <p>เลขที่ {{ invoiceInfo.id }}</p>
          <p>วันที่ {{ invoiceInfo.date }}</p>
        </div>
      </div>

      <div class="info-section">
        <div class="customer-info">
          <strong>ลูกค้า: {{ customerInfo.name }}</strong>
          <p>{{ customerInfo.address }}</p>
          <p>สถานะ: {{ customerInfo.status }}</p>
          <p>วันที่: {{ customerInfo.date }}</p>
          <p>ผู้ส่ง: {{ customerInfo.worker }}</p>
        </div>
      </div>

      <table class="invoice-table">
        <thead>
        <tr>
          <th>ID</th>
          <th>ชื่อสินค้า</th>
          <th>ประเถท</th>
          <th>จำนวน</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in invoiceInfo.items" :key="item.id">
          <td>{{ item.id }}</td>
          <td>{{ item.name }}</td>
          <td>{{ item.type }}</td>
          <td>{{ item.amount }}</td>
        </tr>
        </tbody>
      </table>

      <div class="total-section">
        <p><strong>รวมทั้งสิ้น:</strong> 500 บาท</p>
      </div>
    </div>

    <button class="print-btn" @click="printReceipt">พิมพ์ใบเสร็จ</button>
  </main>
</template>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

.receipt-container {
  width: 80%;
  max-width: 700px;
  padding: 1.5rem;
  border-radius: 12px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
  border: 1px solid #e0e0e0;
  background-color: #fdfdfd;
  text-align: center;
  margin-top: 1rem;
}

.title {
  font-size: 1.5rem;
  font-weight: bold;
}

.subtitle {
  font-size: 1rem;
  color: #555;
  margin-bottom: 10px;
}

.info-section {
  display: flex;
  justify-content: space-between;
  text-align: left;
  margin-bottom: 10px;
}

.company-info, .customer-info {
  flex: 1;
}

.invoice-details {
  text-align: right;
}

.invoice-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
}

.invoice-table th, .invoice-table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: center;
}

.total-section {
  margin-top: 10px;
  text-align: right;
  font-size: 1.2rem;
}

.print-btn {
  margin-top: 10px;
  padding: 10px 16px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  max-width: 30%;
}

.print-btn:hover {
  background-color: #45a049;
}
</style>
