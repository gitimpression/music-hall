package zone.ctp.musichall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Pattern;

/**
 * @author ctp
 * @date 2023/5/1 8:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @Pattern(regexp = "[0-9A-Za-z]{5,18}", message = "账号长度在5到18为之间，由字母和数字组成")
    private String account;
    @Pattern(regexp = "[0-9A-Za-z]{5,18}", message = "密码长度在5到18为之间，由字母和数字组成")
    private String password;
}
