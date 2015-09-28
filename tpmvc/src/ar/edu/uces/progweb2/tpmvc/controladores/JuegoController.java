package ar.edu.uces.progweb2.tpmvc.controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.uces.progweb2.tpmvc.modelo.Jugador;
import ar.edu.uces.progweb2.tpmvc.modelo.Partida;
import ar.edu.uces.progweb2.tpmvc.validadores.IntentoValidador;
import ar.edu.uces.progweb2.tpmvc.validadores.JugadorValidador;



@Controller

public class JuegoController {
	
		@Autowired
		private IntentoValidador intentoValidador;
		@Autowired
		private JugadorValidador jugadorValidador;
		
		@RequestMapping(value = "/juego")
		public String init() {
			return "/views/index.jsp";
		}

		@RequestMapping(value = "/identificarJugador")
		public ModelAndView identificarJugador() {
			return new ModelAndView("/views/identificarJugador.jsp","jugador", new Jugador());
		}

		@RequestMapping(value = "/validarJugador")
		public ModelAndView validarJugador(@ModelAttribute("jugador") Jugador jugador, BindingResult result ) {

			this.jugadorValidador.validate(jugador, result);	
			if (result.hasErrors()) {
				return new ModelAndView("/views/identificarJugador.jsp");
			}
			return new ModelAndView("/iniciarPartida.do", "jugador", jugador);
		}
	
		@RequestMapping(value = "/iniciarPartida")
		public ModelAndView iniciarPartida() {
			ModelAndView modelAndView =new ModelAndView("/views/partida.jsp");
			modelAndView.addObject("partida",new Partida());
			return modelAndView;
		}

		@RequestMapping(value = "/procesarIntento")
		
		public ModelAndView procesarIntento(@ModelAttribute("partida") Partida partida, BindingResult result, SessionStatus status) {
			this.intentoValidador.validate(partida.getUltimoIntento(), result);	
			if (partida.getNumeroADescubrir()==partida.getUltimoIntento().getValorElegido()){
				return new ModelAndView("/views/gano.jsp");
			} else {
				return new ModelAndView("/views/partida.jsp");
			}
			
		}
		
	}
