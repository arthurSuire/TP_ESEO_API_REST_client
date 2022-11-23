package dao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import dto.Ville;

public interface VilleDao {

	ArrayList<Ville> findAllVille();
	ArrayList<Ville> findVilleByCodeCommune(String code);
	ArrayList<Ville> findVilleByCodePostal(String code);
	void createVille(Ville ville);
	void modifyVille(Ville ville) throws InterruptedException, IOException, URISyntaxException;
	void deleteVille(String codeCommune);
}
