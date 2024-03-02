import { createBrowserRouter } from "react-router-dom";
import Layout from "./layout/Layout.tsx";
import Error from "./views/Error.tsx";
import Home from "./views/Home.tsx";
import PlanHarder from "./views/planharder/PlanHarder.tsx";
import Kalender from "./views/planharder/Kalender.tsx";
import DietHarder from "./views/dietharder/DietHarder.tsx";
import Koerpergewicht from "./views/dietharder/Koerpergewicht.tsx";
import KFA from "./views/dietharder/KFA.tsx";
import Umfaenge from "./views/dietharder/Umfaenge.tsx";
import EatHarder from "./views/eatharder/EatHarder.tsx";
import Ernaehrungsplan from "./views/eatharder/Ernaehrungsplan.tsx";
import SuppHarder from "./views/suppharder/SuppHarder.tsx";
import Supplements from "./views/suppharder/Supplements.tsx";
import WorkHarder from "./views/workharder/WorkHarder.tsx";
import Uebungen from "./views/workharder/Uebungen.tsx";
import Trainingsplaene from "./views/workharder/Trainingsplaene.tsx";
import Tagebuch from "./views/workharder/Tagebuch.tsx";
import Kraftwerte from "./views/workharder/Kraftwerte.tsx";
import TrainHarder from "./views/trainharder/TrainHarder.tsx";
import Login from "./views/trainharder/Login.tsx";
import PasswortVergessen from "./views/trainharder/PasswortVergessen.tsx";
import Registrierung from "./views/trainharder/Registrierung.tsx";
import Benutzer from "./views/trainharder/Benutzer.tsx";
import Logout from "./views/trainharder/Logout.tsx";
import Einstellungen from "./views/baseharder/Einstellungen.tsx";
import Impressum from "./views/baseharder/Impressum.tsx";
import Datenschutz from "./views/baseharder/Datenschutz.tsx";

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
      {
        path: "planharder",
        children: [
          { index: true, element: <PlanHarder /> },
          { path: "kalender", element: <Kalender /> },
        ],
      },
      {
        path: "dietharder",
        children: [
          { index: true, element: <DietHarder /> },
          { path: "koerpergewicht", element: <Koerpergewicht /> },
          { path: "kfa", element: <KFA /> },
          { path: "umfaenge", element: <Umfaenge /> },
        ],
      },
      {
        path: "eatharder",
        children: [
          { index: true, element: <EatHarder /> },
          {
            path: "ernaehrungsplan",
            element: <Ernaehrungsplan />,
          },
        ],
      },
      {
        path: "suppharder",
        children: [
          { index: true, element: <SuppHarder /> },
          { path: "supplements", element: <Supplements /> },
        ],
      },
      {
        path: "workharder",
        children: [
          { element: <WorkHarder /> },
          { path: "uebungen", element: <Uebungen /> },
          { path: "trainingsplaene", element: <Trainingsplaene /> },
          { path: "tagebuch", element: <Tagebuch /> },
          { path: "kraftwerte", element: <Kraftwerte /> },
        ],
      },
      { path: "trainharder", element: <TrainHarder /> },
      { path: "login", element: <Login /> },
      { path: "passwort-vergessen", element: <PasswortVergessen /> },
      { path: "registrierung", element: <Registrierung /> },
      { path: "benutzer", element: <Benutzer /> },
      { path: "logout", element: <Logout /> },
      { path: "einstellungen", element: <Einstellungen /> },
      { path: "impressum", element: <Impressum /> },
      { path: "datenschutz", element: <Datenschutz /> },
    ],
  },
]);

export default router;
