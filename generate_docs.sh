#!/bin/bash

echo "🔧 Generando documentación con Dokka..."
./gradlew dokkaHtml

echo "📄 Generando archivos .puml..."
./generate_puml.sh

echo "🖼️ Generando diagramas UML..."
java -jar plantuml.jar -tpng uml_sources/diagram.puml -o uml_diagrams

echo "✅ Proceso completado."
