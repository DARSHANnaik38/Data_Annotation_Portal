import { useEffect, useState } from "react";
import DashboardLayout from "../layouts/DashboardLayout";
import AssignmentModal from "../components/AssignmentModal";

import {
  getAllAssignments,
  createAssignment,
  deleteAssignment,
  updateAssignmentStatus,
} from "../services/assignmentService";

function Assignments() {
  const [assignments, setAssignments] = useState([]);
  const [show, setShow] = useState(false);
  const [search, setSearch] = useState("");

  useEffect(() => {
    load();
  }, []);

  async function load() {
    setAssignments(await getAllAssignments());
  }

  async function save(data) {
    await createAssignment(data);
    setShow(false);
    load();
  }

  async function remove(id) {
    if (!window.confirm("Delete assignment?")) return;

    await deleteAssignment(id);
    load();
  }

  async function complete(id) {
    await updateAssignmentStatus(id, "COMPLETED");
    load();
  }

  const filteredAssignments = assignments.filter(
    (a) =>
      a.datasetName.toLowerCase().includes(search.toLowerCase()) ||
      a.annotatorName.toLowerCase().includes(search.toLowerCase()),
  );

  return (
    <DashboardLayout>
      <div className="d-flex justify-content-between mb-3">
        <h2>Assignments</h2>

        <button className="btn btn-primary" onClick={() => setShow(true)}>
          Assign Dataset
        </button>
      </div>

      <div className="row mb-3">
        <div className="col-md-4">
          <input
            className="form-control"
            placeholder="Search Dataset / Annotator..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
        </div>
      </div>

      <table className="table table-bordered">
        <thead>
          <tr>
            <th>ID</th>
            <th>Dataset</th>
            <th>Annotator</th>
            <th>Status</th>
            <th>Due Date</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {filteredAssignments.map((a) => (
            <tr key={a.id}>
              <td>{a.id}</td>
              <td>{a.datasetName}</td>
              <td>{a.annotatorName}</td>
              <td>{a.status}</td>
              <td>{a.dueDate}</td>

              <td>
                <button
                  className="btn btn-success btn-sm me-2"
                  onClick={() => complete(a.id)}
                >
                  Complete
                </button>

                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => remove(a.id)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <AssignmentModal
        show={show}
        onClose={() => setShow(false)}
        onSave={save}
      />
    </DashboardLayout>
  );
}

export default Assignments;
