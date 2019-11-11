package mybeans;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.lang.StringBuilder;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class ZipBean implements Serializable {
	private String zipcode;
	private String rawJson;
	private String address;
	HttpURLConnection  urlConn;
	BufferedReader reader;
	
	public ZipBean() {
		zipcode = null;
		rawJson = null;
		System.setProperty("proxySet", "true");
		System.setProperty("proxyHost", "172.17.0.2");
		System.setProperty("proxyPort", "80");
		System.out.println("114514");
	}
	public void setZipcode(String zip){
		zipcode = zip;
	}
	public String getZipcode(){
		return zipcode;
	}
	public String getAddress() {
		try {
			urlConn = (HttpURLConnection) new URL("http://zipcloud.ibsnet.co.jp/api/search?zipcode=" + zipcode).openConnection();
			urlConn.setRequestMethod("GET");
			urlConn.connect();
			InputStream in = urlConn.getInputStream();
			
	    	reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
	    	
			StringBuilder output = new StringBuilder();
			String line;
	
			while ((line = reader.readLine()) != null) {
				output.append(line);
			}
			rawJson = output.toString();
			System.out.println(rawJson);
			
			JSONObject obj = new JSONObject(rawJson);
			Map<String, Object> map = new HashMap<>();
	        for(Object key : obj.keySet()) {
	            map.put((String) key, obj.get((String) key));
	        }
	        System.out.println(map);
			return "asdasdsa";
		}
		catch(IOException e) {
			rawJson = "error";
			return "error";
		}catch(Exception e) {
			rawJson = "error";
			e.printStackTrace();
			return "error";
		}finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (urlConn != null) {
					urlConn.disconnect();
				}
			} catch (IOException e) {
					rawJson = "error";
					e.printStackTrace();
					return "error";
				}
		}
	}
}