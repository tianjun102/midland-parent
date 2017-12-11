package com.midland.core.util;


import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 创建时间：2016年11月9日 下午4:16:32
 * 
 * @author andy
 * @version 2.2
 */
@SuppressWarnings("all")
public class HttpUtils {

	private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
	private static final String DEFAULT_CHARSET = "UTF-8";
	
	private static final int CONNECT_TIME_OUT = 5000; //链接超时时间3秒
	
	private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom().setConnectTimeout(CONNECT_TIME_OUT).build();
	
	private static SSLContext wx_ssl_context = null; //微信支付ssl证书
	
	
	/**
	 * @description 功能描述: get 请求
	 * @param url 请求地址
	 * @param params 参数
	 * @param headers headers参数
	 * @return 请求失败返回null
	 */
	public static String get(String url, Map<String, String> params, Map<String, String> headers) {
		boolean flag = url.contains("?");
		CloseableHttpClient httpClient = null;
		if (params != null && !params.isEmpty()) {
			StringBuffer param = new StringBuffer();
			for (Entry<String, String> entry : params.entrySet()) {
				if (flag) {
					param.append("&");
				} else {
					param.append("?");
					flag = true;
				}
				param.append(entry.getKey()).append("=");
				
				try {
					param.append(URLEncoder.encode(entry.getValue()==null?"":entry.getValue(), DEFAULT_CHARSET));
				} catch (UnsupportedEncodingException e) {
					//编码失败
				}
			}
			url += param.toString();
		}

		String body = null;
		CloseableHttpResponse response = null;
		try {;
			httpClient = HttpClients.custom()
					.setDefaultRequestConfig(REQUEST_CONFIG)
					.build();
			HttpGet httpGet = new HttpGet(url);
			response = httpClient.execute(httpGet);
			body = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
		} catch (Exception e) {
			log.error("HttpUtils.get",e.getMessage());
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return body;
	}

	/**
	 * @description 功能描述: get 请求
	 * @param url 请求地址
	 * @return 请求失败返回null
	 */
	public static String get(String url) {
		return get(url, null);
	}

	/**
	 * @description 功能描述: get 请求
	 * @param url 请求地址
	 * @param params 参数
	 * @return 请求失败返回null
	 */
	public static String get(String url, Map<String, String> params)
	{
		
		return get(url, params, null);
	}

	/**
	 * @description 功能描述: post 请求
	 * @param url 请求地址
	 * @param params 参数
	 * @return 请求失败返回null
	 */
	public static String post(String url, Map<String, String> params) {
		return _post(url, params,DEFAULT_CHARSET);
	}
	/**
	 * @description 功能描述: post 请求
	 * @param url 请求地址
	 * @param params 参数
	 * @param charset 编码格式
	 * @return 请求失败返回null
	 */
	public static String post(String url, Map<String, String> params,String charset) {
		
		return _post(url, params,charset);
	}
	
	private static String _post(String url, Map<String, String> params,String charset) {
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nameValuePairs = new ArrayList<>();
		if (params != null && !params.isEmpty()) {;
			for (Entry<String, String> entry : params.entrySet()) {
				nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		
		String body = null;
		CloseableHttpResponse response = null;
		try {
			;
			httpClient = HttpClients.custom()
					.setDefaultRequestConfig(REQUEST_CONFIG)
					.build();
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, charset));
			response = httpClient.execute(httpPost);
			body = EntityUtils.toString(response.getEntity(), charset);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return body;
	}
	
	/**
	 * @description 功能描述: post 请求
	 * @param url 请求地址
	 * @param s 参数xml
	 * @return 请求失败返回null
	 */
	public static String post(String url, String s) {
		
		return _StringPost(url, s,DEFAULT_CHARSET);
	}

	/**
	 * @description 功能描述: post 请求
	 * @param url 请求地址
	 * @param s 参数xml
	 * @return 请求失败返回null
	 */
	public static String post(String url, String s,String charset) throws IOException {
		
		return smsPost(url, s,charset);
	}
	
	
	private static String _StringPost(String url, String s,String charset) {
		CloseableHttpClient httpClient = null;
		;
		HttpPost httpPost = new HttpPost(url);
		String body = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = HttpClients.custom()
					.setDefaultRequestConfig(REQUEST_CONFIG)
					.build();
			;
			httpPost.setEntity(new StringEntity(s, charset));
			response = httpClient.execute(httpPost);
			body = EntityUtils.toString(response.getEntity(), charset);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return body;
	}
	
	/**
	 * @description 功能描述: post https请求，服务器双向证书验证
	 * @param url 请求地址
	 * @param params 参数
	 * @return 请求失败返回null
	 */
	 public static String posts(String url, Map<String, String> params) {
		 return _post(url, params,DEFAULT_CHARSET);
	}
	
	/**
	 * @description 功能描述: post https请求，服务器双向证书验证
	 * @param url 请求地址
	 * @param s 参数xml
	 * @return 请求失败返回null
	 */
	public static String posts(String url, String s)
	{
		return _StringPost(url, s,DEFAULT_CHARSET);
	}
	
	
	public static String smsPost(String urlStr, String param, String charset) throws IOException {
		
		String str =new StringBuffer(urlStr).append("?").append(param).toString();
		URL url = new URL(str);
		
		StringBuffer sbs= new StringBuffer();
		HttpURLConnection connection = null;
		BufferedReader in=null;
		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			in = new BufferedReader(new InputStreamReader(url.openStream(),charset));
			String line=null;
			while((line = in.readLine()) != null){
				sbs.append(line);
			}
			 String strTemp = sbs.toString();
			return strTemp;
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (connection !=null){
				connection.disconnect();
			}if (in !=null){
				in.close();
			}
		}
		
		
		return null;
	}

}
