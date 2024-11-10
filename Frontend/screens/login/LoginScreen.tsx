// Frontend/screens/login/LoginScreen.tsx
import React from 'react';
import { Container } from './LoginScreen.styles';
import IdolBomLogo from '../../components/common/IdolBomLogo';
import KakaoLoginButton from '../../components/login/KakaoLoginButton';

const LoginScreen: React.FC = () => {
    return (
        <Container>
            <IdolBomLogo />
            <KakaoLoginButton />
        </Container>
    );
};

export default LoginScreen;
