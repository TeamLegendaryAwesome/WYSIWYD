package se.turbotorsk.mybar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkAdapter extends ArrayAdapter<Drink>{
	Context context;
	int layoutResourceId;
	Drink data[] = null;
	
	public DrinkAdapter(Context context, int layoutResourceId, Drink[] data){
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}
	
	  @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View row = convertView;
	        DrinkHolder holder = null;
	        
	        if(row == null)
	        {
	            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	            row = inflater.inflate(layoutResourceId, parent, false);
	            
	            holder = new DrinkHolder();
	            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
	            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
	            
	            row.setTag(holder);
	        }
	        else
	        {
	            holder = (DrinkHolder)row.getTag();
	        }
	        
	        Drink drink = data[position];
	        holder.txtTitle.setText(drink.title);
	        holder.imgIcon.setImageResource(drink.icon);
	        
	        return row;
	    }
	    
	    static class DrinkHolder
	    {
	        ImageView imgIcon;
	        TextView txtTitle;
	    }
}
