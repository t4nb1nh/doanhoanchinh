package com.example.duan.service;

import com.example.duan.entity.SanPham;
import com.example.duan.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamService {
    @Autowired
    private SanPhamRepository sanPhamRepository;

    public List<SanPham> getAll(){
        return sanPhamRepository.findAll();
    };

    public SanPham addSanPham(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }

    public SanPham getById(int id) {
        return sanPhamRepository.findById(id).orElse(null);
    }

    public void updateSanPham(int Id, SanPham sanPham) {
        Optional<SanPham> existingSanPham = sanPhamRepository.findById(Id);

        if (existingSanPham.isPresent()) {
            SanPham updatedSanPham = existingSanPham.get();
            updatedSanPham.setMa(sanPham.getMa());
            updatedSanPham.setTen(sanPham.getTen());
            updatedSanPham.setNgayTao(sanPham.getNgayTao());
            updatedSanPham.setTrangThai(sanPham.isTrangThai());
            sanPhamRepository.save(updatedSanPham);
        }
    }

    public void deleteSanPham(int id) {
        sanPhamRepository.deleteById(id);
    }
}
