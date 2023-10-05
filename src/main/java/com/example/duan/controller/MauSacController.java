package com.example.duan.Controller;

import com.example.duan.Entity.MauSac;
import com.example.duan.Service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("mau-sac")
public class MauSacController {
    @Autowired
    private MauSacService mauSacService;

    @GetMapping("list")
    public String list(Model model) {
        List<MauSac> list = mauSacService.findAll();
        model.addAttribute("items", list);
        return "MauSac/index";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("ms", new MauSac());
        return "MauSac/add";
    }

    @PostMapping("save")
    public String save(MauSac mauSac) {
        mauSacService.saveMauSac(mauSac);
        return "redirect:/mau-sac/list";
    }
    @PostMapping("update/{id}")
    public String update(@ModelAttribute MauSac mauSac,@PathVariable("id") Integer id) {
        mauSacService.update(mauSac,id);
        return "redirect:/mau-sac/list";
    }
    @GetMapping("edit")
    public String edit(@RequestParam("id") Integer id, Model model) {
        MauSac optional = mauSacService.findByID(id);
        model.addAttribute("ms", optional);
        return "MauSac/edit";
    }
    @GetMapping("delete")
    public String delete(@RequestParam("id") Integer id) {
        mauSacService.delete(id);
        return "redirect:/mau-sac/list";
    }
}
