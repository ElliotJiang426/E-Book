// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import $ from 'jquery'
import './assets/css/bootstrap.min.css'
import './assets/js/bootstrap.min'
import Client from "./components/Client";
import Books from "./components/Books";
import axios from 'axios';
import qs from 'qs';

Vue.prototype.$axios = axios;
Vue.prototype.$qs = qs;
Vue.prototype.$client = Client;
Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.use($);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
