package com.eazyapp.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {

    private String name;

    private String email;

    private String phoneNumber;

    private Long uniqueId;

    private String roleType;

}
