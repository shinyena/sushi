package com.example.sushi.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class LoginCheckAspect {
    @Before("@annotation(com.example.sushi.annotation.AdminLoginCheck)")
    public void adminLoginCheck() throws Throwable{
        HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        if (session.getAttribute("adminId") == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "/sushi/admin/login");
        }
    }

    @Before("@annotation(com.example.sushi.annotation.UserLoginCheck)")
    public void userLoginCheck() throws Throwable{
        HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        if (session.getAttribute("userId") == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "/");
        }
    }
}
