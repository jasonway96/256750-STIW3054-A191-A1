package com.assignment1;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class excelLink
{
    public static void main() throws IOException
    {
            try {
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("NameLink Table");


                int num=0;

                for (info info : WebLinkTable.findAll()) {
                    Row header = sheet.createRow(0);
                    header.createCell(0).setCellValue("Matric");
                    header.createCell(1).setCellValue("Name");
                    header.createCell(2).setCellValue("Link");

                    Row row = sheet.createRow(num);

                    Cell cellNo = row.createCell(0);
                    cellNo.setCellValue(info.getLine1());

                    Cell cellName = row.createCell(1);
                    cellName.setCellValue(info.getLine2());

                    Cell cellMatric = row.createCell(2);
                    cellMatric.setCellValue(info.getLine3());

                    num++;

                    if (num==0 && num<2){
                        sheet.autoSizeColumn(num);
                    }
                }

                FileOutputStream output = new FileOutputStream(new File("C:\\Assignment1\\Namelink.xls"));
                workbook.write(output);
                output.close();
                workbook.close();
                System.out.println("Info written!");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
}
