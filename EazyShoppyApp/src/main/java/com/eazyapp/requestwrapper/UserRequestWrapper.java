package com.eazyapp.requestwrapper;

import com.eazyapp.model.Role;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestWrapper {

    private String name;

    private String email;

    private String phoneNumber;
    //private String uniqueId;

    private String password;
    private long roleId;

}
