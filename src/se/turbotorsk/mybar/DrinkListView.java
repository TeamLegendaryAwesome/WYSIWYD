package se.turbotorsk.mybar;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DrinkListView extends ListActivity{
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, DRINKS));
        getListView().setTextFilterEnabled(true);
        
    }
    
    static final String[] DRINKS = new String[]{
    	"Drink1",
    	"Drink2",
    	"Drink3",
    	"Drink4",
    	"Drink5",
    };
    
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    	Object o = this.getListAdapter().getItem(position);
    	String pen = o.toString();
    	Toast.makeText(this, "You have chosen the drink: " + " " + pen, Toast.LENGTH_LONG).show();
    }
}