import type { Metadata } from "next";
import "./globals.css";
import { ReactNode } from "react";

export const metadata: Metadata = {
  title: "Create Next App",
  description: "Generated by create next app",
};

const Layout = ({ children }: { children: ReactNode }) => (
  <html lang="de" suppressHydrationWarning>
    <body>{children}</body>
  </html>
);

export default Layout;
