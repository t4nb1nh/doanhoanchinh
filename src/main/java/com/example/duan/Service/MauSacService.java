package com.example.duan.service;

import com.example.duan.entity.MauSac;

import java.util.List;

public interface MauSacService {
    List<MauSac> findAll();

    void saveMauSac(MauSac mauSac);

    MauSac findByID(Integer id);

    MauSac update(MauSac mauSac);

    void delete(Integer id);

    List<MauSac> findMauSacByMa(String ma);
}
