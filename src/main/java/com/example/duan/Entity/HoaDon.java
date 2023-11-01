package com.example.duan.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hoadon")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhoadon")
    private Integer idHoaDon;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "ngaytao")
    private Date ngayTao;

    @Column(name = "mahoadon")
    private String maHoaDon;

    @Column(name = "phuongthucmuahang")
    private boolean phuongThucMuaHang;

    @Column(name = "tienshiphang")
    private Double tienShipHang;

    @Column(name = "trangthai")
    private Integer trangThai;

    @Column(name = "httt")
    private boolean hTTT;

    @Column(name = "tongtien")
    private BigDecimal tongTien;

    @ManyToOne
    @JoinColumn(name = "idkhachhang")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "idnhanvien")
    private NhanVien nhanVien;

    private String generateRandomMaHoaDon() {
        String prefix = "HD";
        // Tạo số ngẫu nhiên có 7 chữ số
        String randomNumber = String.format("%07d", new Random().nextInt(10000000));
        return prefix + randomNumber;
    }

    @PrePersist
    public void generateMaHoaDon() {
        this.maHoaDon = generateRandomMaHoaDon();
    }

}