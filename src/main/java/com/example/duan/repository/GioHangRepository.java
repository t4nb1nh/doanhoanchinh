package com.example.duan.repository;

import com.example.duan.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang,Integer> {

        GioHang findGioHangByIdGioHang(int gioHangId);
}
