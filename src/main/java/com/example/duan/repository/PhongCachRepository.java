package com.example.duan.repository;

import com.example.duan.entity.PhongCach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhongCachRepository extends JpaRepository<PhongCach, Integer> {
}
