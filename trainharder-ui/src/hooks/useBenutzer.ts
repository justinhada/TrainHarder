import { useQuery } from "@tanstack/react-query";
import APIClient from "../services/APIClient.ts";
import Benutzer from "../entities/trainharder/Benutzer.ts";

const apiClient = new APIClient<Benutzer>("/benutzer");

const useBenutzer = () => useQuery();
