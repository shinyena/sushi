package com.example.sushi.entity.admin;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "menuType")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    private String menuName;
    private String menuComment;
    private int menuPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private MenuType menuType;

    public void changeMenuName(String menuName) {
        this.menuName = menuName;
    }
    public void changeMenuComment(String menuComment) {
        this.menuComment = menuComment;
    }
    public void changeMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }
    public void changeMenuType(MenuType menuType) {
        this.menuType = menuType;
    }

}
