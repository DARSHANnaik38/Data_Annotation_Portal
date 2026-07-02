import { useState } from "react";

function AssignmentModal({ show, onClose, onSave }) {
  const [form, setForm] = useState({
    datasetId: "",
    annotatorId: "",
    dueDate: "",
    remarks: "",
  });

  if (!show) return null;

  const change = (e) => {
    setForm({
      ...form,

      [e.target.name]: e.target.value,
    });
  };

  return (
    <div className="modal d-block" style={{ background: "rgba(0,0,0,.5)" }}>
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h5>Assign Dataset</h5>

            <button className="btn-close" onClick={onClose} />
          </div>

          <div className="modal-body">
            <input
              className="form-control mb-2"
              placeholder="Dataset ID"
              name="datasetId"
              onChange={change}
            />

            <input
              className="form-control mb-2"
              placeholder="Annotator ID"
              name="annotatorId"
              onChange={change}
            />

            <input
              className="form-control mb-2"
              type="date"
              name="dueDate"
              onChange={change}
            />

            <textarea
              className="form-control"
              placeholder="Remarks"
              name="remarks"
              onChange={change}
            />
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

export default AssignmentModal;
