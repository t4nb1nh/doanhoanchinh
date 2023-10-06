package com.example.duan.service;

import com.example.duan.entity.KhuyenMai;

import java.util.List;

public interface KhuyenMaiService {

    List<KhuyenMai> getAll();

    KhuyenMai add(KhuyenMai km);

    void delete(Integer id);

    KhuyenMai detail(Integer id);

    KhuyenMai update(KhuyenMai km, Integer id);

}
