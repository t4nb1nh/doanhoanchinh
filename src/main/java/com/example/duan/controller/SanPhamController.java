package com.example.duan.controller;

import com.example.duan.entity.SanPham;
import com.example.duan.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/san-pham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping()
    public String getAll(Model model) {
        List<SanPham> dsSanPham = sanPhamService.getAll();
        model.addAttribute("dsSanPham", dsSanPham);
        model.addAttribute("kh", new SanPham());
        return "SanPham/Index";
    }

    @PostMapping("/add")
    public String addSanPham(@ModelAttribute SanPham sanPham, Model model) {
        sanPham.setTrangThai(true);
        sanPhamService.addSanPham(sanPham);
        List<SanPham> dsSanPham = sanPhamService.getAll();
        model.addAttribute("dsSanPham", dsSanPham);
        return "redirect:/san-pham";
    }

    @GetMapping("/detail/{id}")
    public String editSanPhamForm(@PathVariable("id") int Id, Model model) {
        SanPham sanPham = sanPhamService.getById(Id);
        model.addAttribute("sanpham", sanPham);
        return "SanPham/Detail";
    }

    @PostMapping("/update/{id}")
    public String updateSanPham(@PathVariable("id") int id, @ModelAttribute SanPham sanPham) {
        sanPhamService.updateSanPham(id,sanPham);
        return "redirect:/san-pham";
    }


    @GetMapping("/delete/{id}")
    public String deleteSanPham(@PathVariable("id") int id) {
        sanPhamService.deleteSanPham(id);
        return "redirect:/san-pham";
    }

}