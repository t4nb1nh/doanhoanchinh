package com.example.duan.controller;

import com.example.duan.entity.PhongCach;
import com.example.duan.service.PhongCachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/PhongCach")
public class PhongCachController {

    @Autowired
    private PhongCachService service;

    @GetMapping()
    public String getAll(Model model) {
        List<PhongCach> dsPhongCach = service.getAll();
        model.addAttribute("dsPhongCach", dsPhongCach);
        model.addAttribute("pc", new PhongCach());
        return "PhongCach/Index";
    }

    @PostMapping("/add")
    public String addPhongCach(@ModelAttribute PhongCach phongCach, Model model) {
        phongCach.setTrangThai(true);
        service.addPhongCach(phongCach);
        List<PhongCach> dsPhongCach = service.getAll();
        model.addAttribute("dsPhongCach", dsPhongCach);
        return "redirect:/PhongCach";
    }

    @GetMapping("/detail/{id}")
    public String editPhongCachForm(@PathVariable("id") int Id, Model model) {
        PhongCach phongCach = service.getById(Id);
        model.addAttribute("phongCach", phongCach);
        return "PhongCach/Detail";
    }

    @PostMapping("/update/{id}")
    public String updatePhongCach(@PathVariable("id") int id, @ModelAttribute PhongCach phongCach) {
        service.updatePhongCach(id, phongCach);
        return "redirect:/PhongCach";
    }


    @GetMapping("/delete/{id}")
    public String deletePhongCach(@PathVariable("id") int id) {
        service.deletePhongCach(id);
        return "redirect:/PhongCach";
    }
}
