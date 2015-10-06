
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
//@SessionAttributes("partida")


@Controller
//@RequestMapping(value = "/partida")
public class PartidaController {
	
		@Autowired
		private IntentoValidador intentoValidador;

		@RequestMapping(value = "/jugador/iniciarPartida")
		public ModelAndView iniciarPartida(@ModelAttribute("jugador") Jugador jugador) {
			
			ModelAndView mv;
			
			if (jugador==null) {//valida que haya jugador
				mv=new ModelAndView("/identificarJugador.jsp");
			} else {
				Partida partida=new Partida(jugador);
				mv=new ModelAndView("/views/partida.jsp");
				mv.addObject("intento", new Intento());
				mv.addObject("partida", partida);
			}
			return mv;
		}

		@RequestMapping(value = "/partida/procesarIntento")	
		public ModelAndView procesarIntento(@ModelAttribute("intento") Intento intento, BindingResult result, @ModelAttribute("partida") Partida partida , SessionStatus status) {
			//todo: validar que haya partida
			if(partida.getIntentos().size()<10) //todo: redundante?
			{
				if(partida.getNumeroADescubrir()!=0)//la partida esta en curse
				{
					this.intentoValidador.validate(intento, result);	
					if (result.hasErrors()) {
						return new ModelAndView("/views/partida.jsp");
					}
					if (partida.addIntento(intento)==true) {//devuelve true si el intento es correcto (gano)					
						return new ModelAndView("/partida/persistirScore.do");
					} else {
						return new ModelAndView("/views/partida.jsp","intento", new Intento());
					}
				} else {
					ModelAndView mv;
					mv=new ModelAndView("/views/partida.jsp");
					return mv;
				}	
			} else	{
				return new ModelAndView("/views/partida.jsp");
			}
		}

	}
