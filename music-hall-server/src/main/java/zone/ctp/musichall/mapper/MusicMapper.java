package zone.ctp.musichall.mapper;

import org.apache.ibatis.annotations.Param;
import zone.ctp.musichall.entity.Music;
import zone.ctp.musichall.vo.MusicVO;

import java.util.List;

/**
 * @author ctp
 * @date 2023/5/3 6:01
 */
public interface MusicMapper {

    /**
     * 查询所有
     */
    List<MusicVO> list();

    /**
     * 插入数据
     */
    int insert(@Param("music") Music music);

    /**
     * 批量插入数据
     */
    int batchInsert(@Param("musicList") List<Music> musicList);
}
