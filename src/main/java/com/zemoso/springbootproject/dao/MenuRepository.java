package com.zemoso.springbootproject.dao;

import com.zemoso.springbootproject.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Item,Integer> {

    //add a method to sort by last name
//    public LiItem> findAllByOrderByFirstNameAsc();
//    @Query(value = "select * from items where id = :id",nativeQuery = true)
public Item findById(int id);
}
