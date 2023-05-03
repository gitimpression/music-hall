package zone.ctp.musichall.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import zone.ctp.musichall.entity.User;

/**
 * @author ctp
 * @date 2023/5/1 9:35
 */
@Mapper
public interface UserMapper {

    User selectOne(@Param("user")User user);

}
