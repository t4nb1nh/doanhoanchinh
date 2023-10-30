package com.example.duan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chitietsanpham")
public class ChiTietSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idchitietsanpham")
    private Integer idChiTietSanPham;

    @Column(name = "tensanpham")
    private String tenSanPham;

    @ManyToOne
    @JoinColumn(name = "idsanpham")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "idchatlieu")
    private ChatLieu chatLieu;

    @ManyToOne
    @JoinColumn(name = "idsize")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "idphongcach")
    private PhongCach phongCach;

    @ManyToOne
    @JoinColumn(name = "idnsx")
    private NSX nsx;

    @ManyToOne
    @JoinColumn(name = "idmausac")
    private MauSac mauSac;

    @Column(name = "soluongconlai")
    private Integer soLuong;

    @Column(name = "giaban")
    private BigDecimal giaBan;

    @Column(name = "trangthai")
    private Boolean trangThai;

}
