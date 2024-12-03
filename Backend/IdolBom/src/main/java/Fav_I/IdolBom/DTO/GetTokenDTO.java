package Fav_I.IdolBom.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetTokenDTO {

    @JsonProperty("access_token")
    public String accessToken;

    @JsonProperty("token_type")
    public String tokenType;

    @JsonProperty("refresh_token")
    public String refreshToken;

    @JsonProperty("expires_in")
    public Integer expiresIn;

    @JsonProperty("scope")
    public String scope;

    @JsonProperty("refresh_token_expires_in")
    public Integer refreshTokenExpiresIn;

    @JsonProperty("id_token")
    public String idToken;

}