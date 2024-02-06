package com.HotelReviewSystem.HotelReviewSystem.Controller;


import com.HotelReviewSystem.HotelReviewSystem.Service.HotelService;
import com.HotelReviewSystem.HotelReviewSystem.Model.Hotel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class HotelController {

    private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/getAllHotels")
    public String getAllHotels(Model model){

        List<Hotel> hotels=hotelService.findAllHotels();
        model.addAttribute("hotels",hotels);
        return "hotels-list";

    }
    @PostMapping("/saveHotel")
    public String saveHotel(Hotel hotel) {
        System.out.println("Received hotel name: " + hotel.getName());
        hotelService.saveHotel(hotel);
        // Redirect to a confirmation page or wherever you want to navigate after saving
        return "redirect:/hotelForm";
    }

    @GetMapping("/deleteHotel/{id}")
    public String deleteHotel(@PathVariable int id) {
        hotelService.deleteHotelById(id);
        return "redirect:/getAllHotels";
    }

    @GetMapping("/hotelForm")
    public String showForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "hotel-create";
    }

    @GetMapping("/searchHotels")
    public String searchHotels(@RequestParam(name = "search") String search, Model model) {
        List<Hotel> searchResults = hotelService.searchHotels(search);

        model.addAttribute("hotels", searchResults);
        return "hotels-list"; // Assuming your Thymeleaf template is named "movies.html"
    }

    @GetMapping("/hotelDetails/{id}")
    public String hotelDetails(@PathVariable int id,Model model) {
        Optional<Hotel> hotel=hotelService.hotelDetailsById(id);
        Hotel hotel1=null;
        if(hotel.isPresent()){
            hotel1=hotel.get();
        }
        System.out.println("Here12 "+hotel1);

     //   System.out.println(reviews.get(0).getReviewS());
        model.addAttribute("hotel",hotel1);
        return "hotel-Details";
    }

    @PostMapping("/submitReview")
    public String submitReview(@RequestParam("hotelId") int hotelId,
                               @RequestParam("rating") int rating,
                               @RequestParam("review") String review) {
        // Process the review submission, save to the database, etc.
        // You can use the hotelId, rating, and review parameters in your backend logic.
        updateRatingReview(hotelId,rating,review);
        // Redirect to a confirmation page or the hotel details page
        return "redirect:/hotelDetails/" + hotelId;
    }

    public void updateRatingReview(int hotelId,int rating,String review){
        Optional<Hotel> hotel=hotelService.findHotelById(hotelId);
        Hotel hotel1=null;
        if(hotel.isPresent()){
            hotel1=hotel.get();
        }
        int ratingOld=hotel1.getRating();

        int ratingNew=(ratingOld+rating)/2;
        hotelService.updateRatingById(hotelId,ratingNew);
        hotelService.updateReviewById((hotelId),review);
    }






}
