package com.mailing.poc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class MetadataDto {

    private String contentUseAt;
    private String somekey;
    private String awesomekey;

    public String getContentUseAt() {
        return contentUseAt;
    }

    public void setContentUseAt(String contentUseAt) {
        this.contentUseAt = contentUseAt;
    }

    public String getSomekey() {
        return somekey;
    }

    public void setSomekey(String somekey) {
        this.somekey = somekey;
    }

    public String getAwesomekey() {
        return awesomekey;
    }

    public void setAwesomekey(String awesomekey) {
        this.awesomekey = awesomekey;
    }
}
