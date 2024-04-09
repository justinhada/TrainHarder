import APIClient, { axiosInstance } from "../services/APIClient.ts";
import Registrierung from "../entities/trainharder/Registrierung.ts";
import { useMutation, useQuery } from "@tanstack/react-query";
import CheckEMail from "../entities/trainharder/CheckEMail.ts";
import CheckBenutzername from "../entities/trainharder/CheckBenutzername.ts";

const apiClient = new APIClient<Registrierung>("/registrierungen");

export const useRegistrierung = (id: string) =>
  useQuery({
    queryKey: ["registrierungen", id],
    queryFn: () => apiClient.finde(id),
  });

export const useRegistrierungCheckEMail = (eMailAdresse: string) =>
  useQuery({
    queryKey: ["registrierungen", "check-email", eMailAdresse],
    queryFn: async () =>
      await axiosInstance.get<CheckEMail>(
        `/registrierungen/check-email/${eMailAdresse}`,
      ),
    refetchOnWindowFocus: false,
    enabled: false,
    retry: false,
  });

export const useRegistrierungCheckBenutzername = (benutzername: string) =>
  useQuery({
    queryKey: ["registrierungen", "check-benutzername", benutzername],
    queryFn: async () =>
      await axiosInstance.get<CheckBenutzername>(
        `/registrierungen/check-benutzername/${benutzername}`,
      ),
    refetchOnWindowFocus: false,
    enabled: false,
    retry: false,
  });

export const usePostRegistrierungen = () =>
  useMutation({
    mutationKey: ["registrierungen", "post", "eMailAdresse", "passwort"],
    mutationFn: async (registrierung: Registrierung) =>
      await axiosInstance.post<Registrierung>(
        "/registrierungen",
        registrierung,
      ),
  });

export const usePutRegistrierung = (id: string) =>
  useMutation({
    mutationKey: ["registrierungen", id],
    mutationFn: async (registrierung: Registrierung) =>
      await axiosInstance.put<Registrierung>(
        `/registrierungen/${id}`,
        registrierung,
      ),
  });
