import { useState, useEffect } from "react";

function DatasetModal({ show, onClose, onSave, editData }) {
  const initialState = {
    name: "",
    description: "",
    totalImages: "",
    priority: "LOW",
    status: "ACTIVE",
  };

  const [form, setForm] = useState(initialState);

  useEffect(() => {
    if (editData) setForm(editData);
    else setForm(initialState);
  }, [editData]);

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const submit = (e) => {
    e.preventDefault();

    onSave(form);
  };

  if (!show) return null;

  return (
    <div className="modal d-block" style={{ background: "rgba(0,0,0,.5)" }}>
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h5>{editData ? "Edit Dataset" : "Add Dataset"}</h5>

            <button className="btn-close" onClick={onClose} />
          </div>

          <form onSubmit={submit}>
            <div className="modal-body">
              <input
                className="form-control mb-3"
                placeholder="Dataset Name"
                name="name"
                value={form.name}
                onChange={handleChange}
                required
              />

              <textarea
                className="form-control mb-3"
                placeholder="Description"
                name="description"
                value={form.description}
                onChange={handleChange}
                required
              />

              <input
                className="form-control mb-3"
                type="number"
                name="totalImages"
                value={form.totalImages}
                onChange={handleChange}
                required
              />

              <select
                className="form-select mb-3"
                name="priority"
                value={form.priority}
                onChange={handleChange}
              >
                <option>LOW</option>
                <option>MEDIUM</option>
                <option>HIGH</option>
              </select>

              <select
                className="form-select"
                name="status"
                value={form.status}
                onChange={handleChange}
              >
                <option>ACTIVE</option>
                <option>INACTIVE</option>
              </select>
            </div>

            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                onClick={onClose}
              >
                Cancel
              </button>

              <button className="btn btn-primary">Save</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default DatasetModal;
