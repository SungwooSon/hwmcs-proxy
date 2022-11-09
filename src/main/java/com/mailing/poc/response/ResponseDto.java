package com.mailing.poc.response;

import com.mailing.poc.dto.DataDto;
import com.mailing.poc.dto.ResultDataDto;
import com.mailing.poc.dto.StressDataDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class ResponseDto<T> {
    private String error;
    private T data;
    private String message;

    public ResponseDto(String error, T data, String message) {
        this.error = error;
        this.message = message;
        this.data = data;
    }
}
