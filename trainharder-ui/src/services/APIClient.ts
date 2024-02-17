import axios, { AxiosRequestConfig } from "axios";

const axiosInstance = axios.create({
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

  findeAlle = async (config: AxiosRequestConfig) => {
    return (await axiosInstance.get<T[]>(this.endpunkt, config)).data;
  };

  finde = async (id: string) => {
    return (await axiosInstance.get<T>(`${this.endpunkt}/${id}`)).data;
  };
}

export default APIClient;
