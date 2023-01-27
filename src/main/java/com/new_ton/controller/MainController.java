package com.new_ton.controller;


import com.new_ton.domain.dto.DischargePageDto;
import com.new_ton.service.DischargePageService;
import com.new_ton.service.GerUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping({"/"})
public class MainController {

    private final DischargePageService dischargePageService;

    private final GerUserRoleService gerUserRoleService;

    @GetMapping
    public String main() {
        return "redirect:/role-action";
    }

    @GetMapping("/role-action")
    public String roleAction(HttpServletRequest httpServletRequest) {
        String userRole = gerUserRoleService.getUserRole(httpServletRequest);
        return "redirect:technologist-page";
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
    @GetMapping({"/technologist-page"})
    public String getTechnologistPage() {
        return "TechnologistPage";
    }


    @Secured("ROLE_TECHNOLOGIST")
    @GetMapping("/edite-recipe")
    public String getEditeRecipePage(@RequestParam String idProd) {
        return "EditRecipeTechnologistPage";
    }
}
