package com.example.duan.service;

import com.example.duan.entity.MauSac;
import com.example.duan.repository.MauSacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MauSacService {
    @Autowired
    private MauSacRepository MauSacRepository;

    public List<MauSac> getAll(){
        return MauSacRepository.findAll();
    };

    public MauSac addMauSac(MauSac MauSac) {
        return MauSacRepository.save(MauSac);
    }

    public MauSac getById(int id) {
        return MauSacRepository.findById(id).orElse(null);
    }

    public void updateMauSac(int Id, MauSac MauSac) {
        Optional<MauSac> existingMauSac = MauSacRepository.findById(Id);

        if (existingMauSac.isPresent()) {
            MauSac updatedMauSac = existingMauSac.get();
            updatedMauSac.setMa(MauSac.getMa());
            updatedMauSac.setTen(MauSac.getTen());
            updatedMauSac.setNgayTao(MauSac.getNgayTao());
            updatedMauSac.setTrangThai(MauSac.isTrangThai());
            MauSacRepository.save(updatedMauSac);
        }
    }

    public void deleteMauSac(int id) {
        MauSacRepository.deleteById(id);
    }
}
