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
cd ./target
java -jar gestormusica-0.0.1-SNAPSHOT.jar
```
## 📸 Capturas de Pantalla
<div align="center">
  <img src="https://github.com/user-attachments/assets/0d27a7b5-cf5d-431b-9bad-f8827cc97f90" alt="Menú Principal del Gestor Musical" width="80%" style="border: 1px solid #ddd; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
  <p><em>Menú principal con todas las opciones disponibles</em></p>
</div>

### Funcionalidad de Reproducción

<div align="center">
  <img src="https://github.com/user-attachments/assets/0dae3102-e822-4192-a3c5-69a9d123e658" alt="Reproductor de Música en Acción" width="80%" style="border: 1px solid #ddd; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
  <p><em>Interfaz del reproductor mostrando la canción actual y controles</em></p>
</div>

## 📊 Formatos Soportados

| Tipo        | Formato | Descripción                     | Ejemplo de Uso                  |
|-------------|---------|---------------------------------|----------------------------------|
| Cansons     | JSON    | Almacena todas las canciones    | `{"canciones": [{"titulo": "...", "artista": "..."}]}` |
| Playlists   | XML     | Estructura jerárquica de listas | `<playlist><nombre>Mi Lista</nombre><canciones>...</canciones></playlist>` |
| Audio       | MP3     | Formato de audio compatible     | `cancion.mp3`  |

