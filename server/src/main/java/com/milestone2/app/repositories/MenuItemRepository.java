package com.milestone2.app.repositories;

import com.milestone2.app.models.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MenuItemRepository extends MongoRepository<MenuItem, String> {
    List<MenuItem> findByRestaurant(String restaurant);
    MenuItem findByRestaurantAndItem(String restaurant, String item);
    List<MenuItem> findAll();
}
