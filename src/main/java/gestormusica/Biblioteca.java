package gestormusica;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jakarta.xml.bind.*;
import java.lang.reflect.Type;
import java.util.List;
import com.mpatric.mp3agic.*;
import java.io.File;
import mypackage.*;

public class Biblioteca {

    public static void main(String[] args) {
        String nomFitxerJson = "cansos.json";
        String nomFitxerXml = "PlaylisFts.xml";
        ArrayList<Canso> totesCansons = cargarCansonsDelJson(nomFitxerJson);
        PlaylisFts playlists = cargarPlaylist(nomFitxerXml);
        int opcio;
        do {
            Utils.netejaPantalla();
            List<String> opcions = new ArrayList<>();
            opcions.add("Reprodir"); //1
            opcions.add("Crear playlist");//2
            opcions.add("Editar playlist");//3
            opcions.add("Escanejar directori");//4
            opcions.add("Editar cançons");//5
            opcions.add("Editar ID3");//6
            opcions.add("Sortir i guardar"); // 7

            opcio = Utils.demanaOpcioLlista("Menú principal", opcions) + 1;


            switch (opcio) {
                case 1:
                    List<String> opcionsReproduir = new ArrayList<>();
                    opcionsReproduir.add("Reproduir canço");
                    opcionsReproduir.add("Reproduir playlist");
                    opcionsReproduir.add("Tornar al menú principal");
                    int opcioReproduir = Utils.demanaOpcioLlista("Menú reproducció", opcionsReproduir);
                    if (opcioReproduir == 0) {
                        reproduir(totesCansons);
                    } else if (opcioReproduir == 1) {
                        List<String> opcionsPlaylist = new ArrayList<>();
                        for (PlaylisFts.Playlist playlist : playlists.getPlaylist()) {
                            opcionsPlaylist.add(playlist.getNombre());
                        }
                        int opcioPlaylist = Utils.demanaOpcioLlista("Menú playlists", opcionsPlaylist);
                        PlaylisFts.Playlist playlistSeleccionada = playlists.getPlaylist().get(opcioPlaylist);
                        reproduir(playlistSeleccionada);
                    }
                    break;
                case 2:
                    String nomPlaylist = Utils.demanaText("Introdueix el nom de la playlist");
                    PlaylisFts.Playlist novaPlaylist = new PlaylisFts.Playlist();
                    novaPlaylist.setNombre(nomPlaylist);
                    System.out.println("Afegint cançons a la playlist " + nomPlaylist);


                    afegirCansons(novaPlaylist, totesCansons);
                    playlists.getPlaylist().add(novaPlaylist);
                    System.out.println("Playlist " + nomPlaylist + " afegida correctament");
                    break;
                case 3:
                    List<String> opcionsEditPlaylist = new ArrayList<>();
                    for (PlaylisFts.Playlist playlist : playlists.getPlaylist()) {
                        opcionsEditPlaylist.add(playlist.getNombre());
                    }
                    int opcioEditPlaylist = Utils.demanaOpcioLlista("Menú edició playlists", opcionsEditPlaylist);
                    PlaylisFts.Playlist playlistEditada = playlists.getPlaylist().get(opcioEditPlaylist);
                    System.out.println("Afegint cançons a la playlist " + playlistEditada.getNombre());

                    List<String> opcionsEditarPlaylist = new ArrayList<>();
                    opcionsEditarPlaylist.add("Canviar nom");
                    opcionsEditarPlaylist.add("Afegir cançons");
                    opcionsEditarPlaylist.add("Eliminar cançons");
                    opcionsEditarPlaylist.add("Eliminar playlist");
                    opcionsEditarPlaylist.add("Tornar al menú principal");
                    int opcionsEditarP = Utils.demanaOpcioLlista("Menú Edicio Playlist", opcionsEditarPlaylist) + 1;
                    switch (opcionsEditarP) {
                        case 1:
                            String nouNom = Utils.demanaText("Introdueix el nom de la playlist");
                            playlistEditada.setNombre(nouNom);
                            break;
                        case 2:
                            System.out.println("Afegint cançons a la playlist " + playlistEditada);
                            afegirCansons(playlistEditada, totesCansons);
                            break;
                        case 3:
                            System.out.println("Eliminant cançons a la playlist " + playlistEditada);
                            EliminarCansons(playlistEditada);
                            break;
                        case 4:
                            System.out.println("Eliminant playlist " + playlistEditada.getNombre());
                            playlists.getPlaylist().remove(opcioEditPlaylist);
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Opció no vàlida");
                            break;
                    }

                    break;
                case 4:
                    String rutaDirectoriEscanejar = Utils.demanaText("Introdueix la ruta del directori a escanejar");
                    escanejarDirectori(rutaDirectoriEscanejar, totesCansons);
                    break;
                case 5:
                    List<String> opcionsEditarCanso = new ArrayList<>();
                    opcionsEditarCanso.add("Afegir cançons");
                    opcionsEditarCanso.add("Eliminar cançons");
                    opcionsEditarCanso.add("Tornar al menú principal");

                    int opcionsEditarC = Utils.demanaOpcioLlista("Menú Edicio Cançons", opcionsEditarCanso) + 1;
                    switch (opcionsEditarC) {
                        case 1:
                            String titol = Utils.demanaText("Introdueix el titol de la cançó");
                            String autor = Utils.demanaText("Introdueix l'autor de la cançó");
                            String rutaArxiu = Utils.demanaText("Introdueix la ruta de l'arxiu");
                            Canso canso = new Canso(autor, titol, rutaArxiu);
                            totesCansons.add(canso);
                            break;
                        case 2:
                            System.out.println("0 - Sortir");
                            for (int i = 0; i < totesCansons.size(); i++) {
                                System.out.println(i + 1 + " - " + totesCansons.get(i).getTitol());
                            }
                            int opcioCanso = Utils.llegeixEnterRang(0, totesCansons.size());
                            if (opcioCanso != 0) {
                                System.out.println("Canço eliminada: " + totesCansons.get(opcioCanso - 1).getTitol());
                                totesCansons.remove(opcioCanso - 1);
                            }
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Opció no vàlida");
                            break;
                    }

                    break;
                case 6:
                    for (int i = 0; i < totesCansons.size(); i++) {
                        System.out.println(i + 1 + " - " + totesCansons.get(i).getTitol());
                    }
                    System.out.println("0 - Sortir");
                    int opcioCansoE = Utils.llegeixEnterRang(0, totesCansons.size());
                    String rutaArxiuE = totesCansons.get(opcioCansoE - 1).getArxiu();
                    String titolE = Utils.demanaText("Introdueix el nou titol");
                    String autorE = Utils.demanaText("Introdueix el nou autor");
                    escriureID3(rutaArxiuE, titolE, autorE);
                    totesCansons.get(opcioCansoE - 1).setTitol(titolE);
                    totesCansons.get(opcioCansoE - 1).setAutor(autorE);
                    System.out.println("Cançó editada: " + totesCansons.get(opcioCansoE - 1).getTitol());
                    break;

            }
        } while (opcio != 7);
        guardarPlaylisFtsEnXml(playlists, nomFitxerXml);
        guardarCansosEnFitxer(totesCansons, nomFitxerJson);
    }


