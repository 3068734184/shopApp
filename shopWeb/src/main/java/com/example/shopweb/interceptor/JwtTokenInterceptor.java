package com.example.shopweb.interceptor;

import com.example.shopmodel.properties.JwtProperties;
import com.example.shopmodel.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

@Slf4j
@Component
public class JwtTokenInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     * @param request
     * @param response
     * @param handler
     * @return
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //判断拦截到的是controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，放行
            return true;
        }
        try {
            //从请求头中获取令牌
            String token = request.getHeader(jwtProperties.getTokenName());
            //校验令牌
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(), token);
            Long userId = Long.valueOf(claims.get("userId").toString());
            log.info("当前用户id:{}", userId);
//            BaseContext.setCurrentId(userId);
            //通过放行
            return true;
        } catch (Exception e) {
            //不通过，响应401
            response.setStatus(401);
            return false;
        }
    }

}
