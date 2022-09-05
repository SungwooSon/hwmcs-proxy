package com.mailing.poc;

import lombok.Builder;


public class DDRequestDto {

    private int userId;
    private MetadataDto metadataDto;

    public DDRequestDto(int userId, MetadataDto metadataDto) {
        this.userId = userId;
        this.metadataDto = metadataDto;
    }

    @Override
    public String toString() {
        return "DDRequestDto{" +
                "userId=" + userId +
                ", metadataDto=" + metadataDto +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public MetadataDto getMetadataDto() {
        return metadataDto;
    }

    public void setMetadataDto(MetadataDto metadataDto) {
        this.metadataDto = metadataDto;
    }
}
