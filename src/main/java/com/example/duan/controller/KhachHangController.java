package com.example.duan.controller;

import com.example.duan.entity.KhachHang;
import com.example.duan.entity.KhachHang;
import com.example.duan.service.KhachHangService;
import com.example.duan.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/khachhang")
public class KhachHangController {
    @Autowired
    private KhachHangService khachHangService;

    @GetMapping()
    public String getAll(Model model) {
        List<KhachHang> dsKhachHang = khachHangService.getAll();
        model.addAttribute("dsKhachHang", dsKhachHang);
        model.addAttribute("kh", new KhachHang());
        return "KhachHang/Index";
    }

    @PostMapping("/add")
    public String addKhachHang(@ModelAttribute KhachHang khachHang, Model model) {
        khachHang.setTrangThai(true);
        khachHangService.addKhachHang(khachHang);
        List<KhachHang> dsKhachHang = khachHangService.getAll();
        model.addAttribute("dsKhachHang", dsKhachHang);
        return "redirect:/khachhang";
    }

    @GetMapping("/detail/{id}")
    public String editKhachHangForm(@PathVariable("id") int Id, Model model) {
        KhachHang khachHang = khachHangService.getById(Id);
        model.addAttribute("khachHang", khachHang);
        return "KhachHang/Detail";
    }

    @PostMapping("/update/{id}")
    public String updateKhachHang(@PathVariable("id") int id, @ModelAttribute KhachHang khachHang) {
        khachHangService.updateKhachHang(id, khachHang);
        return "redirect:/khachhang";
    }


    @GetMapping("/delete/{id}")
    public String deleteKhachHang(@PathVariable("id") int id) {
        khachHangService.deleteKhachHang(id);
        return "redirect:/khachhang";
    }

}
