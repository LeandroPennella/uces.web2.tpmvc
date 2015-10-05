package ar.edu.uces.progweb2.tpmvc.controladores;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import ar.edu.uces.progweb2.tpmvc.modelo.Jugador;
import ar.edu.uces.progweb2.tpmvc.validadores.JugadorValidador;

@SessionAttributes("jugador")
@Controller
public class JugadorController {
	@Autowired
	private JugadorValidador jugadorValidador;
	@Autowired
	private SessionLocaleResolver localeResolver;
	
	@RequestMapping(value = "/identificarJugador")
	public ModelAndView identificarJugador(HttpServletRequest request, HttpServletResponse response) {

		//localeResolver.setLocale(request, response, new Locale("es", "AR", "corbobes") );
		//localeResolver.setLocale(request, response, new Locale("en") );

		ModelAndView mv=new ModelAndView("/views/identificarJugador.jsp");
		mv.addObject("jugador", new Jugador());
		mv.addObject("idiomas", listarIdiomas());
		return mv;
	}

	@RequestMapping(value = "/jugador/validarJugador")
	public ModelAndView validarJugador(@ModelAttribute("idioma") String idioma,  @ModelAttribute("jugador") Jugador jugador, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
	
		//Jugador jugador=(Jugador)model.get("jugador");
		//todo:no sabe si jugador viene de sesion o de get?
		this.jugadorValidador.validate(jugador, result);	
		if (result.hasErrors()) {
			return new ModelAndView("/views/identificarJugador.jsp","idiomas", listarIdiomas());
		}
		//todo: separar lengua_pais
		localeResolver.setLocale(request, response, new Locale(idioma) );
		return new ModelAndView("/jugador/iniciarPartida.do","jugador",jugador);
	}
	
	private Map<String,String> listarIdiomas()
	{
		Map<String,String>idiomas= new LinkedHashMap<String,String>();
		idiomas.put("es", "Castellano");
		idiomas.put("en", "Ingles");
		return idiomas;
	}
}
