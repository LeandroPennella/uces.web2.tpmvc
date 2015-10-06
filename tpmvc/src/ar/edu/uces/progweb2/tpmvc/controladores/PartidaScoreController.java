package ar.edu.uces.progweb2.tpmvc.controladores;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.uces.progweb2.tpmvc.modelo.Jugador;
import ar.edu.uces.progweb2.tpmvc.modelo.Partida;

@SessionAttributes({"partida","jugador"}) 
@Controller
public class PartidaScoreController {
	
	@Autowired
	private Jugador mejorJugador;
	
	@RequestMapping(value = "/partida/persistirScore")	
	private ModelAndView persistirScore(@ModelAttribute("partida") Partida partida , @ModelAttribute("jugador") Jugador jugador ,  HttpServletResponse response, HttpServletRequest request)
	{
		boolean encontrado=false;
		Jugador mejorJugador=(Jugador)request.getServletContext().getAttribute("mejorJugador"); 
		Cookie cookie ;
		int scoreActual=partida.getIntentos().size();

		//actualiza mejor score en session
		if (jugador.getMejorScore()>scoreActual)
		{
			jugador.setMejorScore(scoreActual);
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

		if (!encontrado)
		{
			cookie=new Cookie(partida.getJugador().getNombre(),Integer.toString(partida.getIntentos().size()));
			cookie.setMaxAge(60*60*24*365);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		
		//actualiza mejor jugador del mundo mundial (sistema)		
		if ((mejorJugador==null)||(scoreActual<mejorJugador.getMejorScore()))
			//mejorJugador=jugador;
			request.getServletContext().setAttribute("mejorJugador", jugador); 
		
		return new ModelAndView("/views/partida.jsp");//todo: devolver del forward;
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
