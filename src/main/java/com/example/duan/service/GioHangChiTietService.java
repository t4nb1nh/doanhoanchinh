package com.example.duan.service;

import com.example.duan.entity.ChiTietSanPham;
import com.example.duan.entity.GioHang;
import com.example.duan.entity.GioHangChiTiet;

import com.example.duan.repository.ChiTietSanPhamRepository;
import com.example.duan.repository.GioHangChiTietRepository;
import com.example.duan.repository.GioHangRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GioHangChiTietService {
    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;
    @Autowired
    private GioHangRepository gioHangRepository;
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    public List<GioHangChiTiet> getGioHangChiTietByGioHang(Integer idGioHang) {
        return gioHangChiTietRepository.findByGioHangIdAndTrangThai(idGioHang);
    }

    public GioHangChiTiet addOrUpdateCartItem(Integer gioHangId, Integer chiTietSanPhamId) {
        // Tìm kiếm giỏ hàng chi tiết dựa trên giỏ hàng và chi tiết sản phẩm
        GioHang gioHang = gioHangRepository.findById(gioHangId).orElse(null);
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(chiTietSanPhamId).orElse(null);
        GioHangChiTiet existingCartItem =
                gioHangChiTietRepository.findByGioHangIdAndChiTietSanPhamId(gioHangId, chiTietSanPhamId);
        if (existingCartItem != null) {
            // Giỏ hàng chi tiết đã tồn tại, tăng số lượng lên 1
            existingCartItem.setSoLuong(existingCartItem.getSoLuong() + 1);
            existingCartItem.setTrangThai(true);
            existingCartItem.setTongGia(BigDecimal.valueOf(existingCartItem.getSoLuong()).multiply(chiTietSanPham.getGiaBan()));
            gioHangChiTietRepository.save(existingCartItem);
        } else {
            if (gioHang != null && chiTietSanPham != null) {
                // Tạo một đối tượng GioHangChiTiet và kết nối nó với GioHang và ChiTietSanPham
                GioHangChiTiet newCartItem = new GioHangChiTiet();
                newCartItem.setGioHang(gioHang);
                newCartItem.setChiTietSanPham(chiTietSanPham);
                newCartItem.setSoLuong(1);
                newCartItem.setTrangThai(true);
                // Thực hiện tính toán tổng giá và các hoạt động khác
                newCartItem.setTongGia(BigDecimal.valueOf(newCartItem.getSoLuong()).multiply(chiTietSanPham.getGiaBan()));
                // Lưu đối tượng GioHangChiTiet vào cơ sở dữ liệu
                gioHangChiTietRepository.save(newCartItem);
            } else {
                // Xử lý trường hợp không tìm thấy GioHang hoặc ChiTietSanPham
            }
        }
        return existingCartItem;
    }

    @Transactional
    public GioHangChiTiet updateTrangThaiToFalse(GioHangChiTiet gioHangChiTiet) {
        if (gioHangChiTiet != null) {
            gioHangChiTiet.setTrangThai(false);
            return gioHangChiTietRepository.save(gioHangChiTiet);
        } else {
            // Xử lý trường hợp giỏ hàng chi tiết là null
            return null;
        }
    }
}
