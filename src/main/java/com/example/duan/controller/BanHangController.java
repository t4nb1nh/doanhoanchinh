package com.example.duan.controller;

import com.example.duan.entity.*;
import com.example.duan.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/ban-hang")
@SessionAttributes({"kh", "dsSanPham"})
public class BanHangController {
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private ChiTietSanPhamService ctspService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private GioHangService gioHangService;
    @Autowired
    private HoaDonService hoaDonService;


    @GetMapping()
    public String getAll(Model model) {
        List<KhachHang> listKH = khachHangService.getAll();
        model.addAttribute("listKH", listKH);
        List<GioHang> listGioHang = gioHangService.findGioHangByIdKhachHang(3);
        model.addAttribute("listGH", listGioHang);
        return "BanHang/Index";
    }

    @PostMapping("/search/khach-hang")
    public String searchKhachHang(@RequestParam("sdt") String sdt, Model model) {
        KhachHang kh = khachHangService.searchKhachHang(sdt);
        model.addAttribute("kh", kh);
        return "redirect:/admin/ban-hang?khId=" + (kh != null ? kh.getIdKhachHang() : "");
    }


    @PostMapping("/search/san-pham")
    public String searchSanPham(@RequestParam("ten") String ten, Model model) {
        List<ChiTietSanPham> danhSachSanPham = ctspService.searchSanPham(ten);
        List<Size> dsSize = sizeService.getAll();
        List<MauSac> dsMauSac = mauSacService.getAll();
        model.addAttribute("dsSanPham", danhSachSanPham);
        model.addAttribute("dsSize", dsSize);
        model.addAttribute("dsMauSac", dsMauSac);
        return "redirect:/admin/ban-hang";
    }

    @PostMapping("/them-san-pham")
    public String themChiTietSanPham(
            @RequestParam("khachHangId") int khachHangId,
            @RequestParam("chiTietSanPhamId") int chiTietSanPhamId,
            RedirectAttributes redirectAttributes) {
        gioHangService.themChiTietSanPhamVaoGioHang(khachHangId, chiTietSanPhamId);
        return "redirect:/admin/ban-hang";
    }

    @GetMapping("/xoa-gio-hang/{gioHangId}")
    public String xoaGioHang(@PathVariable int gioHangId) {
        gioHangService.xoaGioHang(gioHangId);
        return "redirect:/admin/ban-hang";
    }

    @PostMapping("/thanh-toan")
    public String thanhToan(@ModelAttribute("kh") KhachHang kh, RedirectAttributes redirectAttributes) {
        if (kh != null) {
            List<GioHang> listGioHang = gioHangService.findGioHangByIdKhachHang(kh.getIdKhachHang());

            if (listGioHang != null && !listGioHang.isEmpty()) {
                // Tạo hóa đơn và hóa đơn chi tiết từ giỏ hàng
                boolean thanhToanThanhCong = hoaDonService.xuLyThanhToan(kh.getIdKhachHang(), listGioHang);

                if (thanhToanThanhCong) {
                    // Thanh toán thành công, xóa thông tin khách hàng khỏi session.
                    redirectAttributes.addFlashAttribute("kh", null);

                    return "redirect:/admin/ban-hang";
                } else {
                    // Xử lý lỗi hoặc chuyển hướng lại trang chọn sản phẩm.
                    return "redirect:/admin/ban-hang";
                }
            } else {
                // Xử lý lỗi hoặc chuyển hướng lại trang chọn sản phẩm.
                return "redirect:/admin/ban-hang";
            }
        } else {
            // Xử lý lỗi hoặc chuyển hướng lại trang chọn sản phẩm.
            return "redirect:/admin/ban-hang";
        }
    }
}


