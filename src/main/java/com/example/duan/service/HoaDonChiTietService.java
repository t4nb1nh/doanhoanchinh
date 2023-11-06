package com.example.duan.service;

import com.example.duan.entity.GioHangChiTiet;
import com.example.duan.entity.HoaDon;
import com.example.duan.entity.HoaDonChiTiet;
import com.example.duan.repository.HoaDonChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoaDonChiTietService {
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    public HoaDonChiTiet createHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        // Thực hiện các thao tác khác để thiết lập thông tin hóa đơn chi tiết (nếu cần)
        return hoaDonChiTietRepository.save(hoaDonChiTiet); // Lưu hóa đơn chi tiết vào cơ sở dữ liệu
    }
}
