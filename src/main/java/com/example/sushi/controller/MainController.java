package com.example.sushi.controller;

import com.example.sushi.dto.admin.InformationDTO;
import com.example.sushi.dto.admin.MenuDTO;
import com.example.sushi.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final AdminService adminService;

    @GetMapping("/")
    public ModelAndView getMainPage() {
        ModelAndView mav = new ModelAndView("sushi/main");
        List<MenuDTO> menuList = adminService.getAllMenu();
        mav.addObject("menuList", menuList);
        InformationDTO information = adminService.getInformation("sushicaptain");
        mav.addObject("info", information);
        return mav;
    }
}
