package com.example.lib_man_sis.services;

import com.example.lib_man_sis.models.Book;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExportBookList {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Book> bookList;

    public ExportBookList(List<Book> bookList) {
        this.bookList = bookList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Books");

        org.apache.poi.ss.usermodel.Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Book ID", style);
        createCell(row, 1, "Title", style);
        createCell(row, 2, "Author", style);
        createCell(row, 3, "Edition", style);
        createCell(row, 4, "Category", style);
        createCell(row, 5, "Price", style);
        createCell(row, 6, "Publisher", style);
        createCell(row, 7, "Quantity", style);

    }

    private void createCell(org.apache.poi.ss.usermodel.Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        org.apache.poi.ss.usermodel.Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Integer){
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double){
            cell.setCellValue((Double) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Book book : bookList) {
            org.apache.poi.ss.usermodel.Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, book.getId(), style);
            createCell(row, columnCount++, book.getTitle(), style);
            createCell(row, columnCount++, book.getAuthor(), style);
            createCell(row, columnCount++, book.getEdition(), style);
            createCell(row, columnCount++, book.getCategory(), style);
            createCell(row, columnCount++, book.getPrice(), style);
            createCell(row, columnCount++, book.getPublisher(), style);
            createCell(row, columnCount++, book.getCopies(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
