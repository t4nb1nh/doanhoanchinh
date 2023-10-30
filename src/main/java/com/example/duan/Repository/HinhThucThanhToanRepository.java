package com.example.duan.repository;

import com.example.duan.entity.HinhThucThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface   HinhThucThanhToanRepository extends JpaRepository<HinhThucThanhToan,Integer> {
    @Query("SELECT p FROM HinhThucThanhToan p WHERE p.ten = ?1")
    List<HinhThucThanhToan> findByMa(String ma);
}
