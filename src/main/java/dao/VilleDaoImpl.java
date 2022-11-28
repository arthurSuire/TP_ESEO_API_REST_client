package dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.util.ArrayList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.json.JSONArray;
import org.json.JSONException;

import dto.Ville;
import utils.Converter;

public class VilleDaoImpl implements VilleDao{

    private final Converter converter = new Converter();
    private final ObjectMapper mapper = new ObjectMapper();
    private JSONArray jsonArray;
    
	@Override
	public ArrayList<Ville> findAllVille() {
		ArrayList<Ville> listeVilles = new ArrayList<>();
        try {
            jsonArray = converter.readJsonFromUrl("http://localhost:8181/villes");
            listeVilles = mapper.readValue(jsonArray.toString(), new TypeReference<ArrayList<Ville>>() {
            });
        } catch (JSONException | IOException e) {
            System.out.println(e);
        }
		return listeVilles;
	}

	@Override
	public ArrayList<Ville> findVilleByCodeCommune(String code) {
		ArrayList<Ville> listeVilles = new ArrayList<>();
        try {
            jsonArray = converter.readJsonFromUrl("http://localhost:8181/villeCC?codeCommune="+code);
            listeVilles = mapper.readValue(jsonArray.toString(), new TypeReference<ArrayList<Ville>>() {
            });
        } catch (JSONException | IOException e) {
            System.out.println(e);
        }
		return listeVilles;
	}

	@Override
	public ArrayList<Ville> findVilleByCodePostal(String code) {
		ArrayList<Ville> listeVilles = new ArrayList<>();
        try {
            jsonArray = converter.readJsonFromUrl("http://localhost:8181/villeCP?codePostal="+code);
            listeVilles = mapper.readValue(jsonArray.toString(), new TypeReference<ArrayList<Ville>>() {
            });
        } catch (JSONException | IOException e) {
            System.out.println(e);
        }
		return listeVilles;
	}

	@Override
	public void createVille(Ville ville) {
		try {
			   CloseableHttpClient httpClient = HttpClients.createDefault();
			   
				String requestBody = "{\r\n"
						+ "    \"codeCommune\": \""+ville.getCodeCommune()+"\",\r\n"
						+ "    \"nomCommune\": \""+ville.getNomCommune()+"\",\r\n"
						+ "    \"codePostal\": \""+ville.getCodePostal()+"\",\r\n"
						+ "    \"libelleAcheminement\": \""+ville.getLibelleAcheminement()+"\",\r\n"
						+ "    \"ligne5\": \""+ville.getLigne5()+"\",\r\n"
						+ "    \"coordonnee\": {\r\n"
						+ "        \"latitude\" : \""+ville.getCoordonnee().getLatitude()+"\",\r\n"
						+ "        \"longitude\" : \""+ville.getCoordonnee().getLongitude()+"\"\r\n"
						+ "    }\r\n"
						+ "}";
			   
			   StringEntity stringEntity = new StringEntity(requestBody);
			   HttpPost httpPost = new HttpPost();
			   httpPost.setURI(new URI("http://localhost:8181/ajouter_ville"));
			   httpPost.addHeader("Content-type", "application/json");
			   httpPost.setEntity(stringEntity);
			   CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			  } catch (URISyntaxException e) {
			   e.printStackTrace();
			  } catch (UnsupportedEncodingException e) {
			   e.printStackTrace();
			  } catch (ClientProtocolException e) {
			   e.printStackTrace();
			  } catch (IOException e) {
			   e.printStackTrace();
			  }
	}

	@Override
	public void modifyVille(Ville ville) throws InterruptedException, IOException, URISyntaxException {		   
		String uri = "http://localhost:8181/modifier_ville";	
		
		String requestBody = "{\r\n"
				+ "    \"codeCommune\": \""+ville.getCodeCommune()+"\",\r\n"
				+ "    \"nomCommune\": \""+ville.getNomCommune()+"\",\r\n"
				+ "    \"codePostal\": \""+ville.getCodePostal()+"\",\r\n"
				+ "    \"libelleAcheminement\": \""+ville.getLibelleAcheminement()+"\",\r\n"
				+ "    \"ligne5\": \""+ville.getLigne5()+"\",\r\n"
				+ "    \"coordonnee\": {\r\n"
				+ "        \"latitude\" : \""+ville.getCoordonnee().getLatitude()+"\",\r\n"
				+ "        \"longitude\" : \""+ville.getCoordonnee().getLongitude()+"\"\r\n"
				+ "    }\r\n"
				+ "}";
			   
		var request = HttpRequest.newBuilder()
		   .uri(new URI(uri))
		   .header("Content-type", "application/json")
		   .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
		   .build();
   
		var client = HttpClient.newHttpClient();
   
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());	   
	}

	@Override
	public void deleteVille(String code) {
		try {
			URL url = new URL("http://localhost:8181/supprimer_ville?codeCommune="+code);
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setRequestMethod("DELETE");
			httpUrlConnection.setRequestProperty("Accept", "application/json");

			if (httpUrlConnection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + httpUrlConnection.getResponseCode());
			}
			httpUrlConnection.disconnect();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
}
