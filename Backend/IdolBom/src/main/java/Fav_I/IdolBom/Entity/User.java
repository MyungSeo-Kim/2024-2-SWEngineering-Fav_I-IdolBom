package Fav_I.IdolBom.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class User {
    @Id
    @Column(name = "userID", nullable = false)
    private Long id;

    @Size(max = 10)
    @NotNull
    @Column(name = "userName", nullable = false, length = 10)
    private String userName;

    @Size(max = 255)
    @Column(name = "profileImage")
    private String profileImage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idolID")
    private Idol idolID;

    @NotNull
    @Column(name = "trustScore")
    private Integer trustScore;

    @Size(max = 20)
    @Column(name = "accountNum", length = 20)
    private String accountNum;

    @Size(max = 100)
    @Column(name = "bio", length = 100)
    private String bio;

}