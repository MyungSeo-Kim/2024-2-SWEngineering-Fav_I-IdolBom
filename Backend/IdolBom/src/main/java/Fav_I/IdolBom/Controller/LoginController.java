package Fav_I.IdolBom.Controller;

import Fav_I.IdolBom.DTO.GetTokenDTO;
import Fav_I.IdolBom.DTO.KakaoUserDTO;
import Fav_I.IdolBom.Service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {
    private final LoginService loginService;
    private final HttpSession session;

    // 카카오 Redirect URI에서 GET 요청 처리
    @GetMapping("/callback")
    public ResponseEntity<?> handleCallback(@RequestParam("code") String code) {
        log.info("GET /callback - Authorization Code: {}", code);

        Map<String, Object> response = new LinkedHashMap<>();
        try {
            // 카카오 서버에서 Access Token 요청
            GetTokenDTO accessToken = loginService.getAccessTokenFromKakao(code);

            // Access Token으로 사용자 정보 요청
            KakaoUserDTO userInfo = loginService.getKakaoInfo(accessToken.getAccessToken());

            // 사용자 등록 또는 갱신
            loginService.register(userInfo);

            // 세션에 사용자 정보 저장
            session.setAttribute("userInfo", userInfo);

            response.put("code", "SU");
            response.put("message", "로그인 성공");
            response.put("userInfo", userInfo);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error during GET callback processing", e);
            response.put("code", "Error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // 프론트엔드에서 POST 요청 처리
    @PostMapping("/callback")
    public ResponseEntity<?> handleAuthorizationCode(@RequestBody Map<String, String> requestBody) {
        String code = requestBody.get("code");
        log.info("POST /callback - Authorization Code: {}", code);

        Map<String, Object> response = new LinkedHashMap<>();
        try {
            // 카카오 서버에서 Access Token 요청
            GetTokenDTO accessToken = loginService.getAccessTokenFromKakao(code);

            // Access Token으로 사용자 정보 요청
            KakaoUserDTO userInfo = loginService.getKakaoInfo(accessToken.getAccessToken());

            // 사용자 등록 또는 갱신
            loginService.register(userInfo);

            response.put("code", "SU");
            response.put("message", "로그인 성공");
            response.put("userInfo", userInfo);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error during POST callback processing", e);
            response.put("code", "Error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
