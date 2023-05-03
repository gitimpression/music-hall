package zone.ctp.musichall.service;

import zone.ctp.musichall.util.R;

/**
 * @author ctp
 * @date 2023/5/1 9:48
 */
public interface UserService {
    R<String> login(String account, String password);
}
