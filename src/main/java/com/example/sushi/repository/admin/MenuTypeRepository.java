package com.example.sushi.repository.admin;

import com.example.sushi.entity.admin.MenuType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MenuTypeRepository extends JpaRepository<MenuType, Long> {
    @Query("select mt from MenuType mt where mt.type = :type")
    MenuType findMenuTypeByType(String type);
}
