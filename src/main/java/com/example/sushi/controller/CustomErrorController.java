package com.example.sushi.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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

        model.addAttribute("code", status.toString());
        model.addAttribute("msg", msg.toString());
        model.addAttribute("timestamp", new Date());

        if (status.equals(401)) {
            redirectAttributes.addFlashAttribute("msg", "로그인이 필요한 서비스입니다.");
            return "redirect:" + msg.toString();
        }
        if (status.equals(601)) {
            redirectAttributes.addFlashAttribute("msg", "이미 등록된 일자입니다.");
            return "redirect:" + msg.toString();
        }
        if (status.equals(602)) {
            redirectAttributes.addFlashAttribute("msg", "이미 등록된 시간입니다.");
            return "redirect:" + msg.toString();
        }

        return "sushi/admin/error";
    }

}
