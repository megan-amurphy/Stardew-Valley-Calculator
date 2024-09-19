import axios from 'axios'

const API_URL = 'http://localhost:8080/api/calculate' // Adjust if necessary, depending on your backend URL

export default {
  // Method to call the calculator endpoint with crop data
  calculate(crops, cropQuantities, seeds, seedQuantities) {
    // Create request data
    const requestData = {
      crops, // Crop object from front-end input
      cropQuantities,
      seeds,
      seedQuantities
    }

    // Make POST request to the calculator API
    return axios.post(API_URL, null, {
      body: requestData
    })
  }
}
