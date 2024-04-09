import {
  createContext,
  ReactNode,
  useContext,
  useEffect,
  useMemo,
  useState,
} from "react";
import { axiosInstance } from "../services/APIClient.ts";

interface AuthContextProps {
  token: string | null;
  setToken: (token: string) => void;
}

const AuthContext = createContext({} as AuthContextProps);

interface Props {
  children: ReactNode;
}

const AuthProvider = ({ children }: Props) => {
  const [token, setToken] = useState(localStorage.getItem("token"));

  useEffect(() => {
    if (token) {
      axiosInstance.defaults.headers.common["Authorization"] =
        `Bearer ${token}`;
      localStorage.setItem("token", token);
    } else {
      delete axiosInstance.defaults.headers.common["Authorization"];
      localStorage.removeItem("token");
    }
  }, [token]);

  const contextValue = useMemo(
    () => ({
      token,
      setToken,
    }),
    [token],
  );

  return (
    <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);

export default AuthProvider;
