
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


@SessionAttributes({"partida","jugador", "mejorJugador"}) 
//@SessionAttributes("partida")


@Controller
//@RequestMapping(value = "/partida")
public class PartidaController {
	
		@Autowired
		private IntentoValidador intentoValidador;

		@Autowired
		private Jugador mejorJugador;
		//@Autowired
		//private SessionLocaleResolver localeResolver;
		/*
		@RequestMapping(value = "/juego")
		public String init() {
			return "/views/index.jsp";
		}
	 	*/

		@RequestMapping(value = "/jugador/iniciarPartida")
		public ModelAndView iniciarPartida(@ModelAttribute("jugador") Jugador jugador) {
			
			ModelAndView mv;
			//todo:validar que haya jugador
			
			if (jugador==null) {
				mv=new ModelAndView("/identificarJugador.jsp");
			} else {
//				Partida partida=new Partida(jugador);
				Partida partida=new Partida(jugador);
				mv=new ModelAndView("/views/partida.jsp");
				mv.addObject("intento", new Intento());
				mv.addObject("partida", partida);
			}
			return mv;
		}
		/*
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
		*/
		@RequestMapping(value = "/partida/procesarIntento")	
		public ModelAndView procesarIntento(@ModelAttribute("intento") Intento intento, BindingResult result, @ModelAttribute("partida") Partida partida , SessionStatus status) {
			//todo: validar que haya partida
			this.intentoValidador.validate(intento, result);	
			if (result.hasErrors()) {
				return new ModelAndView("/views/partida.jsp");
			}
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
		
		@RequestMapping(value = "/partida/persistirScore")	
		private ModelAndView persistirScore(@ModelAttribute("partida") Partida partida , @ModelAttribute("jugador") Jugador jugador ,  HttpServletResponse response, HttpServletRequest request)
		{
			boolean encontrado=false;

			Cookie cookie ;
			int scoreActual=partida.getIntentos().size();

			//actualiza mejor score en session
			if (jugador.getMejorScore()>scoreActual)
			{
				jugador.setMejorScore(scoreActual);
			}
			
			//actualiza mejor jugador en cookies
			cookie = obtenerCookie(request, "mejorJugadorNumero");
			if (cookie==null)
			{
				cookie=new Cookie("mejorJugadorNumero","10");
			}
			
			if (Integer.parseInt(cookie.getValue())>scoreActual)		
			{
    			cookie.setValue(Integer.toString(scoreActual));
    			cookie.setMaxAge(60*60*24*365);
    			cookie.setPath("/");
    			response.addCookie(cookie);
    			cookie = new Cookie ("mejorJugadorNombre",mejorJugador.getNombre());
    			cookie.setValue(jugador.getNombre());
    			cookie.setMaxAge(60*60*24*365);
    			cookie.setPath("/");
    			response.addCookie(cookie);
			}
			
			//actualiza mejor score del jugador en cookies
					
			cookie = obtenerCookie(request, jugador.getNombre());
			if (cookie!=null)
			{
				encontrado=true;
            	if (Integer.parseInt(cookie.getValue())>scoreActual)
            		{
            			cookie.setValue(Integer.toString(scoreActual));
            			cookie.setMaxAge(60*60*24*365);
            			cookie.setPath("/");
            			response.addCookie(cookie);
            		}
            } 
			
			
			/*
			Cookie[] cookies = ((HttpServletRequest) request).getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
	                if(cookie.getName().toString().equals(partida.getJugador().getNombre())){
	                	encontrado=true;
	                	if (Integer.parseInt(cookie.getValue())>scoreActual)
	                		{
	                			String nuevoValor=Integer.toString(scoreActual);
	                			cookie.setValue(nuevoValor);
	                			cookie.setMaxAge(60*60*24*365);
	                			cookie.setPath("/");
	                			response.addCookie(cookie);
	                		}
	                } 
				}
			}
			*/
			if (!encontrado)
			{
				cookie=new Cookie(partida.getJugador().getNombre(),Integer.toString(partida.getIntentos().size()));
				cookie.setMaxAge(60*60*24*365);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			
			//actualiza mejor jugador del mundo mundial (sistema)
			if (scoreActual<mejorJugador.getMejorScore())
				mejorJugador=jugador;
			
			
			return new ModelAndView("/views/gano.jsp");
		}
		
	    public static Cookie obtenerCookie(HttpServletRequest request, String name) {
	        if (request.getCookies() != null) {
	            for (Cookie cookie : request.getCookies()) {
	                if (cookie.getName().equals(name)) {
	                    return cookie;
	                }
	            }
	        }
	        return null;
	    }
	}
