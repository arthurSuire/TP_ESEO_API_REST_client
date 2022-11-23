package servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VilleDaoImpl;
import dto.Distance;
import dto.Ville;

/**
 * Servlet implementation class DistanceVilles
 */
@WebServlet("/CalculerDistanceVilles")
public class CalculerDistanceVilles extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VilleDaoImpl villeDaoImpl = new VilleDaoImpl();
	ArrayList<Ville> villes;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculerDistanceVilles() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		villes = villeDaoImpl.findAllVille();
		request.setAttribute("villes", villes);
		this.getServletContext().getRequestDispatcher("/WEB-INF/calculerDistanceVilles.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		villes = villeDaoImpl.findAllVille();
		
		String nomA = request.getParameter("pointA");
		request.setAttribute("nomA", nomA);
		String nomB = request.getParameter("pointB");
		request.setAttribute("nomB", nomB);
		
		Ville villeA = new Ville();
		Ville villeB = new Ville();

		for (Ville current : villes) {
			if (current.getNomCommune().equals(nomA)) {
				villeA = current;
			} else if (current.getNomCommune().equals(nomB)) {
				villeB = current;
			}
		}
				
		Distance distanceGestionner = new Distance();
		double distance = distanceGestionner.calculDistance(villeA.getCoordonnee().getLatitude(), villeA.getCoordonnee().getLongitude(),
				villeB.getCoordonnee().getLatitude(), villeB.getCoordonnee().getLongitude());
		DecimalFormat format = new DecimalFormat();
		format.setMaximumFractionDigits(1);
		request.setAttribute("distance", format.format(distance));		
		
		doGet(request, response);
	}

}
