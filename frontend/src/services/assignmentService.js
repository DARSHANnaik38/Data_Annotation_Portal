import axiosInstance from "../api/axiosConfig";

export const getAllAssignments = async () =>
  (await axiosInstance.get("/assignments")).data.data;

export const createAssignment = async (assignment) =>
  (await axiosInstance.post("/assignments", assignment)).data;

export const updateAssignmentStatus = async (id, status) =>
  (await axiosInstance.put(`/assignments/${id}/status?status=${status}`)).data;

export const deleteAssignment = async (id) =>
  (await axiosInstance.delete(`/assignments/${id}`)).data;

// export const createAssignment = async (assignment) => {
//   console.log("Sending:", assignment);

//   const response = await axiosInstance.post("/assignments", assignment);

//   return response.data;
// };