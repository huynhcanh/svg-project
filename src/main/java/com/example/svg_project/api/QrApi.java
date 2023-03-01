package com.example.svg_project.api;

import com.example.svg_project.utils.GenerateUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

import static com.example.svg_project.constant.QrConstant.QR_CODE_SIZE_WIDTH;

@RestController
@RequestMapping("/api")
public class QrApi {

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

    @GetMapping(value = "/generate-qr")
    public BufferedImage generateQr(@RequestParam("id") String id) {
        return GenerateUtils.generateQrCode(id, QR_CODE_SIZE_WIDTH, QR_CODE_SIZE_WIDTH);
    }
}
