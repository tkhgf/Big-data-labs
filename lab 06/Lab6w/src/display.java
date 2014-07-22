

//import com.example.gestureapp.GameActivity;
//import com.example.gestureapp.MainActivity;

import com.example.lab6w.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Build;

public class display extends Activity{

	//ImageView iv1,iv2;
	Button b1,b2,b3,b4,b5;
	EditText t1,t2;
	String url;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);
		//iv1=(ImageView)findViewById(R.id.imageView2);
		//iv2=(ImageView)findViewById(R.id.imageView3);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		/*b3=(Button)findViewById(R.id.button3);
		b4=(Button)findViewById(R.id.button4);
		b5=(Button)findViewById(R.id.button5);
		t1=(EditText)findViewById(R.id.editText1);
		t2=(EditText)findViewById(R.id.editText2);*/
		b1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String tablename = t1.getText().toString();
    	   		String column_families = t2.getText().toString();
        	//	url = "http://10.0.2.2:8080/HbaseWS/jaxrs/generic/hbaseCreate/"+tablename+"/"+column_families;
    	   		//url = "http://10.0.2.2:8080/HbaseWS/jaxrs/generic/hbaseCreate/testTable12/Location:Date:Humidity";
            	Intent intent = new Intent(display.this,PieChartBuilder.class); 
       			     intent.setData(Uri.parse(url));
       			startActivity(intent);

			}
			
		});
		b2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String tablename = t1.getText().toString();
    	   		String column_families = t2.getText().toString();
        		//url = "http://10.0.2.2:8080/HbaseWS/jaxrs/generic/hbaseInsert/"+tablename+"/E:-sens.txt/"+column_families;
    	   		Intent intent = new Intent(display.this,SensorValuesChart.class);	startActivity(intent);

			}
			
		});
			}



	

	

		
	}

	

