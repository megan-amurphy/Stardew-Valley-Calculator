<template>
  <div class="crop-selection-view">
    <h1>Crop Calculator</h1>

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
      <button @click="addSeedPurchase(index)">Add Seed Purchase</button>

      <hr />
    </div>

    <!-- Add another crop -->
    <button @click="addCropEntry">Add Another Crop</button>

    <!-- Calculate button -->
    <button @click="submitCalculation">Calculate</button>

    <!-- Display results -->
    <div v-if="calculationResult">
      <h3>Results:</h3>
      <p>{{ calculationResult }}</p>
    </div>
  </div>
</template>

<script>
import CalculatorService from '@/services/CalculatorService'
import CropService from '@/services/CropService'

export default {
  data() {
    return {
      cropEntries: [], // Array of crops with multiple seed purchases
      cropSuggestions: [], // Autocomplete suggestions
      calculationResult: null
    }
  },
  methods: {
    // Add a new crop entry
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

    // Add another seed purchase to a crop
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
        const response = await CalculatorService.calculateMultipleCrops(this.cropEntries)
        this.calculationResult = response.data
      } catch (error) {
        console.error('Error calculating result:', error.response?.data || error.message)
        this.calculationResult = 'Error calculating the result. Please try again.'
      }
    }
  }
}
</script>

<style scoped>
/* Add your styles here */
</style>
