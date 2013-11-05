package net.tsz.afinal.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast; 

public class Common {
 
	public static void showMsg(Context ctx,	String content){
		Toast.makeText(ctx, content, Toast.LENGTH_LONG).show();
	}
	
	public static boolean isConnectingToInternet(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getApplicationContext().getSystemService(
						Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}

		}
		return false;
	}
	@SuppressWarnings("rawtypes")
	public static Map<String, String> objectToMap(Object obj) {

		Map<String, String> hashMap = new HashMap<String, String>();
		try {
			Class c = obj.getClass();
			Method m[] = c.getMethods();
			for (int i = 0; i < m.length; i++) {
				if (m[i].getName().indexOf("get") == 0) {
					try {
						String key = m[i].getName().substring(3);
						char first = key.charAt(0);
						first = (char) (first - ('A' - 'a'));
						key = first + key.substring(1);
						if (key.equals("class")) {
							continue;
						}
						String t = String.valueOf(m[i].invoke(obj,
								new Object[0]));
						if (!t.equals("null")) {
							hashMap.put(key, t);
						}
					} catch (Exception e) {

					}
				}
			}
		} catch (Throwable e) {
		}
		return hashMap;

	}
	
	public static boolean isNotBlank(String str) {
		if (str != null && !str.equals("")) {
			return true;
		}
		return false;
	}

	public static boolean isNotBlank(List<?> list) {
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}
}
