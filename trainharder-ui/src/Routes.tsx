import { createBrowserRouter } from "react-router-dom";
import Layout from "./pages/Layout.tsx";
import Error from "./pages/Error.tsx";
import Home from "./pages/Home.tsx";
import Benutzer from "./pages/Benutzer.tsx";
import Einstellungen from "./pages/Einstellungen.tsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout />,
    errorElement: <Error />,
    children: [
      { index: true, element: <Home /> },

      { path: "benutzer", element: <Benutzer /> },
      { path: "einstellungen", element: <Einstellungen /> },
    ],
  },
]);

export default router;
