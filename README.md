# 🎵 Gestor Musical - Java

![Java](https://img.shields.io/badge/Java-17%2B-007396?logo=java&logoColor=white)
![Terminal](https://img.shields.io/badge/Interface-Terminal-4D4D4D?logo=windows-terminal&logoColor=white)

Un potente gestor musical en terminal para organizar tu biblioteca musical amb persistencia.

## 🌟 Características principales

| Función | Icono | Descripción |
|---------|-------|-------------|
| Reproducción | 🔊 | Control completo de archivos MP3 |
| Playlists | 📋 | Crea listas de reproducción personalizadas |
| Escaneo | 🔍 | Detecta archivos MP3 automáticamente |
| Metadatos | 🏷️ | Edita tags ID3 (título, artista, etc.) |
| Persistencia | 💾 | Guarda en JSON (canciones) y XML (playlists) |


## 🛠️ Utilitzacio de codi
Instalación 
```bash
git clone https://github.com/Waffle639/GestorMusical
```
Ejecucion de codigo en terminal
```bash
java -jar gestormusica.jar
```
## 📊 Formatos Soportados

| Tipo        | Formato | Descripción                     | Ejemplo de Uso                  |
|-------------|---------|---------------------------------|----------------------------------|
| Cansons     | JSON    | Almacena todas las canciones    | `{"canciones": [{"titulo": "...", "artista": "..."}]}` |
| Playlists   | XML     | Estructura jerárquica de listas | `<playlist><nombre>Mi Lista</nombre><canciones>...</canciones></playlist>` |
| Audio       | MP3     | Formato de audio compatible     | `cancion.mp3`  |

