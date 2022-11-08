package com.new_ton.controller;


import com.new_ton.domain.dto.DischargePageDto;
import com.new_ton.service.DischargePageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping({"/"})
public class MainController {

    private final DischargePageService dischargePageService;

    @GetMapping
    public String main() {
        return "redirect:role_action";
    }


    @GetMapping("/role_action")
    public String roleAction() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var authorities = auth.getAuthorities();
        var roleOptional = authorities.stream().findFirst();
        if (roleOptional.isPresent()) {
            var role = roleOptional.get();
            if (role.getAuthority().equals("ROLE_ADMIN") || role.getAuthority().equals("ROLE_USER")) {
                return "redirect:productPage";
            } else {
                return "redirect:technologist_page";
            }
        }
        return "";
    }

    @GetMapping({"/calibrationPage"})
    public String addMainPage() {
        return "CalibrationPage";
    }

    @GetMapping({"/productPage"})
    public String addProductPage() {
        return "ProductPage";
    }

    @GetMapping({"/dischargePage"})
    public String addDischargePage(@RequestParam Integer id, Model model) {
        List<DischargePageDto> dischargePageDtoList = dischargePageService.getDischangeList(id);
        model.addAttribute("dispachPageDtos", dischargePageDtoList);
        return "DischargePage";
    }

    @GetMapping({"/recipePage"})
    public String addRecipePage2(@RequestParam Integer id, Model model) {
        model.addAttribute("id", id);
        return "RecipePage";
    }

    @GetMapping({"/login"})
    public String loginPage() {
        return "LoginPage";
    }


    @Secured("ROLE_TECHNOLOGIST")
    @GetMapping({"/technologist_page"})
    public String getTechnologistPage() {
        return "TechnologistPage";
    }
}
