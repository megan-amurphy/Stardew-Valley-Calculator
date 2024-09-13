<template>
  <div class="results-view">
    <h1>Results</h1>

    <!-- Display entered crop details -->
    <div class="data-inputted">
      <h3>Data Entered:</h3>
      <p><strong>Crop Name:</strong> {{ cropData.cropName }}</p>
      <p><strong>Quality:</strong> {{ cropData.quality }}</p>
      <p><strong>Amount:</strong> {{ cropData.amount }}</p>
      <p><strong>Purchased From:</strong> {{ cropData.purchaseLocation }}</p>
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
    // Export the data (TODO: define the export format)
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
      link.setAttribute('download', 'crop_data.json') // TODO: Adjust file format as needed
      document.body.appendChild(link)
      link.click()
      document.body.removeChild(link)
    },
    // Emit start-over event to parent component
    startOver() {
      this.$emit('start-over')
    }
  }
}
</script>

<style scoped>
.results-view {
  /* Add your CSS styling here */
}
.buttons {
  margin-top: 20px;
}
</style>
