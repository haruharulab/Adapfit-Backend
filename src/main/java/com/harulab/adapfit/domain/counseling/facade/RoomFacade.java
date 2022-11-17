package com.harulab.adapfit.domain.counseling.facade;

import com.harulab.adapfit.domain.counseling.domain.Room;
import com.harulab.adapfit.domain.counseling.domain.repository.RoomRepository;
import com.harulab.adapfit.domain.counseling.domain.type.RoomStatus;
import com.harulab.adapfit.domain.counseling.exception.RoomNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomFacade {

    private final RoomRepository roomRepository;

    public Room findRoomById(Long id) {
        return roomRepository.findByIdAndStatus(id, RoomStatus.ACTIVATED)
                .orElseThrow(() -> RoomNotFoundException.EXCEPTION);
    }

    public void create(Room room) {
        roomRepository.save(room);
    }
}
