package com.example.sushi.service;

import com.example.sushi.entity.user.Member;
import com.example.sushi.entity.user.MemberRole;
import com.example.sushi.repository.user.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
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
public class KakaoOAuth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();


        /** 이메일 받아오기 */
        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");

        String email;
        if (kakao_account.get("email").toString().equals("")) {
            email = "";
        }
        else {
            email = kakao_account.get("email").toString();
        }

        /** 이름 받아오기 */
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        String nickname = properties.get("nickname").toString();

        httpSession.setAttribute("userId", email);
        httpSession.setAttribute("userName", nickname);


        Optional<Member> byId = memberRepository.findById(email);
        if (!byId.isPresent()) {
            Member member = Member.builder()
                    .email(email)
                    .memberRole(MemberRole.USER)
                    .build();
            memberRepository.save(member);
        }

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")), attributes, "id");
    }
}
