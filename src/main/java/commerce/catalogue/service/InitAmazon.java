/**
 * Title:        commerce
 * Description:  Class for e-commerce
 * Company:      IUT Laval - Université du Maine
 * @author  A. Corbière
 */
package commerce.catalogue.service;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.zeloon.deezer.client.DeezerClient;
import com.zeloon.deezer.domain.Albums;
import com.zeloon.deezer.domain.Artists;
import com.zeloon.deezer.domain.Tracks;
import com.zeloon.deezer.domain.internal.AlbumId;
import com.zeloon.deezer.domain.internal.ArtistId;
import com.zeloon.deezer.domain.internal.search.SearchArtist;

import amazon.apaIO.ApaiIO;
import amazon.apaIO.configuration.GenericConfiguration;
import amazon.apaIO.operations.Search;
import commerce.catalogue.domaine.modele.Bijou;
import commerce.catalogue.domaine.modele.Film;
import commerce.catalogue.domaine.modele.Livre;
import commerce.catalogue.domaine.modele.Musique;
import commerce.catalogue.domaine.modele.Piste;

public class InitAmazon {

	private CatalogueManager catalogueManager ;

	public InitAmazon(CatalogueManager catalogueManager) {
		this.catalogueManager = catalogueManager ;
	}

	public void init() {
		// Lien pour obtenir la clé d'accès et la clé secrète auprès d'Amazon.
		// https://portal.aws.amazon.com/gp/aws/securityCredentials
		/*
		 * Utiliser l'un des points d'accès en fonction du type d'article/prix/...
		 * 
		 *      US: ecs.amazonaws.com 
		 *      CA: ecs.amazonaws.ca 
		 *      UK: ecs.amazonaws.co.uk 
		 *      DE: ecs.amazonaws.de 
		 *      FR: ecs.amazonaws.fr 
		 *      JP: ecs.amazonaws.jp
		 */
		//String ENDPOINT = "ecs.amazonaws.fr" ; 
		String ENDPOINT = "odp.tuxfamily.org";
		String AWS_ACCESS_KEY_ID = "YOUR_ACCESS_KEY_ID_HERE";
		String AWS_SECRET_KEY = "YOUR_SECRET_KEY_HERE";

		GenericConfiguration conf = new GenericConfiguration();
		conf.setAccessKey(AWS_ACCESS_KEY_ID) ;
		conf.setSecretKey(AWS_SECRET_KEY);
		conf.setEndPoint(ENDPOINT);


		ApaiIO apaiIO = new ApaiIO();
		apaiIO.setConfiguration(conf) ;
		Search search = new Search();
		search.setCategory("All");
		search.setResponseGroup("Offers,ItemAttributes,Images") ;
		String keywords = "Lord of the Rings" ;
		search.setKeywords(keywords);

		Livre livre ;
		Musique musique ;
		Piste piste ;
		Film film;
		Bijou bijou;
		SAXBuilder builder = new SAXBuilder();
		builder.setIgnoringElementContentWhitespace(true);
		Document document ;
		Element racine = null ;
		Namespace espaceNom = null ;

		try {
			document = builder.build(new StringReader(apaiIO.runOperation(search)));
			racine = document.getRootElement() ;
			
			try {
				FileWriter writer = new FileWriter("amazonResponse.xml");
				XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat()) ;
				outputter.output(racine, writer) ;
			}
			catch (IOException e) {
				e.printStackTrace() ;
			}

			espaceNom = Namespace.getNamespace(racine.getNamespaceURI());

			if (espaceNom != null && !racine.getName().equals("ItemSearchErrorResponse")) {
				Element items = racine.getChild("Items",espaceNom) ;
				Iterator<Element> itemIterator = items.getChildren("Item",espaceNom).iterator() ;
				Element item ;
				Element itemAttributes ;
				Element image ;
				int i = 0 ;
				while (itemIterator.hasNext() && i != 10) {
					item = itemIterator.next() ;
					itemAttributes = item.getChild("ItemAttributes",espaceNom);
					image = item.getChild("LargeImage",espaceNom);
					try 
					{
						if (itemAttributes.getChild("ProductGroup",espaceNom).getText().equals("Music")) {
							musique = new Musique();
							musique.setRefArticle(item.getChild("ASIN",espaceNom).getText());
							musique.setTitre(itemAttributes.getChild("Title",espaceNom).getText());
							musique.setEAN(itemAttributes.getChild("EAN",espaceNom).getText());
							musique.setImage(image.getChild("URL",espaceNom).getText());
							musique.setPrix(Integer.parseInt(item.getChild("OfferSummary",espaceNom).getChild("LowestNewPrice",espaceNom).getChild("Amount",espaceNom).getText())/100.0);
							musique.setDisponibilite(1);

							DeezerClient deezerClient = new DeezerClient();
							Artists artists = deezerClient.search(new SearchArtist(keywords)) ;
							Albums albums = deezerClient.getAlbums(new ArtistId(artists.getData().get(0).getId()));
							int j = 0 ;
							Boolean sortir = (j==albums.getData().size()) ;
							Boolean albumTrouve = false ;
							while(!sortir) {
								String titreDeezer = albums.getData().get(j).getTitle().toLowerCase().replaceAll(" ", "") ;
								String titreAmazon = musique.getTitre().toLowerCase().replaceAll(" ", "") ;
								titreDeezer.replaceAll("-", "") ;
								titreAmazon.replaceAll("-", "") ;
								albumTrouve = titreDeezer.equals(titreAmazon) ;
								if (titreAmazon.length() > titreDeezer.length())
									albumTrouve = albumTrouve || (titreAmazon.indexOf(titreDeezer)>=0) ;
								if (titreDeezer.length() > titreAmazon.length())
									albumTrouve = albumTrouve || (titreDeezer.indexOf(titreAmazon)>=0) ;

								j++ ;
								sortir = albumTrouve || (j==albums.getData().size()) ;
							}
							if (albumTrouve) {
								Tracks tracks = deezerClient.getTracks(new AlbumId(albums.getData().get(j-1).getId()));
								j = 0 ;
								List<Piste> listePistes = new ArrayList<Piste>() ;
								while(j<tracks.getData().size()) {
									piste = new Piste() ;
									piste.setTitre(tracks.getData().get(j).getTitle());
									piste.setUrl(tracks.getData().get(j).getPreview());
									catalogueManager.soumettrePiste(piste);
									listePistes.add(piste) ;
									j++;
								}
								if (tracks.getData().size() != 0)
									musique.setPistes(listePistes);
							}
							catalogueManager.soumettreArticle(musique) ;
							i ++ ;
						}
						else if(itemAttributes.getChild("ProductGroup", espaceNom).getText().equals("Book")) {
							livre = new Livre();
							livre.setRefArticle(item.getChild("ASIN", espaceNom).getText());
							livre.setTitre(itemAttributes.getChild("Feature", espaceNom).getText());
							livre.setAuteur(itemAttributes.getChild("Author", espaceNom).getText());
							livre.setISBN(itemAttributes.getChild("ISBN", espaceNom).getText());
							livre.setImage(image.getChild("URL",espaceNom).getText());
							livre.setNbPages(Integer.parseInt(itemAttributes.getChild("NumberOfPages", espaceNom).getText()));
							livre.setLangue(itemAttributes.getChild("Languages", espaceNom).getText());
							livre.setDateDeParution(itemAttributes.getChild("PublicationDate", espaceNom).getText());
							livre.setPrix(Integer.parseInt(item.getChild("OfferSummary", espaceNom).getChild("LowestNewPrice", espaceNom).getChild("Amount", espaceNom).getText()) / 100.0);
							livre.setDisponibilite(1);
							catalogueManager.soumettreArticle(livre);
						}
						else if(itemAttributes.getChild("ProductGroup", espaceNom).getText().equals("Movie") || itemAttributes.getChild("ProductGroup", espaceNom).getText().equals("DVD")) {
							film = new Film();
							film.setRefArticle(item.getChild("ASIN", espaceNom).getText());
							film.setTitre(itemAttributes.getChild("Title", espaceNom).getText());
							film.setImage(image.getChild("URL",espaceNom).getText());
							film.setLangue(itemAttributes.getChild("Languages", espaceNom).getText());
							film.setPrix(Integer.parseInt(item.getChild("OfferSummary", espaceNom).getChild("LowestNewPrice", espaceNom).getChild("Amount", espaceNom).getText()) / 100.0);
							film.setDisponibilite(1);
							film.setActeur(itemAttributes.getChild("Actor", espaceNom).getText());
							film.setDirecteur(itemAttributes.getChild("Director", espaceNom).getText());
							try {
								film.setGenre(itemAttributes.getChild("Genre", espaceNom).getText());
							} catch(NullPointerException e) {
								film.setGenre("None");
							}
							film.setDateDeSortie(itemAttributes.getChild("ReleaseDate", espaceNom).getText());
							film.setStudio(itemAttributes.getChild("Studio", espaceNom).getText());
							film.setDuree(Integer.parseInt(itemAttributes.getChild("RunningTime", espaceNom).getText()));
							catalogueManager.soumettreArticle(film);
						}
						else if(itemAttributes.getChild("ProductGroup", espaceNom).getText().equals("Jewelry")) {
							bijou = new Bijou();
							bijou.setRefArticle(item.getChild("ASIN", espaceNom).getText());
							bijou.setTitre(itemAttributes.getChild("Title", espaceNom).getText());
							bijou.setImage(image.getChild("URL",espaceNom).getText());
							bijou.setLangue("None");
							try {
								bijou.setPrix(Integer.parseInt(item.getChild("OfferSummary", espaceNom).getChild("LowestNewPrice", espaceNom).getChild("Amount", espaceNom).getText()) / 100.0);
							} catch(NullPointerException e) {
								bijou.setPrix(0);
							}
							bijou.setDisponibilite(1);
							bijou.setMarque(itemAttributes.getChild("Brand", espaceNom).getText());
							bijou.setCategorie(itemAttributes.getChild("Department", espaceNom).getText());
							bijou.setConstructeur(itemAttributes.getChild("Manufacturer", espaceNom).getText());
							catalogueManager.soumettreArticle(bijou);
						}
						
					}
					catch (NullPointerException e) {
						e.printStackTrace() ;
					}
					catch (Exception e) {
						e.printStackTrace() ;
					}
				}
			}
			else {
				try { 
					livre = new Livre();
					livre.setRefArticle("1141555677821");
					livre.setTitre("Le seigneur des anneaux");
					livre.setAuteur("J.R.R. TOLKIEN");
					livre.setISBN("2070612880");
					livre.setImage("61PEbZ1QDfL-300x300.jpg");
					livre.setNbPages(736);
					livre.setLangue("fr");
					livre.setDateDeParution("23/08/07");
					livre.setPrix("8.50");
					livre.setDisponibilite("1");
					catalogueManager.soumettreArticle(livre);
					livre = new Livre();
					livre.setRefArticle("1141555897821");
					livre.setTitre("Un paradis trompeur");
					livre.setAuteur("Henning Mankell");
					livre.setISBN("275784797X");
					livre.setImage("61NfUluHsML-300x300.jpg");
					livre.setNbPages(400);
					livre.setLangue("fr");
					livre.setDateDeParution("09/10/14");
					livre.setPrix("7.90");
					livre.setDisponibilite("1");
					catalogueManager.soumettreArticle(livre);
					livre = new Livre();
					livre.setRefArticle("1141556299459");
					livre.setTitre("Dôme tome 1");
					livre.setAuteur("Stephen King");
					livre.setISBN("2212110685");
					livre.setImage("61sGE8edJmL-300x300.jpg");
					livre.setNbPages(840);
					livre.setLangue("fr");
					livre.setDateDeParution("06/03/13");
					livre.setPrix("8.90");
					livre.setDisponibilite("1");
					catalogueManager.soumettreArticle(livre);
				}
				catch (Exception e) {
					e.printStackTrace() ;
				}
			}
		}
		catch (JDOMException e) {
			e.printStackTrace() ;
		}
		catch (IOException e) {
			e.printStackTrace() ;
		}
	}
}
