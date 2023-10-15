package com.example.duan.Repository;

import com.example.duan.Entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac,Integer> {
    @Query("SELECT p FROM MauSac p WHERE p.ma = ?1")
    List<MauSac> findByMa(String ma);
}
