import axios from "axios";

// 请求超时时间
axios.defaults.timeout = 10000 * 5;
// 自定义post请求头
axios.defaults.headers.post["Content-Type"] = "application/json";
// 请求基础URL
axios.defaults.baseURL = "/api";
// axios.defaults.baseURL = "http://49.234.225.78:8090/";

// 请求拦截器
axios.interceptors.request.use(
  (config) => {
    // 自定义请求头
    if (localStorage.getItem("token")) {
      config.headers.token = localStorage.getItem("token");
    }
    return config;
  },
  (error) => {
    // 请求错误时
    console.log(error); // 打印错误信息
    return Promise.reject(error);
  }
);

// 响应拦截器
axios.interceptors.response.use(
  (response) => {
    if (response.status === 200) {
      // 接口HTTP状态码为200时
      return Promise.resolve(response);
    }
  },
  // HTTP状态码非200的情况
  (error) => {
    if (error.response.status) {
      switch (error.response.status) {
        case 500:
          console.log("服务发生错误");
          break;
        case 401:
          console.log("无权限");
          break;
        case 404:
          console.log("当前接口不存在");
          break;
        default:
          console.log(error.response.message);
          return Promise.reject(error.response);
      }
    }
  }
);

/**
 * params: a=1
 * info：路径参数 {id}
 */
export function get(url, params, info = "") {
  return new Promise((resolve, reject) => {
    axios
      .get(url + info, {
        params: params,
      })
      .then((res) => {
        resolve(res.data);
      })
      .catch((err) => {
        reject(err.data);
      });
  });
}

export function post(url, data = {}, isJson = true) {
  return new Promise((resolve, reject) => {
    let newData = data;
    if (!isJson) {
      //  转formData格式
      newData = new FormData();
      newData.append("file", data);
      axios
        .post(url, newData, {
          headers: { "Content-Type": "multipart/form-data;charset=utf-8" },
        })
        .then((res) => {
          resolve(res.data);
        })
        .catch((err) => {
          reject(err.data);
        });
    } else {
      axios
        .post(url, data)
        .then((res) => {
          resolve(res.data);
        })
        .catch((err) => {
          reject(err.data);
        });
    }
  });
}

export function put(url, params = {}, info = "") {
  return new Promise((resolve, reject) => {
    axios.put(url + info, params).then(
      (res) => {
        resolve(res.data);
      },
      (err) => {
        reject(err.data);
      }
    );
  });
}

export function _delete(url, params = {}, info = "") {
  return new Promise((resolve, reject) => {
    axios
      .delete(url + info, {
        params: params,
      })
      .then((res) => {
        resolve(res.data);
      })
      .catch((err) => {
        reject(err.data);
      });
  });
}
