// Frontend/components/login/KakaoLoginButton.tsx
import React from 'react';
import { Linking } from 'react-native';
import { Button, ButtonImage } from './KakaoLoginButton.styles';
import kakaoLoginImage from '../../assets/images/kakao_login_large_narrow.png';
import { KAKAO_REST_API_KEY, REDIRECT_URI } from '@env';

const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${KAKAO_REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;

const KakaoLoginButton: React.FC = () => {
    const handlePress = () => {
        console.log("카카오 로그인 버튼 클릭하세요");

        // 카카오 로그인 페이지로 이동
            Linking.openURL(KAKAO_AUTH_URL).catch(err =>
                console.error("Failed to open Kakao login URL:", err)
            );
    };

    return (
        <Button onPress={handlePress}>
            <ButtonImage source={kakaoLoginImage} />
        </Button>
    );
};

export default KakaoLoginButton;
