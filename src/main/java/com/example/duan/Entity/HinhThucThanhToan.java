package com.example.duan.Entity;

import jakarta.persistence.*;
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
    private String ten;
    @Column(name = "TrangThai")
    private Boolean trangThai;

}
