package ar.edu.uces.progweb2.tpmvc.interceptores;



import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class InterceptorPartida implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		/*
		System.out.println("estoy en prehandle de "+arg2.toString());
		arg0.setAttribute("mensaje", "mensajeSeteadoEnElInterceptor");
		arg0.setAttribute("millisInicio", System.currentTimeMillis());
		*/
		HttpSession session= request.getSession();
		//if ((session.getAttribute("jugador")==null)&&(session.getAttribute("partida")==null))
		if (session.getAttribute("jugador")==null)
		{
			RequestDispatcher rd= request.getRequestDispatcher("/identificarJugador.do");
			rd.forward(request, response);
			return false;		//no pasa por postHandle ni afterCompletion
		}
			
		return true; //sigue o no sigue // al controlador o al siguiente interceptor
		
		//para hacer un forward
		/*
		RequestDispatcher rd= arg0.getRequestDispatcher("/views/holamundo.jsp");
		rd.forward(arg0, arg1);
		return false;		//no pasa por postHandle ni afterCompletion
		*/
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		/*
		System.out.println("estoy en postHandle de "+arg2.toString());
		long millisActual =System.currentTimeMillis();
		long millisInicio= (long) arg0.getAttribute("millisInicio");
		long tiempo=millisActual -millisInicio;
		arg0.setAttribute("tiempoEntreHandles", tiempo);
		System.out.println("to: "+tiempo);	
		*/
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		//System.out.println("estoy en afterCompletion de "+arg2.toString());
		/*long millisActual =System.currentTimeMillis();
		long millisInicio= (long) arg0.getAttribute("millisInicio");
		long tiempoAnterior= (long) arg0.getAttribute("millisInicio");
		long tiempoFinal= millisActual-millisInicio; 
		long tiempoParcial=tiempoActual -tiempoInicial;
		System.out.println("to: "+tiempoParcial);
		System.out.println("to final : "+tiempoFinal);*/
		

	}

}
