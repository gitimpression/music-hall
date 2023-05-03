import router from "./index.js";
import { checkToken } from "@/request/user";

// 路由全局前置守卫
router.beforeEach((to, from, next) => {
  // 如果在mate中有requiresAuth 则返回true
  if (to.matched.some((m) => m.meta.requireAuth)) {
    // 校验token通过后可以访问
    checkToken().then((res) => {
      if (!res.ok) {
        // 没有登录
        console.log("请先登录");
        next({
          path: "/login",
        });
      } else {
        next();
      }
    });
  } else {
    if (to.path == "/login") {
      checkToken()
        .then((res) => {
          if (!res.ok) {
            // 没有登录
            next();
          } else {
            next({
              path: "/",
            });
          }
        })
        .catch(() => {
          next({
            path: "/error",
          });
        });
    }
    next();
  }
});
