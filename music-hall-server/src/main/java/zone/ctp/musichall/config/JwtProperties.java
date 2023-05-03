package zone.ctp.musichall.config;

import lombok.Data;

/**
 * @author ctp
 * @date 2023/5/1 8:32
 */
@Data
public class JwtProperties {
    // 密钥
    public static final String SECRET = "f1sf56!@26456$52&6d1cxVdg2hR";
    // 签名算法 HS256,HS384,HS512,RS256,RS384,RS512,ES256,ES384,ES512,PS256,PS384,PS512
    public static final String ALG = "HS256";
    // jwt签发者
    public static final String ISS = "music-hall";
    // jwt过期时间 单位：ms
    public static final Integer EXP = 60 * 60 * 1000 * 24; // 24小时
    // jwt接收者
    public static final String AUD = "music-hall-share";
    // header存放token的key
    public static final String TOKEN_KEY = "token";
}
