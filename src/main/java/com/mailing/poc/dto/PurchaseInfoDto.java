package com.mailing.poc.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;


@Data
@AllArgsConstructor
public class PurchaseInfoDto {
    private String contact;
    private String postCode;
    private String address;
    private String detailAddress;

    public static PurchaseInfoDto from(String info) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(info, Map.class);
        return new PurchaseInfoDto(map.get("contact"), map.get("postCode"), map.get("address"), map.get("detailAddress"));
    }
}
