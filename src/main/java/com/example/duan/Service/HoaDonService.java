package com.example.duan.service;

import com.example.duan.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoaDonService {
    @Autowired
    HoaDonRepository hoaDonRepository;
    public Integer soDonHangTheoTrangThai(Integer trangThaiDonhang) {
        return this.hoaDonRepository.soDonHangTheoTrangThai(trangThaiDonhang);
    }

    public Integer tongSoDonHang() {
        return this.hoaDonRepository.tongSoDonHang();
    }
}
