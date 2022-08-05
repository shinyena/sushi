package com.example.sushi.service;

import com.example.sushi.dto.user.MemberDTO;
import com.example.sushi.entity.user.Member;
import com.example.sushi.entity.user.MemberRole;
import com.example.sushi.repository.user.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class KakaoLoginService extends DefaultOAuth2UserService {
    private final MemberService memberService;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        /** 카카오 아이디 받아오기 */
        Long kakaoID = Long.parseLong(attributes.get("id").toString());

        /** 세션에 카카오 아이디 저장하기 */
        httpSession.setAttribute("kakaoID", kakaoID);

        /** 회원 저장 */
        memberService.register(kakaoID);

        /** MemberRole 불러오기 */
        MemberRole memberRole = memberService.getOne(kakaoID).getMemberRole();

        return new DefaultOAuth2User(Collections.singleton(
                new SimpleGrantedAuthority("ROLE_"+memberRole.toString())), attributes, "id");
    }
}
