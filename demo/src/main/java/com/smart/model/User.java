package com.smart.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column(name="user_name" , nullable = false)
    private String name;
    @Column(unique = true ,nullable = false)
    private String email;
    private String password;
    @Column(length = 5000)
    private String about ;
    @Column(length = 5000)
    private String profileLink;
    private String phoneNumber;
 
    //information
    
    @Builder.Default
    private boolean enabled=false;
    @Builder.Default
    private boolean emailVerified=false;
    @Builder.Default
    private boolean phoneVerified=false;

    // Self, Google , Faceebook , Github
    @Enumerated(value=EnumType.STRING)
    @Builder.Default
    private Providers provider=Providers.SELF;
    private String providerUserId;

     //mapping contacts
     @OneToMany(cascade = CascadeType.ALL , mappedBy = "user",fetch = FetchType.LAZY,orphanRemoval = true)
    @Builder.Default
     private List<Contact>  contacts=new ArrayList<>();

   

}
