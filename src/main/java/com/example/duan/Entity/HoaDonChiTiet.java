package com.example.duan.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hoadonchitiet")
public class HoaDonChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhoadonchitiet")
    private Integer idHoaDonChiTiet;

    @Column(name = "soluong")
    private Integer soLuong;

    @Column(name = "gia")
    private BigDecimal gia;

    @Column(name = "giamgia")
    private Double giamGia;

    @Column(name = "tongtien")
    private BigDecimal tongTien;

    @Column(name = "trangthai")
    private boolean trangThai;

    @Column(name = "ghichu")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "idhoadon")
    private HoaDon hoaDon;

    @OneToOne
    @JoinColumn(name = "idgiohang")
    private GioHang gioHang;
}