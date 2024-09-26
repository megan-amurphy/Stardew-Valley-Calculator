import axios from 'axios'

const API_URL = 'http://localhost:8080/api/calculate'

export default {
  calculate(cropEntries) {
    const requestData = {
      crops: cropEntries.map((entry) => ({
        cropName: entry.cropName,
        cropQuality: entry.quality,
        price: null
      })),
      seeds: cropEntries.flatMap((entry) =>
        entry.seedPurchases.map((seed) => ({
          seedName: seed.seedName || 'Seed',
          purchaseLocationName: seed.purchaseLocation,
          isOutOfSeason: seed.isOutOfSeason
        }))
      ),
      cropQuantities: cropEntries.map((entry) => entry.quantity),
      seedQuantities: cropEntries.map((entry) =>
        entry.seedPurchases.reduce((sum, seed) => sum + seed.quantity, 0)
      ) // Summing the seed quantities per crop
    }

    return axios.post(API_URL, requestData)
  }
}
