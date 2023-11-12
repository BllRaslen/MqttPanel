import React from 'react';
import styled from 'styled-components';

// Define styled components
const AppContainer = styled.div`
  font-family: 'Arial', sans-serif;
  text-align: center;
  margin: 20px;
`;

const AppHeader = styled.h1`
  color: #333;
`;

function App() {
    return (
        <AppContainer>
            <AppHeader> Table</AppHeader>
        </AppContainer>
    );
}

export default App;
