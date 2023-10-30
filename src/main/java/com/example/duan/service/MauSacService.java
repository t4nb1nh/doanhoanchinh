package com.example.duan.service;

import com.example.duan.entity.MauSac;
import com.example.duan.repository.MauSacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MauSacService {
    @Autowired
    private MauSacRepository mauSacRepository;

    public List<MauSac> getAll(){
        return mauSacRepository.findAll();
    };
}
