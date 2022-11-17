package com.harulab.adapfit.domain.counseling.domain;

import com.harulab.adapfit.domain.counseling.domain.type.RoomStatus;
import com.harulab.adapfit.global.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@DynamicUpdate
@DynamicInsert
@Getter
@Entity
public class Room extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private RoomStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<RoomMember> members = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();

    public Room() {
        this.status = RoomStatus.ACTIVATED;
    }

    public void endRoom() {
        this.status = RoomStatus.ENDED;
    }
}
