package uk.com.holinight;

import android.content.Context;
import android.net.ConnectivityManager;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class HttpConnectionManager {

	private HttpConnectionManager() {
		super();
	}

	public static HttpClient httpClient = null;

	public static HttpClient getClient() {

		if (httpClient == null) {

			HttpParams parameters = new BasicHttpParams();
			HttpConnectionParams.setStaleCheckingEnabled(parameters, false);
			HttpConnectionParams.setConnectionTimeout(parameters, 200000);
			HttpConnectionParams.setSoTimeout(parameters, 200000);

			httpClient = new DefaultHttpClient(parameters);
			ClientConnectionManager mgr = httpClient.getConnectionManager();
			HttpParams params = httpClient.getParams();
			httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(
					params,

					mgr.getSchemeRegistry()), params);
		}

		return httpClient;

	}
	public static boolean checkInternetConnection(Context context) {

		ConnectivityManager conMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (conMgr.getActiveNetworkInfo() != null

				&& conMgr.getActiveNetworkInfo().isAvailable()

				&& conMgr.getActiveNetworkInfo().isConnected()) {

			return true;

		} else {
			return false;
		}
	}

}
