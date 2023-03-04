package com.example.svg_project.utils;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

public class ExcelUtils {
    public static <T> void exportToExcel(List<T> responses, String[] headers, String sheetName, OutputStream outputStream) throws IOException {

        // Tạo workbook mới
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo một sheet mới
        XSSFSheet sheet = workbook.createSheet(sheetName);

        // Tạo một header row mới
        XSSFRow headerRow = sheet.createRow(0);

        // Tạo các cell header với các giá trị từ mảng headers
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Tạo các row mới cho các entity được truyền vào và tạo các cell với các giá trị tương ứng
        for (int i = 0; i < responses.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);
            T response = responses.get(i);

            // Sử dụng reflection để lấy các field của entity và tạo các cell với các giá trị tương ứng
            Field[] fields = response.getClass().getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                Field field = fields[j];
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(response);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to export entity to Excel", e);
                }
                XSSFCell cell = row.createCell(j);
                if (value != null) {
                    if (field.getType().equals(BufferedImage.class)) {
                        // Nếu là ảnh QR, thêm vào sheet
                        BufferedImage qrCode = (BufferedImage) value;
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(qrCode, "png", baos);
                        int pictureIdx = workbook.addPicture(baos.toByteArray(), Workbook.PICTURE_TYPE_PNG);

                        XSSFClientAnchor anchor = new XSSFClientAnchor();
                        anchor.setCol1(j); // cột chứa ảnh
                        anchor.setRow1(i + 1); // dòng chứa ảnh
                        anchor.setCol2(j + 1);
                        anchor.setRow2(i + 2);

                        XSSFDrawing drawing = sheet.createDrawingPatriarch();
                        XSSFPicture picture = drawing.createPicture(anchor, pictureIdx);
                        picture.resize(); // thiết lập kích thước ảnh
                    } else {
                        cell.setCellValue(value.toString());
                    }
                }
            }
        }

        // Ghi dữ liệu vào outputStream
        workbook.write(outputStream);
    }
}
