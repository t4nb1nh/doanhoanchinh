package com.example.duan.Service;

import com.example.duan.Entity.MauSac;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface MauSacService {
    List<MauSac> findAll();

    void saveMauSac(MauSac mauSac);

    MauSac findByID(Integer id);

    MauSac update(MauSac mauSac, Integer id);

    void delete(Integer id);
}
