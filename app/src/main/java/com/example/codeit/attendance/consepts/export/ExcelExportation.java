package com.example.codeit.attendance.consepts.export;

import android.os.Environment;

import com.example.codeit.attendance.check.CheckResult;
import com.example.codeit.attendance.consepts.member.Member;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelExportation {

    public static void exportToExcel(CheckResult checkResult) {

        List<Member> list = checkResult.getAbsentMembers();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("sheet1");// creating a blank sheet
            int rownum = 0;
            for (Member user : list) {
                Row row = sheet.createRow(rownum++);
                createList(user, row);
            }

            String fname = "ExcelDosyam.xlsx";
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(path, "/" + fname);

            FileOutputStream out = new FileOutputStream(file); // file name with path
            workbook.write(out);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void createList(Member user, Row row) // creating cells for each row
    {
        Cell cell = row.createCell(0);
        cell.setCellValue(user.getMac());

        cell = row.createCell(1);
        cell.setCellValue(user.getName());
    }
}
