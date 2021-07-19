import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import Doc from '../views/doc.vue'
import store from "@/store";
import {Tool} from "@/util/tool";

const DocAdmin = () => import("@/views/admin/DocAdmin.vue");
const statistic = () => import("@/views/statistic.vue");
const UserAdmin = () => import("@/views/admin/UserAdmin.vue");
const EbookAdmin = () => import("../views/admin/ebookAdmin.vue");
const CategoryAdmin = () => import("../views/admin/categoryAdmin.vue");




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
    component: EbookAdmin,
    meta: {
      loginRequire: true
    }
  },
  {
    path: '/admin/category',
    name: 'CategoryAdmin',
    component: CategoryAdmin,
    meta: {
      loginRequire: true
    }
  },
  {
    path: '/admin/doc',
    name: 'DocAdmin',
    component: DocAdmin,
    meta: {
      loginRequire: true
    }
  },
  {
    path: '/admin/user',
    name: 'UserAdmin',
    component: UserAdmin,
    meta: {
      loginRequire: true
    }
  },
  {
    path: '/statistic',
    name: 'Statistic',
    component: statistic,
    meta: {
      loginRequire: true
    }
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// 路由登录拦截
router.beforeEach((to, from, next) => {
  // 要不要对meta.loginRequire属性做监控拦截
  if (to.matched.some(function (item) {
    console.log(item, "是否需要登录校验：", item.meta.loginRequire);
    return item.meta.loginRequire
  })) {
    const loginUser = store.state.user;
    if (Tool.isEmpty(loginUser)) {
      console.log("用户未登录！");
      next('/');
    } else {
      next();
    }
  } else {
    next();
  }
});

export default router
