#!/bin/bash

set -e

echo "🔧 Generando documentación con Dokka..."
./gradlew dokkaHtml

echo "📁 Documentación generada en build/dokka"

# PlantUML config
JAR_NAME="plantuml.jar"
JAR_URL="https://sourceforge.net/projects/plantuml/files/plantuml.jar/download"
PUML_DIR="uml_sources"
OUT_DIR="uml_diagrams"

# Crear carpetas si no existen
mkdir -p "$PUML_DIR"
mkdir -p "$OUT_DIR"

# Descargar PlantUML si no existe
if [ ! -f "$JAR_NAME" ]; then
    echo "⬇️  Descargando $JAR_NAME..."
    curl -L -o "$JAR_NAME" "$JAR_URL"
fi

# Validar archivos .puml
PUML_FILES=$(find "$PUML_DIR" -name "*.puml")
if [ -z "$PUML_FILES" ]; then
    echo "⚠️ No se encontraron archivos .puml en $PUML_DIR."
    echo "💡 Puedes crear algunos manualmente o generar con otro script."
    exit 0
fi

echo "📐 Generando diagramas UML desde archivos .puml..."

for file in $PUML_FILES; do
    echo "🧩 Procesando: $file"
    java -jar "$JAR_NAME" -tpng -o "../$OUT_DIR" "$file"
done

echo "✅ Diagramas UML generados en: $OUT_DIR"
