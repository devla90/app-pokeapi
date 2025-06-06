import os
import re
from openai import OpenAI

client = OpenAI(api_key="")

SOURCE_DIR = "./app/src/main/java"
total_prompt_tokens = 0
total_completion_tokens = 0

def find_kotlin_files(directory):
    return [
        os.path.join(root, f)
        for root, _, files in os.walk(directory)
        for f in files if f.endswith(".kt")
    ]

def find_import_end(lines):
    for i, line in enumerate(lines):
        if not line.strip().startswith("import") and line.strip():
            return i
    return 0

def has_kdoc_above(lines, index):
    for i in range(index - 1, max(index - 5, 0), -1):
        if lines[i].strip().startswith("/**"):
            return True
        if lines[i].strip() and not lines[i].strip().startswith("@"):
            break
    return False

def is_main_class_line(line):
    line = line.strip()
    return (
        line.startswith("class ")
        and "private" not in line
        and "data " not in line
        and "sealed " not in line
        and "object " not in line
    )

def generate_kdoc_for_class(code_snippet):
    global total_prompt_tokens, total_completion_tokens
    prompt = f"""
Eres un experto en Kotlin. Agrega un comentario KDoc solo para la clase. No repitas el nombre de la clase ni digas si hay una clase pública. No incluyas instrucciones triviales. No comentes detalles obvios de anotaciones.

Código:
{code_snippet}
"""
    response = client.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=[{"role": "user", "content": prompt}],
        temperature=0.2,
    )
    total_prompt_tokens += response.usage.prompt_tokens
    total_completion_tokens += response.usage.completion_tokens
    return response.choices[0].message.content.strip()

def generate_file_kdoc(filename):
    prompt = f"""
Eres un experto en Kotlin. Escribe un comentario KDoc descriptivo para el propósito de este archivo {filename}. No digas si contiene o no una clase pública. No uses frases genéricas como “archivo principal”, ni repitas el nombre del archivo.
"""
    response = client.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=[{"role": "user", "content": prompt}],
        temperature=0.2,
    )
    return response.choices[0].message.content.strip()

def extract_class_block(lines, index):
    block = []
    brace_count = 0
    for i in range(index, len(lines)):
        block.append(lines[i])
        brace_count += lines[i].count("{") - lines[i].count("}")
        if brace_count <= 0 and "{" in "".join(block):
            break
    return "".join(block)

def process_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        lines = f.readlines()

    new_lines = []
    modified = False
    inserted_file_kdoc = False
    i = 0

    while i < len(lines):
        line = lines[i]

        # Inserta comentario del archivo después de los imports
        if not inserted_file_kdoc:
            import_end = find_import_end(lines)
            if i == import_end:
                file_kdoc = generate_file_kdoc(os.path.basename(filepath))
                if file_kdoc and not file_kdoc.lower().startswith(("no hay", "sí,", "this file")):
                    new_lines.append(file_kdoc + "\n\n")
                    modified = True
                inserted_file_kdoc = True

        # Detectar clase principal pública
        if is_main_class_line(line) and not has_kdoc_above(lines, i):
            annotation_start = i
            while annotation_start > 0 and lines[annotation_start - 1].strip().startswith("@"):
                annotation_start -= 1

            # Extraer bloque de clase para análisis
            class_block = extract_class_block(lines, i)
            kdoc = generate_kdoc_for_class(class_block)

            # Insertar KDoc antes de anotaciones
            new_lines.extend(kdoc.splitlines(keepends=True))
            new_lines.extend(lines[annotation_start:i])
            new_lines.append(line)
            modified = True
            i += 1
            continue

        new_lines.append(line)
        i += 1

    if modified:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.writelines(new_lines)
        print(f"✅ Archivo actualizado: {filepath}")

def main():
    files = find_kotlin_files(SOURCE_DIR)
    print(f"📁 Archivos encontrados: {len(files)}")

    for file in files:
        process_file(file)

    total_tokens = total_prompt_tokens + total_completion_tokens
    input_cost = total_prompt_tokens * 0.0005
    output_cost = total_completion_tokens * 0.0015
    total_cost = input_cost + output_cost

    print("\n📊 USO")
    print(f"Tokens prompt:     {total_prompt_tokens}")
    print(f"Tokens respuesta:  {total_completion_tokens}")
    print(f"Tokens totales:    {total_tokens}")
    print(f"💰 Costo estimado:  ${total_cost:.4f}")

if __name__ == "__main__":
    main()
