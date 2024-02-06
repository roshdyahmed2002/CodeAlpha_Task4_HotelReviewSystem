package com.HotelReviewSystem.HotelReviewSystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Hotel {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int hotel_id;
        private String name;
        private int price;
        private int rating;
        private String review;




}
