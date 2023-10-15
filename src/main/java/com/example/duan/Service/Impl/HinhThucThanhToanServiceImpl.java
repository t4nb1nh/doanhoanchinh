package com.example.duan.Service.Impl;

import com.example.duan.Entity.HinhThucThanhToan;
import com.example.duan.Repository.HinhThucThanhToanRepository;
import com.example.duan.Service.HinhThucThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HinhThucThanhToanServiceImpl implements HinhThucThanhToanService {
    @Autowired
    private HinhThucThanhToanRepository hinhThucThanhToanRepository;
    @Override
    public List<HinhThucThanhToan> findAll() {
        return hinhThucThanhToanRepository.findAll();
    }

    @Override
    public void saveMauSac(HinhThucThanhToan mauSac) {
        hinhThucThanhToanRepository.save(mauSac);
    }

    @Override
    public HinhThucThanhToan findByID(Integer id) {
        return hinhThucThanhToanRepository.findById(id).orElse(null);
    }

    @Override
    public HinhThucThanhToan update( HinhThucThanhToan mauSac) {
        Optional<HinhThucThanhToan> optional = hinhThucThanhToanRepository.findById(mauSac.getIdHTTT());

//        return optional.map(phieuGiaoHang -> {
//            phieuGiaoHang.setMa(mauSac.getMa());
//            phieuGiaoHang.setNgayTao(mauSac.getNgayTao());
//            phieuGiaoHang.setTen(mauSac.getTen());
//            phieuGiaoHang.setTrangThai(mauSac.getTrangThai());
//
//            return mauSacRepository.save(phieuGiaoHang);
//        }).orElse(null);
        HinhThucThanhToan mauSac1 = optional.get();
        mauSac1.setTen(mauSac.getTen());
        mauSac1.setTrangThai(mauSac.getTrangThai());
        return hinhThucThanhToanRepository.save(mauSac1);
    }

    @Override
    public void delete(Integer id) {
        hinhThucThanhToanRepository.deleteById(id);
    }

    @Override
    public List<HinhThucThanhToan> findMauSacByMa(String ma) {
        return hinhThucThanhToanRepository.findByMa(ma);
    }
}
