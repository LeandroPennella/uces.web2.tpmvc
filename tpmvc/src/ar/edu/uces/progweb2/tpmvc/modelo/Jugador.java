package ar.edu.uces.progweb2.tpmvc.modelo;

import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class Jugador {
private String nombre;
private String idioma;
private int mejorScore=10;
 
public Jugador(){}
public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getIdioma() {
	return idioma;
}

public void setIdioma(String idioma) {
	this.idioma = idioma;
}

public int getMejorScore() {
	return mejorScore;
}

public void setMejorScore(int mejorScore) {
	this.mejorScore = mejorScore;
}




}
