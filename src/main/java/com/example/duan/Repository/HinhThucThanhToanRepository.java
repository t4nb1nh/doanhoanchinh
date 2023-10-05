package com.example.duan.Repository;

import com.example.duan.Entity.HinhThucThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface   HinhThucThanhToanRepository extends JpaRepository<HinhThucThanhToan,Integer> {
}
