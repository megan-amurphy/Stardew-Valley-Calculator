<template>
  <div class="crop-selection-view">
    <!-- Header -->
    <header>
      <img src="/sdv-profit-calculator.png" alt="SDV Header" class="header-image" />
    </header>

    <!-- Container for all inputs and buttons -->
    <div class="form-container">
      <!-- Display existing crops -->
      <div v-for="(cropEntry, index) in cropEntries" :key="index" class="crop-entry">
        <h3>Crop {{ index + 1 }}</h3>

        <!-- Crop Name Autocomplete -->
        <label for="cropName">Crop Name</label>
        <input
          type="text"
          v-model="cropEntry.cropName"
          @input="fetchCropSuggestions(cropEntry)"
          list="crop-suggestions"
          placeholder="Enter crop name"
        />
        <datalist id="crop-suggestions">
          <option v-for="suggestion in cropSuggestions" :key="suggestion">{{ suggestion }}</option>
        </datalist>

        <!-- Quality Selection Dropdown -->
        <label for="quality">Quality</label>
        <select v-model="cropEntry.quality">
          <option value="normal">Normal</option>
          <option value="silver">Silver</option>
          <option value="gold">Gold</option>
          <option value="iridium">Iridium</option>
        </select>

        <!-- Crop Quantity Input -->
        <label for="quantity">Quantity</label>
        <input type="number" v-model="cropEntry.quantity" min="1" />

        <!-- Seed Purchases -->
        <div
          v-for="(seedEntry, seedIndex) in cropEntry.seedPurchases"
          :key="seedIndex"
          class="seed-entry"
        >
          <h4>Seed Purchase {{ seedIndex + 1 }}</h4>

          <!-- Seed Purchase Location -->
          <label for="purchaseLocation">Purchase Location</label>
          <select v-model="seedEntry.purchaseLocation">
            <option value="Pierre General Store">Pierre's General Store</option>
            <option value="JojaMart">JojaMart</option>
            <option value="Annual Festival">Annual Festival</option>
            <option value="Oasis Resort">Oasis Resort</option>
            <option value="Traveling Cart">Traveling Cart</option>
            <option value="Island Trader">Island Trader</option>
            <option value="Seedmaker/Misc">Seedmaker or Misc.</option>
          </select>

          <!-- Seed Quantity Input -->
          <label for="seedQuantity">Seed Quantity</label>
          <input type="number" v-model="seedEntry.quantity" min="1" />

          <!-- Out of Season Checkbox -->
          <label for="isOutOfSeason">Out of Season?</label>
          <input type="checkbox" v-model="seedEntry.isOutOfSeason" />
        </div>

        <!-- Add another seed purchase for this crop -->
        <button class="add-seed-btn" @click="addSeedPurchase(index)">Add Seed Purchase</button>

        <hr />
      </div>

      <!-- Add another crop -->
      <button class="add-crop-btn" @click="addCropEntry">Add Another Crop</button>

      <!-- Calculate button positioned at the bottom -->
      <button class="calculate-btn" @click="submitCalculation">Calculate</button>
    </div>

    <!-- Display results -->
    <div v-if="calculationResult">
      <h3>Results:</h3>
      <p>{{ calculationResult }}</p>
    </div>
  </div>
</template>

<script>
import CalculatorService from '../services/CalculatorServices'
import CropService from '../services/CropService'
export default {
  data() {
    return {
      cropEntries: [
        {
          cropName: '',
          quality: 'normal',
          quantity: 1,
          seedPurchases: [
            { purchaseLocation: 'Pierre General Store', quantity: 1, isOutOfSeason: false }
          ]
        }
      ],
      cropSuggestions: [],
      calculationResult: null
    }
  },
  methods: {
    addCropEntry() {
      this.cropEntries.push({
        cropName: '',
        quality: 'normal',
        quantity: 1,
        seedPurchases: [
          { purchaseLocation: 'Pierre General Store', quantity: 1, isOutOfSeason: false }
        ]
      })
    },

    addSeedPurchase(cropIndex) {
      this.cropEntries[cropIndex].seedPurchases.push({
        purchaseLocation: 'Pierre General Store',
        quantity: 1,
        isOutOfSeason: false
      })
    },

    // Fetch crop suggestions for autocomplete
    async fetchCropSuggestions(cropEntry) {
      if (cropEntry.cropName.length > 1) {
        try {
          const response = await CropService.getAllCrops()
          this.cropSuggestions = response.data.map((crop) => crop.cropName)
        } catch (error) {
          console.error('Error fetching crop suggestions:', error.response?.data || error.message)
        }
      }
    },

    // Submit the calculation to the backend
    async submitCalculation() {
      try {
        const response = await CalculatorService.calculate(this.cropEntries)
        //   // crops, // Crop object from front-end input
        // cropQuantities,
        // seeds,
        // seedQuantities
        const data = response.data

        // Navigate to the ResultsView and pass the calculation results as route params
        this.$router.push({
          name: 'results', // Make sure your route is defined with this name
          params: {
            cropData: this.cropEntries,
            revenue: data.revenue,
            revenueDetails: data.revenueDetails,
            cost: data.cost,
            costDetails: data.costDetails,
            netProfit: data.netProfit,
            netProfitDetails: data.netProfitDetails
          }
        })
      } catch (error) {
        console.error('Error calculating result:', error.response?.data || error.message)
        this.calculationResult = 'Error calculating the result. Please try again.'
      }
    }
  }
}
</script>

<style>
.crop-selection-view {
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

.crop-entry {
  margin-bottom: 20px;
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
  margin-top: auto; /* This pushes the button to the bottom */
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
