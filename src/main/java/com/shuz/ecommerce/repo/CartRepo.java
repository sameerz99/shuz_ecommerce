package com.shuz.ecommerce.repo;

import com.shuz.ecommerce.entity.Cart;
import com.shuz.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart, Integer> {
    List<Cart> findByUser(User user);

    @Query(
            nativeQuery = true,
            value = "select * from cart where customer_id=?1 and product_id=?2"
    )
    Cart checkIfProductExistsForUserInCart(Integer customerId, Integer productId);
}
