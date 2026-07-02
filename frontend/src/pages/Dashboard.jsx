import { useEffect, useState } from "react";

import DashboardLayout from "../layouts/DashboardLayout";

import { getAllDatasets } from "../services/datasetService";
import { getAllUsers } from "../services/userService";
import { getAllAssignments } from "../services/assignmentService";

function Dashboard() {
  const [datasetCount, setDatasetCount] = useState(0);
  const [userCount, setUserCount] = useState(0);
  const [assignmentCount, setAssignmentCount] = useState(0);

  useEffect(() => {
    loadDashboard();
  }, []);

  const loadDashboard = async () => {
    try {
      const datasets = await getAllDatasets();
      console.log("Datasets:", datasets);

      setDatasetCount(datasets.length);
    } catch (e) {
      console.log("Dataset Error", e);
    }

    try {
      const users = await getAllUsers();
      console.log("Users:", users);

      setUserCount(users.length);
    } catch (e) {
      console.log("User Error", e);
    }

    try {
      const assignments = await getAllAssignments();
      console.log("Assignments:", assignments);

      setAssignmentCount(assignments.length);
    } catch (e) {
      console.log("Assignment Error", e);
    }
  };

  return (
    <DashboardLayout>
      <h2>Dashboard</h2>

      <div className="row mt-4">
        <div className="col-md-4">
          <div className="card shadow p-4">
            <h5>Total Datasets</h5>

            <h1>{datasetCount}</h1>
          </div>
        </div>

        <div className="col-md-4">
          <div className="card shadow p-4">
            <h5>Total Users</h5>

            <h1>{userCount}</h1>
          </div>
        </div>

        <div className="col-md-4">
          <div className="card shadow p-4">
            <h5>Total Assignments</h5>

            <h1>{assignmentCount}</h1>
          </div>
        </div>
      </div>
    </DashboardLayout>
  );
}

export default Dashboard;
