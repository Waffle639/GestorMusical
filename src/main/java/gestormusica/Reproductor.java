package gestormusica;


import java.io.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * Classe que permet reproduir una cançó des del terminal de Windows fent servir la llibreria Javazoom
 * 
 * @author Juan Martín
 */
public class Reproductor {

	private boolean stop = false;
	private Player player;
	private Canso canso;

	/**
	 * Constructor de la classe. Crea una instància del reproductor i la inicialitza
	 * amb el paràmetre canço. No inicia la reproducció immediatament, s'haurà de
	 * crida al mètode {@link #reproduir() reproduir}.
	 * @param canso la instància de Canço a reproduir
	 */
	public Reproductor(Canso canso) {
		this.canso = canso;
		try {
			player = new Player(new FileInputStream(canso.getArxiu()));
		} catch (FileNotFoundException ex) {
			Utils.mostraError("no s'ha trobat l'arxiu: " + canso.getArxiu());
		} catch (JavaLayerException ex) {
			Utils.mostraError("no s'ha pogut crear el reproductor");
		}
	}

	/**
	 * Inicia la reproducció de la cançó proporcionada al constructor.
	 * La reproducció es pot aturar en qualsevol moment prement alguna tecla.
	 * Durant la reproducció es mostren les dades de la canço i un comptador del 
	 * temps de reproducció.
	 */
	public void reproduir() {
		new Thread() {
			public void run() {
				int segons, segonAnt = -1;
				try {
					while (!stop) {
						segons = player.getPosition() / 1000;
						if (segons > segonAnt) {
							Utils.netejaPantalla();

							Utils.mostraMissatge("Reproduint: " + canso);
							String reproduccio = String.format("%02d:%02d%n", segons / 60, segons % 60);
							Utils.mostraMissatge(reproduccio);

							String nomtxt = canso.getArxiu().replace(".mp3", ".txt");
							File file = new File(nomtxt);
							if (file.exists()) {
								Utils.mostraMissatge("Lletra de la cançó: ");
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
							Utils.mostraMissatge("Prem qualsevol tecla per finalitzar la reproducció");
							segonAnt = segons;
						}
						player.play(1);
					}
				} catch (JavaLayerException e) {
					Utils.mostraError(e.getMessage());
				}
				player.close();
			}
		}.start();
		Utils.pausaFinsTecla();
		stop = true;
	}
}
