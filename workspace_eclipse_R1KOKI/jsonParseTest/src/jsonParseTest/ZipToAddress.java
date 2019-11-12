package jsonParseTest;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.lang.StringBuilder;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.jackson.*;

public class ZipToAddress {

	public static void main(String[] args) {
		System.setProperty("proxySet", "true");
		System.setProperty("proxyHost", "172.17.0.2");
		System.setProperty("proxyPort", "80");
		Sample s = new Sample();
		s.runSample();
	}

}

/**
 * HttpURLConnectionのサンプル
 */
class Sample {

	void runSample() {

		String zipcode = "0290202";
		String strUrl = "http://zipcloud.ibsnet.co.jp/api/search?zipcode=" + zipcode;
		HttpURLConnection  urlConn = null;
		InputStream in = null;
		BufferedReader reader = null;
		String rawJson = "";

		try {
			//接続するURLを指定する
			URL url = new URL(strUrl);
			
			//コネクションを取得する
			urlConn = (HttpURLConnection) url.openConnection();
			
			urlConn.setRequestMethod("GET");
//			urlConn.setRequestMethod("POST");

			urlConn.connect();
			
			int status = urlConn.getResponseCode();
			
			System.out.println("HTTPステータス:" + status);
			
		    if (status == HttpURLConnection.HTTP_OK) {

				in = urlConn.getInputStream();
				
		    	reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
		    	
				StringBuilder output = new StringBuilder();
				String line;

				while ((line = reader.readLine()) != null) {
					output.append(line);
				}
				rawJson = output.toString();
				System.out.println("contents:\n" + rawJson);
				ObjectMapper mapper = new ObjectMapper();
		        Model model = mapper.readValue(rawJson, Model.class);
		        System.out.println(model.results.get(0).address1 + model.results.get(0).address2 + model.results.get(0).address3);
		      }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (urlConn != null) {
					urlConn.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}