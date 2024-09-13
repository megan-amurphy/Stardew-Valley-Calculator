import axios from 'axios'

const API_URL = '/api/crops'

export default {
  // Fetch all crops
  getAllCrops() {
    return axios.get(API_URL)
  },

  // Fetch seed by crop name and purchase location
  getSeedByCropAndLocation(cropName, purchaseLocation, isOutOfSeason) {
    return axios.get(`${API_URL}/seed`, {
      params: {
        cropName: cropName,
        purchaseLocation: purchaseLocation,
        isOutOfSeason: isOutOfSeason
      }
    })
  }
}
