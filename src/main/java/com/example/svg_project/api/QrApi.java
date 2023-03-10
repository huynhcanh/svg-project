package com.example.svg_project.api;

import com.example.svg_project.utils.GenerateUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

import static com.example.svg_project.constant.QrConstant.QR_CODE_SIZE_HEIGHT_WEB;
import static com.example.svg_project.constant.QrConstant.QR_CODE_SIZE_WIDTH_WEB;

@RestController
@RequestMapping("/api")
public class QrApi {

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

    @GetMapping(value = "/generate-qr", produces = "image/png")
    public ResponseEntity<BufferedImage> generateQr(@RequestParam("id") String id) {
        BufferedImage qrCode = GenerateUtils.generateQrCode(id, QR_CODE_SIZE_WIDTH_WEB, QR_CODE_SIZE_HEIGHT_WEB);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/png")
                .body(qrCode);
    }
}
