package com.example.duan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/PhongCach")
public class PhongCachController {

    @GetMapping
    public String index(Model model) {

        return "PhongCach/Index";
    }
}
