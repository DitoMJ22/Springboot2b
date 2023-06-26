package astratech.prg7_m5_p2_019.controller;

import astratech.prg7_m5_p2_019.Result;
import astratech.prg7_m5_p2_019.model.Helm;
import astratech.prg7_m5_p2_019.repository.HelmRepository;
import astratech.prg7_m5_p2_019.service.HelmService;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class HelmController {
    @Autowired
    HelmService helmService;
    @Autowired
    HelmRepository helmRepository;

/*    @GetMapping({"/index"})
    public String showHelmList(Model model){
        model.addAttribute("helms", this.helmRepository.findAll());
        return "index";
    }

    @GetMapping({"/addhelmView"})
    public String addHelmView(Helm helm){
        return "add-helm";
    }

    @PostMapping("/addhelm")
    public String addUser(Helm helm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "add-helm";
        }
        //pembelian.setTanggal(Date.valueOf(LocalDate.now()));
        helmRepository.save(helm);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String updatehelmView(@PathVariable("id") int id, Model model){
        Helm helm=helmRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid helm Id:" + id));
        model.addAttribute("helm", helm);
        return "update-helm";
    }

    @PostMapping("/update/{id}")
    public String updateHelm(@PathVariable("id") int id, Helm helm, BindingResult bindingResult, Model model) {
        helm.setIdHelm(id);
        if (bindingResult.hasErrors()){
            return "update-helm";
        }
        //pembelian.setTanggal(Date.valueOf(LocalDate.now()));
        helmRepository.save(helm);
        return "redirect:/index";
    }

*//*    @GetMapping("/delete/{id}")
    public String deleteHelm(@PathVariable("id") int id, Model model){
        Helm helm=helmRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid helm Id:" + id));
        helmRepository.delete(helm);
        return "redirect:/index";
    }*//*

    @GetMapping("/delete/{id}")
    public String deleteHelm(@PathVariable("id") int id, Model model) {
        Helm helm = helmRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid helm Id:" + id));

        // Menambahkan data helm ke model untuk ditampilkan di halaman konfirmasi
        model.addAttribute("helm", helm);

        return "confirmation"; // Mengarahkan ke halaman konfirmasi (misalnya confirmation.html)
    }

    @PostMapping("/delete/{id}")
    public String confirmDeleteHelm(@PathVariable("id") int id) {
        // Proses penghapusan helm
        Helm helm = helmRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid helm Id:" + id));
        helmRepository.delete(helm);

        return "redirect:/index";
    }*/











    @PostMapping("/saveHelm")
    public Result save(HttpServletResponse response, @RequestBody Helm helmParam){
        Helm helm = new Helm(helmParam.getIdHelm(), helmParam.getMerk(), helmParam.getWarna(), helmParam.getUkuran(),
                helmParam.getStok(), helmParam.getJenis(), helmParam.getHarga());
        boolean isSuccess = helmService.save(helm);
        if (isSuccess){
            return new Result(500, "Success");
        }else{
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(200, "Fail");
        }
    }

    @GetMapping("/getHelm")
    public Helm getHelm(HttpServletResponse response, @RequestParam("id") Integer id){
        Helm helm = helmService.getHelm(id);
        return helm;
    }

    @GetMapping("/getHelms")
    public List<Helm> getHelms(HttpServletResponse response){

        return helmService.getHelms();
    }

    //update helm
    @PutMapping("/updateHelm/{id}")
    public Result updateHelm(@PathVariable("id") Integer id, @RequestBody Helm updatedHelm){
        boolean isSuccess = helmService.updateHelm(id, updatedHelm);
        if (isSuccess){
            return new Result(500, "Success");
        }else{
            return new Result(200, "Fail");
        }
    }

    //delete helm
    //localhost:8080/deleteHelm?id=8 (cara menjalankan sama dengan get)
    @DeleteMapping("/deleteHelm")
    public Result delete(HttpServletResponse response, @RequestParam("id") Integer id){
        boolean isSuccess = helmService.deleteHelm(id);
        if (isSuccess){
            return new Result(500, "Success");
        }else{
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(200, "Fail");
        }
    }


    @GetMapping("/helm/report/{format}")
    public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return helmService.exportReport(format);
    }
}
