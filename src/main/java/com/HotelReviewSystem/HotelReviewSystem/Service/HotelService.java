package com.HotelReviewSystem.HotelReviewSystem.Service;

import com.HotelReviewSystem.HotelReviewSystem.Model.Hotel;
import com.HotelReviewSystem.HotelReviewSystem.Repository.HotelRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

        private HotelRepo hotelRepo;

        public HotelService(HotelRepo hotelRepo) {
            this.hotelRepo = hotelRepo;
        }

        public List<Hotel> findAllHotels(){
            return hotelRepo.findAll();
        }

        public Hotel saveHotel(Hotel hotel) {
            Hotel hotel1 = hotelRepo.save(hotel);
            return hotel1;
        }

        public void deleteHotelById(int hotelId) {
            hotelRepo.deleteById((long)hotelId);
        }

    public Optional<Hotel> findHotelById(int hotelId) {
        return hotelRepo.findById((long)hotelId);
    }

    public void updateRatingById(int hotelId,int newRating){
        findHotelById(hotelId).ifPresent(hotel -> {
            hotel.setRating(newRating);
            hotelRepo.save(hotel);
        });    }
        public void updateReviewById(int hotelId,String review){
        findHotelById(hotelId).ifPresent(hotel -> {
            hotel.setReview(review);
            hotelRepo.save(hotel);
        });    }

    public List<Hotel> searchHotels(String searchTerm) {

            if (searchTerm.equals("")) {
                return findAllHotels();
            }
            else{
                return hotelRepo.findByNameContainingIgnoreCase(searchTerm);
            }
        }

        public Optional<Hotel> hotelDetailsById(int hotelId) {
            return hotelRepo.findById((long)hotelId);

        }




    }
