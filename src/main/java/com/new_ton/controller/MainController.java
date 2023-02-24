package com.new_ton.controller;


import com.new_ton.domain.dto.productionpage.DischargePageDto;
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
        return redirectRole(userRole);
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
    public String getEditeRecipePage(@RequestParam String idProd, Model model) {
        model.addAttribute("idProd", idProd);
        return "EditRecipeTechnologistPage";
    }

    @Secured("ROLE_TECHNOLOGIST")
    @GetMapping("/product-in-production-page")
    public String productInProductionPage() {
        return "ProductInProduction";
    }


    @Secured("ROLE_TECHNOLOGIST")
    @GetMapping("/recipe-in-production-edite-page")
    public String recipeInProductionEditePage(@RequestParam String idProd, Model model) {
        model.addAttribute("idProd", idProd);
        return "RecipeInProductionEditePage";
    }

    @Secured("ROLE_ACCOUNTMANAGER")
    @GetMapping("/account-manager-page")
    public String getAccountManagerPage() {
        return "AccountManagerPage";
    }


    @Secured("ROLE_ACCOUNTMANAGER")
    @GetMapping("/get-account-manager-check-recipe-page")
    public String getAccountManagerCheckRecipePage(@RequestParam String idProd, Model model) {
        model.addAttribute("idProd", idProd);
        return "AccountManagerCheckRecipePage";
    }

    @Secured({"ROLE_ACCOUNTMANAGER", "ROLE_TECHNOLOGIST"})
    @GetMapping("/edite-catalog-page")
    public String getEditeCatalogPage() {
        return "EditeCatalogPage";
    }

    @Secured({"ROLE_ACCOUNTMANAGER", "ROLE_TECHNOLOGIST"})
    @GetMapping("/edite-recipe-catalog-page")
    public String getEditeRecipeCatalogPage(@RequestParam String idCat, Model model) {
        model.addAttribute("idCat", idCat);
        return "EditeRecipeCatalogPage";
    }

    @Secured({"ROLE_ACCOUNTMANAGER", "ROLE_TECHNOLOGIST"})
    @GetMapping("/edite-dissolvers")
    public String getDissolvers() {
        return "EditeDissolversPage";
    }


    @Secured({"ROLE_ACCOUNTMANAGER", "ROLE_TECHNOLOGIST"})
    @GetMapping("/edite-component-page")
    public String getEditeComponentPage() {
        return "EditeComponentPage";
    }


    private String redirectRole(String userRole) {
        if (userRole.equals("ROLE_TECHNOLOGIST")) {
            return "redirect:technologist-page";
        } else if (userRole.equals("ROLE_ACCOUNTMANAGER")) {
            return "redirect:account-manager-page";
        }
        return null;
    }
}
