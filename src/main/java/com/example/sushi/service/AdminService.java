package com.example.sushi.service;

import com.example.sushi.dto.admin.InformationDTO;
import com.example.sushi.dto.admin.MenuDTO;
import com.example.sushi.entity.admin.Information;
import com.example.sushi.entity.admin.Menu;
import com.example.sushi.entity.admin.MenuType;

import java.util.List;

public interface AdminService {

    /** Information Service */
    InformationDTO getInformation(String adminId);
    void modifyInformation(InformationDTO informationDTO);

    /** Menu Service */
    void registerMenu(MenuDTO menuDTO);
    List<MenuDTO> getAllMenu();
    MenuDTO getOneMenu(Long menuId);
    void modifyMenu(MenuDTO menuDTO);
    void removeMenu(Long menuId);

    /** MenuType Service */
    List<MenuType> getMenuType();

    default Information dtoToInformation(InformationDTO dto) {
        Information entity = Information.builder()
                .adminId(dto.getAdminId())
                .password(dto.getPassword())
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
                .password(entity.getPassword())
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
