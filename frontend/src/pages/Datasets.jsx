import { useEffect, useState } from "react";

import DashboardLayout from "../layouts/DashboardLayout";

import DatasetModal from "../components/DatasetModal";

import {
  getAllDatasets,
  createDataset,
  updateDataset,
  deleteDataset,
} from "../services/datasetService";

function Datasets() {
  const [datasets, setDatasets] = useState([]);

  const [showModal, setShowModal] = useState(false);

  const [selected, setSelected] = useState(null);

  useEffect(() => {
    load();
  }, []);

  async function load() {
    setDatasets(await getAllDatasets());
  }

  function addNew() {
    setSelected(null);

    setShowModal(true);
  }

  function edit(dataset) {
    setSelected(dataset);

    setShowModal(true);
  }

  async function remove(id) {
    if (!window.confirm("Delete dataset?")) return;

    await deleteDataset(id);

    load();
  }

  async function save(data) {
    if (selected) await updateDataset(selected.id, data);
    else await createDataset(data);

    setShowModal(false);

    load();
  }

  return (
    <DashboardLayout>
      <div className="d-flex justify-content-between mb-3">
        <h2>Datasets</h2>

        <button className="btn btn-primary" onClick={addNew}>
          Add Dataset
        </button>
      </div>

      <table className="table table-bordered table-hover">
        <thead>
          <tr>
            <th>ID</th>

            <th>Name</th>

            <th>Description</th>

            <th>Images</th>

            <th>Priority</th>

            <th>Status</th>

            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {datasets.map((d) => (
            <tr key={d.id}>
              <td>{d.id}</td>

              <td>{d.name}</td>

              <td>{d.description}</td>

              <td>{d.totalImages}</td>

              <td>{d.priority}</td>

              <td>{d.status}</td>

              <td>
                <button
                  className="btn btn-warning btn-sm me-2"
                  onClick={() => edit(d)}
                >
                  Edit
                </button>

                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => remove(d.id)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <DatasetModal
        show={showModal}
        onClose={() => setShowModal(false)}
        onSave={save}
        editData={selected}
      />
    </DashboardLayout>
  );
}

export default Datasets;
