package se.turbotorsk.mybar;

import android.content.Context;
import android.widget.ArrayAdapter;

public class DrinkListAdapter extends ArrayAdapter<String>{
	private final Context context;
	private final String[] values;
	
	public DrinkListAdapter(Context context, String[] values) {
		super(context, R.layout.drinklistlayout, values);
		this.context = context;
		this.values = values;
	}

}
