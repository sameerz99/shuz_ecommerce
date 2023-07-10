package com.shuz.ecommerce.repo;

import com.shuz.ecommerce.entity.Cart;
import com.shuz.ecommerce.entity.User;
import com.shuz.ecommerce.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {
    List<Wishlist> findByUser(User user);

    @Query(
            nativeQuery = true,
            value = "select * from wishlist where customer_id=?1 and product_id=?2"
    )
    Wishlist checkIfProductExistsForUserInWishlist(Integer customerId, Integer productId);
}
