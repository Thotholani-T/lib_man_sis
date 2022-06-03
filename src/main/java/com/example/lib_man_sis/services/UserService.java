package com.example.lib_man_sis.services;

import com.example.lib_man_sis.models.User;
import com.example.lib_man_sis.repos.UserRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Add new User to database
    public void save(User user) {
        userRepository.save(user);
    }

    // get User by id
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void printCertificate(HttpServletResponse response, User user) throws IOException {
        Document libCard = new Document(PageSize.A6);

        PdfWriter.getInstance(libCard, response.getOutputStream());

        libCard.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(5);

        Paragraph heading = new Paragraph("The Central Public Library\n Library Card", fontTitle);
        heading.setAlignment(Paragraph.ALIGN_CENTER);

        Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA);
        paragraphFont.setSize(3);

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String libraryId = String.valueOf(user.getLibraryId());

        Paragraph fname = new Paragraph("First Name: " + firstName);
        fname.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph lname = new Paragraph("Last Name: " + lastName);
        fname.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph libId = new Paragraph("Library ID: " + libraryId);
        fname.setAlignment(Paragraph.ALIGN_LEFT);

        libCard.add(heading);
        libCard.add(fname);
        libCard.add(lname);
        libCard.add(libId);
        libCard.close();

    }
}