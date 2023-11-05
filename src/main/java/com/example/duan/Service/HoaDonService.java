package com.example.duan.service;

import com.example.duan.entity.HoaDon;
import com.example.duan.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HoaDonService {
    @Autowired
    HoaDonRepository hoaDonRepository;

    public Integer soDonHangTheoTrangThai(Integer trangThaiDonhang) {
        return this.hoaDonRepository.soDonHangTheoTrangThai(trangThaiDonhang);
    }

    public Integer tongSoDonHang() {
        return this.hoaDonRepository.tongSoDonHang();
    }

    public Page<HoaDon> listByPage(int pageNumber, String sortField, String sortDir, String keyword) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 10, Sort.by(SortOrder(sortField, sortDir)));
        if (keyword != null)

            return this.hoaDonRepository.findAllPagination(keyword, (Pageable)pageRequest);

        return this.hoaDonRepository.findAll((Pageable)pageRequest);
    }
    public  Page<HoaDon> listByPageStatus(int pageNumber, String sortField, String sortDir, String keyword, int status) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 10, Sort.by(SortOrder(sortField, sortDir)));
        if (keyword != null)
            return this.hoaDonRepository.findAllPaginationStatus(keyword, (Pageable)pageRequest, Integer.valueOf(status));
        return this.hoaDonRepository.findAll((Pageable)pageRequest);
    }
    public List<Sort.Order> SortOrder(String sort, String sortDirection) {
        Sort.Direction direction;
        List<Sort.Order> sorts = new ArrayList<>();
        if (sortDirection != null) {
            direction = Sort.Direction.fromString(sortDirection);
        } else {
            direction = Sort.Direction.DESC;
        }
        sorts.add(new Sort.Order(direction, sort));
        return sorts;
    }
}
