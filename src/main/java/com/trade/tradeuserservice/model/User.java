package com.trade.tradeuserservice.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Document
@Data
@Getter
@Setter
@ToString
public class User {

    @Id
    private Long userId;
    @NotEmpty(message = "Firstname must not be Empty")
    @Size(min = 6)
    private String firstName;
    private String middleName;
    @NotEmpty(message = "Lastname must not be Empty")
    @Size(min = 6)
    private String lastName;
    @NotEmpty(message = "Username must not be Empty")
    private String userName;
    private String password;
    @Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", message = "Please provide a valid email address")
    private String userEmail;
    @Field
    @Digits(integer = 1, fraction = 0, message = "status must be 1 or 0")
    private Integer status = 1;
    private String createTime;
    private String updateTime;
    @NotEmpty(message = "Pancard number must not be Empty")
    private String panCard;
    @NotEmpty(message = "Passport number must not be Empty")
    private String passportNo;
    @Field
    private Integer emailStatus = 0;
    @Pattern(regexp = "(^$|[0-9]{10})", message = "This is not valid mobile number")
    @NotEmpty(message = "Mobile number must not be Empty")
    private String mobileNo;
    private Integer mobileStatus;
    @Field
    private Integer wallet = 0;

    public User() {

    }

}
