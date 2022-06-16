package com.zemoso.springbootproject.entity;

import lombok.Data;
//import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "items")
public @Data class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Item Name can't be empty")
    @Column(name = "item_name")
    private String itemName;

    @NotNull(message = "Item Price can't be empty")
    @Column(name = "item_price")
    @Range(min = 1)
    private Integer itemPrice;

    @NotEmpty
    @Column(name = "item_availability")
    private String availability;

    @NotEmpty
    @Column(name = "item_type")
    private String itemType;

    @NotNull(message = "Specify the required time for preparation")
    @Range(min = 10, max = 30, message = "Please specify time between the range 10 to 30min")
    @Column(name = "time_for_preparation")
    private Integer timeRequiredToPrepare;

}


