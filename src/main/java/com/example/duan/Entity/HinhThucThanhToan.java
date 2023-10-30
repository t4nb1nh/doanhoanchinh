package com.example.duan.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "HinhThucThanhToan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HinhThucThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHTTT")
    private Integer idHTTT;
    @Column(name = "Ten")
    @NotBlank(message = "không được để trống tên")
    private String ten;
    @Column(name = "TrangThai")
    @NotNull(message = "không được để trống trạng thái")
    private Boolean trangThai;

}
