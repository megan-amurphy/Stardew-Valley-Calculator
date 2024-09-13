import axios from 'axios'

const API_URL = '/api/calculate' // Adjust if necessary, depending on your backend URL

export default {
  // Method to call the calculator endpoint with crop data
  calculate(crop, cropQuantity, seedsPurchased, purchaseLocation, userProvidedSeedPrice = null) {
    // Create request data
    const requestData = {
      crop, // Crop object from front-end input
      cropQuantity,
      seedsPurchased,
      purchaseLocation,
      userProvidedSeedPrice
    }

    // Make POST request to the calculator API
    return axios.post(API_URL, null, {
      params: requestData
    })
  }
}
