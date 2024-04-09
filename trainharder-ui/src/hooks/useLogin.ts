import { axiosInstance } from "../services/APIClient.ts";
import Login, { Token } from "../entities/trainharder/Login.ts";
import { useMutation } from "@tanstack/react-query";

export const usePostLogin = () =>
  useMutation({
    mutationKey: ["login", "post", "benutzername", "passwort"],
    mutationFn: async (login: Login) =>
      await axiosInstance.post<Token>("/login", login),
  });
