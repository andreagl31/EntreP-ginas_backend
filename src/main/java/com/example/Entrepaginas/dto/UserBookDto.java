package com.example.Entrepaginas.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBookDto {
    private Long bookId;
    private String title;
    private String author;
    private String genre;
    private LocalDateTime addedAt;
}

