import Vue from "vue";
import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://192.168.73.8:8080"
});

axiosInstance.defaults.headers.common["Authorization"] =
  "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpYW4uY2VzYXIiLCJjcGYiOiIxMTQuNjA0Ljg0Ni03MyIsIm5vbWUiOiJJYW4gQ2VzYXIgVmlkaW5oYSBSZWdvIiwiZnVuY2lvbmFyaW8iOjU1OCwiZXhwIjoxNTkyMTc4MjUzLCJpYXQiOjE1OTIxNjAyNTMsImVtYWlsIjoiaWFuLmNlc2FyQHNwZGF0YS5jb20uYnIifQ._GIXUyfG1o_4ZbwgnF0Srlq1jIA9-fsHbt1H9cj7V3k8DqDQLZ6Bnv13uyabMu6H2fS0u9XkjLBKNImYUuSxQw";

Vue.prototype.$axios = axiosInstance;
