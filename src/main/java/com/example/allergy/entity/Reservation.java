package com.example.allergy.entity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private int numberOfPeople;
    private LocalDateTime reservationTime;
    private String allergies;
    private String memo;
    
    @ManyToOne
    @JoinColumn(name = "menu_id") // 外部キー列
    private Menu menu; // ✅ メニューと紐づけ
    
    // getter / setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public int getNumberOfPeople() { return numberOfPeople; }
    public void setNumberOfPeople(int numberOfPeople) { this.numberOfPeople = numberOfPeople; }

    public LocalDateTime getReservationTime() { return reservationTime; }
    public void setReservationTime(LocalDateTime reservationTime) { this.reservationTime = reservationTime; }

    public String getAllergies() { return allergies; }
    public void setAllergies(String allergies) { this.allergies = allergies; }
    
    public String getMemo() { return memo; }
    public void setMemo(String memo) { this.memo = memo; }
    
    public Menu getMenu() { return menu; }
    public void setMenu(Menu menu) { this.menu = menu; }
    
    public List<String> getMatchedAllergies() {
        if (menu == null || menu.getDishes() == null || allergies == null || allergies.isEmpty()) {
            return List.of();
        }

        // ユーザーのアレルギーをリスト化
        List<String> userAllergies = Arrays.asList(allergies.split(","));

        // メニュー内の料理のアレルギーをフラット化
        List<String> menuAllergies = menu.getDishes().stream()
                .flatMap(dish -> dish.getAllergies().stream())
                .toList();

        // 共通部分（一致するアレルギー）を抽出
        return userAllergies.stream()
                .filter(menuAllergies::contains)
                .toList();
    }

}
