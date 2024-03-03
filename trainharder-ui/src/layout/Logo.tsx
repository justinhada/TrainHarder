import { CSSProperties } from "react";

interface Props {
  cssProperties?: CSSProperties;
}

export const Logo = ({ cssProperties }: Props) => {
  return (
    <img
      style={cssProperties}
      src="/src/assets/icon.svg"
      alt="TrainHarder-Logo"
      height={96}
    />
  );
};
