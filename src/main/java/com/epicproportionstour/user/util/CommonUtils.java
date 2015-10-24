package com.epicproportionstour.user.util;

import java.util.Date;
import java.text.SimpleDateFormat;

public class CommonUtils {
	
	public String loginDateTime()
	{
		Date dt = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println(ft.format(dt).toString());
		return ft.format(dt).toString();
	}

}
