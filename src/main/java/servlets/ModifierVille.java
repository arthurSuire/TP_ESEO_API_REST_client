package servlets;

import java.io.IOException;
import java.net.URISyntaxException;
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
 * Servlet implementation class ModifierVille
 */
@WebServlet("/ModifierVille")
public class ModifierVille extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VilleDaoImpl villeDaoImpl = new VilleDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierVille() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String villeModifCode = request.getParameter("codeCommune");
		ArrayList<Ville> ville = villeDaoImpl.findVilleByCodeCommune(villeModifCode);
		
		request.setAttribute("codeCommune", villeModifCode);
		request.setAttribute("ville", ville.get(0));
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierVille.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomVille = request.getParameter("nomVille");
		String nom = request.getParameter("nom");
		String codeCommune = request.getParameter("codeCommune");
		String codePostal = request.getParameter("codePostal");
		String libelle = request.getParameter("libelle");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		String ligne5 = request.getParameter("ligne5");

		request.setAttribute("villeNonModif", nomVille);
		
		Ville villeModifiee = new Ville(nom, codeCommune, codePostal, ligne5, libelle, new Coordonnee(longitude, latitude));
		try {
			villeDaoImpl.modifyVille(villeModifiee);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		ArrayList<Ville> villeModif = villeDaoImpl.findVilleByCodeCommune(codeCommune);
		request.setAttribute("villeModif", villeModif.get(0));
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/villeModifiee.jsp").forward(request, response);	
	}

}
