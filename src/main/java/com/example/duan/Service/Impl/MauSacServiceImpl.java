package com.example.duan.Service.Impl;

import com.example.duan.Entity.MauSac;
import com.example.duan.Repository.MauSacRepository;
import com.example.duan.Service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MauSacServiceImpl implements MauSacService {
    @Autowired
    private MauSacRepository mauSacRepository;
    @Override
    public List<MauSac> findAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public void saveMauSac(MauSac mauSac) {
         mauSacRepository.save(mauSac);
    }

    @Override
    public MauSac findByID(Integer id) {
        return mauSacRepository.findById(id).orElse(null);
    }

    @Override
    public MauSac update( MauSac mauSac, Integer id) {
        Optional<MauSac> optional = mauSacRepository.findById(id);
        return optional.map(phieuGiaoHang -> {
            phieuGiaoHang.setMa(mauSac.getMa());
            phieuGiaoHang.setNgayTao(mauSac.getNgayTao());
            phieuGiaoHang.setTen(mauSac.getTen());
            phieuGiaoHang.setTrangThai(mauSac.getTrangThai());

            return mauSacRepository.save(phieuGiaoHang);
        }).orElse(null);
    }

    @Override
    public void delete(Integer id) {
    mauSacRepository.deleteById(id);
    }
}
