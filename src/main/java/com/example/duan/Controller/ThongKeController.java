package com.example.duan.controller;

import com.example.duan.entity.StatusValue;
import com.example.duan.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/thong-ke")
public class ThongKeController {
    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/hoa-don")
    private String Thongke(Model model) {
        List<StatusValue> statusValues = Arrays.asList(new StatusValue[]{new StatusValue(0, "Chờ giao hàng"), new StatusValue(1, "Đang chuẩn bị"), new StatusValue(2, "Đang giao hàng"), new StatusValue(3, "Hoàn thành"), new StatusValue(4, "đã hủy"), new StatusValue(5, "Yêu cầu hoàn trả"), new StatusValue(6, "Đã hoàn trả")});
        model.addAttribute("statusValues", statusValues);
        model.addAttribute("sumAll", this.hoaDonService.tongSoDonHang());
        model.addAttribute("sumDHShipping", this.hoaDonService.soDonHangTheoTrangThai(Integer.valueOf(2)));
        model.addAttribute("sumDHDone", this.hoaDonService.soDonHangTheoTrangThai(Integer.valueOf(3)));
        model.addAttribute("sumDHCancel", this.hoaDonService.soDonHangTheoTrangThai(Integer.valueOf(4)));
        model.addAttribute("sumDHReturn", this.hoaDonService.soDonHangTheoTrangThai(Integer.valueOf(6)));
        return "ThongKe/ThongKehoadon";
    }
}
