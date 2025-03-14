package com.example.ChoVe.config;

import com.example.ChoVe.entity.Account;
import com.example.ChoVe.exception.AuthenException;
import com.example.ChoVe.service.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;

@Component // de bien thang nay thanh 1 thu vien va khi muon su dung chi can autowired
public class Filter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // kiem tra truoc khi cho phep truy cap vao controller
        // check xem api ma nguoi dung yeu cau co phai la 1 public api ko ( có phải là 1 public api hay ko , ai cũng dùng đc )
        boolean isPublicAPI = checkIsPublicAPI(request.getRequestURI());
        if (isPublicAPI) {
            filterChain.doFilter(request, response); // cho phep truy cap luon
        } else {
            // neu ko phai public api -> kiem tra token
            String token = getToken(request);
            if (token == null){
                //ko co token -> tra ve loi
                handlerExceptionResolver.resolveException(request,response,null, new AuthenException("Empty Token"));
                return;
            }
            //co token -> kiem tra token
            // check token

            // neu token hop le -> cho phep truy cap
            // neu token ko hop le -> tra ve loi
            Account account ;
            try{
               account = tokenService.getAccountByToken(token);

            }catch (ExpiredJwtException e){
                //token het han
                handlerExceptionResolver.resolveException(request,response,null, new AuthenException("Expired Token"));
                return;
        }catch (MalformedJwtException e){
                //toke sai
                handlerExceptionResolver.resolveException(request,response,null, new AuthenException("Invalid Token"));
            return;
        }

            //toke chuan thi cho phep truy cap
            //luu lai thong tin account vao security context
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    account,
                    token,
                    account.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            //cho phep truy cap
            filterChain.doFilter(request, response);
        }
    }

    //ham nay dung de lay cai token tu FE gui len
//    private String getToken(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        if (token != null && token.startsWith("Bearer ")) {
//            return token.replace("Bearer ", "");
//        }
//        return null;
//    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null )return null;
        return token.substring(7);
    }

    private final List<String> AUTH_PERMISSION = List.of(
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/api/login",
            "/api/register",
            "/api/forgot-password"
    );

    public boolean checkIsPublicAPI(String uri) {
        // check xem api user that user request allow who can access ( có phải là 1 public api hay ko , ai cũng dùng đc )
        //Nếu gặp những API như list trên -> cho phép truy cập luôn
        //ngược lại , phân quyền ( authorization) , check token
        AntPathMatcher pathMatcher = new AntPathMatcher();// check pattern vs uri người dùng truy
        return AUTH_PERMISSION.stream().anyMatch(pattern ->  pathMatcher.match(pattern, uri));// nếu match -> true ; else -> false

    }
}
