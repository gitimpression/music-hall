package zone.ctp.musichall.controller;

import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zone.ctp.musichall.config.JwtProperties;
import zone.ctp.musichall.dto.LoginDTO;
import zone.ctp.musichall.service.UserService;
import zone.ctp.musichall.util.JwtUtils;
import zone.ctp.musichall.util.R;
import javax.annotation.Resource;

/**
 * @author ctp
 * @date 2023/5/1 8:27
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public R<String> login(@RequestBody @Validated LoginDTO loginDTO){
        String account = loginDTO.getAccount();
        String password = loginDTO.getPassword();
        return userService.login(account, password);
    }

    @GetMapping("/checkToken")
    public R<String> checkToken(@RequestHeader(value = JwtProperties.TOKEN_KEY, required = false) String token){
        if (!StringUtils.hasLength(token)){
            return R.fail();
        }
        try{
            JwtUtils.parseJWT(token);
            return R.ok();
        }catch (Exception e){
            return R.fail();
        }
    }
}
