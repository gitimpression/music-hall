import { get } from "@/request/http";

// 拉取歌曲列表
export const musicList = () => get("/music/list");

// 拉取歌曲列表
export const musicHallCDN = "http://cdn.ctp.zone/music-hall/music/";
