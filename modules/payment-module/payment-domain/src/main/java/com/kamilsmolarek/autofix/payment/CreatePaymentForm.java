package com.kamilsmolarek.autofix.payment;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePaymentForm {

    @NotBlank(message = "Workshop ID is required")
    private String workshopId;

    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount must be greater than 0")
    private Integer amount;

    @NotBlank(message = "Currency is required")
    private String currency;

    @NotNull(message = "Months is required")
    @Min(value = 1, message = "Months must be at least 1")
    private Integer months;
}