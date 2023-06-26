package astratech.prg7_m5_p2_019.controller;

import astratech.prg7_m5_p2_019.model.Pembelian;
import astratech.prg7_m5_p2_019.repository.AdminCredentialRepository;
import astratech.prg7_m5_p2_019.repository.PembelianRepository;
import astratech.prg7_m5_p2_019.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.time.LocalDate;

@Controller
public class UserController {
    @Autowired
    PembelianRepository pembelianRepository;

    @Autowired
    AdminCredentialRepository adminCredentialRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String showUserList(Model model) {
        model.addAttribute("users", pembelianRepository.findAll());
        return "index";
    }

    @GetMapping("/index")
    public String showUser(Model model) {
        model.addAttribute("users", pembelianRepository.findAll());
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Pembelian user){ return "add-user";}

    @PostMapping("/adduser")
    public String addUser(Pembelian pembelian, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "add-user";
        }
        pembelian.setTanggal(Date.valueOf(LocalDate.now()));
        pembelianRepository.save(pembelian);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        Pembelian pembelian = pembelianRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid user Id:" + id));
        pembelianRepository.delete(pembelian);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showupdateform(@PathVariable("id") int id, Model model) {
        Pembelian pembelian = pembelianRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
        model.addAttribute("pembelian", pembelian);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, Pembelian pembelian, BindingResult bindingResult, Model model) {
        pembelian.setIdPembelian(id);
        if(bindingResult.hasErrors()){
            return "update-user";
        }
        pembelian.setTanggal(Date.valueOf(LocalDate.now()));
        pembelianRepository.save(pembelian);
        return "redirect:/index";
    }
}
