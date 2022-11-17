package com.harulab.adapfit.domain.counseling.presentation.dto.req;

import com.harulab.adapfit.global.utils.ValidMessageConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequestDto {

    @NotNull(message = ValidMessageConstants.ROOM_ID_NOT_NULL)
    private String roomId;

}
