import Vue from "vue";
import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://192.168.73.75:8080"
});

axiosInstance.defaults.headers.common["Authorization"] =
  "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZUB0ZXN0ZS5jb20iLCJuYW1lIjoiVGVzdGUiLCJpZCI6MSwiZXhwIjoxNTk0OTE3MDcxLCJpYXQiOjE1OTQ4OTkwNzF9.0CXg31BVbaUunI9E-XqxrtUI3G0llntIJGw00-ksU9YylEPy2IpBbp_7mvE3XovRw4iA_7xLyl115TLnFJshIw";

Vue.prototype.$axios = axiosInstance;
