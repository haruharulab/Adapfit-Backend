package com.harulab.adapfit.domain.counseling.sevice;

import com.harulab.adapfit.domain.counseling.domain.Room;
import com.harulab.adapfit.domain.counseling.facade.RoomFacade;
import com.harulab.adapfit.domain.user.facade.UserFacade;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class RoomService {

    private final RoomFacade roomFacade;
    private final UserFacade userFacade;

    @Transactional
    public void createRoom() {
        roomFacade.create(new Room());
    }
}
