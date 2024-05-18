package siemens.model.mapper;

import org.springframework.stereotype.Component;
import siemens.model.dto.HotelDTO;
import siemens.model.dto.InsertHotelDTO;
import siemens.model.dto.InsertRoomDTO;
import siemens.model.dto.RoomDTO;
import siemens.model.entity.Hotel;
import siemens.model.entity.Room;

import java.util.List;

@Component
public class HotelMapper {

    public HotelDTO entityToDTO(Hotel hotel){
        return HotelDTO.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .rooms(this.entitiesToDTOsRoomsAvailable(hotel.getRooms()))
                .build();
    }

    public List<HotelDTO> entitiesToDTOs(List<Hotel> hotels){
        return hotels.stream().map(this::entityToDTO).toList();
    }

    private List<RoomDTO> entitiesToDTOsRoomsAvailable(List<Room> rooms){
        return rooms.stream().map(this::entityToDTORoom).toList();
    }

    private RoomDTO entityToDTORoom(Room room){
        return RoomDTO.builder()
                .roomNumber(room.getRoomNumber())
                .type(room.getType())
                .price(room.getPrice())
                .isAvailable(room.getIsAvailable())
                .build();
    }

    public Hotel dtoToEntityForInsert(InsertHotelDTO hotelDTO){
        return Hotel.builder()
                .name(hotelDTO.getName())
                .id(hotelDTO.getId())
                .latitude(hotelDTO.getLatitude())
                .longitude(hotelDTO.getLongitude())
                .rooms(this.dtosToEntitiesForInsertRooms(hotelDTO.getRooms()))
                .build();
    }

    public List<Hotel> dtosToEntitiesForInsert(List<InsertHotelDTO> rooms){
        return rooms.stream().map(this::dtoToEntityForInsert).toList();
    }

    private Room dtoToEntityForInsertRoom(InsertRoomDTO room){
        return Room.builder()
                .roomNumber(room.getRoomNumber())
                .type(room.getType())
                .price(room.getPrice())
                .isAvailable(room.getIsAvailable())
                .build();
    }
    private List<Room> dtosToEntitiesForInsertRooms(List<InsertRoomDTO> rooms){
        return rooms.stream().map(this::dtoToEntityForInsertRoom).toList();
    }

}
