/**
 * A drink object contains information about a drink.
 * 
 * @author Dag Frid�n
 * 
 */

/**
 * @copywright Dag Frid�n
 * 
 */



package se.turbotorsk.mybar.model;

import java.util.LinkedList;

public class Data {

	private final boolean SQLITE = false;
	private final boolean EDATA = false;
	private final boolean FAKE = true;
	private Drink exampleDrink1 = null, exampleDrink2 = null;
	private LinkedList<Drink> fakeDrinkList = null;
	//private XXXXXXXX sqlite;
	//private XXXXXXXX jsonParser;
	//private XXXXXXXX httpGet;
	
	public Data()
	{
		if(SQLITE); //creates SQlite objects.
		if(EDATA);
		if(FAKE){
			fakeDrinkList = new LinkedList<Drink>();
			fakeDrinkList.add(exampleDrink1 = new Drink(1, "testDrink1", "http://www.google.se", 1,"ingredient1", "description1",3));
			fakeDrinkList.add(exampleDrink2 = new Drink(2, "testDrink2", "http://www.google.se", 2,"ingredient2", "description2",2));
		}
		
	}
	
	/**
	 * This method return a preview (thumbnail, name, rating, description) of the information in the data source.
	 * @return DrinkPreview object.
	 */
	public LinkedList<Drink> getDrinksPreview()
	{
		if(SQLITE);
		if(EDATA);
		if(FAKE);
		
		return null; 
	}
	
	public String[] getDrinkNameArray()
	{
		String[] drinks; 
		if(SQLITE);
		if(EDATA);
		if(FAKE){
			drinks = new String[5];
			drinks[0] ="Cola och tonic";
			drinks[1] ="Cola lime";
			drinks[2] ="Cola gin";
			drinks[3] ="Cola citrus";
			drinks[4] ="Cola hallon";
			drinks[5] ="Cola light";
			drinks[6] ="Cola sun";
			drinks[7] ="Cola looka";
			drinks[8] ="Cola with ice";
			drinks[9] ="Rom and cocke";
			
			return drinks;
		}
		return null; 
	}
	
	/**
	 * This method return a Drink (thumbnail, name, rating, decprition) 
	 * @param ID
	 * @return
	 */
	public Drink getDrinkByID(int ID)
	{
		if(SQLITE);
		if(EDATA);
		if(FAKE);
		
		return null; 	
	}
	
	/**
	 * Returns a LinkedList with the current users favorit drinks. 
	 * @return
	 */
	public LinkedList<Drink> getFavoritDrinks()
	{
		if(SQLITE);
		if(EDATA);
		if(FAKE){ return fakeDrinkList; }
		
		return null; 
	}
	
	/**
	 * Search for Drinks in the database.
	 * @param searchName
	 * @return A LinkedList with the Drink containing the searchName string
	 */
	public LinkedList<Drink> searchDrinkName(String searchName)
	{
		if(FAKE){ return fakeDrinkList; }
		return null;
	}
	/**
	 * Search for ingredients in the database.
	 * @param searchName
	 * @return A LinkedList with the ingredients containing the searchName string
	 */
	public LinkedList<Drink> searchIngredientName(String searchName)
	{
		return null;
	}
}
