import PropTypes from "prop-types";
import { Link } from "react-router-dom";

function Display({ filteredData = [] }) {
  // Default to an empty array
  return (
    <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8 p-4">
      {filteredData.map((game) => (
        <div
          key={`${game.id}-${game.name}`}
          className="flex items-center bg-white shadow-md rounded-md p-4 space-x-4"
        >
          <Link to={`/game/${game.name}`}>
            <img
              src={game.imageUrl}
              alt={game.name}
              className="w-24 h-32 object-cover rounded-md"
            />
          </Link>
          <div className="text-center flex-1">
            <h1 className="text-lg font-bold">{game.name}</h1>
            <h1 className="text-sm text-gray-600">{game.releaseDate}</h1>
            <h1 className="text-sm text-gray-800">{game.genres.join(", ")}</h1>
            <h1 className="text-sm text-gray-800">
              {game.platforms.join(", ")}
            </h1>
          </div>
        </div>
      ))}
    </div>
  );
}

Display.propTypes = {
  filteredData: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.object.isRequired,
      name: PropTypes.string.isRequired,
      imageUrl: PropTypes.string.isRequired,
      releaseDate: PropTypes.string.isRequired,
      developers: PropTypes.arrayOf(PropTypes.string).isRequired,
      description: PropTypes.string.isRequired,
      genres: PropTypes.arrayOf(PropTypes.string).isRequired,
      platforms: PropTypes.arrayOf(PropTypes.string).isRequired,
      reviews: PropTypes.arrayOf(PropTypes.object).isRequired,
    })
  ),
};

export default Display;
