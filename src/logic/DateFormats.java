package logic;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateFormats {

	private final static SimpleDateFormat innerSimpleDateFormat = 
			new SimpleDateFormat("hh:mm:ss a" );
	private final static SimpleDateFormat outerSimpleDateFormat = 
			new SimpleDateFormat("dd MMM yyyy");
	private final static SimpleDateFormat fullSimpleDateFormat = 
			new SimpleDateFormat("dd MMM yyyy - hh:mm:ss a");

	//innerSimpleDateFormat
	public static String getinnerSimpleDateFormat(){
		return innerSimpleDateFormat.format(new Date()).toString();
	}
	
	//outerSimpleDateFormat
	public static String getouterSimpleDateFormat(){
		return outerSimpleDateFormat.format(new Date()).toString();
	}

	public static String getFullSimpleDateFormat(){
		return fullSimpleDateFormat.format(new Date()).toString();
	}

}
