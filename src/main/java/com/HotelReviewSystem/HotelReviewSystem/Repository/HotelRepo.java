package com.HotelReviewSystem.HotelReviewSystem.Repository;

import com.HotelReviewSystem.HotelReviewSystem.Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepo extends JpaRepository<Hotel,Long> {


    List<Hotel> findByNameContainingIgnoreCase(String searchTerm);

    /*
    @Query("UPDATE Hotel h SET h.rating = :newRating WHERE h.hotelId = :hotelId")
    void updateRatingById(@Param("hotelId") int hotelId, @Param("newRating") int newRating);
*/

}
