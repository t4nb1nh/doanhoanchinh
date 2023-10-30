package com.example.duan.service;

import com.example.duan.entity.ChiTietSanPham;
import com.example.duan.entity.GioHang;
import com.example.duan.entity.KhachHang;
import com.example.duan.repository.ChiTietSanPhamRepository;
import com.example.duan.repository.GioHangRepository;
import com.example.duan.repository.KhachHangRepository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GioHangService {
    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    public List<GioHang> findGioHangByIdKhachHang(int idKhacHang){
        return gioHangRepository.findGioHangByKhachHangId(idKhacHang);
    }

    public void themChiTietSanPhamVaoGioHang(int khachHangId, int chiTietSanPhamId) {
        // Tìm khách hàng theo ID
        KhachHang khachHang = khachHangRepository.findById(khachHangId).orElse(null);

        if (khachHang != null) {
            // Tìm tất cả giỏ hàng của khách hàng
            List<GioHang> gioHangs = gioHangRepository.findGioHangByKhachHangId(khachHangId);

            // Tìm chi tiết sản phẩm theo ID
            ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(chiTietSanPhamId).orElse(null);

            if (chiTietSanPham != null) {
                boolean timThayTrongGioHang = false;

                // Kiểm tra xem chi tiết sản phẩm đã có trong bất kỳ giỏ hàng nào chưa
                for (GioHang gioHang : gioHangs) {
                    if (gioHang.getChiTietSanPham() != null && gioHang.getChiTietSanPham().getIdChiTietSanPham() == chiTietSanPhamId) {
                        // Nếu đã có, tăng số lượng
                        gioHang.setSoLuong(gioHang.getSoLuong() + 1);
                        gioHangRepository.save(gioHang);
                        timThayTrongGioHang = true;
                        break;
                    }
                }

                if (!timThayTrongGioHang) {
                    // Nếu chưa có, tạo giỏ hàng mới và thêm chi tiết sản phẩm vào đó
                    GioHang gioHangMoi = new GioHang();
                    gioHangMoi.setKhachHang(khachHang);
                    gioHangMoi.setChiTietSanPham(chiTietSanPham);
                    gioHangMoi.setSoLuong(1);
                    gioHangRepository.save(gioHangMoi);
                }
            }
        }
    }

    public void xoaGioHang(int gioHangId) {
        gioHangRepository.deleteById(gioHangId);
    }
}
