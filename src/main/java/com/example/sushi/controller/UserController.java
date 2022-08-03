package com.example.sushi.controller;

import com.example.sushi.annotation.UserLoginCheck;
import com.example.sushi.dto.admin.InformationDTO;
import com.example.sushi.dto.admin.MenuDTO;
import com.example.sushi.dto.user.ReservationDTO;
import com.example.sushi.service.AdminService;
//import com.example.sushi.service.KakaoLoginService;
import com.example.sushi.service.KakaoOAuth2UserService;
import com.example.sushi.service.UserService;
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
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/sushi")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final AdminService adminService;
    private final KakaoOAuth2UserService kakaoService;

    @UserLoginCheck
    @GetMapping("/register")
    public void getRegisterPage(Model model, HttpSession session) {
        log.info(session);
        model.addAttribute("email", session.getAttribute("userId"));
        model.addAttribute("name", session.getAttribute("userName"));
        System.out.println("kakaoService.toString() = " + kakaoService.toString());
        InformationDTO information = adminService.getInformation("sushicaptain");
        model.addAttribute("info", information);
    }

    @UserLoginCheck
    @PostMapping("/register")
    public String registerReservation(ReservationDTO reservationDTO,
                                      RedirectAttributes redirectAttributes) {
        service.register(reservationDTO);
        redirectAttributes.addFlashAttribute("msg", "예약이 완료되었습니다.");
        return "redirect:/";
    }

    @UserLoginCheck
    @GetMapping("/list")
    public void getAllReservation(HttpSession session, Model model) {
        String email = session.getAttribute("userId").toString();
        List<ReservationDTO> dtoList = service.getList(email);
        model.addAttribute("dtoList", dtoList);
        InformationDTO information = adminService.getInformation("sushicaptain");
        model.addAttribute("info", information);
    }

    @UserLoginCheck
    @PostMapping("/modify")
    public String modifyReservation(ReservationDTO reservationDTO,
                                    RedirectAttributes redirectAttributes) {
        service.modify(reservationDTO);
        redirectAttributes.addFlashAttribute("msg", "예약이 변경 되었습니다.");
        return "redirect:/sushi/list#specials";
    }

    @UserLoginCheck
    @GetMapping("/delete")
    public String removeReservation(Long rid,
                                    RedirectAttributes redirectAttributes) {
        service.remove(rid);
        redirectAttributes.addFlashAttribute("msg", "예약이 삭제 되었습니다.");
        return "redirect:/sushi/list#specials";
    }

    /** 예약 가능 날짜 및 시간 불러오기 */
    @UserLoginCheck
    @GetMapping("/date")
    public ResponseEntity<List<LocalDate>> getDate() {
        List<LocalDate> dateList = service.getDate();
        return new ResponseEntity<>(dateList, HttpStatus.OK);
    }

    @UserLoginCheck
    @GetMapping("/time")
    public ResponseEntity<List<ReservationDTO>> getTime(@RequestBody ReservationDTO reservationDTO) {
        List<ReservationDTO> dtoList = service.getTime(reservationDTO.getRdate());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
}
