#!/bin/bash

JAR_NAME="kotlin2plantuml.jar"
OUTPUT_DIR="uml_sources"
mkdir -p "$OUTPUT_DIR"

if [ ! -f "$JAR_NAME" ]; then
    echo "❌ No se encontró $JAR_NAME."
    exit 1
fi

ROOT_CLASS="com.example.pokeapi.MainActivity"  # Cambiar según tu app
PACKAGES="com.example.pokeapi"                 # Ajustar al root del paquete

java -jar "$JAR_NAME" "$ROOT_CLASS" \
    --packages "$PACKAGES" \
    --recurse \
    --output "$OUTPUT_DIR/diagram.puml"
