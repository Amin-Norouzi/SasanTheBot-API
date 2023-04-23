package dev.aminnorouzi.userservice.dto;

import dev.aminnorouzi.userservice.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private Long telegramId;
    private Long telegramChatId;
    private String fullName;
    private String email;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pageable {

        private List<UserResponse> content;
        private Integer numberOfElements;
        private Integer totalPages;
        private Integer totalElements;
    }
}
