package com.example.kiemtra.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kiemtra.Entity.NhanVien;
import com.example.kiemtra.Repository.INhanVienRepository;

@Service
public class NhanVienService {
    @Autowired
    private INhanVienRepository nhanVienRepository;

    public List<NhanVien> getAllNhanViens(){
        
        return nhanVienRepository.findAll();
    }
    public NhanVien getNhanVienByManv(String manv){
        
        return nhanVienRepository.findById(manv).orElse(null);
    }
    public void addNhanVien(NhanVien nhanVien){
        
        nhanVienRepository.save(nhanVien);
    }
    public void deleteNhanVien(String manv){
        
        nhanVienRepository.deleteById(manv);
    }
    public void updateNhanVien(NhanVien nhanVien){
        
        nhanVienRepository.save(nhanVien);
    }
}
