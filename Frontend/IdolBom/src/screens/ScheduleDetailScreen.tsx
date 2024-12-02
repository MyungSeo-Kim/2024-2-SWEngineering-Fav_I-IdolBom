// ScheduleDetailScreen.tsx
// 스케줄 상세 정보

import React from 'react';
import { SafeAreaView } from 'react-native';
import styled from 'styled-components/native';
import ScheduleInfo from '../components/schedule/ScheduleInfo';
import Header from '../components/common/Header';

const ScreenContainer = styled(SafeAreaView)`
  flex: 1;
  background-color: #F3F8FF;
`;

export default function ScheduleDetailScreen({ route }) {
  const { schedule } = route.params;

  return (
    <ScreenContainer>
     <Header title="스케줄 상세" />
      <ScheduleInfo
        title={schedule.title}
        date={schedule.date}
        location={schedule.location}
        type={schedule.type}
        description={schedule.description}
        link={schedule.link}
      />
    </ScreenContainer>
  );
}
