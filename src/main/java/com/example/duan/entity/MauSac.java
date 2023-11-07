package com.example.duan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    @NotBlank(message = "không được để trống mã")
    private String ma;
    @Column(name = "Ten")
    @NotBlank(message = "không được để trống tên")
    private String ten;
    @Column(name = "NgayTao")
    @NotNull(message = "không được để trống ngày tạo")
    private Date ngayTao;
    @Column(name = "TrangThai")
    @NotNull(message = "không được để trống trạng thái")
    private Boolean trangThai;
}
