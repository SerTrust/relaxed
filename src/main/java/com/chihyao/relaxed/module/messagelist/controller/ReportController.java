package com.chihyao.relaxed.module.messagelist.controller;

import com.chihyao.relaxed.module.messagelist.service.ReportService;
import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping(value = "/userAll", produces = MediaType.MULTIPART_FORM_DATA_VALUE, name = "user.pdf")
    @ApiOperation(value = "測試 PDF 產製", notes = "測試 PDF 產製")
    public ResponseEntity<byte[]> getUserReport() throws FileNotFoundException, JRException {
        byte[] bytes = reportService.exportReport();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "user.pdf");
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}
