{
  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixpkgs-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };
  outputs = { self, nixpkgs, flake-utils, ... }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        inherit (nixpkgs) lib;
        pkgs = nixpkgs.legacyPackages.${system};
      in
      with pkgs;
      {
        devShells.default = mkShell rec {
          buildInputs = [
            pkg-config
            fontconfig
            openssl
            libxkbcommon
            libGL
            openal
            vulkan-loader
            wayland
            xorg.libXcursor
            xorg.libXrandr
            xorg.libXi
            xorg.libX11
          ];
          LD_LIBRARY_PATH = lib.makeLibraryPath buildInputs;
          nativeBuildInputs = with pkgs; [ openjdk17-bootstrap gradle ];
        };
      }
    );
}

