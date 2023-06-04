package com.example.kiemtra.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kiemtra.Entity.PhongBan;
import com.example.kiemtra.Repository.IPhongBanRepository;

@Service
public class PhongBanService {
    @Autowired
    private IPhongBanRepository phongBanRepository;

    public List<PhongBan> getAllPhongBans(){
        
        return phongBanRepository.findAll();
    }
}
