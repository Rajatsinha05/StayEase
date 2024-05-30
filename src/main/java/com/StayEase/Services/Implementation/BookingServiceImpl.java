package com.StayEase.Services.Implementation;

import com.StayEase.Models.Booking;
import com.StayEase.Models.Hotel;
import com.StayEase.Models.User;
import com.StayEase.Repository.BookingRepository;
import com.StayEase.Services.IHotelService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl {

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private BookingRepository bookingRepository;

    public String bookRoom(User user, Long hotelId) {
        Hotel hotel = hotelService.getHotelById(hotelId);


        List<Booking> bookings = bookingRepository.findByHotel(hotel);
        boolean exists = bookingRepository.existsByUserAndHotel(user, hotel);
        if (exists) {
            throw new ValidationException("User has already booked a room in this hotel");
        }

        if (bookings.size() >= hotel.getAvailableRooms()) {
            throw new ValidationException("No rooms available in this hotel");
        }

        Booking booking = new Booking();
        booking.setHotel(hotel);
        booking.setUser(user);
        bookingRepository.save(booking);

        return "Room booked successfully";
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public String updateBooking(Long id, Booking updatedBooking) {
        Optional<Booking> existingBookingOpt = bookingRepository.findById(id);
        if (existingBookingOpt.isEmpty()) {
            throw new ValidationException("Booking not found");
        }
        Booking existingBooking = existingBookingOpt.get();
        existingBooking.setHotel(updatedBooking.getHotel());
        existingBooking.setUser(updatedBooking.getUser());
        bookingRepository.save(existingBooking);
        return "Booking updated successfully";
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public String deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new ValidationException("Booking not found");
        }
        bookingRepository.deleteById(id);
        return "Booking deleted successfully";
    }
}
