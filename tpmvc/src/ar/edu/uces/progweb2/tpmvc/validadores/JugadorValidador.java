package ar.edu.uces.progweb2.tpmvc.validadores;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.uces.progweb2.tpmvc.modelo.Jugador;

@Component
public class JugadorValidador implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Jugador.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object object, Errors errors) {
		Jugador jugador= (Jugador) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "error.jugador.valor.vacio");
		if (!errors.hasFieldErrors("nombre")) {
			
			//TODO: validar que no empiece con numeros
			//char primerCaracter=jugador.getNombre().substring(0,1).toCharArray()[0];
			//boolean esNumero=(primerCaracter=='0');
			if (esNumero(jugador.getNombre()))
			{
				errors.rejectValue("nombre", "error.jugador.valor.comienzaConNumero");
			}
		}
	}
	private  boolean esNumero(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	/*
	private  boolean esNumero(char caracter){
		try {
			Integer.parseUnsignedInt(caracter);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}*/
}
