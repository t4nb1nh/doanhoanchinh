package com.example.duan.service.Impl;

import com.example.duan.entity.MauSac;
import com.example.duan.repository.MauSacRepository;
import com.example.duan.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public MauSac update( MauSac mauSac) {
        Optional<MauSac> optional = mauSacRepository.findById(mauSac.getIdMauSac());

//        return optional.map(phieuGiaoHang -> {
//            phieuGiaoHang.setMa(mauSac.getMa());
//            phieuGiaoHang.setNgayTao(mauSac.getNgayTao());
//            phieuGiaoHang.setTen(mauSac.getTen());
//            phieuGiaoHang.setTrangThai(mauSac.getTrangThai());
//
//            return mauSacRepository.save(phieuGiaoHang);
//        }).orElse(null);
        MauSac mauSac1 = optional.get();
        mauSac1.setMa(mauSac.getMa());
        mauSac1.setNgayTao(mauSac.getNgayTao());
           mauSac1.setTen(mauSac.getTen());
            mauSac1.setTrangThai(mauSac.getTrangThai());
            return mauSacRepository.save(mauSac1);
    }

    @Override
    public void delete(Integer id) {
    mauSacRepository.deleteById(id);
    }

    @Override
    public List<MauSac> findMauSacByMa(String ma) {
        return mauSacRepository.findByMa(ma);
    }
}
