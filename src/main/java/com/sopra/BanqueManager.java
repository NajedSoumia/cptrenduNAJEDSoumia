package com.sopra;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class BanqueManager {

	public static void main(String[] args) throws JDOMException {
		
		List<CompteBancaire> banque;
		System.out.println("Welcome to Our Bank \n");
		
		//1-faire la lecture des comptes bancaires et sauvegarde dans un fichier xml
		//System.out.println("\t 1)Lecture des comptes ");
		
		// saisieComptes();
		
		//2-affichage des comptes à partir de la base de données
		 
		//System.out.println("\t 1)Affichage des comptes ");
		banque = listComptesXML();
		
		
		// 3-Ajout d'un nouveau compte
		
		//CompteBancaire newAccount = new CompteBancaire(111111, "aaaa", 6000, "Epargne", LocalDateTime.now());
		//banque.add(newAccount);
		
		//addNewAccountToXML("banque.xml",banque);
		
		// 4-ajout d'un nouvel attribut
		
		//		addNewAttribute("banque.xml",banque,"typeBanque","Physique");
		//		addNewAttribute("banque.xml",banque,"ville","Rabat");
		// 5-suppression d'un compte	
		supprimerCompte("banque.xml", banque, 456);

			}
			
			private static void supprimerCompte(String pathXmlFile, List<CompteBancaire> banque, int i) {
				try {
			        SAXBuilder builder = new SAXBuilder();
			        File xmlFile = new File(pathXmlFile);
			        Document jdomDoc = (Document) builder.build(xmlFile);
			        Element root = jdomDoc.getRootElement();

			        List<Element> listOfcb = root.getChildren("CompteBancaire");

			        // Parcours des comptes pour trouver celui avec le numéro de compte spécifié
			        for (Element cb : listOfcb) {
			            int numero = Integer.parseInt(cb.getChildText("numero"));
			            if (numero == i) {
			                // Suppression du compte de la liste et du fichier XML
			                listOfcb.remove(cb);
			                break; 
			            }
			        }

			        // Mettre à jour le fichier XML avec la liste mise à jour
			        XMLOutputter xmlOutput = new XMLOutputter();
			        xmlOutput.setFormat(Format.getPrettyFormat());
			        xmlOutput.output(jdomDoc, new FileWriter("banque.xml"));
			        System.out.println("Compte supprimé avec succès!");
			    } catch (IOException | JDOMException e) {
			        e.printStackTrace();
			    }
			}
	

			public static void addNewAttribute(String pathXmlFile, List<CompteBancaire> banque, String nomAttribut, String valeurAttribut) throws JDOMException
			{
				

				try {
					SAXBuilder builder = new SAXBuilder();
					File xmlFile = new File(pathXmlFile);
				    
		            Document jdomDoc = (Document) builder.build(xmlFile);
		           
		           Element root = jdomDoc.getRootElement();
		           
		           List <Element> listOfCB = root.getChildren("CompteBancaire");
		            
		            
		            for(Element cb:listOfCB)
		            {
		              
		        
		              
		              cb.setAttribute(nomAttribut,valeurAttribut);
		            }
		            
		            //XmlOutputter : objet pour ecrire dans un fichier .xml
		            XMLOutputter xmlOutput = new XMLOutputter();
		            xmlOutput.setFormat(Format.getPrettyFormat());
		            xmlOutput.output(jdomDoc, new FileWriter("banque.xml"));
		            System.out.println("File Saved!");
		       }
			 catch (IOException io) {
		            System.out.println(io.getMessage());
		        }
			}
			

	
	
	public static void addNewAccountToXML(String pathXmlFile, List<CompteBancaire> banque)
	{
	        
		try {
		    // création de l'element racine en mémoire  de type Document
            Document doc = new Document();
            // création de l'élément racine : CompteBancaires
            doc.setRootElement(new Element("CompteBancaires"));
            for(CompteBancaire cb:banque)
            {
              doc.getRootElement().addContent(createCompteBancaireXMLElement(cb));
            }
            
            //XmlOutputter : objet pour ecrire dans un fichier .xml
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("banque.xml"));
            System.out.println("File Saved!");
       }
	 catch (IOException io) {
            System.out.println(io.getMessage());
        }
	}
	
	
	private static Element createCompteBancaireXMLElement(CompteBancaire compteBancaire) {
        Element cbElement = new Element("CompteBancaire");
        
        cbElement.addContent(new Element("numero").setText(""+compteBancaire.getNumCompte()));
        cbElement.addContent(new Element("proprietaire").setText(compteBancaire.getProprietaire()));
        cbElement.addContent(new Element("type").setText(""+compteBancaire.getTypeCompte()));
        cbElement.addContent(new Element("dateCreation").setText(""+compteBancaire.getDateCreate()));
        cbElement.addContent(new Element("solde").setText(""+compteBancaire.getSolde()));
        return cbElement;
    }
	
	public static List<CompteBancaire> listComptesXML()
	{
		//System.out.println("Parsing...");
		final String fileName = "banque.xml";
		 List <CompteBancaire> cbList = new ArrayList<CompteBancaire>();
        try {
            // we can create JDOM Document from DOM, SAX and STAX Parser Builder classes
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(fileName);
            Document jdomDoc = (Document) builder.build(xmlFile);
            
            
            Element root = jdomDoc.getRootElement();
            
            List < Element > listOfAccounts = root.getChildren("CompteBancaire");
            
           
            
            for(Element cbElement: listOfAccounts) {
                CompteBancaire cb = new CompteBancaire();
                
                try {
                cb.setNumCompte(Integer.parseInt(cbElement.getChildText("numero")));
                cb.setProprietaire(cbElement.getChildText("proprietaire"));
                cb.setTypeCompte(cbElement.getChildText("type"));
                cb.setSolde(Double.parseDouble(cbElement.getChildText("solde")));
                cb.setDateCreate(LocalDateTime.parse(cbElement.getChildText("dateCreation")));
                //System.out.println("CB:" + cb);
                }
                catch(NumberFormatException ex)
                {
                	System.out.println(ex.getMessage());
                }
            
                cbList.add(cb);
            }
           
            System.out.println(cbList);
           

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cbList;

	}
	public static void saisieComptes()
	{
		Scanner scanner = new Scanner(System.in);
		
		// faire la saisie de deux comptes
		
		
		 //System.out.println(newAccount(scanner,"Courant"));
		 CompteBancaire cbEpargne = newAccount(scanner,"Courant");
		 CompteBancaire cbCourant = newAccount(scanner,"Epargne");
		 
		 System.out.println("****");
		 System.out.println(cbCourant);
		 System.out.println(cbEpargne);
		//faire la sauvegarde dans un fichier xml
				 try {
					    // création de l'element racine en mémoire  de type Document
			            Document doc = new Document();
			            // création de l'élément racine : CompteBancaires
			            doc.setRootElement(new Element("CompteBancaires"));
			            
			            doc.getRootElement().addContent(createCompteBancaireXMLElement(cbCourant));
			            doc.getRootElement().addContent(createCompteBancaireXMLElement(cbEpargne));
			            
			            //XmlOutputter : objet pour ecrire dans un fichier .xml
			            XMLOutputter xmlOutput = new XMLOutputter();
			            xmlOutput.setFormat(Format.getPrettyFormat());
			            xmlOutput.output(doc, new FileWriter("banque.xml"));
			            System.out.println("File Saved!");
			       }
				 catch (IOException io) {
			            System.out.println(io.getMessage());
			        }
	}
	
	public static CompteBancaire newAccount(Scanner scanner, String typeCompte) {
		
		CompteBancaire cb = new CompteBancaire();
		
		System.out.println("Donner le numéro de compte");
		int numCompte = scanner.nextInt();
		cb.setNumCompte(numCompte);
		
		System.out.println("Donner le nom du proprietaire du compte");
		String nom = scanner.next();
		cb.setProprietaire(nom);

		System.out.println("Donner le solde initial");
		double solde = scanner.nextDouble();
		cb.setSolde(solde);
		
		//System.out.println("Donner le type de compte : Epargne ou Courant");
		//String typeCompte = scanner.next();
		cb.setTypeCompte(typeCompte);
		
		cb.setDateCreate(LocalDateTime.now());
		
		return cb;
		
	}

}
