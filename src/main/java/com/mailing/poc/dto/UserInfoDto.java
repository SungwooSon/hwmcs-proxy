package com.mailing.poc.dto;

import com.mailing.poc.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDto {

    private String name;
    private String flowlyAccount;

    public static UserInfoDto from(User user) {
        return new UserInfoDto(user.getName(), user.getThirdPartyEmail());
    }
}
