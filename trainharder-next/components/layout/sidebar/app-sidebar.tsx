"use client";

import * as React from "react";
import { ComponentProps } from "react";
import {
  AudioWaveform,
  Command,
  GalleryVerticalEnd,
  LifeBuoy,
  type LucideIcon,
  Send,
  Settings,
} from "lucide-react";

import NavMain from "@/components/layout/sidebar/nav-main";
import NavUser from "@/components/layout/sidebar/nav-user";
import TeamSwitcher from "@/components/layout/sidebar/team-switcher";
import {
  Sidebar,
  SidebarContent,
  SidebarFooter,
  SidebarGroup,
  SidebarGroupContent,
  SidebarHeader,
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
  SidebarRail,
} from "@/components/ui/sidebar";
import Link from "next/link";

type Item = {
  title: string;
  url: string;
  icon?: LucideIcon;
  isActive?: boolean;
  items?: {
    title: string;
    url: string;
  }[];
};

const AppSidebar = ({ ...props }: ComponentProps<typeof Sidebar>) => (
  <Sidebar variant="sidebar" collapsible="icon" {...props}>
    <SidebarHeader>
      <TeamSwitcher
        teams={[
          {
            name: "Acme Inc",
            logo: GalleryVerticalEnd,
            plan: "Enterprise",
          },
          {
            name: "Acme Corp.",
            logo: AudioWaveform,
            plan: "Startup",
          },
          {
            name: "Evil Corp.",
            logo: Command,
            plan: "Free",
          },
        ]}
      />
    </SidebarHeader>

    <SidebarContent>
      <NavMain />

      <SidebarGroup className="mt-auto">
        <SidebarGroupContent>
          <SidebarMenu>
            {[
              {
                title: "Support",
                url: "#",
                icon: LifeBuoy,
              },
              {
                title: "Feedback",
                url: "#",
                icon: Send,
              },
              {
                title: "Einstellungen",
                url: "#",
                icon: Settings,
              },
            ].map((item) => (
              <SidebarMenuItem key={item.title}>
                <SidebarMenuButton asChild>
                  <Link href={item.url} title={item.title}>
                    <item.icon />

                    <span>{item.title}</span>
                  </Link>
                </SidebarMenuButton>
              </SidebarMenuItem>
            ))}
          </SidebarMenu>
        </SidebarGroupContent>
      </SidebarGroup>
    </SidebarContent>

    <SidebarFooter>
      <NavUser />
    </SidebarFooter>

    <SidebarRail />
  </Sidebar>
);

export default AppSidebar;
