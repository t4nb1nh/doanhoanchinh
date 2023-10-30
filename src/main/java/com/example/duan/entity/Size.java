package com.example.duan.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "size")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsize")
    private Integer idSize;

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
