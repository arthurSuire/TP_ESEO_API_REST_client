package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VilleDaoImpl;
import dto.Coordonnee;
import dto.Ville;

/**
 * Servlet implementation class AjouterVille
 */
@WebServlet("/AjouterVille")
public class AjouterVille extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VilleDaoImpl villeDaoImpl = new VilleDaoImpl();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterVille() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterVille.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String codeCommune = request.getParameter("codeCommune");
		String codePostal = request.getParameter("codePostal");
		String libelle = request.getParameter("libelle");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		String ligne5 = request.getParameter("ligne5");
		
		Ville villeAjoutee = new Ville(nom, codeCommune, codePostal, ligne5, libelle, new Coordonnee(longitude, latitude));
		villeDaoImpl.createVille(villeAjoutee);
		
		ArrayList<Ville> ville = villeDaoImpl.findVilleByCodeCommune(codeCommune);
		request.setAttribute("villeAjout", ville.get(0));
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/villeAjoutee.jsp").forward(request, response);
	}

}
