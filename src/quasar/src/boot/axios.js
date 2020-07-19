import Vue from "vue";
import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://192.168.73.75:8080"
});

axiosInstance.defaults.headers.common["Authorization"] =
  "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZUB0ZXN0ZS5jb20iLCJuYW1lIjoiVGVzdGUiLCJpZCI6MSwiZXhwIjoxNTk1MTQ2NTU5LCJpYXQiOjE1OTUxMjg1NTl9.AsO9uxi02hGG9opowzdByod7jHzCKRRDdx-Mtj3AL63RWUFqnoD1nzFy1iN51U-etT_9AreIPh1mIpxeSqCjsw";

Vue.prototype.$axios = axiosInstance;
