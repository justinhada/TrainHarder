import { createBrowserRouter } from "react-router-dom";
import Layout from "./layout/Layout.tsx";
import Error from "./pages/Error.tsx";
import Home from "./pages/Home.tsx";
import PlanHarder from "./pages/planharder/PlanHarder.tsx";
import Kalender from "./pages/planharder/Kalender.tsx";
import DietHarder from "./pages/dietharder/DietHarder.tsx";
import Koerpergewicht from "./pages/dietharder/Koerpergewicht.tsx";
import KFA from "./pages/dietharder/KFA.tsx";
import Umfaenge from "./pages/dietharder/Umfaenge.tsx";
import EatHarder from "./pages/eatharder/EatHarder.tsx";
import Ernaehrungsplan from "./pages/eatharder/Ernaehrungsplan.tsx";
import SuppHarder from "./pages/suppharder/SuppHarder.tsx";
import Supplements from "./pages/suppharder/Supplements.tsx";
import WorkHarder from "./pages/workharder/WorkHarder.tsx";
import Uebungen from "./pages/workharder/Uebungen.tsx";
import Trainingsplaene from "./pages/workharder/Trainingsplaene.tsx";
import Tagebuch from "./pages/workharder/Tagebuch.tsx";
import Kraftwerte from "./pages/workharder/Kraftwerte.tsx";
import TrainHarder from "./pages/trainharder/TrainHarder.tsx";
import Login from "./pages/trainharder/Login.tsx";
import PasswortZuruecksetzen from "./pages/trainharder/PasswortZuruecksetzen.tsx";
import Registrierung from "./pages/trainharder/Registrierung.tsx";
import Benutzer from "./pages/trainharder/Benutzer.tsx";
import Logout from "./pages/trainharder/Logout.tsx";
import Einstellungen from "./pages/baseharder/Einstellungen.tsx";
import Impressum from "./pages/baseharder/Impressum.tsx";
import Datenschutz from "./pages/baseharder/Datenschutz.tsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout />,
    errorElement: (
      <Layout>
        <Error />
      </Layout>
    ),
    children: [
      { index: true, element: <Home /> },
      /* PlanHarder */
      { path: "planharder", element: <PlanHarder /> },
      { path: "kalender", element: <Kalender /> },
      /* DietHarder */
      { path: "dietharder", element: <DietHarder /> },
      { path: "koerpergewicht", element: <Koerpergewicht /> },
      { path: "kfa", element: <KFA /> },
      { path: "umfaenge", element: <Umfaenge /> },
      /* EatHarder */
      { path: "eatharder", element: <EatHarder /> },
      { path: "ernaehrungsplan", element: <Ernaehrungsplan /> },
      /* SuppHarder */
      { path: "suppharder", element: <SuppHarder /> },
      { path: "supplements", element: <Supplements /> },
      /* WorkHarder */
      { path: "workharder", element: <WorkHarder /> },
      { path: "uebungen", element: <Uebungen /> },
      { path: "trainingsplaene", element: <Trainingsplaene /> },
      { path: "tagebuch", element: <Tagebuch /> },
      { path: "kraftwerte", element: <Kraftwerte /> },
      /* TrainHarder */
      { path: "trainharder", element: <TrainHarder /> },
      { path: "login", element: <Login /> },
      { path: "passwort-zuruecksetzen", element: <PasswortZuruecksetzen /> },
      { path: "registrierung", element: <Registrierung /> },
      { path: "benutzer", element: <Benutzer /> },
      { path: "logout", element: <Logout /> },
      /* BaseHarder */
      { path: "einstellungen", element: <Einstellungen /> },
      { path: "impressum", element: <Impressum /> },
      { path: "datenschutz", element: <Datenschutz /> },
    ],
  },
]);

export default router;
