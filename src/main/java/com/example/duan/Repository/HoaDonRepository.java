package com.example.duan.repository;

import com.example.duan.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    @Query("SELECT count(hd) FROM HoaDon hd ")
    Integer tongSoDonHang();
    @Query("SELECT count(hd) FROM HoaDon hd WHERE dh.trangThai = ?1")
    Integer soDonHangTheoTrangThai(Integer trangThai);
}
