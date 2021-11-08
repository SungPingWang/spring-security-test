package com.lui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityTest2ApplicationTests {

	@Test
	void contextLoads() {
		Calendar currentTimeNow = Calendar.getInstance();
    	System.out.println("Current time now : " + currentTimeNow.getTime());
    	currentTimeNow.add(Calendar.MINUTE, 5);
    	Date tenMinsFromNow = currentTimeNow.getTime();
    	System.out.println("Converted String: " + tenMinsFromNow.toString()); 
    	System.out.println("Converted String: " + tenMinsFromNow.toString()); 
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        String strDate = dateFormat.format(tenMinsFromNow);  
        System.out.println("Converted String: " + strDate); 
	}

}
