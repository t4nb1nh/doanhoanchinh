package com.example.duan.Controller;

import com.example.duan.Entity.HinhThucThanhToan;
import com.example.duan.Service.HinhThucThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("httt")
public class HinhThucThanhToanController {
    @Autowired
    private HinhThucThanhToanService mauSacService;

    @GetMapping("list")
    public String list(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 2;

        List<HinhThucThanhToan> list = mauSacService.findAll();
        int totalPages = (int) Math.ceil((double) list.size() / pageSize);
        List<HinhThucThanhToan> currentPageMauSac = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

        model.addAttribute("mausac", currentPageMauSac);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "HinhThucThanhToan/index";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("ms", new HinhThucThanhToan());
        return "HinhThucThanhToan/add";
    }

    @PostMapping("save")
    public String save(@Validated @ModelAttribute("ms") HinhThucThanhToan mauSac, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", "vui lòng sửa các lỗi sau");
            model.addAttribute("ms", mauSac);
            return "HinhThucThanhToan/add";
        } else {
            mauSacService.saveMauSac(mauSac);
            return "redirect:/httt/list";
        }
    }

    @PostMapping("update")
    public String update(@Validated @ModelAttribute("ms") HinhThucThanhToan mauSac, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", "vui lòng sửa lỗi sau");
            return "/HinhThucThanhToan/edit";
        }
        mauSacService.update(mauSac);
        return "redirect:/httt/list";

    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        HinhThucThanhToan optional = mauSacService.findByID(id);
        model.addAttribute("ms", optional);
        return "HinhThucThanhToan/edit";
    }

    @GetMapping("delete")
    public String delete(@RequestParam("id") Integer id) {
        mauSacService.delete(id);
        return "redirect:/httt/list";
    }


    @PostMapping("search")
    public String searchProductByCode(@RequestParam String ma, Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 2;
        List<HinhThucThanhToan> list = mauSacService.findMauSacByMa(ma);
        int totalPages = (int) Math.ceil((double) list.size() / pageSize);
        List<HinhThucThanhToan> currentPageMauSac = list.subList(page * pageSize, Math.min((page + 1) * pageSize, list.size()));

        model.addAttribute("mausac", currentPageMauSac);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "/HinhThucThanhToan/index"; // Trả về trang hiển thị kết quả

    }
}
