package ar.edu.uces.progweb2.tpmvc.modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Partida {

	private Jugador jugador;
	private int numeroADescubrir;
	private List<Intento> intentos;

	//constructor
	public Partida(Jugador jugador) {
		Random random = new Random();
		this.numeroADescubrir = random.nextInt(99)+1;
		this.intentos = new ArrayList<Intento>();
		this.jugador = jugador;
		System.out.println("El numero a adivinar es "+this.numeroADescubrir);
	}

	//Getters y Setters
	public Jugador getJugador() {return jugador;}

	public void setJugador(Jugador jugador) {this.jugador = jugador;	}
	
	public int getNumeroADescubrir() { return numeroADescubrir; }
	
	public List<Intento> getIntentos() {return intentos;}

	public boolean addIntento(Intento intento) {
		int nDiferencia = intento.getValorElegido() - this.numeroADescubrir;
		String diferencia = "intentos.Correcto";
		if (nDiferencia > 0) {
			diferencia = "intentos.EsMayor";
		}
		if (nDiferencia < 0) {
			diferencia = "intentos.EsMenor";
		}
		intento.setDiferencia(diferencia);
		this.intentos.add(intento);
		if (nDiferencia==0) {//gano
			this.numeroADescubrir=0;
		} 
		return (nDiferencia == 0);
	}

	
}
