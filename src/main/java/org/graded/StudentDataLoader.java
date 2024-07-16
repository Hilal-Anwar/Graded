package org.graded;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;


// Main class
public class StudentDataLoader {

    private final LinkedHashMap<String, Student> studentTreeMap = new LinkedHashMap<>(20);

    public LinkedHashMap<String, Student> getStudentTreeMap() {
        return studentTreeMap;
    }

    public List<Student> getStudentStream() {
        return studentTreeMap.values().stream().sorted(Comparator.comparing(Student::points, Comparator.reverseOrder())).toList();
    }

    StudentDataLoader() {

        FileInputStream file;
        try {
            var filename = System.getProperty("user.home") + "\\OneDrive - MSFT\\GRADED_DATA\\TIME_LEADER_BOARD\\time_table_leader.xlsx";
            file = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        XSSFSheet sheet = workbook.getSheetAt(0);

        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row != null) {
                var stu = new Student(row.getCell(0).getStringCellValue(),
                        getAsRequired(row.getCell(1)), getAsRequired(row.getCell(2)),
                        row.getCell(row.getLastCellNum() - 1).getNumericCellValue());
                studentTreeMap.put(row.getCell(0).getStringCellValue(), stu);
            }
        }
    }

    String getAsRequired(Cell c) {
        try {
            int x = (int) c.getNumericCellValue();
            return "" + x;
        } catch (IllegalStateException e) {
            return c.getStringCellValue();
        }
    }

}
