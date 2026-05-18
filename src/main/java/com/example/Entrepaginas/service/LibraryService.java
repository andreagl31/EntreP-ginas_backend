package com.example.Entrepaginas.service;

import com.example.Entrepaginas.dto.UserBookDto;
import com.example.Entrepaginas.model.Book;
import com.example.Entrepaginas.model.User;
import com.example.Entrepaginas.model.UserBook;
import com.example.Entrepaginas.repository.BookRepository;
import com.example.Entrepaginas.repository.UserBookRepository;
import com.example.Entrepaginas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final UserBookRepository userBookRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public List<UserBookDto> getMyBooks(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        return userBookRepository.findByUser(user).stream()
                .map(ub -> UserBookDto.builder()
                        .bookId(ub.getBook().getId())
                        .title(ub.getBook().getTitle())
                        .author(ub.getBook().getAuthor())
                        .genre(ub.getBook().getGenre())
                        .addedAt(ub.getAddedAt())
                        .build())
                .toList();
    }

    @Transactional
    public UserBookDto addBook(String username, Long bookId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado: " + bookId));

        if (userBookRepository.existsByUserAndBookId(user, bookId)) {
            throw new IllegalStateException("El libro ya está en tu biblioteca");
        }

        UserBook userBook = UserBook.builder().user(user).book(book).build();
        UserBook saved = userBookRepository.save(userBook);

        return UserBookDto.builder()
                .bookId(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .addedAt(saved.getAddedAt())
                .build();
    }

    @Transactional
    public void removeBook(String username, Long bookId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        if (!userBookRepository.existsByUserAndBookId(user, bookId)) {
            throw new IllegalArgumentException("El libro no está en tu biblioteca");
        }
        userBookRepository.deleteByUserAndBookId(user, bookId);
    }
}
