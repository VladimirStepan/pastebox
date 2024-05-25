package ru.education.pastebox.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "pastebin")
public class Paste {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "hash")
    @NotEmpty(message = "Хэш не должен быть пустым!")
    private String hash;

    @Column(name = "status")
    @NotEmpty(message = "Поле статус не может быть пустым!")
    private String status;

    @Column(name = "life_time")
    @NotNull
    private int lifeTime;

    @Column(name = "body")
    @NotEmpty(message = "Поле содержащее код, не может быть пустым!")
    @Size(min = 10, max = 256, message = "Код для отправки должен содержать не менее 10 и не более 256 символов")
    private String body;

    @Column(name = "created_at")
    @NotNull
    private LocalDateTime createdAt;

    public Paste() {
    }
}