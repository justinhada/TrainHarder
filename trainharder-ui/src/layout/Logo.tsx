import { CSSProperties } from "react";

interface Props {
  sx?: CSSProperties;
}

export const Logo = ({ sx }: Props) => {
  return (
    <img
      style={sx}
      src="/src/assets/icon.svg"
      alt="TrainHarder-Logo"
      height={96}
    />
  );
};
