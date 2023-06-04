package com.example.kiemtra.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    @GetMapping
    public String Index(){
        return"/index";
    }
    // @GetMapping("gioithieu")
    // public String GioiThieu(){
    //     return"nguoidung/gioithieu";
    // }
    // @GetMapping("khoa")
    // public String Khoa(){
    //     return"nguoidung/khoa";
    // }
    // @GetMapping("danhsachbacsi")
    // public String DanhSachBacSi(){
    //     return"nguoidung/bacsi";
    // }
    // @GetMapping("datlich")
    // public String DatLich(){
    //     return"nguoidung/datlich";
    // }
    // @GetMapping("tracuu")
    // public String TraCuu(){
    //     return"nguoidung/tracuu";
    // }
    // @GetMapping("chitietkhambenh")
    // public String CTKhamBenh(){
    //     return"nguoidung/chitietkhambenh";
    // }
    // @GetMapping("lienhe")
    // public String LienHe(){
    //     return"nguoidung/lienhe";
    // }


}
