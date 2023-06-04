package com.example.kiemtra.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kiemtra.Entity.PhongBan;

@Repository
public interface IPhongBanRepository extends JpaRepository<PhongBan, String> {
    // @Query("SELECT r.id FROM Role r WHERE r.name = ?1")
    // Long getRoleIdByName(String roleName);

    
}
