package com.xiao.filter;
import com.alibaba.fastjson.JSON;
import com.xiao.domain.ResponseResult;
import com.xiao.domain.entity.LoginUser;
import com.xiao.enums.AppHttpCodeEnum;
import com.xiao.utils.JwtUtil;
import com.xiao.utils.RedisCache;
import com.xiao.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
//博客前台的登录认证过滤器。OncePerRequestFilter是springsecurity提供的类
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    //RedisCache是我们在huanf-framework工程写的工具类，用于操作redis
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //获取请求头中的token值
        String token = request.getHeader("token");
        //判断上面那行有没有拿到token值
        if(!StringUtils.hasText(token)){
            //说明该接口不需要登录，直接放行，不拦截
            filterChain.doFilter(request,response);
            return;
        }
        //JwtUtil是我们在huanf-framework工程写的工具类。解析获取的token，把原来的密文解析为原文
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            //当token过期或token被篡改就会进入下面那行的异常处理
            e.printStackTrace();
            //报异常之后，把异常响应给前端，需要重新登录。ResponseResult、AppHttpCodeEnum、WebUtils是我们在huanf-framework工程写的类
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        String userid = claims.getSubject();

        //在redis中，通过key来获取value，注意key我们是加过前缀的，取的时候也要加上前缀
        LoginUser loginUser = redisCache.getCacheObject("bloglogin:" + userid);
        //如果在redis获取不到值，说明登录是过期了，需要重新登录
        if(Objects.isNull(loginUser)){
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }

        //把从redis获取到的value，存入到SecurityContextHolder(Security官方提供的类)
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);

    }
}