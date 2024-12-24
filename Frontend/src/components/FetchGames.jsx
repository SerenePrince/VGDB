import { useState, useEffect } from "react";
import axios from "axios";

const baseURL = "http://localhost:8080/api/v1/games";

function FetchGames() {
  const [data, setData] = useState([]); // Default to an empty array
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(baseURL);
        setData(response.data || []); // Default to an empty array if no data is returned
      } catch (err) {
        console.error("Failed to fetch games:", err.message);
        setError("Failed to load games. Please try again later.");
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  return { data, loading, error };
}

export default FetchGames;
