import { SidebarTrigger } from "@/components/ui/sidebar";
import { Separator } from "@/components/ui/separator";
import {
  Breadcrumb,
  BreadcrumbItem,
  BreadcrumbLink,
  BreadcrumbList,
  BreadcrumbPage,
  BreadcrumbSeparator,
} from "@/components/ui/breadcrumb";
import ModeToggle from "@/components/theme/mode-toggle";

const AppHeader = () => (
  <header className="flex h-14 shrink-0 items-center gap-2 border-b">
    <div className="flex flex-1 items-center gap-2 px-3">
      <SidebarTrigger />

      <Separator orientation="vertical" className="mr-2 h-4" />

      <Breadcrumb>
        <BreadcrumbList>
          <BreadcrumbItem className="hidden md:block">
            <BreadcrumbLink href="#">Building Your Application</BreadcrumbLink>
          </BreadcrumbItem>

          <BreadcrumbSeparator className="hidden md:block" />

          <BreadcrumbItem>
            <BreadcrumbPage>Data Fetching</BreadcrumbPage>
          </BreadcrumbItem>
        </BreadcrumbList>
      </Breadcrumb>
    </div>

    <div className="ml-auto px-3">
      <ModeToggle />
    </div>
  </header>
);

export default AppHeader;
