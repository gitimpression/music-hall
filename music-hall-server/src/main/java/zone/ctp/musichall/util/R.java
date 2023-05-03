package zone.ctp.musichall.util;

import lombok.Data;

/**
 * 统一响应类
 * @author ctp
 * @date 2023/5/1 8:50
 */
@Data
public class R<T> {
    private Boolean ok;
    private Integer code;
    private String msg;
    private T data;

    /**
     * 成功
     */
    public static <T> R<T> ok() {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.SUCCESS.getOk());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMsg(ResultCodeEnum.SUCCESS.getMsg());
        return r;
    }

    public static <T> R<T> ok(int code) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.SUCCESS.getOk());
        r.setCode(code);
        r.setMsg(ResultCodeEnum.SUCCESS.getMsg());
        return r;
    }

    public static <T> R<T> ok(String msg) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.SUCCESS.getOk());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMsg(msg);
        return r;
    }

    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.SUCCESS.getOk());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMsg(ResultCodeEnum.SUCCESS.getMsg());
        r.setData(data);
        return r;
    }

    public static <T> R<T> ok(int code, String msg) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.SUCCESS.getOk());
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static <T> R<T> ok(int code, T data) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.SUCCESS.getOk());
        r.setCode(code);
        r.setData(data);
        return r;
    }

    public static <T> R<T> ok(String msg, T data) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.SUCCESS.getOk());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static <T> R<T> ok(int code, String msg, T data) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.SUCCESS.getOk());
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static <T> R<T> ok(ResultCodeEnum resultCodeEnum) {
        R<T> r = new R<>();
        r.setOk(resultCodeEnum.getOk());
        r.setCode(resultCodeEnum.getCode());
        r.setMsg(resultCodeEnum.getMsg());
        return r;
    }

    /**
     * 失败
     */
    public static <T> R<T> fail() {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.FAIL.getOk());
        r.setCode(ResultCodeEnum.FAIL.getCode());
        r.setMsg(ResultCodeEnum.FAIL.getMsg());
        return r;
    }

    public static <T> R<T> fail(int code) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.FAIL.getOk());
        r.setCode(code);
        r.setMsg(ResultCodeEnum.FAIL.getMsg());
        return r;
    }

    public static <T> R<T> fail(String msg) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.FAIL.getOk());
        r.setCode(ResultCodeEnum.FAIL.getCode());
        r.setMsg(msg);
        return r;
    }

    public static <T> R<T> fail(T data) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.FAIL.getOk());
        r.setCode(ResultCodeEnum.FAIL.getCode());
        r.setMsg(ResultCodeEnum.FAIL.getMsg());
        r.setData(data);
        return r;
    }

    public static <T> R<T> fail(int code, String msg) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.FAIL.getOk());
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static <T> R<T> fail(int code, T data) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.FAIL.getOk());
        r.setCode(code);
        r.setMsg(ResultCodeEnum.FAIL.getMsg());
        r.setData(data);
        return r;
    }

    public static <T> R<T> fail(String msg, T data) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.FAIL.getOk());
        r.setCode(ResultCodeEnum.FAIL.getCode());
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static <T> R<T> fail(int code, String msg, T data) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.FAIL.getOk());
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static <T> R<T> fail(ResultCodeEnum resultCodeEnum) {
        R<T> r = new R<>();
        r.setOk(resultCodeEnum.getOk());
        r.setCode(resultCodeEnum.getCode());
        r.setMsg(resultCodeEnum.getMsg());
        return r;
    }

    public static <T> R<T> fail(ResultCodeEnum resultCodeEnum, T data) {
        R<T> r = new R<>();
        r.setOk(resultCodeEnum.getOk());
        r.setCode(resultCodeEnum.getCode());
        r.setMsg(resultCodeEnum.getMsg());
        r.setData(data);
        return r;
    }

    /**
     * 自定义
     */
    public static <T> R<T> of(boolean ok) {
        R<T> r = new R<>();
        r.setOk(ok);
        int code = ok ? ResultCodeEnum.SUCCESS.getCode() : ResultCodeEnum.FAIL.getCode();
        r.setCode(code);
        String msg = ok ? ResultCodeEnum.SUCCESS.getMsg() : ResultCodeEnum.FAIL.getMsg();
        r.setMsg(msg);
        return r;
    }
    public static <T> R<T> of(boolean ok, int code) {
        R<T> r = new R<>();
        r.setOk(ok);
        r.setCode(code);
        String msg = ok ? ResultCodeEnum.SUCCESS.getMsg() : ResultCodeEnum.FAIL.getMsg();
        r.setMsg(msg);
        return r;
    }
    public static <T> R<T> of(boolean ok, int code, String msg) {
        R<T> r = new R<>();
        r.setOk(ok);
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
    public static <T> R<T> of(boolean ok, int code, String msg, T data) {
        R<T> r = new R<>();
        r.setOk(ok);
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    /**
     * 获取信息
     */
    public boolean isOk(){
        return this.getOk();
    }
    public boolean isFail(){
        return !this.getOk();
    }

    /**
     * 务器发生错误
     */
    public static <T> R<T> error() {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.SERVER_ERROR.getOk());
        r.setCode(ResultCodeEnum.SERVER_ERROR.getCode());
        r.setMsg(ResultCodeEnum.SERVER_ERROR.getMsg());
        return r;
    }

    public static <T> R<T> error(int code) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.SERVER_ERROR.getOk());
        r.setCode(code);
        r.setMsg(ResultCodeEnum.SERVER_ERROR.getMsg());
        return r;
    }

    public static <T> R<T> error(String msg) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.SERVER_ERROR.getOk());
        r.setCode(ResultCodeEnum.SERVER_ERROR.getCode());
        r.setMsg(msg);
        return r;
    }

    public static <T> R<T> error(T data) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.SERVER_ERROR.getOk());
        r.setCode(ResultCodeEnum.SERVER_ERROR.getCode());
        r.setMsg(ResultCodeEnum.SERVER_ERROR.getMsg());
        r.setData(data);
        return r;
    }

    public static <T> R<T> error(int code, String msg) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.SERVER_ERROR.getOk());
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static <T> R<T> error(int code, T data) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.SERVER_ERROR.getOk());
        r.setCode(code);
        r.setMsg(ResultCodeEnum.SERVER_ERROR.getMsg());
        r.setData(data);
        return r;
    }

    public static <T> R<T> error(String msg, T data) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.SERVER_ERROR.getOk());
        r.setCode(ResultCodeEnum.SERVER_ERROR.getCode());
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static <T> R<T> error(int code, String msg, T data) {
        R<T> r = new R<>();
        r.setOk(ResultCodeEnum.SERVER_ERROR.getOk());
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static <T> R<T> error(ResultCodeEnum resultCodeEnum) {
        R<T> r = new R<>();
        r.setOk(resultCodeEnum.getOk());
        r.setCode(resultCodeEnum.getCode());
        r.setMsg(resultCodeEnum.getMsg());
        return r;
    }

    public static <T> R<T> error(ResultCodeEnum resultCodeEnum, T data) {
        R<T> r = new R<>();
        r.setOk(resultCodeEnum.getOk());
        r.setCode(resultCodeEnum.getCode());
        r.setMsg(resultCodeEnum.getMsg());
        r.setData(data);
        return r;
    }

}
