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

package se.turbotorsk.mybar;

import se.turbotorsk.mybar.controller.Controller;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

/**
 * This activity handles the functions that add ingredients.
 */
public class AddIngredientActivity extends ListActivity {
	// Create a new IngredientAdapter object to dynamically
	// populate list views.
	private IngredientAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Initiate the adapter, send in the layout of choice and
		// also send in the LinkedList of Ingredients.
		adapter = new IngredientAdapter(this, R.layout.rowlayout,
				Controller.getAllIngredients());

		// Set the adapter that we just created.
		setListAdapter(adapter);

	}

	/**
	 * When clicking on an list item (ingredient) this method will add the
	 * ingredient to the MyBar-table in SQLite database.
	 */
	@Override
	public void onListItemClick(ListView parent, View v, int position, long id) {

		{
			// If the ingredient is not already added in the database,
			// add it.
			if (!(Controller.isInMyBar(adapter.getId(position)))) {
				Controller.addMyBarIngredient(adapter.getId(position));
				// Display a Toast that the ingredient has been added.
				Toast.makeText(AddIngredientActivity.this,
						adapter.getName(position) + " added",
						Toast.LENGTH_SHORT).show();
			}

			else {
				// If the ingredient is already added in the database,
				// display a Toast that lets the user know it is
				// already added.
				Toast.makeText(AddIngredientActivity.this,
						adapter.getName(position) + " is already added!",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

}
