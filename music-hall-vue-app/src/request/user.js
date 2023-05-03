import { get, post } from "@/request/http";

// 登录
export const login = (p) => post("/user/login", p);

// 验证token
export const checkToken = () => get("/user/checkToken");
