package siemens.model.mapper;

import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Component;
import siemens.model.dto.InsertRoomDTO;
import siemens.model.entity.Room;

import java.util.List;

@Component
public class RoomMapper {

    public Room dtoToEntityForInsert(InsertRoomDTO room){
        return Room.builder()
                .roomNumber(room.getRoomNumber())
                .type(room.getType())
                .price(room.getPrice())
                .isAvailable(room.getIsAvailable())
                .build();
    }

    public List<Room> dtosToEntitiesForInsert(List<InsertRoomDTO> rooms){
        return rooms.stream().map(this::dtoToEntityForInsert).toList();
    }

}
