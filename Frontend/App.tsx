import React from 'react';
import styled from 'styled-components/native';


const Container = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
  background-color: #f5f5f5;
`;

const Title = styled.Text`
  font-size: 24px;
  color: #333;
  font-weight: bold;
`;

const App: React.FC = () => {
  return (
    <Container>
      <Title>Hello, Styled Component</Title>
    </Container>
  );
};

export default App;
