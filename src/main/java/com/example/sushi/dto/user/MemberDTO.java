package com.example.sushi.dto.user;

import com.example.sushi.entity.user.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long mid;
    private String name;
    private String phone;
    private MemberRole memberRole;

}
