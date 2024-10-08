package ru.education.pastebox.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasteDTO {

    @NotEmpty(message = "Поле статус не может быть пустым!")
    private String status;

    @NotNull
    private int lifeTime;

    @NotEmpty(message = "Поле содержащее код, не может быть пустым!")
    @Size(min = 10, max = 256, message = "Код для отправки должен содержать не менее 10 и не более 256 символов")
    private String body;
}
