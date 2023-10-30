package com.example.duan.controller;

import com.example.duan.entity.MauSac;
import com.example.duan.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("mau-sac")
public class MauSacController {
    @Autowired
    private MauSacService mauSacService;

    @GetMapping("list")
    public String list(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 2;

        List<MauSac> list = mauSacService.findAll();
        int totalPages = (int) Math.ceil((double) list.size() / pageSize);
        List<MauSac> currentPageMauSac = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

        model.addAttribute("mausac", currentPageMauSac);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "MauSac/index";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("ms", new MauSac());
        return "MauSac/add";
    }

    @PostMapping("save")
    public String save(@Validated @ModelAttribute("ms") MauSac mauSac, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", "vui lòng sửa các lỗi sau");
            model.addAttribute("ms", mauSac);
            return "MauSac/add";
        } else {
            mauSacService.saveMauSac(mauSac);
            return "redirect:/mau-sac/list";
        }
    }

    @PostMapping("update")
    public String update(@Validated @ModelAttribute("ms") MauSac mauSac, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", "vui lòng sửa lỗi sau");
            return "/MauSac/edit";
        }
            mauSacService.update(mauSac);
            return "redirect:/mau-sac/list";

    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        MauSac optional = mauSacService.findByID(id);
        model.addAttribute("ms", optional);
        return "MauSac/edit";
    }

    @GetMapping("delete")
    public String delete(@RequestParam("id") Integer id) {
        mauSacService.delete(id);
        return "redirect:/mau-sac/list";
    }


    @PostMapping("search")
    public String searchProductByCode(@RequestParam String ma, Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 2;
        List<MauSac> list = mauSacService.findMauSacByMa(ma);
        int totalPages = (int) Math.ceil((double) list.size() / pageSize);
        List<MauSac> currentPageMauSac = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

        model.addAttribute("mausac", currentPageMauSac);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "/MauSac/index"; // Trả về trang hiển thị kết quả

    }
}
