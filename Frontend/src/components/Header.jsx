import PropTypes from "prop-types";
import { Link } from "react-router-dom";

function Header({ searchTerm = "", setSearchTerm = () => {} }) {
  const handleInputChange = (e) => {
    setSearchTerm(e.target.value);
  };

  return (
    <header className="flex bg-black p-5 gap-3">
      <Link to={`/`}>
        <h1>
          <strong className="inline-flex text-2xl bg-purple px-2 rounded">
            VGDB
          </strong>
        </h1>
      </Link>
      <input
        className="rounded pl-3"
        type="text"
        placeholder="Search VGDB"
        value={searchTerm}
        onChange={handleInputChange}
      />
    </header>
  );
}

Header.propTypes = {
  searchTerm: PropTypes.string,
  setSearchTerm: PropTypes.func,
};

export default Header;
