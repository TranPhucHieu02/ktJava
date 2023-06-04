package com.example.kiemtra.Entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.example.kiemtra.Validator.annotation.ValidUsername;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    @NotEmpty(message = "username không được để trống")
    @Size(max = 50, message = "Tên không vượt quá 50 ký tự")
    @ValidUsername
    private String username;

    @Column(name = "password", length = 205, nullable = false)
    @NotEmpty(message = "password không được để trống")
    private String password;

    @Column(name = "email", length = 50)
    @NotNull(message = "email không được để trống")
    @Size(max = 50, message = "email không vượt quá 50 ký tự")
    private String email;

    @Column(name = "name", nullable = false)
    @NotNull(message = "name không được để trống")
    private String name;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    @Fetch(FetchMode.JOIN)
    @ManyToMany()
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Fetch(FetchMode.JOIN)
    @OneToOne(mappedBy = "user")
    private NhanVien nhanVien;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public User() {
    }
    

}
