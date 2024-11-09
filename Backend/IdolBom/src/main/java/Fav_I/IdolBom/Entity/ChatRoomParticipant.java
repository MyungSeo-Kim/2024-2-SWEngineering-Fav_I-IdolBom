package Fav_I.IdolBom.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
public class ChatRoomParticipant {
    @Id
    @Column(name = "chatRoomID", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chatRoomID", nullable = false)
    private ChatRoom chatRoom;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userID", nullable = false)
    private User userID;

    @Column(name = "lastReadMessageID")
    private Integer lastReadMessageID;

    @NotNull
    @Column(name = "joinDate", nullable = false)
    private Instant joinDate;

}