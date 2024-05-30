package com.StayEase.Controllers;


import com.StayEase.Models.Hotel;
import com.StayEase.Services.Implementation.HotelServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelServiceImpl hotelService;

    @Autowired
    public HotelController(HotelServiceImpl hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<String> addHotel(@Valid @RequestBody Hotel hotel) {
        String response = hotelService.addHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getHotels() {
        List<Hotel> hotels = hotelService.getHotels();
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateHotel(@PathVariable Long id, @Valid @RequestBody Hotel hotel) {
        String response = hotelService.updateHotel(id, hotel);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id) {
        String response = hotelService.deleteHotel(id);
        return ResponseEntity.ok(response);
    }
}
