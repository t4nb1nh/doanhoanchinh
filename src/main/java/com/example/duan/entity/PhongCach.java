package com.example.duan.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phongcach")
public class PhongCach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idphongcach")
    private Integer idPhongCach;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngaytao")
    private Date ngayTao;

    @Column(name = "trangthai")
    private Boolean trangThai;
}
