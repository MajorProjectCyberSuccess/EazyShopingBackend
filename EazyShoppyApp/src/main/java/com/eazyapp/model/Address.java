package com.eazyapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "state")
    private String state;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "pincode" ,length = 6)
    private Integer pincode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
