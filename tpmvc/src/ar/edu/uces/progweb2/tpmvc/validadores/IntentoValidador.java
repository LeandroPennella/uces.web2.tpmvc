package ar.edu.uces.progweb2.tpmvc.validadores;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.uces.progweb2.tpmvc.modelo.Intento;


@Component
public class IntentoValidador implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Intento.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Intento intento= (Intento) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valorElegido", "error.intento.valor.vacio");
		
		if (!errors.hasFieldErrors("valorElegido")) {
			if ((intento.getValorElegido()<1)||(intento.getValorElegido()>100)) {
				errors.rejectValue("valorElegido", "error.intento.valor.fueraDeRango");
			}
		}
	}
}
