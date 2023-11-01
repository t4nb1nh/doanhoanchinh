package com.example.duan.repository;

import com.example.duan.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    @Query("SELECT count(hd) FROM HoaDon hd ")
    Integer tongSoDonHang();
    @Query("SELECT count(hd) FROM HoaDon hd WHERE hd.trangThai = ?1")
    Integer soDonHangTheoTrangThai(Integer trangThai);
    @Query("SELECT d\n    FROM HoaDon d\n")
    Page<HoaDon> findAll(Pageable paramPageable);
    @Query("SELECT dh FROM HoaDon dh WHERE UPPER(CONCAT( dh.maHoaDon, ' ', dh.khachHang.ten, '')) LIKE %?1% ")
    Page<HoaDon> findAllPagination(String paramString, Pageable paramPageable);

    @Query("SELECT dh FROM HoaDon dh WHERE UPPER(CONCAT( dh.maHoaDon, ' ', dh.khachHang.ten, '')) LIKE %?1% AND dh.trangThai = ?2")
    Page<HoaDon> findAllPaginationStatus(String paramString, Pageable paramPageable, Integer paramInteger);
}
