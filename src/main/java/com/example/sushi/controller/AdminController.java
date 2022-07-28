package com.example.sushi.controller;

import com.example.sushi.annotation.AdminLoginCheck;
import com.example.sushi.dto.admin.InformationDTO;
import com.example.sushi.dto.admin.MenuDTO;
import com.example.sushi.dto.user.ReservationDTO;
import com.example.sushi.entity.admin.MenuType;
import com.example.sushi.service.AdminService;
import com.example.sushi.service.LoginService;
import com.example.sushi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/sushi/admin")
@RequiredArgsConstructor
public class AdminController implements ErrorController{
    private final UserService service;
    private final AdminService adminService;
    private final LoginService loginService;

    /** 관리자 로그인 관리 */
    @GetMapping("/login")
    public String getAdminLoginPage(Model model,
                                    HttpSession httpSession,
                                    RedirectAttributes redirectAttributes) {
        if (httpSession.getAttribute("adminId") != null) {
            redirectAttributes.addFlashAttribute("msg", "이미 로그인 되어있습니다!");
            return "redirect:/sushi/admin/list";
        }
        else {
            model.addAttribute("noLogin", true);
        }
        return null;
    }

    @PostMapping("/login")
    public String adminLogin(InformationDTO informationDTO,
                             HttpSession httpSession,
                             RedirectAttributes redirectAttributes) {
        if (loginService.amdinLogin(informationDTO)) {
            httpSession.setAttribute("adminId", informationDTO.getAdminId());
            return "redirect:/sushi/admin/list";
        }
        else {
            redirectAttributes.addFlashAttribute("msg", "로그인 정보가 일치하지 않습니다!");
            return "redirect:/sushi/admin/login";
        }


    }

    @AdminLoginCheck
    @GetMapping("/logout")
    public String adminLogout(HttpSession httpSession,
                              RedirectAttributes redirectAttributes) {
        httpSession.setAttribute("adminId", null);
        redirectAttributes.addFlashAttribute("msg", "로그아웃 되었습니다.");
        return "redirect:/sushi/admin/login";

    }

    /** 관리자 예약 관리 */
    @AdminLoginCheck
    @GetMapping("/list")
    public void getAllReservation(Model model) {
        List<ReservationDTO> reservationDTOList = service.getAll();
        model.addAttribute("dtoList", reservationDTOList);
    }

    @AdminLoginCheck
    @GetMapping("/read")
    public void getOneReservation(Long rid, Model model) {
        ReservationDTO dto = service.getOne(rid);
        model.addAttribute("dto", dto);
    }

    @AdminLoginCheck
    @GetMapping("/delete")
    public String removeReservation(Long rid, RedirectAttributes redirectAttributes) {
        service.remove(rid);
        redirectAttributes.addFlashAttribute("msg", "예약이 삭제 되었습니다.");
        return "redirect:/sushi/admin/list";
    }

    /** 관리자 메뉴 관리 */
    @AdminLoginCheck
    @GetMapping("/menu")
    public void getAllMenu(Model model) {
        List<MenuDTO> dtoList = adminService.getAllMenu();
        model.addAttribute("dtoList", dtoList);
        List<MenuType> typeList = adminService.getMenuType();
        model.addAttribute("typeList", typeList);
    }

    @AdminLoginCheck
    @GetMapping("/menu/{menuId}")
    public ResponseEntity<MenuDTO> getOneMenu(@PathVariable Long menuId) {
        MenuDTO menuDTO = adminService.getOneMenu(menuId);
        return new ResponseEntity<>(menuDTO, HttpStatus.OK);
    }

    @AdminLoginCheck
    @PostMapping("/menu")
    public void registerMenu(@RequestBody MenuDTO menuDTO) {
        adminService.registerMenu(menuDTO);
    }

    @AdminLoginCheck
    @PutMapping("/menu")
    public void modifyMenu(@RequestBody MenuDTO menuDTO) {
        adminService.modifyMenu(menuDTO);
    }

    @AdminLoginCheck
    @DeleteMapping("/menu")
    public void removeMenu(@RequestBody MenuDTO menuDTO) {
        adminService.removeMenu(menuDTO.getMenuId());
    }

    /** 관리자 매장 정보 관리 */
    @AdminLoginCheck
    @GetMapping("/information")
    public void getInformation(Model model) {
        InformationDTO dto = adminService.getInformation("sushicaptain");
        model.addAttribute("dto", dto);
    }

    @AdminLoginCheck
    @PutMapping("/information")
    public String modifyInformation(InformationDTO informationDTO,
                                    RedirectAttributes redirectAttributes) {
        adminService.modifyInformation(informationDTO);
        redirectAttributes.addFlashAttribute("msg", "저장되었습니다!");
        return "redirect:/sushi/admin/information";
    }

}