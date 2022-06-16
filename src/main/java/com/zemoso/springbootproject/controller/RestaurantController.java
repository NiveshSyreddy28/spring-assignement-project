package com.zemoso.springbootproject.controller;

import com.zemoso.springbootproject.entity.Item;
import com.zemoso.springbootproject.entity.User;
import com.zemoso.springbootproject.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    private MenuService menuService;
    private List<String> availabilityOptions;
    private List<String> itemTypeOptions;

    private List<String> userAuthorities;

    @PostConstruct
    public void addAvailabilityOptions(){
        availabilityOptions = new ArrayList<String>();
        availabilityOptions.add("Yes");
        availabilityOptions.add("No");

        itemTypeOptions = new ArrayList<String>();
        itemTypeOptions.add("Veg");
        itemTypeOptions.add("Non-Veg");

        userAuthorities = new ArrayList<String>();
        userAuthorities.add("ADMIN");
        userAuthorities.add("CHEF");
    }

    @Autowired
    public RestaurantController(MenuService menuService) {

        this.menuService = menuService;
    }

    @GetMapping("/home")
    public String welcomePage(){

        return "Home";
    }
    @GetMapping("/loginHome")
    public String AfterLoginPage(){

        return "after-login";
    }

    @GetMapping("/accessDenied")
    public String getAccessDenied(){

        return "access-denied";
    }

    //add mapping for "/list"
    @GetMapping("/updateMenu")
    public String listItems(Model model){

        List<Item> itemsList = menuService.findAllItems();

        model.addAttribute("items", itemsList);

//        Item item = menuService.findByItemId(2);
//        model.addAttribute("finditem", item);

        return "admin/configure-items-list";

    }
    @GetMapping("/showFormForAddItem")
    public String showFormForAdd(Model model){

        model.addAttribute("availabilityOptions", availabilityOptions);
        model.addAttribute("typeOptions", itemTypeOptions);

        Item item = new Item();
        model.addAttribute("item", item);

        return "admin/add-item-form";

    }

@GetMapping("/updateItem")
public String updateItem(@RequestParam("itemId") int itemId, Model model){

        Item item = menuService.findByItemId(itemId);
        model.addAttribute("item", item);
        model.addAttribute("availabilityOptions",availabilityOptions);
        model.addAttribute("typeOptions",itemTypeOptions);

        return "admin/add-item-form";
    }
    @GetMapping("/updateAvailability")
    public String updateAvailability(@RequestParam("itemId")int id, Model model){
        Item item = menuService.findByItemId(id);
        model.addAttribute("item", item);
        model.addAttribute("availabilityOptions",availabilityOptions);
//        model.addAttribute("typeOptions", itemTypeOptions);
        return "chef/update-availability-form.html";
    }
    @PostMapping("/saveItem")
    public String saveItem(@Valid @ModelAttribute("item") Item item, BindingResult bindingResult,Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("typeOptions",itemTypeOptions);
            model.addAttribute("availabilityOptions",availabilityOptions);
            return "admin/add-item-form";
        }
        menuService.saveItem(item);
        return "redirect:/restaurant/menu";
    }
    @GetMapping("/deleteItem")
    public String delete(@RequestParam("itemId") int id){

        //delete the item
        menuService.deleteItemById(id);

        return "redirect:/restaurant/menu";
    }

    @GetMapping("/menu")
    public String itemsMenu(Model model){

        List<Item> itemsList = menuService.findAllItems();

        model.addAttribute("items", itemsList);

        return "default-menu";

    }

    @GetMapping("/chefMenu")
    public String chefList(Model model){

        List<Item> itemsList = menuService.findAllItems();

        model.addAttribute("items", itemsList);

        return "chef/chef-menu";

    }
    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("userAuthorities", userAuthorities);
            return "admin/add-user-form";
        }
        String password = user.getPassword();
        password = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(password);

        menuService.saveUser(user);
        return "redirect:/restaurant/loginHome";
    }

    @GetMapping("/addUser")
    public String showFormForAddUser(Model model){

        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("userAuthorities", userAuthorities);

        return "admin/add-user-form";

    }

}
