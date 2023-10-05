package com.example.duan.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "MauSac")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MauSac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMauSac")
    private Integer idMauSac;
    @Column(name = "ma")
    private String ma;
    @Column(name = "Ten")
    private String ten;
    @Column(name = "NgayTao")
    private Date ngayTao;
    @Column(name = "TrangThai")
    private Boolean trangThai;
}
