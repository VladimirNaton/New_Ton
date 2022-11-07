package com.new_ton.controller;


import com.new_ton.domain.dto.DischargePageDto;
import com.new_ton.service.DischargePageService;
import lombok.RequiredArgsConstructor;
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
        return "redirect:productPage";
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
        List<DischargePageDto> dischargePageDtoList = this.dischargePageService.getDischangeList(id);
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
}
