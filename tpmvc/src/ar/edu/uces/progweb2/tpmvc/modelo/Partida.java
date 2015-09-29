package ar.edu.uces.progweb2.tpmvc.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Partida {

	private Jugador jugador;
	private int numeroADescubrir;
	private List<Intento> intentos;
	private Intento ultimoIntento;

	public Partida() {
		Random random = new Random();
		this.numeroADescubrir = random.nextInt(100);
		this.intentos = new ArrayList<Intento>();
	}

	public Partida(Jugador jugador) {
		super();
		this.jugador = jugador;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public int getNumeroADescubrir() {
		return numeroADescubrir;
	}

	public List<Intento> getIntentos() {
		return intentos;
	}

	public void addIntento(Intento intento) {
		this.intentos.add(intento);
		this.ultimoIntento = intento;
	}

	public Intento getUltimoIntento() {
		return this.ultimoIntento;
	}

}
