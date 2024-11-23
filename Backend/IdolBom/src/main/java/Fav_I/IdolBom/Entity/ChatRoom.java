package Fav_I.IdolBom.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
public class ChatRoom {
    @Id
    @Column(name = "chatRoomID", nullable = false)
    private Integer id;
/*
    @NotNull
    @Column(name = "chatRoomType", nullable = false)
    private Byte chatRoomType;
*/
    @Size(max = 255)
    @Column(name = "title")
    private String title;

    @Size(max = 255)
    @Column(name = "chatRoomImage")
    private String chatRoomImage;

    @NotNull
    @CreationTimestamp
    @Column(name = "createdDate", nullable = false)
    private Instant createdDate;
/*
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "recruitmentID", nullable = false)
    private Recruitment recruitmentID;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "scheduleID", nullable = false)
    private Schedule scheduleID;
*/
}