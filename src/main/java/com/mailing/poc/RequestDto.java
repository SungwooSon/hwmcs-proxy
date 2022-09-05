package com.mailing.poc;

import lombok.Data;
import lombok.Getter;

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
