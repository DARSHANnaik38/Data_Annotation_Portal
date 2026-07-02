import { useEffect, useState } from "react";

import DashboardLayout from "../layouts/DashboardLayout";

import UserModal from "../components/UserModal";

import {
  getAllUsers,
  createUser,
  updateUser,
  deleteUser,
} from "../services/userService";

function Users() {
  const [users, setUsers] = useState([]);

  const [show, setShow] = useState(false);

  const [selected, setSelected] = useState(null);

  useEffect(() => {
    load();
  }, []);

  async function load() {
    setUsers(await getAllUsers());
  }

  async function save(user) {
    if (selected) await updateUser(selected.id, user);
    else await createUser(user);

    setShow(false);

    load();
  }

  async function remove(id) {
    if (!window.confirm("Delete User?")) return;

    await deleteUser(id);

    load();
  }

  return (
    <DashboardLayout>
      <div className="d-flex justify-content-between mb-3">
        <h2>Users</h2>

        <button
          className="btn btn-primary"
          onClick={() => {
            setSelected(null);

            setShow(true);
          }}
        >
          Add User
        </button>
      </div>

      <table className="table table-bordered">
        <thead>
          <tr>
            <th>ID</th>

            <th>Name</th>

            <th>Email</th>

            <th>Role</th>

            <th>Status</th>

            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {users.map((u) => (
            <tr key={u.id}>
              <td>{u.id}</td>

              <td>{u.name}</td>

              <td>{u.email}</td>

              <td>{u.role}</td>

              <td>{u.enabled ? "Enabled" : "Disabled"}</td>

              <td>
                <button
                  className="btn btn-warning btn-sm me-2"
                  onClick={() => {
                    setSelected(u);

                    setShow(true);
                  }}
                >
                  Edit
                </button>

                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => remove(u.id)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <UserModal
        show={show}
        onClose={() => setShow(false)}
        onSave={save}
        editData={selected}
      />
    </DashboardLayout>
  );
}

export default Users;
