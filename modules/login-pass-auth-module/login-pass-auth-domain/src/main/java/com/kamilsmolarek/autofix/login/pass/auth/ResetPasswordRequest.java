package com.kamilsmolarek.autofix.login.pass.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPasswordRequest {
    private String id;
    private Instant createdOn;
    private Instant expiredOn;
    private String userId;
}

