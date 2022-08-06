package com.example.sushi.controller;

import com.example.sushi.annotation.LoginCheck;
import com.example.sushi.dto.admin.InformationDTO;
import com.example.sushi.dto.admin.MenuDTO;
import com.example.sushi.dto.user.MemberDTO;
import com.example.sushi.dto.user.ReservationDTO;
import com.example.sushi.entity.admin.MenuType;
import com.example.sushi.entity.user.Member;
import com.example.sushi.service.AdminService;
import com.example.sushi.service.MemberService;
import com.example.sushi.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${adminid}")
    private String adminId;

    private final ReservationService reservationService;
    private final MemberService memberService;
    private final AdminService adminService;

    /** 관리자 예약 관리 */
    @LoginCheck
    @GetMapping("/reservation")
    public void getAllReservation(Model model) {
        List<ReservationDTO> reservationDTOList = reservationService.getAll();
        model.addAttribute("dtoList", reservationDTOList);
    }

    @LoginCheck
    @GetMapping("/read")
    public void getOneReservation(Long rid, Model model) {
        ReservationDTO dto = reservationService.getOne(rid);
        model.addAttribute("dto", dto);
    }

    @LoginCheck
    @GetMapping("/delete")
    public String removeReservation(Long rid, RedirectAttributes redirectAttributes) {
        reservationService.remove(rid);
        redirectAttributes.addFlashAttribute("msg", "예약이 삭제 되었습니다.");
        return "redirect:/admin/reservation";
    }

    /** 관리자 회원 관리 */
    @LoginCheck
    @GetMapping("/member")
    public void getAllMember(Model model) {
        List<MemberDTO> memberDTOList = memberService.getAll();
        model.addAttribute("dtoList", memberDTOList);
    }

    @LoginCheck
    @GetMapping("/role")
    public String modifyMemberRole(Long mid, RedirectAttributes redirectAttributes) {
        memberService.changeRole(mid);
        redirectAttributes.addFlashAttribute("msg", "회원 권한이 변경 되었습니다.");
        return "redirect:/admin/member";
    }

    @LoginCheck
    @GetMapping("/list")
    public void getMemberReservation(Long mid, Model model) {
        List<ReservationDTO> reservationDTOList = reservationService.getList(mid);
        model.addAttribute("dtoList", reservationDTOList);
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
        InformationDTO dto = adminService.getInformation(adminId);
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