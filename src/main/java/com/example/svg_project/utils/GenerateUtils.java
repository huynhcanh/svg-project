package com.example.svg_project.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

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

//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        Map<EncodeHintType, Object> hints = new HashMap<>();
//        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//        BitMatrix bitMatrix = null;
//        try {
//            bitMatrix = qrCodeWriter.encode(dataJson, BarcodeFormat.QR_CODE, wid, hei, hints);
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
//        int matrixWidth = bitMatrix.getWidth();
//        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
//        image.createGraphics();
//
//        Graphics2D graphics = (Graphics2D) image.getGraphics();
//        graphics.setColor(Color.WHITE);
//        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
//        graphics.setColor(Color.BLACK);
//
//        for (int i = 0; i < matrixWidth; i++) {
//            for (int j = 0; j < matrixWidth; j++) {
//                if (bitMatrix.get(i, j)) {
//                    graphics.fillRect(i, j, 1, 1);
//                }
//            }
//        }
//        return image;
    }
}