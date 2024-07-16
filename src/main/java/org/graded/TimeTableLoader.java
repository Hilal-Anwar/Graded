package org.graded;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class TimeTableLoader {
    private final LinkedList<TimeTable> timeTableLinkedList = new LinkedList<>();

    public LinkedList<TimeTable> getTimeTableLinkedList() {
        return timeTableLinkedList;
    }

    TimeTableLoader() {

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
        XSSFSheet sheet = workbook.getSheetAt(getIndex(new Date()));
        timeTableLinkedList.add(new TimeTable("Time", "Class", "Room No.", "Subject"));

        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row != null) {
                var stu = new TimeTable(row.getCell(0).getLocalDateTimeCellValue().toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a")),
                        row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue(),
                        "" + row.getCell(3));
                timeTableLinkedList.add(stu);
            }
        }
    }

    private int getIndex(Date d) {
        var c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.DAY_OF_WEEK);
    }
}
