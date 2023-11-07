package com.example.duan.service;

import com.example.duan.entity.GioHang;
import com.example.duan.entity.GioHangChiTiet;
import com.example.duan.repository.GioHangRepository;
import com.example.duan.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class GioHangService {

    @Autowired
    private GioHangChiTietService gioHangChiTietService;

    public BigDecimal calculateTotalPriceOfGioHang(int gioHangId) {
        List<GioHangChiTiet> gioHangChiTietList = gioHangChiTietService.getGioHangChiTietByGioHang(gioHangId);

        BigDecimal tongTien = BigDecimal.ZERO;
        for (GioHangChiTiet gioHangChiTiet : gioHangChiTietList) {
            tongTien = tongTien.add(gioHangChiTiet.getTongGia());
        }

        return tongTien;
    }

}
