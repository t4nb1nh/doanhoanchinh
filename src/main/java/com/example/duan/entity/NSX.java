package com.example.duan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "NSX")
public class NSX {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNSX")
    private Integer idNSX;

    @Column(name = "ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "TrangThai")
    private boolean trangThai;

}
