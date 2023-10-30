package com.example.duan.service;

import com.example.duan.entity.ChiTietSanPham;
import com.example.duan.repository.ChiTietSanPhamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietSanPhamService {

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    public List<ChiTietSanPham> getAll() {
        return chiTietSanPhamRepository.findAll();
    }

    public List<ChiTietSanPham> searchSanPham(String ten){
        return chiTietSanPhamRepository.findChiTietSanPhamByTenSanPhamContaining(ten);
    }
}
