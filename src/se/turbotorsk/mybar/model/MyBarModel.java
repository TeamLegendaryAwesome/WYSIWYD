package se.turbotorsk.mybar.model; 
//import se.turbotorsk.mybar.database.*;
import java.util.LinkedList;

public class MyBarModel {
	
	private LinkedList<Drink> dList = new LinkedList<Drink>();
	
	public MyBarModel(){
		dList = new LinkedList<Drink>();
		dList.add(new Drink(1, "Dags Special1", "1001000102", 1,"vatten;1;mjölk;2;", "En God drink från Varberg", 5));
		dList.add(new Drink(2, "Dags Special2", "1001000102", 2,"vatten;1;mjölk;2;", "En God drink från Borås", 3));
		dList.add(new Drink(3, "Dags Special3", "1001000102", 3,"vatten;1;mjölk;2;", "En God drink från Göteborg", 4));
	}
	
	public LinkedList<Drink>getMenuList()
	{	
		return dList;
	}
	
	public Drink getDrinkById(int ID)
	{    
		return dList.get(ID);
	}
	
}
