package com.example.cubicmetercommunity.dbutil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.JSONObject;

import com.salesforce.androidsdk.rest.RestRequest;

public class DBUtil {
	final String apiVersion = "v27.0";

	public JSONObject create(String table, Map<String, Object> fields) {
		RestRequest request = null;
		try {
			request = RestRequest
					.getRequestForCreate(apiVersion, table, fields);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getResponse(request);
	}

	public JSONObject select(String table, Map<String, Object> fields,
			String where) {
		RestRequest request = null;
		String str = "";
		int x = 0;
		for (String s : fields.keySet()) {
			str += (x == 0 ? "" : ", ") + s;
			x++;
		}
		String query = "select " + str + " from " + table
				+ (where != null ? " where " + where : "");
		try {
			request = RestRequest.getRequestForQuery(apiVersion, query);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return getResponse(request);
	}

	public JSONObject update(String table, String objectId,
			Map<String, Object> fields) {
		RestRequest request = null;
		try {
			request = RestRequest.getRequestForUpdate(apiVersion, table,
					objectId, fields);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getResponse(request);
	}

	public JSONObject delete(String table, String objectId) {
		RestRequest request = null;
		request = RestRequest.getRequestForDelete(apiVersion, table, objectId);
		return getResponse(request);
	}

	public JSONObject getResponse(RestRequest request) {
		JSONObject response = null;
		try {
			response = (JSONObject) new OnlineDBUtil.sendSync()
					.execute(request).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return response;
	}
}
