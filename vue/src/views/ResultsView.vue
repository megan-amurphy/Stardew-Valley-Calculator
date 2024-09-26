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
        <div v-for="(crop, index) in results" :key="index">
          <p><strong>Crop Name:</strong> {{ crop.cropName }}</p>
          <p><strong>Quality: </strong> {{ crop.quality}}</p>
          <p><strong>Crops Sold:</strong> {{ crop.cropQuantity}}</p>
          <p><strong>Revenue:</strong> {{ crop.revenue }}G</p>
          <p><strong>Net Profit:</strong> {{ crop.netProfit }}G</p>
          <p v-if="crop.seedPurchases">
            <strong>Seed Purchase Details:</strong>
            <ul>
              <li v-for="(seed, seedIndex) in crop.seedPurchases" :key="seedIndex">
                <strong>Seed {{ seedIndex + 1 }} Purchase Location:</strong> {{ seed.purchaseLocation }}<br />
                <strong>Seed Quantity:</strong> {{ seed.quantity }}<br />
                <strong>Out of Season:</strong> {{ seed.isOutOfSeason ? 'Yes' : 'No' }}<br />
                <strong>Seed Cost:</strong> {{ seed.cost }}G
              </li>
            </ul>
          </p>
          <hr />
        </div>
      </div>

      <!-- Grand Total Revenue and Net Profit -->
      <div class="grand-totals">
        <h3>Grand Totals</h3>
        <p><strong>Total Revenue:</strong> {{ totalRevenue }}G</p>
        <p><strong>Total Net Profit:</strong> {{ totalNetProfit }}G</p>
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
  data() {
    return {
      results: [], 
      totalRevenue: 0,
      totalNetProfit: 0,
    };
  },
  mounted() {
    if (this.$route.query.results) {
      this.results = JSON.parse(this.$route.query.results);

      // Calculate the grand totals for revenue and net profit
      this.totalRevenue = this.results.reduce((total, crop) => total + crop.revenue, 0);
      this.totalNetProfit = this.results.reduce((total, crop) => total + crop.netProfit, 0);
    }
  },
  methods: {
    exportData() {
      const data = {
        results: this.results,
        totalRevenue: this.totalRevenue,
        totalNetProfit: this.totalNetProfit,
      };
      const blob = new Blob([JSON.stringify(data, null, 2)], { type: 'application/json' });
      const url = URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', 'crop_results.json');
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    },
    startOver() {
      this.$router.push({ name: 'cropinput' });
    }
  }
}
</script>

<style scoped>
.results-view {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
}

.form-container {
  background-color: rgba(0, 0, 0, 0.7);
  padding: 20px;
  border-radius: 15px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
  max-width: 600px;
  width: 100%;
  display: flex;
  flex-direction: column;
}

h3,
h4 {
  color: white;
}

.buttons {
  margin-top: 20px;
}

button {
  background-color: #28a745;
  color: white;
  border: none;
  padding: 10px 20px;
  font-size: 1.1rem;
  border-radius: 5px;
  cursor: pointer;
  margin-right: 10px;
}

button:hover {
  background-color: #218838;
}
</style>
