package com.example.duan.repository;

import com.example.duan.entity.LoaiSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPham, Integer> {
}