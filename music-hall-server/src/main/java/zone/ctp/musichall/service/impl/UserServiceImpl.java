package zone.ctp.musichall.service.impl;

import org.springframework.stereotype.Service;
import zone.ctp.musichall.entity.User;
import zone.ctp.musichall.mapper.UserMapper;
import zone.ctp.musichall.service.UserService;
import zone.ctp.musichall.util.JwtUtils;
import zone.ctp.musichall.util.R;
import zone.ctp.musichall.vo.UserVO;
import javax.annotation.Resource;

/**
 * @author ctp
 * @date 2023/5/1 9:49
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public R<String> login(String account, String password) {
        User user = User.builder().account(account).password(password).build();
        User loginUser = userMapper.selectOne(user);
        if (loginUser != null){
            UserVO userVO = UserVO.builder().account(account).build();
            String token = JwtUtils.createJWT(userVO.toString());
            return R.ok("登录成功", token);
        }else{
            return R.fail("登录失败");
        }
    }
}
