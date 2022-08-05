package com.example.sushi.controller;

import com.example.sushi.annotation.LoginCheck;
import com.example.sushi.dto.admin.InformationDTO;
import com.example.sushi.dto.user.MemberDTO;
import com.example.sushi.dto.user.ReservationDTO;
import com.example.sushi.service.AdminService;
//import com.example.sushi.service.KakaoLoginService;
import com.example.sushi.service.MemberService;
import com.example.sushi.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/sushi")
@RequiredArgsConstructor
public class UserController {
    private final ReservationService reservationService;
    private final AdminService adminService;
    private final MemberService memberService;
    private final String adminId = "yena5790@naver.com";


    @LoginCheck
    @GetMapping("/register")
    public void getRegisterPage(Model model, HttpSession session) {
        Long mid = (Long) session.getAttribute("kakaoID");
        MemberDTO memberDTO = memberService.getOne(mid);
        model.addAttribute("memberDTO", memberDTO);
        InformationDTO informationDTO = adminService.getInformation(adminId);
        model.addAttribute("info", informationDTO);
    }

    @LoginCheck
    @PostMapping("/register")
    public String registerReservation(ReservationDTO reservationDTO,
                                      RedirectAttributes redirectAttributes) {
        reservationService.register(reservationDTO);
        memberService.modify(reservationDTO);
        redirectAttributes.addFlashAttribute("msg", "예약이 완료되었습니다.");
        return "redirect:/";
    }

    @LoginCheck
    @GetMapping("/list")
    public void getAllReservation(HttpSession session, Model model) {
        Long kakaoID = (Long) session.getAttribute("userId");
        List<ReservationDTO> dtoList = reservationService.getList(kakaoID);
        model.addAttribute("dtoList", dtoList);
        InformationDTO information = adminService.getInformation(adminId);
        model.addAttribute("info", information);
    }

    @LoginCheck
    @PostMapping("/modify")
    public String modifyReservation(ReservationDTO reservationDTO,
                                    RedirectAttributes redirectAttributes) {
        reservationService.modify(reservationDTO);
        memberService.modify(reservationDTO);
        redirectAttributes.addFlashAttribute("msg", "예약이 변경 되었습니다.");
        return "redirect:/sushi/list#specials";
    }

    @LoginCheck
    @GetMapping("/delete")
    public String removeReservation(Long rid,
                                    RedirectAttributes redirectAttributes) {
        reservationService.remove(rid);
        redirectAttributes.addFlashAttribute("msg", "예약이 삭제 되었습니다.");
        return "redirect:/sushi/list#specials";
    }

    /** 예약 가능 날짜 및 시간 불러오기 */
    @LoginCheck
    @GetMapping("/date")
    public ResponseEntity<List<LocalDate>> getDate() {
        List<LocalDate> dateList = reservationService.getDate();
        return new ResponseEntity<>(dateList, HttpStatus.OK);
    }

    @LoginCheck
    @GetMapping("/time")
    public ResponseEntity<List<ReservationDTO>> getTime(@RequestBody ReservationDTO reservationDTO) {
        List<ReservationDTO> dtoList = reservationService.getTime(reservationDTO.getRdate());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
}
