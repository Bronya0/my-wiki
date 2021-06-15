import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import EbookAdmin from '../views/admin/ebookAdmin.vue'
import CategoryAdmin from '../views/admin/categoryAdmin.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/admin/ebook',
    name: 'EbookAdmin',
    component: EbookAdmin
  },
  {
    path: '/admin/category',
    name: 'CategoryAdmin',
    component: CategoryAdmin
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
