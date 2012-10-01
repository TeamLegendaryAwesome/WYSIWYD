package se.turbotorsk.mybar;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class Testactivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testactivity);    
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_testactivity, menu);
        return true;
    }
}
