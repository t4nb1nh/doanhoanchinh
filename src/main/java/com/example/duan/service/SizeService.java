package com.example.duan.service;

import com.example.duan.entity.Size;
import com.example.duan.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    public List<Size> getAll(){
        return sizeRepository.findAll();
    };
}
