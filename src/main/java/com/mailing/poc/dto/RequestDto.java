package com.mailing.poc.dto;

import com.mailing.poc.dto.MetadataDto;
import lombok.Data;

@Data
public class RequestDto {

    private String userId;
    private MetadataDto metadata;

    public String getUserId() {
        return userId;
    }

    public MetadataDto getMetadata() {
        return metadata;
    }
}
