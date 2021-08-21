package com.example.hmscommon.model.api.user;

import com.example.hmscommon.model.card.UserCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRspModel {
    private UserCard userCard;
    private String token;
    private String account;
    private boolean isBind;
}
