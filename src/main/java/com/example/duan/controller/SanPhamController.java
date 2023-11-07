package com.example.duan.controller;

import com.example.duan.entity.LoaiSanPham;
import com.example.duan.service.LoaiSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/san-pham")
public class SanPhamController {
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;

    @GetMapping()
    public String getAll(Model model) {
        List<LoaiSanPham> dsLoaiSanPham = loaiSanPhamService.getAll();
        model.addAttribute("dsSanPham", dsLoaiSanPham);
        model.addAttribute("kh", new LoaiSanPham());
        return "SanPham/Index";
    }

    @PostMapping("/add")
    public String addSanPham(@ModelAttribute LoaiSanPham loaiSanPham, Model model) {
        loaiSanPham.setTrangThai(true);
        loaiSanPhamService.addSanPham(loaiSanPham);
        List<LoaiSanPham> dsLoaiSanPham = loaiSanPhamService.getAll();
        model.addAttribute("dsSanPham", dsLoaiSanPham);
        return "redirect:/san-pham";
    }

    @GetMapping("/detail/{id}")
    public String editSanPhamForm(@PathVariable("id") int Id, Model model) {
        LoaiSanPham loaiSanPham = loaiSanPhamService.getById(Id);
        model.addAttribute("sanpham", loaiSanPham);
        return "SanPham/Detail";
    }

    @PostMapping("/update/{id}")
    public String updateSanPham(@PathVariable("id") int id, @ModelAttribute LoaiSanPham loaiSanPham) {
        loaiSanPhamService.updateSanPham(id, loaiSanPham);
        return "redirect:/san-pham";
    }


    @GetMapping("/delete/{id}")
    public String deleteSanPham(@PathVariable("id") int id) {
        loaiSanPhamService.deleteSanPham(id);
        return "redirect:/san-pham";
    }

}