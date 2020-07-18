import Vue from "vue";
import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://192.168.73.75:8080"
});

axiosInstance.defaults.headers.common["Authorization"] =
  "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZUB0ZXN0ZS5jb20iLCJuYW1lIjoiVGVzdGUiLCJpZCI6MSwiZXhwIjoxNTk1MTI4NTI5LCJpYXQiOjE1OTUxMTA1Mjl9.9zy3VcNjFsOB00fioNwcZuqREmxa3h5-DcyjdWDzxxec5KGtbGOVe6G5okmnuUpYsE3pkYYeWCBsDt24FoqZUw";

Vue.prototype.$axios = axiosInstance;
