package ar.edu.uces.progweb2.tpmvc.controladores;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.uces.progweb2.tpmvc.modelo.Jugador;
import ar.edu.uces.progweb2.tpmvc.validadores.JugadorValidador;

@SessionAttributes("jugador")
@Controller
public class JugadorController {
	@Autowired
	private JugadorValidador jugadorValidador;
	
	@RequestMapping(value = "/identificarJugador")
	public ModelAndView identificarJugador(HttpServletRequest request, HttpServletResponse response) {
		//LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		//localeResolver.setLocale(request, response, new Locale("es_AR") );
		//Config.set(request.getSession(), Config.FMT_LOCALE, new java.util.Locale("es"));
		Map<String,String> idiomas= new LinkedHashMap<String,String>();
		idiomas.put("es_AR", "Castellano");
		idiomas.put("en_US", "Ingles");
		//mapaIdiomas.put("idiomas", idiomas);
		ModelAndView mv=new ModelAndView("/views/identificarJugador.jsp");
		mv.addObject("jugador", new Jugador());
		mv.addObject("idiomas", idiomas);
		return mv;
	}

	@RequestMapping(value = "/validarJugador")
	public ModelAndView validarJugador(@ModelAttribute("jugador") Jugador jugador, BindingResult result ,HttpServletRequest request, HttpServletResponse response) {
		//todo:no sabe si jugador viene de sesion o de get?
		this.jugadorValidador.validate(jugador, result);	
		if (result.hasErrors()) {
			return new ModelAndView("/views/identificarJugador.jsp");
		}
		//localeResolver.setLocale(request, response, new Locale("es_AR") );
		return new ModelAndView("/partida/iniciarPartida.do");
	}
}
