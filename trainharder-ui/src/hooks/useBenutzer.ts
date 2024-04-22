import { useQuery } from "@tanstack/react-query";
import APIClient, { axiosInstance } from "../services/APIClient.ts";
import Benutzer from "../entities/trainharder/Benutzer.ts";

const apiClient = new APIClient<Benutzer>("/benutzer");

export const useBenutzer = (benutzername: string) =>
  useQuery({
    queryKey: ["benutzer", benutzername],
    queryFn: async () =>
      (
        await axiosInstance.get<Benutzer>(
          `/benutzer/benutzername/${benutzername}`,
        )
      ).data,
  });
