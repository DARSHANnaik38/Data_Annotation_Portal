import axiosInstance from "../api/axiosConfig";

export const getAllDatasets = async () => {
  return (await axiosInstance.get("/datasets")).data.data;
};

export const createDataset = async (dataset) => {
  return (await axiosInstance.post("/datasets", dataset)).data;
};

export const updateDataset = async (id, dataset) => {
  return (await axiosInstance.put(`/datasets/${id}`, dataset)).data;
};

export const deleteDataset = async (id) => {
  return (await axiosInstance.delete(`/datasets/${id}`)).data;
};
