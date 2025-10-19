package com.example.allergy.form;

import java.time.LocalDateTime;

public class ReservationForm {

	    private String customerName;
	    private int numberOfPeople;
	    private LocalDateTime reservationTime;
	    private String memo;
	    private Long menuId;

	    // getter / setter 
	 // 複数チェックできるように String[] を用意
	    private String[] allergies;

	    // --- getter / setter ---
	    public String getCustomerName() {
	        return customerName;
	    }
	    public void setCustomerName(String customerName) {
	        this.customerName = customerName;
	    }

	    public int getNumberOfPeople() {
	        return numberOfPeople;
	    }
	    public void setNumberOfPeople(int numberOfPeople) {
	        this.numberOfPeople = numberOfPeople;
	    }

	    public LocalDateTime getReservationTime() {
	        return reservationTime;
	    }
	    public void setReservationTime(LocalDateTime reservationTime) {
	        this.reservationTime = reservationTime;
	    }

	    public String getMemo() {
	        return memo;
	    }
	    public void setMemo(String memo) {
	        this.memo = memo;
	    }

	    public String[] getAllergies() {
	        return allergies;
	    }
	    public void setAllergies(String[] allergies) {
	        this.allergies = allergies;
	    }
	    
	    public Long getMenuId() {
	        return menuId;
	    }
	    public void setMenuId(Long menuId) {
	        this.menuId = menuId;
	    }

}
