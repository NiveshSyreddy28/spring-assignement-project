package com.zemoso.springbootproject.service;

import com.zemoso.springbootproject.entity.Item;
import com.zemoso.springbootproject.entity.User;

import java.util.List;

public interface MenuService {
    List<Item> findAllItems();

    public Item findByItemId(int Id);

    public void saveItem(Item item);

    public void deleteItemById(int Id);

    public void saveUser(User user);

}


