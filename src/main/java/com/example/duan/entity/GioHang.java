package com.example.duan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "giohang")
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgiohang")
    private Integer idGioHang;

    @ManyToOne
    @JoinColumn(name = "idkhachhang")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "idchitietsanpham")
    private ChiTietSanPham chiTietSanPham;

    @OneToOne(mappedBy = "gioHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private HoaDonChiTiet hoaDonChiTiet;


    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngaytao")
    private Date ngayTao;

    @Column(name = "soluong")
    private Integer soLuong;

    @Column(name = "chietkhau", precision = 18, scale = 2)
    private BigDecimal chietKhau;

    @Column(name = "tonggia", precision = 18, scale = 2)
    private BigDecimal tongGia;

}
