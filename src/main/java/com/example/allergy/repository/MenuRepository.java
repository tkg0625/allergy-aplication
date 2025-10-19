package com.example.allergy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.allergy.entity.Menu;

public interface MenuRepository extends JpaRepository <Menu, Long> {

}
