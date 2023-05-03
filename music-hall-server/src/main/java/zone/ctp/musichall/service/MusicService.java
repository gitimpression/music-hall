package zone.ctp.musichall.service;

import zone.ctp.musichall.vo.MusicVO;
import java.util.List;

/**
 * @author ctp
 * @date 2023/5/3 4:34
 */
public interface MusicService {

    /**
     * 列出歌单
     */
    List<MusicVO> list();

    /**
     * 指定目录文音乐件数据插入到数据库
     */
    void init();
}
