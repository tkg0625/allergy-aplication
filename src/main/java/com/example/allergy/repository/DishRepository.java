package com.example.allergy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.allergy.entity.Dish;

public interface DishRepository extends JpaRepository <Dish, Long> {

}
