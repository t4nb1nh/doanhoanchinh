package com.example.duan.service;

import com.example.duan.entity.NhanVien;
import com.example.duan.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;

    public List<NhanVien> getAll(){
        return nhanVienRepository.findAll();
    };

    public NhanVien addNhanVien(NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }

    public NhanVien getById(int id) {
        return nhanVienRepository.findById(id).orElse(null);
    }

    public void updateNhanVien(int Id, NhanVien nhanVien) {
        // Kiểm tra xem nhân viên đã tồn tại trong cơ sở dữ liệu hay chưa
        Optional<NhanVien> existingNhanVien = nhanVienRepository.findById(Id);

        if (existingNhanVien.isPresent()) {
            NhanVien updatedNhanVien = existingNhanVien.get();
            updatedNhanVien.setHoTen(nhanVien.getHoTen());
            updatedNhanVien.setGioiTinh(nhanVien.getGioiTinh());
            updatedNhanVien.setNgaySinh(nhanVien.getNgaySinh());
            updatedNhanVien.setDiaChi(nhanVien.getDiaChi());
            updatedNhanVien.setSoDienThoai(nhanVien.getSoDienThoai());
            updatedNhanVien.setEmail(nhanVien.getEmail());
            updatedNhanVien.setMatKhau(nhanVien.getMatKhau());
            updatedNhanVien.setVaiTro(nhanVien.getVaiTro());
            updatedNhanVien.setNgayTao(nhanVien.getNgayTao());
            updatedNhanVien.setTrangThai(nhanVien.getTrangThai());
            nhanVienRepository.save(updatedNhanVien);
        }
    }

    public void deleteNhanVien(int id) {
        nhanVienRepository.deleteById(id);
    }
}
