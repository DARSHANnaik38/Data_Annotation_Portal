import { Link } from "react-router-dom";

function Sidebar() {
  return (
    <div
      className="bg-dark text-white p-3"
      style={{
        width: "250px",
        minHeight: "100vh",
      }}
    >
      <h3>Annotation Portal</h3>

      <hr />

      <ul className="nav flex-column">
        <li className="nav-item mb-2">
          <Link className="nav-link text-white" to="/dashboard">
            Dashboard
          </Link>
        </li>

        <li className="nav-item mb-2">
          <Link className="nav-link text-white" to="/datasets">
            Datasets
          </Link>
        </li>

        <li className="nav-item mb-2">
          <Link className="nav-link text-white" to="/users">
            Users
          </Link>
        </li>

        <li className="nav-item mb-2">
          <Link className="nav-link text-white" to="/assignments">
            Assignments
          </Link>
        </li>
      </ul>
    </div>
  );
}

export default Sidebar;
