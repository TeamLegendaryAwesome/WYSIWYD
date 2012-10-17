/*
New BSD License
Copyright (c) 2012, MyBar Team All rights reserved.
mybar@turbotorsk.se

Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the following conditions are met:
�	Redistributions of source code must retain the above copyright notice,
 	this list of conditions and the following disclaimer.
�	Redistributions in binary form must reproduce the above copyright notice,
 	this list of conditions and the following disclaimer in the documentation
 	and/or other materials provided with the distribution.
�	Neither the name of the MyBar nor the names of its contributors may be 
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

import se.turbotorsk.mybar.model.Data;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 * This activity handles the Collection. The collection is the third tab in the
 * application and should show all of the drinks from our database. If the user
 * wants to see information about the drink they could press on the drink and
 * get to another activity, the ViewDrinkActivity.
 * 
 * The activity is built on the custom listview xml file rowlayout.xml. This
 * file can be found where all of the other layout files are.
 */
public class CollectionActivity extends ListActivity {

	DrinkAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		adapter = new DrinkAdapter(this, R.layout.rowlayout,
				Data.getAllDrinks());

		// Sets the adapter that we just did.
		setListAdapter(adapter);
	}

	/**
	 * This method handles what happens when pressing a item in the list.
	 */

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// String item = (String) getListAdapter().getItem(position);
		// We will replace this to start another activity instead. Dont know how
		// to do that yet.
		// Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
		Intent intent = new Intent(this, ViewDrinkActivity.class);
		intent.putExtra("drinkname", adapter.getDrinkName(position));
		intent.putExtra("rating", adapter.getRating(position));
		intent.putExtra("ingredients", adapter.getIngredients(position));
		intent.putExtra("descrip", adapter.getDescrip(position));
		intent.putExtra("url", adapter.getUrl(position));
		intent.putExtra("id", adapter.getId(position));
		startActivity(intent);
	}
}
