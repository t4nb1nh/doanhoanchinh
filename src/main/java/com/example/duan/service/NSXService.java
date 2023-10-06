package com.example.duan.service;

import com.example.duan.entity.NSX;

import java.util.List;

public interface NSXService {

    List<NSX> getAll();

    NSX add(NSX nsx);

    void delete(Integer id);

    NSX detail(Integer id);

    NSX update(NSX nsx, Integer id);

}
