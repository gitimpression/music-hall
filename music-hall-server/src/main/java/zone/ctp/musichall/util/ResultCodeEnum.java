package zone.ctp.musichall.util;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    /**
     * 通用成功
     */
    SUCCESS(true,200,"success"),

    /**
     * 通用失败
     */
    FAIL(false,400,"fail"),

    /**
     * 通用服务器错误
     */
    SERVER_ERROR(false,500,"server error");

    // 响应是否成功
    private final Boolean ok;
    // 响应状态码
    private final Integer code;
    // 响应信息
    private final String msg;

    ResultCodeEnum(boolean ok, Integer code, String msg) {
        this.ok = ok;
        this.code = code;
        this.msg = msg;
    }

}
