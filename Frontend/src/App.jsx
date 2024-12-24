import { useState, useMemo } from "react";
import { Route, Routes } from "react-router-dom";

import Header from "./components/Header";
import Display from "./components/Display";
import FetchGames from "./components/FetchGames";
import Message from "./components/Message";
import GamePage from "./components/GamePage";

function App() {
  const {
    data: gameData = [],
    loading: gameLoading,
    error: gameError,
  } = FetchGames();
  const [searchTerm, setSearchTerm] = useState("");

  // Filtered data with memoization
  const filteredData = useMemo(
    () =>
      gameData.filter((game) =>
        game.name.toLowerCase().includes(searchTerm.toLowerCase())
      ),
    [gameData, searchTerm]
  );

  // Handle loading and error states
  if (gameLoading) return <Message type="loading">Loading...</Message>;
  if (gameError)
    return (
      <Message type="error">
        Oops! Something went wrong. Please try again later.
      </Message>
    );

  return (
    <>
      <Header searchTerm={searchTerm} setSearchTerm={setSearchTerm} />
      <Routes>
        <Route path="/" element={<Display filteredData={filteredData} />} />
        <Route path="/game/:name" element={<GamePage gameData={gameData} />} />
      </Routes>
    </>
  );
}

export default App;
