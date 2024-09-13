import { createRouter, createWebHistory } from 'vue-router'
import CropSelectionView from '../views/CropSelectionView.vue'
import ResultsView from '@/views/ResultsView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'cropinput',
      component: CropSelectionView
    },
    {
      path: '/results',
      name: 'results',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/ResultsView.vue')
    }
  ]
})

export default router
