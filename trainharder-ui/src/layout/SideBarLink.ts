import { JSX } from "react";

export default interface SideBarLink {
  readonly title: string;
  readonly icon: JSX.Element;
  readonly link: string;
}
