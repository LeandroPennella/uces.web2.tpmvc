
package ar.edu.uces.progweb2.tpmvc.controladores;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import ar.edu.uces.progweb2.tpmvc.modelo.Intento;
import ar.edu.uces.progweb2.tpmvc.modelo.Jugador;
import ar.edu.uces.progweb2.tpmvc.modelo.Partida;
import ar.edu.uces.progweb2.tpmvc.validadores.IntentoValidador;
import ar.edu.uces.progweb2.tpmvc.validadores.JugadorValidador;


@SessionAttributes("partida")

@Controller

public class JuegoController {
	
		@Autowired
		private IntentoValidador intentoValidador;
		@Autowired
		private JugadorValidador jugadorValidador;
		//@Autowired
		//private SessionLocaleResolver localeResolver;
		/*
		@RequestMapping(value = "/juego")
		public String init() {
			return "/views/index.jsp";
		}
*/
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

			this.jugadorValidador.validate(jugador, result);	
			if (result.hasErrors()) {
				return new ModelAndView("/views/identificarJugador.jsp");
			}
			
			//localeResolver.setLocale(request, response, new Locale("es_AR") );
			
			
			
			
			
			
			//iniciar partida
			//return new ModelAndView("/iniciarPartida.do", "jugador", jugador);
			ModelAndView mv=new ModelAndView("/views/partida.jsp");
			mv.addObject("intento", new Intento());
			Partida partida=new Partida(jugador);
			mv.addObject("partida", partida);
			return mv;
			//return new ModelAndView("/views/partida.jsp", "intento", new Intento());
		}
	
		/*
		@RequestMapping(value = "/iniciarPartida")
		public ModelAndView iniciarPartida(@ModelAttribute("jugador") Jugador jugador) {
			
			//todo:validar que haya jugador 
			Partida partida=new Partida(jugador);
			ModelAndView modelAndView =new ModelAndView("/views/partida.jsp");
			modelAndView.addObject("partida",);
			return modelAndView;
		}
*/
		@RequestMapping(value = "/procesarIntento")	
		public ModelAndView procesarIntento(@ModelAttribute("intento") Intento intento, @ModelAttribute("partida") Partida partida , BindingResult result, SessionStatus status) {
			//todo: validar que haya partida
			this.intentoValidador.validate(intento, result);	
			
			if (partida.getNumeroADescubrir()==intento.getValorElegido()){
				return new ModelAndView("/views/gano.do");
			} else {
				partida.addIntento(intento);
				return new ModelAndView("/views/partida.jsp", "intento", new Intento());
			}
			
		}
		
	}
