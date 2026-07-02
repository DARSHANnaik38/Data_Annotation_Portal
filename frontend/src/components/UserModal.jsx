import { useEffect, useState } from "react";

function UserModal({ show, onClose, onSave, editData }) {
  const initialState = {
    name: "",
    email: "",
    password: "",
    role: "ANNOTATOR",
    enabled: true,
  };

  const [form, setForm] = useState(initialState);

  useEffect(() => {
    editData ? setForm(editData) : setForm(initialState);
  }, [editData]);

  if (!show) return null;

  const change = (e) => {
    setForm({
      ...form,

      [e.target.name]:
        e.target.name === "enabled"
          ? e.target.value === "true"
          : e.target.value,
    });
  };

  return (
    <div className="modal d-block" style={{ background: "rgba(0,0,0,.5)" }}>
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h5>{editData ? "Edit User" : "Add User"}</h5>

            <button className="btn-close" onClick={onClose} />
          </div>

          <div className="modal-body">
            <input
              className="form-control mb-2"
              placeholder="Name"
              name="name"
              value={form.name}
              onChange={change}
            />

            <input
              className="form-control mb-2"
              placeholder="Email"
              name="email"
              value={form.email}
              onChange={change}
            />

            <input
              className="form-control mb-2"
              type="password"
              placeholder="Password"
              name="password"
              value={form.password}
              onChange={change}
            />

            <select
              className="form-select mb-2"
              name="role"
              value={form.role}
              onChange={change}
            >
              <option>ADMIN</option>
              <option>ANNOTATOR</option>
            </select>

            <select
              className="form-select"
              name="enabled"
              value={form.enabled}
              onChange={change}
            >
              <option value={true}>Enabled</option>

              <option value={false}>Disabled</option>
            </select>
          </div>

          <div className="modal-footer">
            <button className="btn btn-secondary" onClick={onClose}>
              Cancel
            </button>

            <button className="btn btn-primary" onClick={() => onSave(form)}>
              Save
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default UserModal;
