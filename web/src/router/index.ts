import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import Doc from '../views/doc.vue'
import UserAdmin from "@/views/admin/UserAdmin.vue";
import EbookAdmin from '../views/admin/ebookAdmin.vue'
import CategoryAdmin from '../views/admin/categoryAdmin.vue'
import DocAdmin from "@/views/admin/DocAdmin.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/doc',
    name: 'Doc',
    component: Doc
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
  {
    path: '/admin/doc',
    name: 'DocAdmin',
    component: DocAdmin
  },
  {
    path: '/admin/user',
    name: 'UserAdmin',
    component: UserAdmin
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
