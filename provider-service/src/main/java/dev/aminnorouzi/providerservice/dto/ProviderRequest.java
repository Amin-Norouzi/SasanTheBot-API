package dev.aminnorouzi.providerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderRequest {

    @NotNull
    @NotBlank
    private String title;

    @URL
    @NotNull
    @NotBlank
    private String url;

    @NotNull
    @NotBlank
    private String phrase;

    @NotNull
    private Boolean isBanned;

    @NotNull
    private Boolean isAvailable;
}
