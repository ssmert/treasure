package com.jakers.mustneed.core.exception.handler;

import com.jakers.mustneed.core.enums.Errors;
import com.jakers.mustneed.core.exception.BaseRuntimeException;
import java.util.Map;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

/**
 * 전역 오류 속성 설정
 */
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest,
        ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        Throwable thrown = getError(webRequest);

        if (null == thrown) {
            errorAttributes.put("code", Errors.INTERNAL_SERVER_ERROR.getCode());
            errorAttributes.put("message", errorAttributes.get("error"));
        }

        // 발생된 예외객체를 구하여 의미있는 메세지 재설정
        if (thrown instanceof BaseRuntimeException) {
            BaseRuntimeException bre = (BaseRuntimeException) thrown;
            errorAttributes.put("status", bre.getErrorStatus());
            errorAttributes.put("code", bre.getErrorCode());
            errorAttributes.put("message", bre.getErrorMessage());
        }

        return errorAttributes;
    }
}
