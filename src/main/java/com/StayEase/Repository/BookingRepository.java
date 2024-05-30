package com.StayEase.Repository;

import com.StayEase.Models.Booking;
import com.StayEase.Models.Hotel;
import com.StayEase.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByHotel(Hotel hotel);
    boolean existsByUserAndHotel(User user, Hotel hotel);
}
