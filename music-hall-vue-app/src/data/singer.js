// 获取歌手照片
export const getSingerImg = (name) => {
  switch (name) {
    case "G.E.M. 邓紫棋":
      return "https://imgessl.kugou.com/stdmusic/20220711/20220711074007533569.jpg";
    case "BEYOND":
      return "https://imgessl.kugou.com/stdmusic/20200620/20200620071029104961.jpg";
    case "苏打绿":
      return "https://imgessl.kugou.com/uploadpic/softhead/400/20210103/20210103100705627.jpg";
    default:
      return "http://img95.699pic.com/element/40093/8554.png_860.png";
  }
};
