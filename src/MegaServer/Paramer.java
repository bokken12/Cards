package MegaServer;

public class Paramer {
	String params = "";
	int param = 0;
	public Paramer(String string){
		params = string;
	}
	public String nextParam(){
		param++;
		try{
			return getParam(param);
		} catch(IllegalArgumentException e){
			param = 1;
			try{
				return getParam(param);
			} catch(IllegalArgumentException i){
				return " ";
			}
		}
	}
	public String getParam(int integer){
		return getParam(integer, params);
	}
	public String getParam(int integer, String string){
		if(integer <= 1){
			return string;
		}
		if(string.contains(" ")){
			return getParam(integer - 1, string.substring(string.indexOf(' ')));
		} else {
			throw new IllegalArgumentException("There are not that many parameters");
		}
	}
}
