package com.smart.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {

    @Size(min = 3, message = "Required 3 Character")
    private String name;

    @NotBlank(message = "Email Required")
    private String email;

    @Size(min = 6, message = "Atleast 6 Character Required")
    private String password;
    
    @Size(min=8 , max = 12 , message = "Invalid Phone Number")
    private String phoneNumber;

    @NotBlank(message = "Required to write about yourself")
    private String about;

}
