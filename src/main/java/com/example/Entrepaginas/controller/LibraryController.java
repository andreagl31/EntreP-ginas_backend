package com.example.Entrepaginas.controller;


import com.example.Entrepaginas.dto.UserBookDto;
import com.example.Entrepaginas.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;
    //te trae tus libros de tu biblioteca
    @GetMapping("/me")
    public ResponseEntity<List<UserBookDto>> getMyBooks(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(libraryService.getMyBooks(userDetails.getUsername()));
    }
    //añadior un libro a tu biblioteca
    @PostMapping("/add/{bookId}")
    public ResponseEntity<UserBookDto> addBook(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long bookId) {
        return ResponseEntity.ok(libraryService.addBook(userDetails.getUsername(), bookId));
    }
    // borrar un libro de tu biblioteca recordwemos que el usuario guarda libros de su biblioteca
    @DeleteMapping("/remove/{bookId}")
    public ResponseEntity<Void> removeBook(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long bookId) {
        libraryService.removeBook(userDetails.getUsername(), bookId);
        return ResponseEntity.noContent().build();
    }
}

