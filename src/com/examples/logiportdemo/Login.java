package com.examples.logiportdemo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity implements View.OnClickListener {
	int flag;
	String unam, pass;
	EditText uname, pword;
	Button loginbt;
	SharedPreferences sharedpref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		pword = (EditText) findViewById(R.id.password);
		uname = (EditText) findViewById(R.id.username);
		loginbt = (Button) findViewById(R.id.loginn);
		flag = 2;
		loginbt.setOnClickListener(this);
		sharedpref = PreferenceManager.getDefaultSharedPreferences(this);
		flag = sharedpref.getInt("fval", 2);
		if (flag == 3) {
			unam = sharedpref.getString("name", "could not load");
			Bundle basket2 = new Bundle();
			basket2.putString("key", unam);
			Intent i = new Intent(Login.this, Main.class);
			i.putExtras(basket2);
			startActivity(i);
			finish();
		} else {
			SharedPreferences.Editor editor = sharedpref.edit();
			editor.putString("name", "task");
			editor.putString("password", "logitask");
			editor.commit();

		}

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.loginn:
			// TODO Auto-generated method stub
			String newuser = uname.getText().toString().trim();
			String newpass = pword.getText().toString().trim();
			unam = sharedpref.getString("name", "could not load");
			pass = sharedpref.getString("password", "sorry");
			if ((!newuser.isEmpty()) && (!newpass.isEmpty())
					&& (newuser.equals(unam)) && (newpass.equals(pass))) {
				SharedPreferences.Editor editor1 = sharedpref.edit();
				editor1.putInt("fval", 3);
				editor1.commit();
				Bundle basket1 = new Bundle();
				basket1.putString("key", unam);
				Intent i = new Intent(Login.this, Main.class);
				i.putExtras(basket1);
				startActivity(i);

			} else {
				Dialog d2 = new Dialog(this);
				d2.setTitle("status");
				TextView tv = new TextView(this);
				tv.setText("Unable to Login\n enter the correct username and password");
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
				timer.start();
			}
			break;
		}
	}
}