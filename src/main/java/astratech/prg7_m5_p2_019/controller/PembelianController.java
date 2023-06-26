package astratech.prg7_m5_p2_019.controller;


import astratech.prg7_m5_p2_019.Result;
import astratech.prg7_m5_p2_019.model.Helm;
import astratech.prg7_m5_p2_019.model.Pembelian;
import astratech.prg7_m5_p2_019.service.HelmService;
import astratech.prg7_m5_p2_019.service.PembelianService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
public class PembelianController {
    @Autowired
    PembelianService pembelianService;

    @PostMapping("savePembelian")
    public Result save(HttpServletResponse response, @RequestBody Pembelian pembelian){
        LocalDate now = LocalDate.now();
        java.util.Date date = java.sql.Date.valueOf(now);

        Pembelian pembelian1 = new Pembelian(pembelian.getIdPembelian(),pembelian.getNama(), (java.sql.Date) date, 0);
        boolean isSuccess = pembelianService.save(pembelian1);

        if (isSuccess){
            return new Result(500, "Success");
        }else{
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(200, "Fail");
        }
    }

    @GetMapping("/getPembelian")
    public Pembelian getPembelian(HttpServletResponse response, @RequestParam("id") Integer id){
        Pembelian pembelian = pembelianService.getPembelian(id);
        return pembelian;
    }

    @GetMapping("/getPembelians")
    public List<Pembelian> getPembelians(HttpServletResponse response){
        return pembelianService.getPembelians();
    }
}
