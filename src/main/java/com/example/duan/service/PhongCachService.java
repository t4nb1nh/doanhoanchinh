package com.example.duan.service;

import com.example.duan.entity.PhongCach;
import com.example.duan.repository.PhongCachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhongCachService {

    @Autowired
    private PhongCachRepository repository;

    public List<PhongCach> getAll() {
        return repository.findAll();
    }

    public PhongCach addPhongCach(PhongCach phongCach) {
        return repository.save(phongCach);
    }

    public PhongCach getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public void updatePhongCach(int Id, PhongCach PhongCach) {
        Optional<PhongCach> existingPhongCach = repository.findById(Id);

        if (existingPhongCach.isPresent()) {
            PhongCach updatedPhongCach = existingPhongCach.get();
            updatedPhongCach.setMa(PhongCach.getMa());
            updatedPhongCach.setTen(PhongCach.getTen());
            updatedPhongCach.setNgayTao(PhongCach.getNgayTao());
            updatedPhongCach.setTrangThai(PhongCach.isTrangThai());
            repository.save(updatedPhongCach);
        }
    }

    public void deletePhongCach(int id) {
        repository.deleteById(id);
    }
}