    private static ArrayList<Canso> cargarCansonsDelJson(String nomFitxerJson) {
        Gson gson = new Gson();
        ArrayList<Canso> cansos = new ArrayList<>();

        try (Reader reader = new FileReader(nomFitxerJson)) {
            Type tipuscanso = new TypeToken<List<Canso>>() {
            }.getType();

            cansos = gson.fromJson(reader, tipuscanso);
        } catch (IOException e) {
            System.err.println("Error en llegir el fitxer JSON: " + e.getMessage());
        }

        return cansos;
    }

    private static void guardarCansosEnFitxer(ArrayList<Canso> cansons, String nomFitxerJson) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Writer writer = new FileWriter(nomFitxerJson)) {
            gson.toJson(cansons, writer);
            System.out.println("Dades guardades correctament al fitxer: " + nomFitxerJson);
        } catch (IOException e) {
            System.err.println("Error en guardar al fitxer JSON: " + e.getMessage());
        }
    }

    private static void guardarPlaylisFtsEnXml(PlaylisFts playlists, String nomFitxerXml) {
        try {
            JAXBContext contexto = JAXBContext.newInstance(new Class[]{PlaylisFts.class});
            Marshaller m = contexto.createMarshaller();
            File f = new File(nomFitxerXml);
            m.marshal(playlists, f);
            System.out.println("Dades guardades correctament al fitxer XML: " + nomFitxerXml);
        } catch (Exception e) {
            System.out.println("Error al guardar les PlaylisFts en XML: " + e.getMessage());
        }
    }

    private static PlaylisFts cargarPlaylist(String nomFitxerXml) {
        PlaylisFts PlaylisFts = new PlaylisFts();
        try {
            JAXBContext contexto = JAXBContext.newInstance(new Class[]{PlaylisFts.class});
            Unmarshaller m = contexto.createUnmarshaller();
            File f = new File(nomFitxerXml);
            PlaylisFts = (PlaylisFts) m.unmarshal(f);
            return PlaylisFts;
        } catch (Exception ex) {
            System.out.println("Error al carregar les PlaylisFts: " + ex.getMessage());
            return null;
        }

    }

    public static void escanejarDirectori(String path, ArrayList<Canso> totesCansons) {
        ArrayList<String> rutes = new ArrayList<>();
        File dir = new File(path);
        File[] arxius = dir.listFiles();

        if (arxius != null) {
            for (File arxiu : arxius) {
                if (arxiu.getName().toLowerCase().endsWith(".mp3")) {
                    rutes.add(arxiu.getAbsolutePath());
                }
            }
        }
        afegirCanconsLlista(rutes, totesCansons);
    }

    public static void afegirCanconsLlista(ArrayList<String> rutes, ArrayList<Canso> totesCansons) { // encontes de fer servir el id3v1 he decidit buscar com fer per poder fer servir el id3v2
        String titol;
        String autor;
        for (String r : rutes) {
            boolean repetit = false;
            titol = "Sense titol";
            autor = "Sense autor";
            try {
                Mp3File mp3 = new Mp3File(r);
                if (mp3.hasId3v2Tag()) {
                    ID3v2 tag = mp3.getId3v2Tag();
                    if (tag.getTitle() != null && !tag.getTitle().isEmpty()) {
                        titol = Utils.retallaZeros(tag.getTitle());
                    }
                    if (tag.getArtist() != null && !tag.getArtist().isEmpty()) {
                        autor = Utils.retallaZeros(tag.getArtist());
                    }
                } else if (mp3.hasId3v1Tag()) {
                    ID3v1 tag = mp3.getId3v1Tag();
                    if (tag.getTitle() != null && !tag.getTitle().isEmpty()) {
                        titol = Utils.retallaZeros(tag.getTitle());
                    }
                    if (tag.getArtist() != null && !tag.getArtist().isEmpty()) {
                        autor = Utils.retallaZeros(tag.getArtist());
                    }
                }

                Canso canso = new Canso(autor, titol, r);
                for (Canso c : totesCansons) {
                    if (c.getArxiu().equals(canso.getArxiu())) {
                        repetit = true;
                        break;
                    }
                }

                if (!repetit) {
                    totesCansons.add(canso);
                }

            } catch (Exception ex) {
                Utils.mostraError("no s'ha pogut llegir de l'arxiu " + r);
            }
        }
    }

    public static void escriureID3(String ruta, String nouTitol, String nouAutor) {// encontes de fer servir el id3v1 he decidit buscar com fer per poder fer servir el id3v2
        try {
            Mp3File arxiu = new Mp3File(ruta);

            ID3v2 etiquetaID3;
            if (arxiu.hasId3v2Tag()) {
                etiquetaID3 = arxiu.getId3v2Tag();
            } else {
                etiquetaID3 = new ID3v24Tag();
                arxiu.setId3v2Tag(etiquetaID3);
            }
            etiquetaID3.setTitle(nouTitol);
            etiquetaID3.setArtist(nouAutor);

            String rutaTemporal = ruta.replace(".mp3", "_TEMP.mp3");
            arxiu.save(rutaTemporal);

            Files.delete(Paths.get(ruta));
            Files.move(Paths.get(rutaTemporal), Paths.get(ruta));


        } catch (UnsupportedTagException | InvalidDataException | IOException | NotSupportedException e) {
            Utils.mostraError("No s'ha pogut modificar l'arxiu: " + e.getMessage());
        }
    }


    public static void reproduir(ArrayList<Canso> llista) {
        Utils.netejaPantalla();
        if (llista.size() == 0) {
            Utils.mostraError("La biblioteca està buida");
            Utils.mostraMissatge("Premi una tecla per tornar al menú principal...");
            Utils.pausaFinsTecla();
        } else {
            int opcio = Utils.demanaOpcioLlista("Llistat de cançons", llistarTitols(llista));
            Reproductor reproductor = new Reproductor(llista.get(opcio));
            reproductor.reproduir();
        }
    }

    public static void reproduir(PlaylisFts.Playlist playlist) {
        for (Canso c : playlist.getCanso()) {
            System.out.println("Reproduint: " + c.getTitol());
            Reproductor reproductor = new Reproductor(c);
            String nomtxt = c.getArxiu().replace(".mp3", ".txt");
            File file = new File(nomtxt);
            if (file.exists()) {
                Utils.mostraMissatge("Lleta de la cançó: ");
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) { // mostro el fitxer que tingui estigui en el matix directori i es digui igual
                        System.out.println(line);            // el printo nomes una vegada peque sino no es pot llegir.
                    }
                } catch (IOException e) {
                    System.err.println("Error llegint el fitxer de lletres: " + e.getMessage());
                }
            }else{
                System.out.println("No hi ha una lletra asociada a aquesta cançó");
            }
            reproductor.reproduir();
        }
    }

    public static List<String> llistarTitols(List<Canso> cancons) {
        List<String> titols = new ArrayList<>();
        for (Canso c : cancons) {
            titols.add(c.getTitol());
        }
        return titols;
    }

    public static void afegirCansons(PlaylisFts.Playlist playlist, ArrayList<Canso> totesCansons) {
        int opcioCanso;
        ArrayList<Canso> copia = new ArrayList<>(totesCansons);
        do {
            Utils.netejaPantalla();
            System.out.println("0 - Sortir");
            for (int i = 0; i < copia.size(); i++) {
                System.out.println(i + 1 + " - " + copia.get(i).getTitol());
                if (copia.size() == 0) {
                    System.out.println("No hi ha cançons disponibles per afegir a la playlist");
                }
            }
            opcioCanso = Utils.llegeixEnterRang( 0, copia.size());
            if (opcioCanso != 0) {
                Canso cansoSeleccionat = copia.get(opcioCanso - 1);
                playlist.getCanso().add(cansoSeleccionat);
                copia.remove(cansoSeleccionat);
                System.out.println("Afegida canço: " + cansoSeleccionat.getTitol());
            }

        } while (opcioCanso != 0);
    }

    public static void EliminarCansons(PlaylisFts.Playlist playlist) {
        int opcioCanso;
        do {
            System.out.println("0 - Sortir");
            for (int i = 0; i < playlist.getCanso().size(); i++) {
                System.out.println(i + 1 + "-" + playlist.getCanso().get(i).getTitol());
            }
            opcioCanso = Utils.llegeixEnterRang(0, playlist.getCanso().size());
            if (opcioCanso != 0) {
                System.out.println("Canço eliminada: " + playlist.getCanso().get(opcioCanso - 1).getTitol());
                playlist.getCanso().remove(opcioCanso - 1);
            }

        } while (opcioCanso != 0);
    }

}
