package se.turbotorsk.mybar;

import se.turbotorsk.mybar.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Add_Ingredient_Activity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] coffeeChoices = this.getResources().getStringArray(R.array.drinkList);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coffeeChoices);
	    setListAdapter(adapter);  //Provided by the ListActivity extension
        

        
        
        
       /* SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) findViewById(R.id.searchView1);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        
     // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.textView1);
        // Get the string array
        String[] ingredients = getResources().getStringArray(R.array.ingredients);
        // Create the adapter and set it to the AutoCompleteTextView 
        ArrayAdapter<String> adapter = 
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ingredients);
        textView.setAdapter(adapter);
        
       */
    }

}
