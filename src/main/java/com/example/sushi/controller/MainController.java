package com.example.sushi.controller;

import com.example.sushi.dto.admin.InformationDTO;
import com.example.sushi.dto.admin.MenuDTO;
import com.example.sushi.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {
    @Value("${adminid}")
    private String adminId;

    private final AdminService adminService;

    @GetMapping("/")
    public String getMainPage(Model model) {
        List<MenuDTO> menuList = adminService.getAllMenu();
        model.addAttribute("menuList", menuList);
        InformationDTO information = adminService.getInformation(adminId);
        model.addAttribute("info", information);
        return "main";
    }
}
