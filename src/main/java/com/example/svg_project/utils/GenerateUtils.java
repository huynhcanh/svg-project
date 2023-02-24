package com.example.svg_project.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;

public class GenerateUtils {
    public static BufferedImage generateQrCode(String dataJson, int wid, int hei){
        if(!dataJson.isEmpty()){
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix bitMatrix = null;
            try {
                bitMatrix = writer.encode(dataJson, BarcodeFormat.QR_CODE, wid, hei);
            } catch (WriterException e) {
                e.printStackTrace();
            }
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        }
        return null;
    }
}
