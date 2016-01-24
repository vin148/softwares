package com.examples.logiportdemo;

import android.app.Application;

import com.parse.Parse;

public class Demoapplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Parse.initialize(this, "xCZkxJAECrzIstFxSYKYAQUHllOdGU1RcRIMefGR",
				"5uzD5OvR87TZDSvFtMfhvzfKsf5GsOVgPEefRGfn");
	}
}
