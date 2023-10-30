package com.example.duan.controller;

import com.example.duan.entity.MauSac;
import com.example.duan.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/mau-sac")
public class MauSacController {
    @Autowired
    private MauSacService mauSacService;

    @GetMapping()
    public String getAll(Model model) {
        List<MauSac> dsMauSac = mauSacService.getAll();
        model.addAttribute("dsMauSac", dsMauSac);
        return "MauSac/Index";
    }
}
