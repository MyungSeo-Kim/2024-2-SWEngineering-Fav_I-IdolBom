// Frontend/components/common/IdolBomLogo.tsx
import React from 'react';
import { LogoImage } from './IdolBomLogo.styles';
import idolBomLogoImage from '../../assets/images/IdolBomLogo.png';

const IdolBomLogo: React.FC = () => {
    // 카카오 로그인
    return <LogoImage source={idolBomLogoImage} />;
};

export default IdolBomLogo;
