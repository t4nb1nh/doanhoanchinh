package com.example.duan.service;

import com.example.duan.entity.KhachHang;
import com.example.duan.entity.KhachHang;
import com.example.duan.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    public List<KhachHang> getAll(){
        return khachHangRepository.findAll();
    };

    public KhachHang addKhachHang(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }

    public KhachHang getById(int id) {
        return khachHangRepository.findById(id).orElse(null);
    }

    public void updateKhachHang(int Id, KhachHang KhachHang) {
        Optional<KhachHang> existingKhachHang = khachHangRepository.findById(Id);

        if (existingKhachHang.isPresent()) {
            KhachHang updatedKhachHang = existingKhachHang.get();
            updatedKhachHang.setTen(KhachHang.getTen());
            updatedKhachHang.setGioiTinh(KhachHang.getGioiTinh());
            updatedKhachHang.setNgaySinh(KhachHang.getNgaySinh());
            updatedKhachHang.setDiaChi(KhachHang.getDiaChi());
            updatedKhachHang.setSoDienThoai(KhachHang.getSoDienThoai());
            updatedKhachHang.setEmail(KhachHang.getEmail());
            updatedKhachHang.setMatKhau(KhachHang.getMatKhau());
            updatedKhachHang.setNgayTao(KhachHang.getNgayTao());
            updatedKhachHang.setTrangThai(KhachHang.getTrangThai());
            khachHangRepository.save(updatedKhachHang);
        }
    }

    public void deleteKhachHang(int id) {
        khachHangRepository.deleteById(id);
    }
}
