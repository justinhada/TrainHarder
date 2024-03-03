import APIClient from "../services/APIClient.ts";
import Registrierung from "../entities/trainharder/Registrierung.ts";
import { useQuery } from "@tanstack/react-query";

const apiClient = new APIClient<Registrierung>("/registrierungen");

const useRegistrierung = (id: string) =>
  useQuery({
    queryKey: ["registrierungen", id],
    queryFn: () => apiClient.finde(id),
  });

export default useRegistrierung;
