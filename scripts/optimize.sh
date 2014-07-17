#!/bin/sh
cd src/main/resources/assets/artifice/textures
for f in $(find . -name '*.png'); do pngout -y $f $f; done
