package com.example.duan.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping("list")
    public String list(Model model) {

        return "product/list";
    }

    @GetMapping("detail")
    public String detail() {

        return "product/detail";
    }
}
