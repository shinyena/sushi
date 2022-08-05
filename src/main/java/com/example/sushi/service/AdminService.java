package com.example.sushi.service;

import com.example.sushi.dto.admin.InformationDTO;
import com.example.sushi.dto.admin.MenuDTO;
import com.example.sushi.entity.admin.Information;
import com.example.sushi.entity.admin.Menu;
import com.example.sushi.entity.admin.MenuType;

import java.util.List;

public interface AdminService {

    /** Information Service */
    InformationDTO getInformation(String adminId);          // 레스토랑 정보 조회
    String modifyInformation(InformationDTO informationDTO);  // 레스토랑 정보 수정

    /** Menu Service */
    Long registerMenu(MenuDTO menuDTO);                     // 메뉴 등록
    Long modifyMenu(MenuDTO menuDTO);                       // 메뉴 수정
    void removeMenu(Long menuId);                           // 메뉴 삭제
    List<MenuDTO> getAllMenu();                             // 전체 메뉴 조회
    MenuDTO getOneMenu(Long menuId);                        // 개별 메뉴 조회

    /** MenuType Service */
//    void registerMenuType();                                // 메뉴 종류 등록
//    void modifyMenyType();                                  // 메뉴 종류 수정
//    void removeMenuType();                                  // 메뉴 종류 삭제
    List<MenuType> getMenuType();                           // 메뉴 종류 조회

    default Information dtoToInformation(InformationDTO dto) {
        Information entity = Information.builder()
                .adminId(dto.getAdminId())
                .location(dto.getLocation())
                .open(dto.getOpen())
                .close(dto.getClose())
                .instagram(dto.getInstagram())
                .phone(dto.getPhone())
                .title1(dto.getTitle1())
                .title2(dto.getTitle2())
                .title3(dto.getTitle3())
                .content1(dto.getContent1())
                .content2(dto.getContent2())
                .content3(dto.getContent3())
                .notice(dto.getNotice())
                .build();
        return entity;
    }

    default InformationDTO informationToDto(Information entity) {
        InformationDTO dto = InformationDTO.builder()
                .adminId(entity.getAdminId())
                .location(entity.getLocation())
                .open(entity.getOpen())
                .close(entity.getClose())
                .instagram(entity.getInstagram())
                .phone(entity.getPhone())
                .title1(entity.getTitle1())
                .title2(entity.getTitle2())
                .title3(entity.getTitle3())
                .content1(entity.getContent1())
                .content2(entity.getContent2())
                .content3(entity.getContent3())
                .notice(entity.getNotice())
                .build();
        return dto;
    }

    default Menu dtoToMenu(MenuDTO dto, MenuType menuType) {
        Menu entity = Menu.builder()
                .menuId(dto.getMenuId())
                .menuName(dto.getMenuName())
                .menuComment(dto.getMenuComment())
                .menuPrice(dto.getMenuPrice())
                .menuType(menuType)
                .build();
        return entity;
    }

    default MenuDTO menuToDto(Menu entity) {
        MenuDTO dto = MenuDTO.builder()
                .menuId(entity.getMenuId())
                .menuName(entity.getMenuName())
                .menuComment(entity.getMenuComment())
                .menuPrice(entity.getMenuPrice())
                .menuType(entity.getMenuType().getType())
                .build();
        return dto;
    }
}
