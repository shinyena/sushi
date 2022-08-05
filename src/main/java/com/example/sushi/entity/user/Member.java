package com.example.sushi.entity.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member {
    @Id
    private Long mid;

    private String name;
    private String phone;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    public void changeName(String name) { this.name = name; }
    public void changePhone(String phone) { this.phone = phone; }
}
