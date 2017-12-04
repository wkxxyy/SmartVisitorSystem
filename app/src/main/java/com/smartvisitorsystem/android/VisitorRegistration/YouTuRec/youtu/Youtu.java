package com.smartvisitorsystem.android.VisitorRegistration.YouTuRec.youtu;

import android.graphics.Bitmap;
import android.util.Base64;


import com.smartvisitorsystem.android.VisitorRegistration.YouTuRec.youtu.common.ImageUtil;
import com.smartvisitorsystem.android.VisitorRegistration.YouTuRec.youtu.sign.YoutuSign;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


/**
 * 
 * @author tyronetao
 */
public class Youtu {

	public final static String API_YOUTU_END_POINT = "http://api.youtu.qq.com/youtu/";

	// 30 days
	private static int EXPIRED_SECONDS = 2592000;
	private String m_appid;
	private String m_secret_id;
	private String m_secret_key;
	private String m_end_point;
	private boolean m_use_https;
	
	/**
	 * Youtu 构造方法
	 * 
	 * @param appid
	 *            授权appid
	 * @param secret_id
	 *            授权secret_id
	 * @param secret_key
	 *            授权secret_key
	 */
	public Youtu(String appid, String secret_id, String secret_key, String end_point) {
		m_appid = appid;
		m_secret_id = secret_id;
		m_secret_key = secret_key;
		m_end_point= end_point;
		m_use_https = end_point.startsWith("https");
	}
	
	public String StatusText(int status) {
		
		String statusText = "UNKOWN";

        switch (status)
        {
        	case 0:
                statusText = "CONNECT_FAIL";
                break;
            case 200:
                statusText = "HTTP OK";
                break;
            case 400:
                statusText = "BAD_REQUEST";
                break;
            case 401:
                statusText = "UNAUTHORIZED";
                break;
            case 403:
                statusText = "FORBIDDEN";
                break;
            case 404:
                statusText = "NOTFOUND";
                break;
            case 411:
                statusText = "REQ_NOLENGTH";
                break;
            case 423:
                statusText = "SERVER_NOTFOUND";
                break;
            case 424:
                statusText = "METHOD_NOTFOUND";
                break;
            case 425:
                statusText = "REQUEST_OVERFLOW";
                break;
            case 500:
                statusText = "INTERNAL_SERVER_ERROR";
                break;
            case 503:
                statusText = "SERVICE_UNAVAILABLE";
                break;
            case 504:
                statusText = "GATEWAY_TIME_OUT";
                break;
        }
        return statusText;		
	}

	public static String bitmapToBase64(Bitmap bitmap) throws IOException {

		String result = null;
		ByteArrayOutputStream baos = null;
		try {
			if (bitmap != null) {
				baos = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

				baos.flush();
				baos.close();

				byte[] bitmapBytes = baos.toByteArray();
				result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (baos != null) {
					baos.flush();
					baos.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		return result;
	}

	
	private JSONObject SendHttpRequest(JSONObject postData, String mothod)
	throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException {

		StringBuffer mySign = new StringBuffer("");//初始化签名秘钥
		System.out.println("1234"+"开始1");
		YoutuSign.appSign(m_appid, m_secret_id, m_secret_key,
			System.currentTimeMillis() / 1000 + EXPIRED_SECONDS,
			"", mySign);//设置签名秘钥，把对象传过去，直接在那边操作对象，所以这里mySign地址的数据也被改了
		System.out.println("1234"+"开始2");
		System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		System.setProperty("sun.net.client.defaultReadTimeout", "30000");
		URL url = new URL(m_end_point + mothod);//http://api.youtu.qq.com/youtu/ocrapi/idcardocr
		//请求地址，刚才的ocrapi/idcardocr拼接到这里的
		System.out.println("1234"+"开始3");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();//开始连接

		// set header
		connection.setRequestMethod("POST");
		connection.setRequestProperty("accept", "*/*");
//		connection.setRequestProperty("Host", "api.youtu.qq.com");
		connection.setRequestProperty("user-agent", "youtu-android-sdk");
		connection.setRequestProperty("Authorization", mySign.toString());
		System.out.println("1234"+"开始4");
//		connection.setConnectTimeout(30000);
//		connection.setReadTimeout(30000);
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type", "text/json");
		connection.connect();
		System.out.println("1234"+"开始5");

		// POST请求
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		System.out.println("1234"+"开始6");

		postData.put("app_id", m_appid);
		out.write(postData.toString().getBytes("utf-8"));
		//out.writeBytes(postData.toString());
		out.flush();
		out.close();
		System.out.println("1234"+"开始7");
		// 读取响应
		InputStream isss = connection.getInputStream();
		System.out.println("1234"+"开始8");
		BufferedReader reader = new BufferedReader(new InputStreamReader(isss));
		String lines;

		StringBuffer resposeBuffer = new StringBuffer("");
		while ((lines = reader.readLine()) != null) {
			lines = new String(lines.getBytes(), "utf-8");
			resposeBuffer.append(lines);
		}
		// System.out.println(resposeBuffer+"\n");
		reader.close();
		// 断开连接
		connection.disconnect();

		JSONObject respose = new JSONObject(resposeBuffer.toString());

		return respose;

	}
	
	/*
	 * 身份证OCR识别
	 *
	 * @param bitmap  输入图片
	 * @param cardType 身份证图片类型，0-正面，1-反面
	 */

	public JSONObject IdcardOcr(Bitmap bitmap, int cardType) throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException {
		JSONObject data = new JSONObject();//创建要发送的json数据
		String imageData = bitmapToBase64(ImageUtil.ratio(bitmap,1024,768));//获取图片的bse64编码，ratio是压缩图片算法，不然图片太大了
		System.out.println("1234"+"开始");
		data.put("image", imageData);//存入数据主体
		data.put("card_type", cardType);//设置身份证正反面，默认是正面0;

		JSONObject response = SendHttpRequest(data, "ocrapi/idcardocr");//ocrapi/udacardocr是请求身份证识别的地址
		return response;
	}

    public JSONObject NamecardOcr(Bitmap bitmap) throws  IOException,
            JSONException, KeyManagementException, NoSuchAlgorithmException {
        JSONObject data = new JSONObject();
        String imageData = bitmapToBase64(bitmap);
        data.put("image", imageData);
        JSONObject response = SendHttpRequest(data, "ocrapi/namecardocr");
        return response;
    }

}

