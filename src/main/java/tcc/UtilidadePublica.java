package tcc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilidadePublica {
	
	public static Date formatarData(Date data) {
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");    
		Date data1 = new Date();
		
		String str = fmt.format(data); 
		
		try {
			data1 = fmt.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data1;
	}

}
