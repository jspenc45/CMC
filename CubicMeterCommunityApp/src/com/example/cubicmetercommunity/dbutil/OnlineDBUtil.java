package com.example.cubicmetercommunity.dbutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.salesforce.androidsdk.auth.HttpAccess;
import com.salesforce.androidsdk.rest.RestClient;
import com.salesforce.androidsdk.rest.RestClient.ClientInfo;
import com.salesforce.androidsdk.rest.RestRequest;
import com.salesforce.androidsdk.rest.RestResponse;

import android.os.AsyncTask;
import android.util.Log;

public class OnlineDBUtil {
	public static class sendSync extends
			AsyncTask<Object, String, JSONObject> {

		@Override
		protected JSONObject doInBackground(Object... arg0) {
			RestRequest request = (RestRequest) arg0[0];
			JSONObject oauthLoginResponse;
			JSONObject response = null;

			try {
				oauthLoginResponse = oAuthSessionProvider();
				response = getResponse(oauthLoginResponse, request);
			} catch (HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			return response;
		}
	}

	private static JSONObject oAuthSessionProvider() throws HttpException,
			IOException, JSONException {
		String loginHost = "https://login.database.com";
		String username = "z547597@hotmail.com";
		String password = "password1230enaYxp5xmGD2MJTkOEaHQct";
		String clientId = "3MVG9A2kN3Bn17hscIyVDAIdqTCwGmSOZeknfWCCiYleEc4xqTBh92miMkBpjn9DAlf_33e_E.WqUDJ8B.YYc";
		String secret = "8393277502219606078";

		DefaultHttpClient client = new DefaultHttpClient();
		HttpParams params = client.getParams();
		params.setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 30000);

		String baseUrl = loginHost + "/services/oauth2/token";
		HttpPost oauthPost = new HttpPost(baseUrl);
		List<BasicNameValuePair> parametersBody = new ArrayList<BasicNameValuePair>();
		parametersBody.add(new BasicNameValuePair("grant_type", "password"));
		parametersBody.add(new BasicNameValuePair("username", username));
		parametersBody.add(new BasicNameValuePair("password", password));
		parametersBody.add(new BasicNameValuePair("client_id", clientId));
		parametersBody.add(new BasicNameValuePair("client_secret", secret));
		oauthPost
				.setEntity(new UrlEncodedFormEntity(parametersBody, HTTP.UTF_8));

		HttpResponse response = client.execute(oauthPost);
		HttpEntity entity = response.getEntity();
		InputStream is = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		Log.d("debug", sb.toString());

		JSONObject oauthLoginResponse = new JSONObject(sb.toString());
		return oauthLoginResponse;
	}

	private static JSONObject getResponse(JSONObject oauthLoginResponse,
			RestRequest request) throws JSONException, ClientProtocolException,
			IOException, URISyntaxException {
		
		String accessToken = oauthLoginResponse.get("access_token").toString();
		String instanceURL = oauthLoginResponse.getString("instance_url");
		
		ClientInfo clientInfo = new ClientInfo(null, new URI(instanceURL), null, null, null, null, null, null);
		

		
		RestClient restClient = new RestClient(clientInfo, accessToken, HttpAccess.DEFAULT, null);
		RestResponse rResponse = restClient.sendSync(request);
		
		JSONObject response = rResponse.asJSONObject();
		
		Log.d("debug","response: " + response);
		
		
		
		return response;
	}

}
