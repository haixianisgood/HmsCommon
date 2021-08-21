package com.example.hmscommon.model.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCard {
    private String id;
    private String name;
    private String role;
    private boolean isFollowed;
    private boolean isFollower;
}