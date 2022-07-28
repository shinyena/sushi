package com.example.sushi.service;

import com.example.sushi.dto.admin.InformationDTO;
import com.example.sushi.dto.user.MemberDTO;
import com.example.sushi.entity.admin.Information;
import com.example.sushi.entity.user.Member;
import com.example.sushi.repository.admin.InformationRepository;
import com.example.sushi.repository.user.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    private final InformationRepository informationRepository;

    public boolean login(MemberDTO memberDTO) {
        Member member = memberRepository.findByEmail(memberDTO.getEmail());
        if (member == null) {
            return false;
        }
        if (!member.getPassword().equals(memberDTO.getPassword())) {
            return false;
        }
        return true;
    }

    public boolean isAdmin(MemberDTO memberDTO) {
        if (memberDTO.getEmail().equals("admin") && memberDTO.getPassword().equals(memberRepository.getAdminPassword()))
            return true;
        else
            return false;
    }

    public boolean amdinLogin(InformationDTO informationDTO) {
        Optional<Information> byId = informationRepository.findById(informationDTO.getAdminId());
        if (byId.isPresent()) {
            Information information = byId.get();
            if (information.getPassword().equals(informationDTO.getPassword())) {
                return true;
            }
        }
        return false;
    }

}
