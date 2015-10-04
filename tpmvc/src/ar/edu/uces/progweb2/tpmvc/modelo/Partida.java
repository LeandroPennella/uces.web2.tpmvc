package ar.edu.uces.progweb2.tpmvc.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Partida {

	private Jugador jugador;
	private int numeroADescubrir;
	private List<Intento> intentos;
	//private Intento ultimoIntento;
/*
	public Partida() {
		Random random = new Random();
		this.numeroADescubrir = random.nextInt(100);
		this.intentos = new ArrayList<Intento>();
	}
*/
	public Partida(Jugador jugador) {
		Random random = new Random();
		this.numeroADescubrir = random.nextInt(100);
		this.intentos = new ArrayList<Intento>();
		this.jugador = jugador;
		System.out.println("El numero a adivinar es "+this.numeroADescubrir);
	}

	//Getters y Setters
	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	
	  public int getNumeroADescubrir() { return numeroADescubrir; }
	
	public List<Intento> getIntentos() {
		return intentos;
	}

	public boolean addIntento(Intento intento) {
		int nDiferencia = intento.getValorElegido() - this.numeroADescubrir;
		String diferencia = "";
		if (nDiferencia > 0) {
			diferencia = "Es mayor";
		}
		if (nDiferencia < 0) {
			diferencia = "Es menor";
		}
		intento.setDiferencia(diferencia);
		// this.ultimoIntento = intento;
		this.intentos.add(intento);
		this.numeroADescubrir=0;
		return (nDiferencia == 0);
	}
	/*
	 * public Intento getUltimoIntento() { return this.ultimoIntento; }
	 */
	
	
}
