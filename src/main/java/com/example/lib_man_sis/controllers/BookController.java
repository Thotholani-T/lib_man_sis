package com.example.lib_man_sis.controllers;

import com.example.lib_man_sis.models.Book;
import com.example.lib_man_sis.services.BookService;
import com.example.lib_man_sis.services.ExportBookList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> booksList = bookService.getAllBooks();
        model.addAttribute("books", booksList);
        return "books";
    }

    @PostMapping("/books/add")
    public String add(Book book, RedirectAttributes redirAttrs) {
        bookService.save(book);
        redirAttrs.addFlashAttribute("success", "Operation Successful");
        return "redirect:/books";
    }

    @RequestMapping("books/findById")
    @ResponseBody
    public Optional<Book> findById(long id) {
        return bookService.findById(id);
    }

    @RequestMapping(value="/books/update", method =  {RequestMethod.PUT, RequestMethod.GET})
    public String update(Book book, RedirectAttributes redirAttrs) {
        bookService.update(book);
        redirAttrs.addFlashAttribute("success", "Operation Successful");
        return "redirect:/books";
    }

    @RequestMapping(value="/books/delete", method =  {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(long id, RedirectAttributes redirAttrs) {
        bookService.delete(id);
        redirAttrs.addFlashAttribute("success", "Operation Successful");
        return "redirect:/books";
    }

    //Then finally here is your getMapping method
    @GetMapping("/books/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Book> exportBooks = bookService.getAllBooks();

        ExportBookList excelExporter = new ExportBookList(exportBooks);

        excelExporter.export(response);
    }
}