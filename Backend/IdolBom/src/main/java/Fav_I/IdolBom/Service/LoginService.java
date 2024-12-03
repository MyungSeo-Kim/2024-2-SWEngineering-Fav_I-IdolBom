package Fav_I.IdolBom.Service;

import Fav_I.IdolBom.DTO.GetTokenDTO;
import Fav_I.IdolBom.DTO.KakaoUserDTO;
import Fav_I.IdolBom.Entity.User;
import Fav_I.IdolBom.Repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    @Value("${kakao.client_id}")
    private String clientId;

    @Value("${kakao.redirect_uri}")
    private String redirectUri;

    public GetTokenDTO getAccessTokenFromKakao(String code) throws JsonProcessingException {
        String reqUrl = "https://kauth.kakao.com/oauth/token";
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = rt.exchange(reqUrl, HttpMethod.POST, kakaoTokenRequest, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.getBody(), GetTokenDTO.class);
    }

    public KakaoUserDTO getKakaoInfo(String accessToken) throws JsonProcessingException {
        String reqUrl = "https://kapi.kakao.com/v2/user/me";
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);

        ResponseEntity<String> response = rt.exchange(reqUrl, HttpMethod.GET, kakaoUserInfoRequest, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        Long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("properties").get("nickname").asText();
        String profileImage = jsonNode.get("properties").get("profile_image").asText();

        return KakaoUserDTO.builder()
                .id(id)
                .nickname(nickname)
                .profile_image(profileImage)
                .build();
    }

    public Optional<User> register(KakaoUserDTO kakaoUserDTO) {
        Long id = kakaoUserDTO.getId();
        Optional<User> userInfo = userRepository.findById(id);

        // Register
        log.info(userInfo.toString());
        if (userInfo.isEmpty()) {
            User newUser = new User();
            newUser.setId(id);
            newUser.setUserName(kakaoUserDTO.getNickname());
            newUser.setProfileImage(kakaoUserDTO.getProfile_image());
            userRepository.save(newUser);
        }
        return userInfo;
    }
}
