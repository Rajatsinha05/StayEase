package com.StayEase.Services;

import com.StayEase.Models.Hotel;

import java.util.List;

public interface IHotelService {
    String addHotel(Hotel hotel);
    List<Hotel> getHotels();
    Hotel getHotelById(Long id);
    String deleteHotel(Long id);
    String updateHotel(Long id, Hotel hotel);
}
