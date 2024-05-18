package siemens.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import siemens.exception.model.*;
import siemens.model.dto.*;
import siemens.model.entity.*;
import siemens.model.mapper.HotelMapper;
import siemens.repository.FeedbackRepository;
import siemens.repository.HotelRepository;
import siemens.repository.RoomRepository;
import siemens.repository.UserRepository;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final FeedbackRepository feedbackRepository;
    private static final double EARTH_RADIUS = 6371.0;

    @Transactional
    public void insertHotels(List<InsertHotelDTO> insertHotelDTOList){
        List<Hotel> hotels = hotelMapper.dtosToEntitiesForInsert(insertHotelDTOList);
        hotels.forEach(hotel -> hotel.getRooms().forEach(room -> room.setHotel(hotel)));
        hotelRepository.saveAll(hotels);
    }
    @Transactional
    public void insertHotel(InsertHotelDTO insertHotelDTO){
        Hotel hotel = hotelMapper.dtoToEntityForInsert(insertHotelDTO);
        hotel.getRooms().forEach(room -> room.setHotel(hotel));
        hotelRepository.save(hotel);
    }
    public List<HotelDTO> getHotels(GetHotelsDTO hotelDTO){
        List<Hotel> hotels = hotelRepository.findAll();
        List<Hotel> hotelsInSpecifiedRange = hotels.stream()
                .filter(hotel -> calculateDistance(hotelDTO.getLatitude(), hotelDTO.getLongitude(), hotel.getLatitude(), hotel.getLongitude()) <= hotelDTO.getDistanceInKm())
                .peek(hotel -> {
                    List<Room> availableRooms = hotel.getRooms().stream()
                            .filter(Room::getIsAvailable)
                            .collect(Collectors.toList());
                    LocalDate date = LocalDateTime.now().toLocalDate();
                    long start = date.atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000;
                    long end = date.plusDays(1).atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000 - 1;
                    List<Room> myReservedRooms = hotel.getRooms().stream()
                            .filter(room -> !room.getIsAvailable())
                            .filter(room -> room.getBookings().stream()
                                    .anyMatch(reservation -> reservation.getDate() <= end && reservation.getDate() >= start &&
                                            reservation.getUser().getEmail().equals(hotelDTO.getEmail())))
                                    .collect(Collectors.toList());
                    availableRooms.addAll(myReservedRooms);
                    hotel.setRooms(availableRooms);
                })
                .filter(hotel -> !hotel.getRooms().isEmpty())
                .collect(Collectors.toList());
        return hotelMapper.entitiesToDTOs(hotelsInSpecifiedRange);
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                + Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    public void book(BookDTO bookDTO) throws UserNotFoundException, RoomNotFoundException, HotelNotFoundException {
        //user can make a new book on every day
        //once user selected start hour for a booking, in all day that room will be reserved
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        LocalTime localTime = LocalTime.parse(bookDTO.getTime(), timeFormatter);
        LocalDate currentDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(currentDate, localTime);

        User user = userRepository.findById(bookDTO.getEmail()).orElseThrow(UserNotFoundException::new);
        Hotel hotel = hotelRepository.findById(bookDTO.getHotelId()).orElseThrow(HotelNotFoundException::new);
        Room room = hotel.getRooms().stream()
                .filter(r -> r.getRoomNumber().equals(bookDTO.getRoomNumber()))
                .findFirst()
                .orElseThrow(RoomNotFoundException::new);
        if(!room.getIsAvailable())
            throw new RoomNotFoundException();

        Booking booking = Booking.builder()
                .date(localDateTime.toEpochSecond(ZoneOffset.UTC) * 1000)
                .user(user)
                .room(room)
                .build();
        room.getBookings().add(booking);
        room.setIsAvailable(false);
        roomRepository.save(room);
    }

    public void cancelBooking(BookDTO bookDTO) throws UserNotFoundException, HotelNotFoundException, RoomNotFoundException, BookingNotFoundException, BookingExpiredException {
        User user = userRepository.findById(bookDTO.getEmail()).orElseThrow(UserNotFoundException::new);
        Hotel hotel = hotelRepository.findById(bookDTO.getHotelId()).orElseThrow(HotelNotFoundException::new);
        Room room = hotel.getRooms().stream()
                .filter(r -> r.getRoomNumber().equals(bookDTO.getRoomNumber()))
                .findFirst()
                .orElseThrow(RoomNotFoundException::new);
        LocalDate date = LocalDateTime.now().toLocalDate();
        long start = date.atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000;
        long end = date.plusDays(1).atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000 - 1;
        Booking booking = room.getBookings().stream()
                .filter(b -> b.getUser().getEmail().equals(bookDTO.getEmail()) && b.getDate() <= end && b.getDate() >= start)
                .findFirst()
                .orElseThrow(BookingNotFoundException::new);

        LocalDateTime localTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(booking.getDate()), ZoneOffset.UTC);
        LocalDateTime currentTimePlusTwoHours = LocalDateTime.now().plusHours(2);
        boolean invalidCancel = currentTimePlusTwoHours.isAfter(localTime);
        if(invalidCancel){
            throw new BookingExpiredException();
        }
        room.getBookings().remove(booking);
        room.setIsAvailable(true);
        roomRepository.save(room);
    }

    public void review(ReviewDTO reviewDTO) throws UserNotFoundException, HotelNotFoundException {


        User user = userRepository.findById(reviewDTO.getEmail()).orElseThrow(UserNotFoundException::new);
        Hotel hotel = hotelRepository.findById(reviewDTO.getHotelId()).orElseThrow(HotelNotFoundException::new);

        Feedback feedback = Feedback.builder()
                .comment(reviewDTO.getReview())
                .rating(-1)//TODO: calculate rating
                .user(user)
                .hotel(hotel)
                .date(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000)
                .build();
        hotel.getFeedbackList().add(feedback);
        hotelRepository.save(hotel);
    }


}
