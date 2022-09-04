package com.mailing.poc;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private String error;
    private DataDto data;
    private String message;
}
