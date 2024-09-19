<template>
  <div class="results-view">
    <header>
      <img src="/sdv-profit-calculator.png" alt="SDV Header" class="header-image" />
    </header>

    <div class="form-container">
      <h1>Results</h1>

      <!-- Display entered crop details -->
      <div class="data-inputted">
        <h3>Data Entered:</h3>
        <div v-for="(crop, index) in cropData" :key="index">
          <p><strong>Crop Name:</strong> {{ crop.cropName }}</p>
          <p><strong>Quality:</strong> {{ crop.quality }}</p>
          <p><strong>Amount:</strong> {{ crop.quantity }}</p>
          <p v-for="(seed, seedIndex) in crop.seedPurchases" :key="seedIndex">
            <strong>Seed {{ seedIndex + 1 }} Purchase Location:</strong> {{ seed.purchaseLocation
            }}<br />
            <strong>Seed {{ seedIndex + 1 }} Quantity:</strong> {{ seed.quantity }}<br />
            <strong>Out of Season:</strong> {{ seed.isOutOfSeason ? 'Yes' : 'No' }}
          </p>
          <hr />
        </div>
      </div>

      <!-- Revenue -->
      <div class="revenue-section">
        <h3>Revenue</h3>
        <p><strong>Total Revenue:</strong> ${{ revenue }}</p>
        <p><strong>Revenue Calculation:</strong> {{ revenueDetails }}</p>
      </div>

      <!-- Cost -->
      <div class="cost-section">
        <h3>Cost</h3>
        <p><strong>Total Cost:</strong> ${{ cost }}</p>
        <p><strong>Cost Calculation:</strong> {{ costDetails }}</p>
      </div>

      <!-- Net Profit -->
      <div class="net-profit-section">
        <h3>Net Profit</h3>
        <p><strong>Net Profit:</strong> ${{ netProfit }}</p>
        <p><strong>Net Profit Calculation:</strong> {{ netProfitDetails }}</p>
      </div>

      <!-- Buttons for exporting and starting over -->
      <div class="buttons">
        <button @click="exportData">Export</button>
        <button @click="startOver">Start Over</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: [
    'cropData',
    'revenue',
    'revenueDetails',
    'cost',
    'costDetails',
    'netProfit',
    'netProfitDetails'
  ],
  methods: {
    exportData() {
      const data = {
        cropData: this.cropData,
        revenue: this.revenue,
        revenueDetails: this.revenueDetails,
        cost: this.cost,
        costDetails: this.costDetails,
        netProfit: this.netProfit,
        netProfitDetails: this.netProfitDetails
      }
      const blob = new Blob([JSON.stringify(data, null, 2)], { type: 'application/json' })
      const url = URL.createObjectURL(blob)
      const link = document.createElement('a')
      link.href = url
      link.setAttribute('download', 'crop_data.json')
      document.body.appendChild(link)
      link.click()
      document.body.removeChild(link)
    },
    startOver() {
      this.$router.push({ name: 'cropinput' })
    }
  }
}
</script>

<!-- Style Section (copied from CropSelectionView.vue) -->
<style>
.results-view {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
}

.form-container {
  background-color: rgba(0, 0, 0, 0.7); /* Semi-transparent background */
  padding: 20px;
  border-radius: 15px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
  max-width: 600px;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

h3,
h4 {
  color: white;
}

.add-crop-btn {
  margin-top: 10px;
  margin-bottom: 20px;
}

.calculate-btn {
  margin-top: auto;
  background-color: #28a745;
  color: white;
  border: none;
  padding: 10px 20px;
  font-size: 1.1rem;
  border-radius: 5px;
  cursor: pointer;
  align-self: flex-end;
}

.calculate-btn:hover {
  background-color: #218838;
}
</style>
