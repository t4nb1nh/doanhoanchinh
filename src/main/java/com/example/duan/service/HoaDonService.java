package com.example.duan.service;

import com.example.duan.entity.GioHang;
import com.example.duan.entity.HoaDon;
import com.example.duan.entity.HoaDonChiTiet;
import com.example.duan.entity.KhachHang;
import com.example.duan.repository.GioHangRepository;
import com.example.duan.repository.HoaDonChiTietRepository;
import com.example.duan.repository.HoaDonRepository;
import com.example.duan.repository.KhachHangRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    public List<HoaDon> getAll(){
        return hoaDonRepository.findAll();
    };
    public List<HoaDon> getHoaDonByTrangThai(int trangThai) {
        return hoaDonRepository.findHoaDonByTrangThai(trangThai);
    }

    @Transactional
    public boolean updateTrangThaiHoaDon(int hoaDonId, int newTrangThai) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(hoaDonId);
        if (optionalHoaDon.isPresent()) {
            HoaDon hoaDon = optionalHoaDon.get();
            hoaDon.setTrangThai(newTrangThai);
            hoaDonRepository.save(hoaDon);
            return true; // Cập nhật thành công
        } else {
            return false; // Hóa đơn không tồn tại
        }
    }

    @Transactional
    public boolean xuLyThanhToan(int idKhachHang, List<GioHang> gioHangItems) {
        // Kiểm tra xem có sản phẩm trong giỏ hàng hay không
        if (gioHangItems.isEmpty()) {
            return false; // Giỏ hàng trống, không thể thanh toán
        }

        // Tạo một hóa đơn từ giỏ hàng
        HoaDon hoaDon = taoHoaDon(idKhachHang, gioHangItems);

        // Lưu hóa đơn vào cơ sở dữ liệu
        hoaDon = hoaDonRepository.save(hoaDon);

        // Tạo hóa đơn chi tiết cho từng mục trong giỏ hàng
        for (GioHang gioHangItem : gioHangItems) {
            taoHoaDonChiTiet(hoaDon, gioHangItem);
        }

        return true; // Thanh toán thành công
    }


    private HoaDon taoHoaDon(int idKhachHang, List<GioHang> gioHangItems) {
        // Tạo một hóa đơn mới
        HoaDon hoaDon = new HoaDon();
        KhachHang khachHang = new KhachHang();
        khachHang.setIdKhachHang(idKhachHang); // Thiết lập ID khách hàng trực tiếp
        hoaDon.setKhachHang(khachHang);
        hoaDon.setNgayTao(new Date());
        hoaDon.setHTTT(true);
        hoaDon.setPhuongThucMuaHang(true);
        hoaDon.setTrangThai(3); // Trạng thái mặc định
        hoaDon.setTongTien(tinhTongTien(gioHangItems)); // Tính tổng cộng từ giỏ hàng
        return hoaDon;
    }



    private void taoHoaDonChiTiet(HoaDon hoaDon, GioHang gioHangItem) {
        // Tạo hóa đơn chi tiết cho từng mục trong giỏ hàng
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setGioHang(gioHangItem); // Đặt giỏ hàng cho hóa đơn chi tiết
        hoaDonChiTiet.setSoLuong(gioHangItem.getSoLuong());

        // Lấy giá từ chi tiết sản phẩm trong giỏ hàng
        BigDecimal giaSanPham = gioHangItem.getChiTietSanPham().getGiaBan();
        hoaDonChiTiet.setGia(giaSanPham);

        // Tính tổng tiền cho hóa đơn chi tiết
        BigDecimal tongTien = giaSanPham.multiply(BigDecimal.valueOf(hoaDonChiTiet.getSoLuong()));
        hoaDonChiTiet.setTongTien(tongTien);

        hoaDonChiTietRepository.save(hoaDonChiTiet);
    }


    private BigDecimal tinhTongTien(List<GioHang> gioHangItems) {
        BigDecimal tongTien = BigDecimal.ZERO;
        for (GioHang gioHangItem : gioHangItems) {
            BigDecimal gia = gioHangItem.getChiTietSanPham().getGiaBan();
            int soLuong = gioHangItem.getSoLuong();
            tongTien = tongTien.add(gia.multiply(BigDecimal.valueOf(soLuong)));
        }
        return tongTien;
    }
}
