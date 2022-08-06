package com.example.sushi.service;

import com.example.sushi.dto.user.MemberDTO;
import com.example.sushi.dto.user.ReservationDTO;
import com.example.sushi.entity.user.Member;
import com.example.sushi.entity.user.MemberRole;
import com.example.sushi.repository.user.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    @Override
    public void register(Long mid) {
        Optional<Member> byId = memberRepository.findById(mid);
        if (!byId.isPresent()) {
            Member member = Member.builder()
                    .mid(mid)
                    .memberRole(MemberRole.USER)
                    .build();
            memberRepository.save(member);
        } else {
            log.error(mid + ": memberRepository 조회 오류");
        }
    }

    @Override
    public void modify(ReservationDTO reservationDTO) {
        Optional<Member> byId = memberRepository.findById(reservationDTO.getMid());
        if (byId.isPresent()) {
            Member member = byId.get();
            member.changeName(reservationDTO.getName());
            member.changePhone(reservationDTO.getPhone());
            memberRepository.save(member);
        }
        else {
            log.error(reservationDTO.getRid() + ": memberRepository 조회 오류");
        }
    }

    @Override
    public void remove(Long mid) {
        memberRepository.deleteById(mid);
    }

    @Override
    public List<MemberDTO> getAll() {
        List<Member> memberList = memberRepository.findAll();
        List<MemberDTO> dtoList = new ArrayList<>();
        memberList.forEach( member -> {
            MemberDTO dto = entityToDTO(member);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public MemberDTO getOne(Long mid) {
        Optional<Member> byId = memberRepository.findById(mid);
        if (byId.isPresent()) {
            Member member = byId.get();
            return entityToDTO(member);
        }
        else {
            log.error(mid + ": memberRepository 조회 오류");
            return null;
        }
    }

    @Override
    public void changeRole(Long mid) {
        Optional<Member> byId = memberRepository.findById(mid);
        if (byId.isPresent()) {
            Member member = byId.get();
            if (member.getMemberRole() == MemberRole.ADMIN) {
                member.changeRole(MemberRole.USER);
            } else {
                member.changeRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
        } else {
            log.error("changeRole(), memberRepository 조회 오류");
        }
    }



}
