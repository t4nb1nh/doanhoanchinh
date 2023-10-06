package com.example.duan.controller;

import com.example.duan.entity.NSX;
import com.example.duan.service.NSXService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NSXController {

    @Autowired
    private NSXService nsxService;

    private List<NSX> nsx = new ArrayList<>();

    @GetMapping("/nsx/index")
    public String view(Model model){
        nsx = nsxService.getAll();
        model.addAttribute("nsx", nsx);
        return "indexNSX";
    }

    @PostMapping("/nsx/add")
    public String add(@ModelAttribute("nsx") NSX nsx){
        nsxService.add(nsx);
        return "redirect:/nsx/index";
    }

    @GetMapping("/nsx/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model){
        NSX nsx = nsxService.detail(id);
        model.addAttribute("nsx", nsx);
        return "updateNSX";
    }

    @GetMapping("/nsx/delete/?id={id}")
    public String delete(@PathVariable("id") Integer id){
        nsxService.delete(id);
        return "redirect:/nsx/index";
    }

    @PostMapping("/nsx/update/{id}")
    public String update(@ModelAttribute("nsx") NSX nsx, @PathVariable("id") Integer id ){
        NSX n = nsxService.detail(id);
        BeanUtils.copyProperties(nsx, n);
        nsxService.update(nsx, id);
        return "redirect:/nsx/index";
    }


}
