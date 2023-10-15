package com.example.duan.Service;

import com.example.duan.Entity.HinhThucThanhToan;
import com.example.duan.Entity.MauSac;

import java.util.List;

public interface HinhThucThanhToanService {
    List<HinhThucThanhToan> findAll();

    void saveMauSac(HinhThucThanhToan mauSac);

    HinhThucThanhToan findByID(Integer id);

    HinhThucThanhToan update(HinhThucThanhToan mauSac);

    void delete(Integer id);

    List<HinhThucThanhToan> findMauSacByMa(String ma);
}
