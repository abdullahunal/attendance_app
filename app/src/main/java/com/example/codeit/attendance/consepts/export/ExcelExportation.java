package com.example.codeit.attendance.consepts.export;

import android.os.Environment;

import com.example.codeit.attendance.check.CheckResult;
import com.example.codeit.attendance.consepts.member.Member;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelExportation {

    public static void exportToExcel(CheckResult checkResult) {

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("sheet1");// creating a blank sheet

            int rownum = 0;
            rownum = setUpFormat(checkResult.getPresentMembers(), sheet, rownum, "Present Members");
            rownum = setUpFormat(checkResult.getAbsentMembers(), sheet, rownum, "Absent Members");
            rownum = setUpFormat(checkResult.getUnregisteredMembers(), sheet, rownum, "Unregistered Members");

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

    private static int setUpFormat(List<Member> list, XSSFSheet sheet, int rownum, String listName) {
        Row row = sheet.createRow(rownum);
        row.createCell(0).setCellValue(listName);
        rownum++;
        row = sheet.createRow(rownum);
        row.createCell(0).setCellValue("Id");
        row.createCell(1).setCellValue("Name");
        row.createCell(2).setCellValue("MAC");
        rownum++;

        for (int i = 0; i < list.size(); i++) { // Member member : list
            row = sheet.createRow(rownum++);
            createList(list.get(i), row, i + 1);
        }
        return rownum;
    }

    private static void createList(Member member, Row row, int no) // creating cells for each row
    {
        Cell cell = row.createCell(0);
        cell.setCellValue(no);

        cell = row.createCell(1);
        cell.setCellValue(member.getName());

        cell = row.createCell(2);
        cell.setCellValue(member.getMac());
    }

    private static boolean isRowEmpty(Row row) {
        boolean isEmpty = true;
        DataFormatter dataFormatter = new DataFormatter();

        if (row != null) {
            for (Cell cell : row) {
                if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
                    isEmpty = false;
                    break;
                }
            }
        }
        return isEmpty;
    }
}
