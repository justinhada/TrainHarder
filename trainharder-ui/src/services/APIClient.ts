import axios, { AxiosRequestConfig } from "axios";
import QueryParameter from "./QueryParameter.ts";

export const axiosInstance = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    Accept: "application/json",
  },
});

class APIClient<T> {
  endpunkt: string;

  constructor(endpunkt: string) {
    this.endpunkt = endpunkt;
  }

  findeAlle = async (config: AxiosRequestConfig) =>
    (await axiosInstance.get<T[]>(this.endpunkt, config)).data;

  finde = async (id: string) =>
    (await axiosInstance.get<T>(`${this.endpunkt}/${id}`)).data;

  findeMit = async (queryParameter: QueryParameter) =>
    (
      await axiosInstance.get<T>(this.endpunkt, {
        params: {
          [queryParameter.schluessel]: queryParameter.wert,
        },
      })
    ).data;
}

export default APIClient;
