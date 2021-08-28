import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import Homeview from "../views/Homeview";
import Loginview from "../views/Loginview";
import Bookview from "../views/Bookview";
import Cartview from "../views/Cartview";
import Cart from "../components/Counter/Cart";
import Order from "../components/Counter/Order";
import Complete from "../components/Counter/Complete";
import Account from "../components/Account";
import LikeList from "../components/Account/LikeList";
import OrderList from "../components/Account/OrderList";
import Balance from "../components/Account/Balance";
import Information from "../components/Account/Information";
import UserManagement from "../components/Admin/UserManagement";
import BookManagement from "../components/Admin/BookManagement";
import AddBook from "../components/Admin/AddBook";
import Statics from "../components/Admin/Statics";
import myStatics from "../components/Account/Statics";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path:'/',
      redirect:"/login",
      name:"mainpage"
    },
    {
      path: '/home',
      name: 'home',
      component: Homeview,
    },
    {
      path: '/login',
      name: 'login',
      component: Loginview
    },
    {
      path:'/bookdetails',
      name:'bookdetails',
      component: Bookview,
    },
    {
      path:'/counter',
      name:'counter',
      component: Cartview,
      children:[
        {
          path:'/counter/cart',
          name:'cart',
          component: Cart,
        },
        {
          path:'/counter/order',
          name:'order',
          component: Order,
        },
        {
          path:'/counter/complete',
          name:'complete',
          component: Complete,
        }
      ]
    },
    {
      path:'/account',
      name:'account',
      component: Account,
      children:[
        {
          path: "/account/information",
          name: "information",
          component: Information,
        },
        {
          path: "/account/likelist",
          name: "likelist",
          component: LikeList
        },
        {
          path: "/account/orderlist",
          name: "orderlist",
          component: OrderList
        },
        {
          path: "/account/balance",
          name: "balance",
          component: Balance
        },
        {
          path: "/account/userManagement",
          name: "userManagement",
          component: UserManagement
        },
        {
          path: "/account/bookManagement",
          name: "bookManagement",
          component: BookManagement
        },
        {
          path: "/account/addBook",
          name: "addBook",
          component: AddBook
        },
        {
          path: "/account/statics",
          name: "statics",
          component: Statics,
        },
        {
          path: "/account/myStatics",
          name: "myStatics",
          component: myStatics,
        }
      ]
    }
  ],
  scrollBehavior(to, from,savedPosition) {
    return {
      x: 0,
      y: 0
    }
  }
})
