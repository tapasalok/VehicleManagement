package com.dpautomations.vehiclemanagement.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GettingDateTime {
	private static GettingDateTime gettingDateTime;

	private GettingDateTime() {
		// TODO Auto-generated constructor stub
	}

	public static GettingDateTime getInstance() {
		// TODO Auto-generated method stub
		if (gettingDateTime == null) {
			gettingDateTime = new GettingDateTime();
		}
		return gettingDateTime;
	}

	 /**
     * get datetime
     * */
    public String getDate() {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    public String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
