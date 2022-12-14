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
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
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
        if (byId.isPresent()) {
            Information information = byId.get();
            InformationDTO informationDTO = informationToDto(information);
            return informationDTO;
        } else {
            log.error("informationRepository 조회 오류");
        }
        return null;

    }

    @Override
    public void modifyInformation(InformationDTO informationDTO) {
        Optional<Information> byId = informationRepository.findById(informationDTO.getAdminId());
        if (byId.isPresent()) {
            Information information = byId.get();
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
        } else {
            log.error("informationRepository 조회 오류");
        }
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
        if (byId.isPresent()) {
            Menu menu = byId.get();
            MenuDTO dto = menuToDto(menu);
            return dto;
        } else {
            log.error("menuRepository 조회 오류");
        }
        return null;
    }

    @Override
    public void modifyMenu(@RequestBody MenuDTO menuDTO) {
        MenuType menuType = menuTypeRepository.findMenuTypeByType(menuDTO.getMenuType());
        Optional<Menu> byId = menuRepository.findById(menuDTO.getMenuId());
        if (byId.isPresent()) {
            Menu menu = byId.get();
            menu.changeMenuName(menuDTO.getMenuName());
            menu.changeMenuComment(menuDTO.getMenuComment());
            menu.changeMenuPrice(menuDTO.getMenuPrice());
            menu.changeMenuType(menuType);
            menuRepository.save(menu);
        } else {
            log.error("menuRepository 조회 오류");
        }
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
