package com.example.User.Management.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = "ФИО не может быть пустым")
    private String fio;
    @NotBlank(message = "Номер телефона не может быть пустым")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Некорректный номер телефона")
    private String phoneNumber;
    @URL(message = "Некорректный URL аватара")
    private String avatarUrl;
    @NotBlank(message = "Роль не может быть пустой")
    private String role;
}
