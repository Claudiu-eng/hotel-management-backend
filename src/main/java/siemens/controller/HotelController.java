package siemens.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siemens.exception.model.*;
import siemens.model.dto.*;
import siemens.service.HotelService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotel")
public class HotelController {

    private final HotelService hotelService;

    @PostMapping("/initial-data")
    public ResponseEntity<Void> initialData(@RequestBody List<InsertHotelDTO> hotels) {
        hotelService.insertHotels(hotels);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("insert-hotel")
    public ResponseEntity<Void> insertHotel(@RequestBody InsertHotelDTO insertHotelDTO){
        hotelService.insertHotel(insertHotelDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/get-hotels")
    public ResponseEntity<List<HotelDTO>> getHotels(@RequestBody GetHotelsDTO getHotelsDTO) {
        return ResponseEntity.ok(hotelService.getHotels(getHotelsDTO));
    }

    @PostMapping("/booking")
    public ResponseEntity<Void> book(@RequestBody BookDTO bookDTO) throws UserNotFoundException, RoomNotFoundException, HotelNotFoundException {
        hotelService.book(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/cancel-booking")
    public ResponseEntity<Void> cancelBooking(@RequestBody BookDTO bookDTO) throws UserNotFoundException, BookingNotFoundException, RoomNotFoundException, HotelNotFoundException, BookingExpiredException {
        hotelService.cancelBooking(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/review")
    public ResponseEntity<Void> review(@RequestBody ReviewDTO reviewDTO) throws UserNotFoundException, RoomNotFoundException, HotelNotFoundException {
        hotelService.review(reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
