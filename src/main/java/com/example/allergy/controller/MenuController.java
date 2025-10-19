package com.example.allergy.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.allergy.entity.Dish;
import com.example.allergy.entity.Menu;
import com.example.allergy.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MenuController {

	private final MenuRepository menuRepository;
	
	// メニュー一覧を表示
	@GetMapping("/menus")
	public String listMenus(Model model) {
		model.addAttribute("menus", menuRepository.findAll());
		return "menu-list";
	}
	
	// 新規登録フォームを表示
	@GetMapping("/menus/new")
	public String newMenuForm(Model model) {
		Menu menu = new Menu();
		
		menu.setDishes(List.of(new Dish()));
		model.addAttribute("menu", new Menu());
		return "menu-form";
	}
	
	// 登録処理
	@PostMapping("/menus")
	public String createMenu(Menu menu) {
		// dishes の中で空欄のものを除外
	    menu.setDishes(
	        menu.getDishes().stream()
	            .filter(d -> d.getName() != null && !d.getName().isBlank())
	            .toList()
	    );
		menuRepository.save(menu);
		return "redirect:/menus";
	}
	
	// 編集画面
    @GetMapping("/menus/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid menu ID"));
        model.addAttribute("menu", menu);
        return "menu-form"; // 登録と同じフォームを使う
    }
    
    // 更新処理
    @PostMapping("/menus/update")
    public String update(@ModelAttribute Menu menu) {
    	// 既存メニュー取得
        Menu existing = menuRepository.findById(menu.getId()).orElseThrow();

        // 古い料理リストをクリアして新しいものに置き換える
        existing.getDishes().clear();
        existing.getDishes().addAll(menu.getDishes());

        existing.setMenuName(menu.getMenuName());
        menuRepository.save(existing);
        return "redirect:/menus";
    }
    
 // 削除
    @GetMapping("/menus/delete/{id}")
    public String delete(@PathVariable Long id) {
        menuRepository.deleteById(id);
        return "redirect:/menus";
    }
}
