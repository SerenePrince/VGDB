import { useParams } from "react-router-dom";
import PropTypes from "prop-types";
import { useMemo } from "react";
import Message from "./Message";

function GamePage({ gameData = [] }) {
  // Default gameData to an empty array if not provided
  const { name } = useParams();

  // Memoize the game data lookup to avoid recomputing on every render
  const game = useMemo(
    () =>
      gameData.find((game) => game.name.toLowerCase() === name.toLowerCase()),
    [gameData, name]
  );

  // Early returns for error/loading states
  if (!game) {
    return (
      <Message type="error">
        Game not found. We couldn&apos;t find the game you&apos;re looking for.
        Please try again.
      </Message>
    );
  }

  // Reviews data is now part of the game object, so we can use it directly
  const reviewData = game.reviews || [];

  return (
    <div className="p-6 bg-gray-800 text-white min-h-screen">
      {/* Game Details */}
      <div className="max-w-4xl mx-auto bg-gray-900 p-6 rounded-lg shadow-lg">
        <h1 className="text-4xl font-bold mb-4">{game.name}</h1>
        <p className="text-lg mb-2">
          Release Date:{" "}
          <span className="text-gray-400">{game.releaseDate}</span>
        </p>
        <img
          src={game.imageUrl}
          alt={game.name}
          className="w-50 h-auto rounded-lg mb-4"
        />
        <p className="text-lg mb-2">
          Genres:{" "}
          <span className="text-gray-400">{game.genres.join(", ")}</span>
        </p>
        <p className="text-lg mb-2">
          Platforms:{" "}
          <span className="text-gray-400">{game.platforms.join(", ")}</span>
        </p>
        <p className="text-lg mb-2">
          Description: <span className="text-gray-400">{game.description}</span>
        </p>
        <p className="text-lg mb-4">
          Developers:{" "}
          <span className="text-gray-400">{game.developers.join(", ")}</span>
        </p>
      </div>

      {/* Reviews Section */}
      <div className="mt-10 max-w-4xl mx-auto bg-gray-900 p-6 rounded-lg shadow-lg">
        <h2 className="text-3xl font-semibold mb-4 text-purple-400">Reviews</h2>
        {reviewData && reviewData.length > 0 ? (
          reviewData.map((review) => (
            <div
              key={review.id}
              className="mb-6 p-4 bg-gray-800 rounded-lg shadow-md"
            >
              <h3 className="text-xl font-bold text-indigo-300">
                {review.username}
              </h3>
              <p className="text-sm text-gray-500">
                Score: <span className="text-yellow-400">{review.score}</span>
              </p>
              <h4 className="text-lg font-semibold text-gray-200 mt-2">
                {review.reviewHeader}
              </h4>
              <p className="text-gray-300 mt-2">{review.reviewBody}</p>
            </div>
          ))
        ) : (
          <p className="text-gray-500">No reviews available for this game.</p>
        )}
      </div>
    </div>
  );
}

// Prop validation for GamePage
GamePage.propTypes = {
  gameData: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.string.isRequired,
      name: PropTypes.string.isRequired,
      imageUrl: PropTypes.string.isRequired,
      releaseDate: PropTypes.string.isRequired,
      developers: PropTypes.arrayOf(PropTypes.string).isRequired,
      description: PropTypes.string.isRequired,
      genres: PropTypes.arrayOf(PropTypes.string).isRequired,
      platforms: PropTypes.arrayOf(PropTypes.string).isRequired,
      reviews: PropTypes.arrayOf(
        PropTypes.shape({
          id: PropTypes.string.isRequired,
          reviewHeader: PropTypes.string.isRequired,
          reviewBody: PropTypes.string.isRequired,
          score: PropTypes.number.isRequired,
          username: PropTypes.string.isRequired,
        })
      ),
    })
  ).isRequired,
};

export default GamePage;
