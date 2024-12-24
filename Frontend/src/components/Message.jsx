import PropTypes from "prop-types";

const Message = ({ type, children = null }) => (
  <div
    className="p-10 flex justify-center items-center"
    role={type === "error" ? "alert" : "status"}
    aria-live={type === "error" ? "assertive" : "polite"}
  >
    <p className="text-center text-xl">
      <span
        className={`bg-black p-3 rounded ${
          type === "loading" ? "text-purple" : "text-red"
        }`}
      >
        {children || (type === "loading" ? "Loading..." : "An error occurred.")}
      </span>
    </p>
  </div>
);

Message.propTypes = {
  type: PropTypes.oneOf(["loading", "error"]).isRequired,
  children: PropTypes.node,
};

export default Message;
