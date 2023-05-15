package dev.aminnorouzi.userservice.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`user`")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long telegramId;
    private Long telegramChatId;

    @NotNull
    @NotBlank
    private String fullName;

    @NotNull
    @NotBlank
    @Column(unique = true)
    @Email(regexp = "^(.+)@(\\S+)$")
    private String email;

    @NotNull
    @NotBlank
    @ToString.Exclude
    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}