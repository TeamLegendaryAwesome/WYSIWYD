/*
New BSD License
Copyright (c) 2012, MyBar Team All rights reserved.
mybar@turbotorsk.se

Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice,
  this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.
 * Neither the name of the MyBar nor the names of its contributors may be 
  used to endorse or promote products derived from this software without
  specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY 
OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED 
OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package se.turbotorsk.mybar.controller;

import java.util.LinkedList;

import se.turbotorsk.mybar.model.Drink;
import se.turbotorsk.mybar.model.Ingredient;
import android.util.Log;

/**
 * This activity handles the Drinks.
 * 
 * @author Dag Fridén (<a href="mailto:dag@daysoft.se">email</a>)
 */
public final class DrinkManager {
	// Hide Utility Class Constructor.
	private DrinkManager() {
	};

	/**
	 * Get the drinks in the bar. Get all drinks Check if the is a drink that
	 * can me done with the ingredients in the bar.
	 */
	public static LinkedList<Drink> getMyBar() {
		LinkedList<Drink> myBar = new LinkedList<Drink>();
		LinkedList<Ingredient> myIngredientList = Controller.getMyIngredients();
		Log.d("myIngredientList", myIngredientList.toString());
		LinkedList<Drink> drinkList = Controller.getAllDrinks();
		Log.d("drinkList", drinkList.toString());
		Log.d("getFrist", drinkList.getFirst().getName());
		String[] ingredients;
		int ingredientID = 0;
		boolean found = false;
		try {// Iteration drink list.
			for (Drink drink : drinkList) {
				// Gets the ingredientes in the drink (in a array)
				ingredients = drink.getIngredient().split(";");
				// iterate the array with the ingrediences.
				for (int countID = 0; countID <= ingredients.length - 1; countID += 2) {
					// Gets the current ID.
					ingredientID = Integer.parseInt(ingredients[countID]);
					found = false; // Sets the found variable to false.
					// Iterate the MyIngredient list. If id found, stop the
					// search.
					for (Ingredient ingredient : myIngredientList) {
						if (ingredient.getId() == ingredientID) {
							Log.d("Found", ingredientID + "");
							found = true;
							break;
						}
					}
					// If the item can not be found the drink can not be done.
					// So lets stop
					// checking the current drink and move on to the next
					// one in the list.
					if (!found){
						break;
					}	
				}
				if (found) {
					myBar.add(drink);
				}
			}
		} catch (Exception e) {
			Log.d("Error:", e.toString());
			return myBar;
		}
		return myBar;
	}

}
