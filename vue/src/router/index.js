import { createRouter, createWebHistory } from 'vue-router'
import CropSelectionView from '../views/CropSelectionView.vue'
import ResultsView from '../views/ResultsView.vue'

const routes = [
  {
    path: '/',
    name: 'cropinput',
    component: CropSelectionView
  },
  {
    path: '/results',
    name: 'results',
    component: ResultsView
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
