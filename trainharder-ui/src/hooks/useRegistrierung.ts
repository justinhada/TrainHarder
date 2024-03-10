import APIClient, { axiosInstance } from "../services/APIClient.ts";
import Registrierung from "../entities/trainharder/Registrierung.ts";
import { useMutation, useQuery } from "@tanstack/react-query";
import CheckEMail from "../entities/trainharder/CheckEMail.ts";

const apiClient = new APIClient<Registrierung>("/registrierungen");

export const useRegistrierung = (id: string) =>
  useQuery({
    queryKey: ["registrierungen", id],
    queryFn: () => apiClient.finde(id),
  });

export const useRegistrierungMitEMailAdresse = (eMailAdresse: string) =>
  useQuery({
    queryKey: ["registrierungen", "eMailAdresse", eMailAdresse],
    queryFn: () =>
      apiClient.findeMit({ schluessel: "eMailAdresse", wert: eMailAdresse }),
    refetchOnWindowFocus: false,
    enabled: false,
    retry: false,
  });

export const useRegistrierungCheckEMail = (eMailAdresse: string) =>
  useQuery({
    queryKey: ["registrierungen", "check-email", "eMailAdresse", eMailAdresse],
    queryFn: async () =>
      await axiosInstance.get<CheckEMail>(
        `/registrierungen/check-email/${eMailAdresse}`,
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
