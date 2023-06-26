package astratech.prg7_m5_p2_019.controller;

import astratech.prg7_m5_p2_019.Result;
import astratech.prg7_m5_p2_019.model.Detail;
import astratech.prg7_m5_p2_019.model.Helm;
import astratech.prg7_m5_p2_019.model.Pembelian;
import astratech.prg7_m5_p2_019.repository.DetailRepository;
import astratech.prg7_m5_p2_019.service.DetailService;
import astratech.prg7_m5_p2_019.service.HelmService;
import astratech.prg7_m5_p2_019.service.PembelianService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller                                     ////////////////////////////    harus pake Controller bukan RestController
public class DetailController {

    @Autowired
    HelmService helmService;
    @Autowired
    PembelianService pembelianService;
    @Autowired
    DetailService detailService;
    @Autowired
    DetailRepository detailRepository;
    @Autowired
    DetailRepository helmRepository;
    @Autowired
    DetailRepository pembelianRepository;

    @GetMapping({"/detailView"})
    public String showHelmList(Model model){
        model.addAttribute("details", this.detailRepository.findAll());
        model.addAttribute("helms", this.helmRepository.findAll());
        model.addAttribute("pembelians", this.pembelianRepository.findAll());
        return "/Detail/detailView";
    }

    @GetMapping({"/addDetailView"})
    public String addDetailView(Model model){
        Iterable<Detail> helmList = helmRepository.findAll();
        model.addAttribute("helmList", helmList);
        model.addAttribute("helm", new Helm());
        return "/Detail/add-detail";            ////////////////////////////////////////  Cara ngakses HTML di dalem direktori
    }








    @PostMapping("/saveDetail")
    public Result save(HttpServletResponse response, @RequestBody Detail detail){
        Helm helm = helmService.getHelm(detail.getIdHelm().getIdHelm());
        helm.setStok(helm.getStok() - detail.getJumlah());
        helmService.save(helm);

        Pembelian pembelian = pembelianService.getPembelian(detail.getIdPembelian().getIdPembelian());
        pembelian.setTotal(pembelian.getTotal() + detail.getJumlah() * helm.getHarga());
        pembelianService.save(pembelian);

        Detail detail1 = new Detail(detail.getIdDetail(), detail.getIdPembelian(), detail.getIdHelm(),
                detail.getJumlah(), detail.getJumlah() * helm.getHarga());

        boolean isSuccess = detailService.save(detail1);
        if (isSuccess){
            return new Result(500, "Success");
        }else{
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(200, "Fail");
        }
    }

    @GetMapping("/getDetail")
    public Detail getDetail(HttpServletResponse response, @RequestParam("id") Integer id){
        Detail detail = detailService.getDetail(id);
        return detail;
    }

    //Latihan  No 5 Detail pembelian
    @GetMapping("/detailPembelian")
    public List<Detail> detailPembelian(HttpServletResponse response, @RequestParam("idPembelian") Pembelian idPembelian){
        List<Detail> details = detailService.detailPembelian(idPembelian);
        return details;
    }

}
