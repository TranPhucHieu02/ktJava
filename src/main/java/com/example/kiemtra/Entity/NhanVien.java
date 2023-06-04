package com.example.kiemtra.Entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name="nhanvien")
public class NhanVien {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long id;
    @Id
    @Column(name = "manv", length = 50, nullable = false, unique = true)
    @NotEmpty(message = "manv không được để trống")
    @Size(max = 50, message = "mavv không vượt quá 50 ký tự")
    private String manv;

    @Column(name = "hoten", length = 50, nullable = false, unique = true)
    @NotEmpty(message = "hoten không được để trống")
    @Size(max = 50, message = "Tên không vượt quá 50 ký tự")
    private String hoten;

    @Column(name = "phai")
    @NotEmpty(message = "phai không được để trống")
    private String phai;

    @Column(name = "noisong")
    @NotEmpty(message = "noi sống không được để trống")
    private String noisong;

    @Column(name = "luong")
    private long luong ;
    
    @Fetch(FetchMode.JOIN)
    @OneToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "phongban_mapb")
    private PhongBan phongban;

    public String getManv() {
        return manv;
    }

    public NhanVien() {
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getPhai() {
        return phai;
    }

    public void setPhai(String phai) {
        this.phai = phai;
    }

    public String getNoisong() {
        return noisong;
    }

    public void setNoisong(String noisong) {
        this.noisong = noisong;
    }

    public long getLuong() {
        return luong;
    }

    public void setLuong(long luong) {
        this.luong = luong;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PhongBan getPhongban() {
        return phongban;
    }

    public void setPhongban(PhongBan phongban) {
        this.phongban = phongban;
    }
    
}
