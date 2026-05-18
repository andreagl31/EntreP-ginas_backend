package com.example.Entrepaginas.repository;

import com.example.Entrepaginas.model.User;
import com.example.Entrepaginas.model.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {
    List<UserBook> findByUser(User user);
    boolean existsByUserAndBookId(User user, Long bookId);
    void deleteByUserAndBookId(User user, Long bookId);
}
