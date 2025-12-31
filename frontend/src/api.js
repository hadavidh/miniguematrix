import axios from "axios";

export const api = axios.create({
  baseURL: "http://localhost:8080",
});

export async function computeGematria(text, methods) {
  const { data } = await api.post("/api/gematria", { text, methods });
  return data;
}
