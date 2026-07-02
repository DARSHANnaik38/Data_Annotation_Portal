import axiosInstance from "../api/axiosConfig";

export const getAllUsers = async () =>
  (await axiosInstance.get("/users")).data.data;

export const createUser = async (user) =>
  (await axiosInstance.post("/users", user)).data;

export const updateUser = async (id, user) =>
  (await axiosInstance.put(`/users/${id}`, user)).data;

export const deleteUser = async (id) =>
  (await axiosInstance.delete(`/users/${id}`)).data;
