
package ar.edu.uces.progweb2.tpmvc.controladores;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
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


@SessionAttributes({"partida","jugador"}) 


@Controller
@RequestMapping(value = "/partida")
public class PartidaController {
	
		@Autowired
		private IntentoValidador intentoValidador;

		//@Autowired
		//private SessionLocaleResolver localeResolver;
		/*
		@RequestMapping(value = "/juego")
		public String init() {
			return "/views/index.jsp";
		}
	 	*/

		@RequestMapping(value = "/iniciarPartida")
		public ModelAndView iniciarPartida(@ModelAttribute("jugador") Jugador jugador) {
			
			//todo:validar que haya jugador
			Partida partida=new Partida(jugador);
			ModelAndView mv=new ModelAndView("/views/partida.jsp");
			mv.addObject("intento", new Intento());
			mv.addObject("partida", partida);
			return mv;
		}
		
		@RequestMapping(value = "/reIniciarPartida")
		public ModelAndView reIniciarPartida(@ModelAttribute("partida") Partida partida) {
			
			Jugador jugador=partida.getJugador();
			//todo:validar que haya jugador
			partida=new Partida(jugador);
			ModelAndView mv=new ModelAndView("/views/partida.jsp");
			mv.addObject("intento", new Intento());
			mv.addObject("partida", partida);
			return mv;
		}
		@RequestMapping(value = "/procesarIntento")	
		public ModelAndView procesarIntento(@ModelAttribute("intento") Intento intento, @ModelAttribute("partida") Partida partida , BindingResult result, SessionStatus status) {
			//todo: validar que haya partida
			this.intentoValidador.validate(intento, result);	
			
			if (partida.addIntento(intento)==true)//devuelve true si el intento es correcto 
			{
				return new ModelAndView("/partida/persistirScore.do");
			}
			else
				if(partida.getIntentos().size()<10)
					return new ModelAndView("/views/partida.jsp", "intento", new Intento());
				else	
					return new ModelAndView("/views/perdio.jsp","jugador",partida.getJugador());
		}
		
		@RequestMapping(value = "/persistirScore")	
		private ModelAndView persistirScore(@ModelAttribute("partida") Partida partida ,  HttpServletResponse response, HttpServletRequest request)
		{
			boolean encontrado=false;
			//obtener cookies
			Cookie[] cookies = ((HttpServletRequest) request).getCookies();
			if (cookies != null) {
				//recorrer cookies
				for (Cookie cookie : cookies) {
					//si existe el jugador
	                if(cookie.getName().toString().equals(partida.getJugador().getNombre())){
	                	encontrado=true;
	                    //si el puntaje guardado es menor al actual
	                	if (Integer.parseInt(cookie.getValue())>partida.getIntentos().size())
	                		//cambiar puntaje
	                		cookie.setValue(Integer.toString(partida.getIntentos().size()));
	                } 
				}
			}
			if (!encontrado)
			{
				Cookie c=new Cookie(partida.getJugador().getNombre(),Integer.toString(partida.getIntentos().size()));
				response.addCookie(c);
			}
			return new ModelAndView("/views/gano.jsp");
		}
	}
