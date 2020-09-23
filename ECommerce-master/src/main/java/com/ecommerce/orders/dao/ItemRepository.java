package com.ecommerce.orders.dao;

import com.ecommerce.orders.domain.Customer;
import com.ecommerce.orders.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
