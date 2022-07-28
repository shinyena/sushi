package com.example.sushi.repository.user;

import com.example.sushi.entity.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByEmail(String email);

    @Query("select password from Member where email = 'admin'")
    String getAdminPassword();

}
