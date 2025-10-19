package com.example.allergy.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.allergy.entity.Menu;
import com.example.allergy.entity.Reservation;
import com.example.allergy.form.ReservationForm;
import com.example.allergy.repository.MenuRepository;
import com.example.allergy.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationRepository reservationRepository;
	private final MenuRepository menuRepository;

    // 一覧画面
    @GetMapping
    public String listReservations(Model model) {
    	List<Reservation> reservations = reservationRepository.findAll(); // ここで取得
        model.addAttribute("reservations", reservations);
        return "reservation-list";
    }

    // 新規予約フォーム
    @GetMapping("/new")
    public String newReservation(Model model) {
        model.addAttribute("reservationForm", new ReservationForm());
        model.addAttribute("menus", menuRepository.findAll()); // メニュー一覧を渡す
        return "reservation-form";
    }

    // フォーム送信
    @PostMapping
    public String createReservation(@ModelAttribute ReservationForm form, Model model) {
        Reservation reservation = new Reservation();
        reservation.setCustomerName(form.getCustomerName());
        reservation.setNumberOfPeople(form.getNumberOfPeople());
        reservation.setReservationTime(form.getReservationTime());
        reservation.setMemo(form.getMemo());

        // アレルギー配列をカンマ区切りで保存
        if (form.getAllergies() != null) {
            reservation.setAllergies(String.join(",", form.getAllergies()));
        }
        
     // メニューIDから実体を取得
        if (form.getMenuId() != null) {
        	Menu menu = menuRepository.findById(form.getMenuId()).orElse(null);
            reservation.setMenu(menu);
        }
        reservationRepository.save(reservation);
        return "redirect:/reservations";
    }
 }
