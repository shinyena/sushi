package com.example.sushi.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Log4j2
@Controller
public class CustomErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping("/error")
    public String getErrorPage(HttpServletRequest request, Model model,
                               RedirectAttributes redirectAttributes) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object msg = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        String err_code = status.toString();
        String err_msg = msg.toString();
        Date err_date = new Date();

        model.addAttribute("code", err_code);
        model.addAttribute("msg", err_msg);
        model.addAttribute("timestamp", err_date);

        if (status.equals(401)) {
            redirectAttributes.addFlashAttribute("msg", "로그인이 필요한 서비스입니다.");
            return "redirect:" + msg.toString();
        }
        if (status.equals(403)) {
            redirectAttributes.addFlashAttribute("msg", "관리자만 접근이 가능합니다.");
            return "redirect:/";
        }
        if (status.equals(601)) {
            redirectAttributes.addFlashAttribute("msg", "이미 등록된 일자입니다.");
            return "redirect:" + msg.toString();
        }
        if (status.equals(602)) {
            redirectAttributes.addFlashAttribute("msg", "이미 등록된 시간입니다.");
            return "redirect:" + msg.toString();
        }
        return "error";
    }

}
