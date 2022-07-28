package com.example.sushi.service;

import com.example.sushi.dto.admin.InformationDTO;
import com.example.sushi.dto.admin.MenuDTO;
import com.example.sushi.entity.admin.Information;
import com.example.sushi.entity.admin.Menu;
import com.example.sushi.entity.admin.MenuType;
import com.example.sushi.repository.admin.InformationRepository;
import com.example.sushi.repository.admin.MenuRepository;
import com.example.sushi.repository.admin.MenuTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final InformationRepository informationRepository;
    private final MenuRepository menuRepository;
    private final MenuTypeRepository menuTypeRepository;

    /** Information Service */
    @Override
    public InformationDTO getInformation(String adminId) {
        Optional<Information> byId = informationRepository.findById(adminId);
        Information information = byId.get();
        InformationDTO informationDTO = informationToDto(information);
        return informationDTO;
    }

    @Override
    public void modifyInformation(InformationDTO informationDTO) {
        Optional<Information> byId = informationRepository.findById(informationDTO.getAdminId());
        Information information = byId.get();
        information.changePassword(informationDTO.getPassword());
        information.changeLocation(informationDTO.getLocation());
        information.changeOpen(informationDTO.getOpen());
        information.changeClose(informationDTO.getClose());
        information.changeInstagram(informationDTO.getInstagram());
        information.changeCall(informationDTO.getPhone());
        information.changeTitle1(informationDTO.getTitle1());
        information.changeTitle2(informationDTO.getTitle2());
        information.changeTitle3(informationDTO.getTitle3());
        information.changeContent1(informationDTO.getContent1());
        information.changeContent2(informationDTO.getContent2());
        information.changeContent3(informationDTO.getContent3());
        information.changeNotice(informationDTO.getNotice());
        informationRepository.save(information);
    }

    @Override
    public void registerMenu(MenuDTO menuDTO) {
        MenuType menuType = menuTypeRepository.findMenuTypeByType(menuDTO.getMenuType());
        Menu menu = dtoToMenu(menuDTO, menuType);
        menuRepository.save(menu);
    }

    @Override
    public List<MenuDTO> getAllMenu() {
        List<Menu> menuList = menuRepository.findAll();
        List<MenuDTO> dtoList = new ArrayList<>();
        menuList.forEach(menu -> {
            MenuDTO dto = menuToDto(menu);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public MenuDTO getOneMenu(Long menuId) {
        Optional<Menu> byId = menuRepository.findById(menuId);
        Menu menu = byId.get();
        MenuDTO dto = menuToDto(menu);
        return dto;
    }

    @Override
    public void modifyMenu(@RequestBody MenuDTO menuDTO) {
        MenuType menuType = menuTypeRepository.findMenuTypeByType(menuDTO.getMenuType());
        Optional<Menu> byId = menuRepository.findById(menuDTO.getMenuId());
        Menu menu = byId.get();
        menu.changeMenuName(menuDTO.getMenuName());
        menu.changeMenuComment(menuDTO.getMenuComment());
        menu.changeMenuPrice(menuDTO.getMenuPrice());
        menu.changeMenuType(menuType);
        menuRepository.save(menu);
    }

    @Override
    public void removeMenu(Long menuId) {
        menuRepository.deleteById(menuId);
    }

    @Override
    public List<MenuType> getMenuType() {
        List<MenuType> menuTypeList = menuTypeRepository.findAll();
        return menuTypeList;
    }
}
