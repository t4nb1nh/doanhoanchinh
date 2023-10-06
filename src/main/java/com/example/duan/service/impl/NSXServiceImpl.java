package com.example.duan.service.impl;

import com.example.duan.entity.NSX;
import com.example.duan.repository.NSXRepository;
import com.example.duan.service.NSXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NSXServiceImpl implements NSXService {

    @Autowired
    private NSXRepository nsxRepository;

    @Override
    public List<NSX> getAll() {
        return nsxRepository.findAll();
    }

    @Override
    public NSX add(NSX nsx) {
        return nsxRepository.save(nsx);
    }

    @Override
    public void delete(Integer id) {
        nsxRepository.deleteById(id);
    }

    @Override
    public NSX detail(Integer id) {
        for (NSX nsx : nsxRepository.findAll()) {
            if (nsx.getIdNSX().equals(id)) {
                return nsx;
            }
        }
        return null;
    }

    @Override
    public NSX update(NSX nsx, Integer id) {
        NSX n = nsxRepository.findById(id).orElse(null);
        if (n == null) {
            return null;
        }
        n.setMa(n.getMa());
        n.setTen(n.getTen());
        n.setNgayTao(n.getNgayTao());
        n.setTrangThai(n.isTrangThai());
        return nsxRepository.save(n);
    }

}
