package api.utilities;

import java.util.ResourceBundle;

public class GetProp {

	public static ResourceBundle getURL(){
		ResourceBundle route=ResourceBundle.getBundle("routes");
		return  route;
	}
	
}
