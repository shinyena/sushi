package com.example.sushi.controller;

import com.example.sushi.annotation.LoginCheck;
import com.example.sushi.dto.admin.InformationDTO;
import com.example.sushi.dto.admin.MenuDTO;
import com.example.sushi.dto.user.ReservationDTO;
import com.example.sushi.entity.admin.MenuType;
import com.example.sushi.service.AdminService;
import com.example.sushi.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ReservationService service;
    private final AdminService adminService;

    /** 관리자 예약 관리 */
    @LoginCheck
    @GetMapping("/list")
    public void getAllReservation(Model model) {
        List<ReservationDTO> reservationDTOList = service.getAll();
        model.addAttribute("dtoList", reservationDTOList);
    }

    @LoginCheck
    @GetMapping("/read")
    public void getOneReservation(Long rid, Model model) {
        ReservationDTO dto = service.getOne(rid);
        model.addAttribute("dto", dto);
    }

    @LoginCheck
    @GetMapping("/delete")
    public String removeReservation(Long rid, RedirectAttributes redirectAttributes) {
        service.remove(rid);
        redirectAttributes.addFlashAttribute("msg", "예약이 삭제 되었습니다.");
        return "redirect:/admin/list";
    }

    /** 관리자 메뉴 관리 */
    @LoginCheck
    @GetMapping("/menu")
    public void getAllMenu(Model model) {
        List<MenuDTO> dtoList = adminService.getAllMenu();
        model.addAttribute("dtoList", dtoList);
        List<MenuType> typeList = adminService.getMenuType();
        model.addAttribute("typeList", typeList);
    }

    @LoginCheck
    @GetMapping("/menu/{menuId}")
    public ResponseEntity<MenuDTO> getOneMenu(@PathVariable Long menuId) {
        MenuDTO menuDTO = adminService.getOneMenu(menuId);
        return new ResponseEntity<>(menuDTO, HttpStatus.OK);
    }

    @LoginCheck
    @PostMapping("/menu")
    public void registerMenu(@RequestBody MenuDTO menuDTO) {
        adminService.registerMenu(menuDTO);
    }

    @LoginCheck
    @PutMapping("/menu")
    public void modifyMenu(@RequestBody MenuDTO menuDTO) {
        adminService.modifyMenu(menuDTO);
    }

    @LoginCheck
    @DeleteMapping("/menu")
    public void removeMenu(@RequestBody MenuDTO menuDTO) {
        adminService.removeMenu(menuDTO.getMenuId());
    }

    /** 관리자 매장 정보 관리 */
    @LoginCheck
    @GetMapping("/information")
    public void getInformation(Model model) {
        InformationDTO dto = adminService.getInformation("sushicaptain");
        model.addAttribute("dto", dto);
    }

    @LoginCheck
    @PutMapping("/information")
    public String modifyInformation(InformationDTO informationDTO,
                                    RedirectAttributes redirectAttributes) {
        adminService.modifyInformation(informationDTO);
        redirectAttributes.addFlashAttribute("msg", "저장되었습니다!");
        return "redirect:/admin/information";
    }

}