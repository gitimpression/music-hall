package zone.ctp.musichall.advice;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zone.ctp.musichall.util.R;

/**
 * 全局异常处理
 * @author ctp
 * @date 2023/5/1 10:00
 */
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * 处理 实体参数 校验失败
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R<String> handleValidException(MethodArgumentNotValidException e){
        // 错误对象
        ObjectError errorObject = e.getBindingResult().getAllErrors().get(0);
        // 错误信息
        String errorMessage = errorObject.getDefaultMessage();
        // 返回校验失败信息
        return R.fail(errorMessage);
    }
}
