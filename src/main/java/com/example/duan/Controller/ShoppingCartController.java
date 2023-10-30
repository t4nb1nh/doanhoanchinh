package com.example.duan.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/cart/view")
public class ShoppingCartController {
    @GetMapping("")
    public String detail(){
        return "cart/view";
    }

}
