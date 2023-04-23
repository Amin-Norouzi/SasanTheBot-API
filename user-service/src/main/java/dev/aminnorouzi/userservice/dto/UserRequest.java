package dev.aminnorouzi.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private Long telegramId;
    private Long telegramChatId;

    @NotNull
    @NotBlank
    private String fullName;

    @NotNull
    @NotBlank
    @Email(regexp = "^(.+)@(\\S+)$")
    private String email;

    @NotNull
    @NotBlank
    private String password;
}
