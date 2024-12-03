package Fav_I.IdolBom.DTO;

import Fav_I.IdolBom.Entity.User;
import lombok.*;

@Data
@Builder
@ToString
public class KakaoUserDTO {
    private Long id; // 고유 id
    private String nickname; // 카카오톡 상의 이름
    private String profile_image; // 카카오톡 프로필 사진

    // DTO를 User 엔티티로 변환
    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        user.setUserName(this.nickname);
        user.setProfileImage(this.profile_image);
        return user;
    }
}
