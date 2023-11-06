package com.example.duan.controller;

import com.example.duan.entity.*;
import com.example.duan.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/admin/ban-hang")
@SessionAttributes({"dsSanPham"})
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
    @Autowired
    private GioHangChiTietService gioHangChiTietService;
    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping()
    public String getAll(Model model, HttpSession session) {
        KhachHang khachhang = (KhachHang) session.getAttribute("kh");
        if (khachhang == null) {
            khachhang = khachHangService.createKhachHangWithGioHang(new KhachHang());
            session.setAttribute("kh", khachhang);
            List<GioHangChiTiet> gioHangChiTietList =
                    gioHangChiTietService.getGioHangChiTietByGioHang(khachhang.getGioHang().getIdGioHang());
            session.setAttribute("listGHCT", gioHangChiTietList);
            model.addAttribute("kh", khachhang);
            model.addAttribute("listGHCT", gioHangChiTietList);
        } else {
            List<GioHangChiTiet> gioHangChiTietList =
                    gioHangChiTietService.getGioHangChiTietByGioHang(khachhang.getGioHang().getIdGioHang());
            model.addAttribute("kh", khachhang);
            model.addAttribute("listGHCT", gioHangChiTietList);
            session.setAttribute("listGHCT", gioHangChiTietList);
        }
        BigDecimal tongTien = gioHangService.calculateTotalPriceOfGioHang(khachhang.getGioHang().getIdGioHang());
        model.addAttribute("tongTien", tongTien);
        String maHD = (String) session.getAttribute("maHD");
        if (maHD == null) {
            maHD = generateRandomMaHoaDon();
            model.addAttribute("maHD", maHD);
            session.setAttribute("maHD", maHD);
        } else {
            model.addAttribute("maHD", maHD);
        }
        LocalDate ngayHienTai = LocalDate.now();
        model.addAttribute("ngayTao", ngayHienTai);
        return "BanHang/Index";
    }


    private String generateRandomMaHoaDon() {
        // Tạo số ngẫu nhiên 10 chữ số
        Random random = new Random();
        int randomDigits = 1000000000 + random.nextInt(900000000);
        String maHoaDon = "HĐ" + randomDigits;
        return maHoaDon;
    }


    @PostMapping("/search/khach-hang")
    public String searchKhachHang(@RequestParam("sdt") String sdt, Model model, HttpSession session) {
        KhachHang kh = khachHangService.searchKhachHang(sdt);
        if (kh != null && kh.getIdKhachHang() != null) {
            session.removeAttribute("kh");
            model.addAttribute("kh", kh);
            session.setAttribute("kh", kh);
        }
        return "redirect:/admin/ban-hang";
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
    public String addOrUpdateCartItem(@RequestParam("chiTietSanPhamId") Integer chiTietSanPhamId, HttpSession session) {
        KhachHang khachhang = (KhachHang) session.getAttribute("kh");
        Integer gioHangId = khachhang.getGioHang().getIdGioHang();
        gioHangChiTietService.addOrUpdateCartItem(gioHangId, chiTietSanPhamId);
        return "redirect:/admin/ban-hang"; // Chuyển hướng đến một trang web khác sau khi thêm hoặc cập nhật giỏ hàng chi tiết
    }


    @PostMapping("/thanh-toan")
    public String thanhToan(HttpSession session) {
        String maHD = (String) session.getAttribute("maHD");
        NhanVien nv = nhanVienService.getById(1);
        List<GioHangChiTiet> listGioHangChiTiet = (List<GioHangChiTiet>) session.getAttribute("listGHCT");
        if (listGioHangChiTiet != null && !listGioHangChiTiet.isEmpty()) {
            hoaDonService.xuLyThanhToan(maHD,nv, listGioHangChiTiet);
            session.removeAttribute("kh");
            session.removeAttribute("maHD");
            session.removeAttribute("listGHCT");
            System.out.println("Thanh Toán Thành Công");
            return "redirect:/admin/ban-hang";
        } else {
            System.out.println("Thanh Toán Thất Bại");
            return "redirect:/trang-loi-thanh-toan";
        }
    }


}



