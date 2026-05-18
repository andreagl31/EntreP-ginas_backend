package com.example.Entrepaginas;

import com.example.Entrepaginas.model.Book;
import com.example.Entrepaginas.model.Role;
import com.example.Entrepaginas.repository.BookRepository;
import com.example.Entrepaginas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.Entrepaginas.model.User;
//clase que inicializa los datos con los que vamosm a trabajar, evita que tengamos data sql
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Crear admin si no existe
        if (!userRepository.existsByUsername("admin")) {
            userRepository.save(User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ROLE_ADMIN)
                    .build());
        }

        // Crear usuario de prueba si no existe
        if (!userRepository.existsByUsername("usuario")) {
            userRepository.save(User.builder()
                    .username("usuario")
                    .password(passwordEncoder.encode("usuario123"))
                    .role(Role.ROLE_USER)
                    .build());
        }

        // Insertar libros de ejemplo si no hay ninguno
        if (bookRepository.count() == 0) {
            bookRepository.save(Book.builder().title("Don Quijote de la Mancha").author("Miguel de Cervantes").genre("Novela").build());
            bookRepository.save(Book.builder().title("Cien años de soledad").author("Gabriel García Márquez").genre("Realismo mágico").build());
            bookRepository.save(Book.builder().title("1984").author("George Orwell").genre("Distopía").build());
            bookRepository.save(Book.builder().title("El principito").author("Antoine de Saint-Exupéry").genre("Fábula").build());
            bookRepository.save(Book.builder().title("Dune").author("Frank Herbert").genre("Ciencia ficción").build());
        }
    }
}
