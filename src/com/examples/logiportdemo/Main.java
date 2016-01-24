package com.examples.logiportdemo;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class Main extends Activity implements OnClickListener {
	String user;
	TextView uname;
	EditText note, done, remark, day;
	Button submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Bundle gotbasket = getIntent().getExtras();
		user = gotbasket.getString("key");
		uname = (TextView) findViewById(R.id.name);
		note = (EditText) findViewById(R.id.note);
		done = (EditText) findViewById(R.id.done);
		remark = (EditText) findViewById(R.id.remarks);
		day = (EditText) findViewById(R.id.days);
		submit = (Button) findViewById(R.id.submit);
		submit.setOnClickListener(this);
		uname.setText(user);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String nuname = uname.getText().toString();
		String nnote = note.getText().toString();
		Boolean d = Boolean.valueOf(done.getText().toString());
		String donee = Boolean.toString(d);
		// Toast.makeText(getApplicationContext(),Boolean.toString(d),Toast.LENGTH_LONG).show();
		String r = remark.getText().toString();
		String ndays = day.getText().toString();

		if ((nuname.isEmpty()) || (nnote.isEmpty()) || (d.toString().isEmpty())
				|| (r.isEmpty()) || (ndays.isEmpty())) {
			Dialog d2 = new Dialog(this);
			d2.setTitle("status");
			TextView tv = new TextView(this);
			tv.setText("failure\nplease fill the information correctly");
			d2.setContentView(tv);
			d2.show();
			Thread timer = new Thread() {
				public void run() {
					try {
						sleep(2000);

					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						finish();
						startActivity(getIntent());

					}
				}
			};
		} else {
			int indays = Integer.parseInt(ndays);
			ParseObject testObject = new ParseObject("TestObject");
			testObject.put("note", nnote);
			testObject.put("Done", donee);
			testObject.put("ndays", indays);
			testObject.put("REMARKS", r);
			testObject.saveInBackground(new SaveCallback() {

				@Override
				public void done(ParseException e) {

					// TODO Auto-generated method stub
					if (e != null) {
						Toast.makeText(getApplicationContext(), e.getMessage(),
								Toast.LENGTH_LONG).show();
					} else {

						Toast.makeText(getApplicationContext(),
								"sucessfull submition of data",
								Toast.LENGTH_LONG).show();

					}

				}

			});
			Dialog d1 = new Dialog(this);
			d1.setTitle("status");
			TextView tv = new TextView(this);
			tv.setText("succes");
			d1.setContentView(tv);
			d1.show();
			Thread timer = new Thread() {
				public void run() {
					try {
						sleep(2000);

					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						finish();
						startActivity(getIntent());

					}
				}
			};
			timer.start();

		}

	}

}
