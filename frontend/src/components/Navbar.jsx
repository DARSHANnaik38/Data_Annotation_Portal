function Navbar({ logout }) {
  return (
    <nav className="navbar navbar-light bg-light shadow-sm px-4">
      <h4>Dashboard</h4>

      <button className="btn btn-danger" onClick={logout}>
        Logout
      </button>
    </nav>
  );
}

export default Navbar;
