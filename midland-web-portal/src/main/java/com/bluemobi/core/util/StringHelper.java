package com.bluemobi.core.util;


import java.security.MessageDigest;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;


public class StringHelper {
	
	private static final boolean String = false;

	/**
	 * 设置默认字符串
	 * @param requestValue 请求值
	 * @param defaultVal 请求为空的时候设置的默认值
	 * @return
	 */
	public static String setDefaultString(Object str,String defaultVal) {
		if(str==null){
			return defaultVal;
		}
		return StringHelper.isNullOrEmpty(str.toString())?defaultVal:str.toString();
	}
	
	/**
	 * 屏蔽字段
	 * @param fromValue 表单提交的值
	 * @return
	 */
	/*public static String replaceSensitive(String fromValue) {
		String sqlQuery = "and ParentID =" + AppSetting.getAppSetting("Sensitive");
		List<DictionaryModel> sensitives = Factory.dictionaryService().getList(sqlQuery);
		String sign = "";
		for (DictionaryModel dic : sensitives) {
			if (fromValue.contains(dic.getDictionaryName())) {
				int len = dic.getDictionaryName().length();
				sign = "";
				for(int i = 0; i < len; i++) {
					sign += "*";
				}
			}
			fromValue = fromValue.replace(dic.getDictionaryName(), sign);
		}
		return fromValue;
	}*/
	
	/**
	 * 生成随机字符串
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijkmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  
	
	
	
	/**
	 * 生成随机字符串
	 * @param length
	 * @return
	 */
	public static String getRandomNum(int length) { //length表示生成字符串的长度
	    String base = "0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 } 
	
	/**
	 * 
	 * @param name
	 *            将首字母转换为 大写
	 * @return 转换后的答谢字母
	 */
	public static String captureName(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 对字符串进行md5加密
	 * 
	 * @param s
	 *            将要加密的字符串
	 * @return 加密后的字符串
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 *            进行判断的字符串
	 * @return 如果为null 或者"" 则返回true代表为空 如果字符串不为空则返回false
	 */
	public final static boolean isNullOrEmpty(String str) {
		if (str == null || str.length() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 取部分字符,len为0则返回原字符
	 * @param str 要截取的字符串
	 * @param len 截取长度
	 * @return
	 */
    public static String ShowPartStr(String str, int len)
    {
        if (len == 0)
        {
            return str;
        }
        else
        {
            if (!isNullOrEmpty(str))
            {
            	char[] strChar = str.toCharArray();
                int t = 0;
                int n = 0;
                String strTemp = "";
                for (char c : strChar)
                {
                    n++;
                    if ((int)c < 0 || (int)c > 255)
                    {
                        t = t + 2;
                    }
                    else
                    {
                        t = t + 1;
                    }
                    if (t > len)
                    {
                        strTemp = str.substring(0, n - 1) + "...";
                        break;
                    }
                    else
                    {
                        strTemp = str;
                    }
                }
                return strTemp;
            }
            else
            {
                return "";
            }
        }
    }
	
	
	 /**
	  * 根据条件输出字符,strParA与strParB相等,则返回str1,否则返回str2 mxy
	  * @param strParA 要比较的参数
	  * @param strParB 要比较的参数
	  * @param str1 要返回的字符
	  * @param str2 要返回的字符
	  * @return
	  */
     public static String showStr(String strParA, String strParB, String str1, String str2)
     {
         if (strParA.equals(strParB))
         {
             return str1;
         }
         else
         {
             return str2;
         }
     }
	
     /**
      * 数字前面加字符0 mxy
      * @param 要检查的数字
      * @param 要显示的字符位数
      * @return
      */
     public static String showZero(int n, int count)
     {
         int len =Integer.toString(n).length();
         StringBuilder strReturn = new StringBuilder();
         if (count > len)
         {
             for (int i = 0; i < (count - len); i++)
             {
                 strReturn.append("0");
             }
         }
         strReturn.append(Integer.toString(n));
         return strReturn.toString();
     }
     /**
      * 下拉时、分列表
      * @param MaxNum 最大值
      * @param Setvalue 选中值
      * @return
      */
     public static String getTimeList(int MaxNum, int Setvalue)
     {
         StringBuilder suBuilder = new StringBuilder();
         for (int i = 0; i <= MaxNum; i++)
         {
        	 suBuilder.append(MessageFormat.format("<option " + (i == Setvalue ? "{2}" : "{3}") + " value=\"{0}\">{1}</option>",showZero(i,2), showZero(i,2),"selected=selected", ""));
         }
         return suBuilder.toString();
     }
     
   /**
    * 会员中心左侧导航样式选中验证 mxy
    * @param request的值
    * @param 设置的值(多值,只要包含request的值则选中)
    * @param strStyle 样式类名
    * @return  返回样式类名
    */
     public static String getSelectClass(Object objReq,String selvalue,String strStyle)
     {
         String str ="";
         if (!objReq.equals(null) && !isNullOrEmpty(selvalue))
         {
        	 String [] arry=selvalue.split(",");
             for (String string : arry){
            	 if(string.toLowerCase().equals(objReq.toString().toLowerCase())){
            		 str=strStyle;
            		 break;
            	 }
             }
         }
         return str;
     }
     
     

     /**
      * 用星号代替部分字符显示字符串
      * @param str 原字符
      * @param startPosition 开始显示星号位置
      * @param hideLen 显示星号长度
      * @return
      */
     public static String HidePartStr(String str, int startPosition, int hideLen)
     {
         String newStr ="";
     	 char[] strChar = str.toCharArray();
         for (int i = 0; i < str.length(); i++)
         {
             if (i >= startPosition && i < startPosition + hideLen)
             {
                 newStr += "*";
             }
             else
             {
                 newStr += strChar[i];
             }
         }
         return newStr;
     }
     
     
	/**
	 * 将消息发送到iframe页面中  并且调用页面的js事件
	 * @param message 响应消息
	 * @return
	 */
	public static String msgGotoUrl(String message,String link) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
		sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		sb.append("<head>\n");
		sb.append("<script src=\"../resource/jquery/jquery-1.11.1.min.js\" type=\"text/javascript\"></script>\n");
		sb.append("<script src=\"../resource/layer/layer.min.js\" type=\"text/javascript\"></script>\n");
		sb.append("<script src=\"../resource/common/msg.js\" type=\"text/javascript\"></script>\n");
		sb.append("</head>\n");
		sb.append("<body>\n");
		sb.append("<script  type=\"text/javascript\">msg.gotoUrl('" + message + "','"+link+"');</script>\n");
		sb.append("</body>\n");
		sb.append("</html>\n");
		return sb.toString();
	}
	
	/**
	 * 将消息发送到iframe页面中  并且调用页面的js事件
	 * @param message 响应消息
	 * @return
	 */
	public static String msgGotoUrl_phone(String message,String link) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html >\n");
		sb.append("<html>\n");
		sb.append("<head>\n");
		sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		sb.append("<script src=\"../resource/jquery/jquery-1.11.1.min.js\" type=\"text/javascript\"></script>\n");
		sb.append("<script src=\"../resource/layer/layer.min.js\" type=\"text/javascript\"></script>\n");
		sb.append("<script src=\"../resource/common/msg.js\" type=\"text/javascript\"></script>\n");
		sb.append("</head>\n");
		sb.append("<body>\n");
		sb.append("<script  type=\"text/javascript\">msg.gotoUrl('" + message + "','"+link+"');</script>\n");
		sb.append("</body>\n");
		sb.append("</html>\n");
		return sb.toString();
	}
	
	
	
	/**
	 * 将消息发送到iframe页面中  并且调用页面的js事件
	 * @param message 响应消息
	 * @return
	 */
	public static String msgGotoUrl_(String message,String link) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
		sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
		sb.append("<head>\n");
		sb.append("<script src=\"../resource/jquery/jquery-1.11.1.min.js\" type=\"text/javascript\"></script>\n");
		sb.append("<script src=\"../resource/layer/layer.min.js\" type=\"text/javascript\"></script>\n");
		sb.append("<script src=\"../resource/common/msg.js\" type=\"text/javascript\"></script>\n");
		sb.append("</head>\n");
		sb.append("<body>\n");
		sb.append("<script  type=\"text/javascript\">msg.gotoUrl_('" + message + "','"+link+"');</script>\n");
		sb.append("</body>\n");
		sb.append("</html>\n");
		return sb.toString();
	}

	/**
	 * 将消息发送到iframe页面中  并且调用页面的js事件
	 * @param message 响应消息
	 * @return
	 */
	public static String msgGoBack(String message) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
		sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		sb.append("<head>\n");
		sb.append("<head>\n");
		sb.append("<script src=\"../resource/jquery/jquery-1.11.1.min.js\" type=\"text/javascript\"></script>\n");
		sb.append("<script src=\"../resource/layer/layer.min.js\" type=\"text/javascript\"></script>\n");
		sb.append("<script src=\"../resource/common/msg.js\" type=\"text/javascript\"></script>\n");
		sb.append("</head>\n");
		sb.append("<body>\n");
		sb.append("<script  type=\"text/javascript\">msg.gotoBack('" + message + "');</script>\n");
		sb.append("</body>\n");
		sb.append("</html>\n");
		return sb.toString();
	}
	
	/**
	 * 将消息发送到iframe页面中  并且调用页面的js事件
	 * @param message 响应消息
	 * @return
	 */
	public static String jsonResponse(String message) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
		sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		sb.append("<head>\n");
		sb.append("<head>\n");
		sb.append("<script src=\"../resource/jquery/jquery-1.11.1.min.js\" type=\"text/javascript\"></script>\n");
		sb.append("<script src=\"../resource/layer/layer.min.js\" type=\"text/javascript\"></script>\n");
		sb.append("<script src=\"../resource/common/msg.js\" type=\"text/javascript\"></script>\n");
		sb.append("</head>\n");
		sb.append("<body>\n");
		sb.append("<script  type=\"text/javascript\">msg.errMsg(" + message + ");</script>\n");
		sb.append("</body>\n");
		sb.append("</html>\n");
		return sb.toString();
	}
	/**
	 * 加载百度编辑器
	 * @param strEditorID
	 * @param strInitContent
	 * @return
	 */
	public static String LoadUEditor(String strEditorID, String strInitContent) {
		StringBuilder strUEditor = new StringBuilder();
		strUEditor.append("<textarea id=\"" + strEditorID + "\"  style=\"width: 100%; height: 350px;\">"+ strInitContent + "</textarea>\n");
		strUEditor.append("<script type=\"text/javascript\">\n");
		strUEditor.append("var ue = UE.getEditor('" + strEditorID + "',{\n");
		strUEditor.append("textarea:'" + strEditorID + "'\n");
		strUEditor.append("});\n");
		strUEditor.append("</script>\n");
		return strUEditor.toString();
	}
	

	/**
	 * 加载简易版百度编辑器
	 * @param strEditorID
	 * @param strInitContent
	 * @strWidthHeight widith:800px;height:500px;
	 * @return
	 */
      public static String LoadEditorEasy(String strEditorID, String strInitContent, String strWidthHeight)
      {
          StringBuilder strUEditor = new StringBuilder();
          strUEditor.append("<textarea id=\"" + strEditorID + "\"  name=\"" + strEditorID + "\" style=\"" + strWidthHeight + "\">" + strInitContent + "</textarea>\n");
          strUEditor.append("<script type=\"text/javascript\">\n");
          strUEditor.append("var ue = UE.getEditor('" + strEditorID + "',{\n");
          strUEditor.append("toolbars: [['undo', 'redo', '|','bold', 'italic', 'underline', 'forecolor', 'backcolor', 'superscript', 'subscript', 'removeformat', '|','insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|','fontsize', '|','indent', '|','justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|','link', '|','simpleupload']],textarea:'" + strEditorID + "'\n");
          strUEditor.append("});\n");
          strUEditor.append("</script>\n");
          return strUEditor.toString();
      }
      
	
	/**
	 * 去掉结尾的某些字符串
	 * @param string
	 * @param charsToTrim
	 * @return
	 */
	public static String trimEnd(String string, Character... charsToTrim) {
		if (string == null || charsToTrim == null)
			return string;

		int lengthToKeep = string.length();
		for (int index = string.length() - 1; index >= 0; index--) {
			boolean removeChar = false;
			if (charsToTrim.length == 0) {
				if (Character.isWhitespace(string.charAt(index))) {
					lengthToKeep = index;
					removeChar = true;
				}
			} else {
				for (int trimCharIndex = 0; trimCharIndex < charsToTrim.length; trimCharIndex++) {
					if (string.charAt(index) == charsToTrim[trimCharIndex]) {
						lengthToKeep = index;
						removeChar = true;
						break;
					}
				}
			}
			if (!removeChar)
				break;
		}
		return string.substring(0, lengthToKeep);
	}
	/**
	 * 去掉开始的某些字符
	 * @param string
	 * @param charsToTrim
	 * @return
	 */
	public static String trimStart(String string, Character... charsToTrim) {
		if (string == null || charsToTrim == null)
			return string;

		int startingIndex = 0;
		for (int index = 0; index < string.length(); index++) {
			boolean removeChar = false;
			if (charsToTrim.length == 0) {
				if (Character.isWhitespace(string.charAt(index))) {
					startingIndex = index + 1;
					removeChar = true;
				}
			} else {
				for (int trimCharIndex = 0; trimCharIndex < charsToTrim.length; trimCharIndex++) {
					if (string.charAt(index) == charsToTrim[trimCharIndex]) {
						startingIndex = index + 1;
						removeChar = true;
						break;
					}
				}
			}
			if (!removeChar)
				break;
		}
		return string.substring(startingIndex);
	}
	
	/**
	 * 去掉字符串前后的某些字符
	 * @param string
	 * @param charsToTrim
	 * @return
	 */
	public static String trim(String string, Character... charsToTrim) {
		return trimEnd(trimStart(string, charsToTrim), charsToTrim);
	}
	
	/**
	 * 将数组以某种字符串连接为一个新的字符串
	 * @param separator
	 * @param stringarray
	 * @param startindex
	 * @param count
	 * @return
	 */
	public static String join(String separator, String[] stringarray, int startindex) {
		String result = "";

		if (stringarray == null)
			return null;

		for (int index = startindex; index < stringarray.length; index++) {
			if (separator != null && index > startindex)
				result += separator;

			if (stringarray[index] != null)
				result += stringarray[index];
		}

		return result;
	}
	
	/**
	 * 验证是否为email
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){  
        if (null==email || "".equals(email)) return false;    
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配  
        Matcher m = p.matcher(email);  
        return m.matches();  
    }  
	/**
	 * 验证是否为手机号码
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobile(String mobile){
		if (null==mobile || "".equals(mobile)) return false;    
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}
	
	/**
	 * 检查是否为用户名
	 * @param mobile
	 * @return
	 */
	public static boolean isUserName(String mobile){
		if (null==mobile || "".equals(mobile)) return false;    
		Pattern p = Pattern.compile("[a-zA-Z]\\w{3,15}");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}
	
	/**
	 * 检查是否为正整数
	 * @param 
	 * @return
	 */
	public static boolean isInt(String requestValue){
		if (null==requestValue || "".equals(requestValue)) return false;    
		Pattern p = Pattern.compile("[1-9][0-9]{0,}");
		Matcher m = p.matcher(requestValue);
		return m.matches();
	}
	
	/**
	 * 检查是否为数字
	 * @param mobile
	 * @return
	 */
	public static boolean isNumber(String requestValue){
		if (null==requestValue || "".equals(requestValue)) return false;    
		Pattern p = Pattern.compile("[0-9]+(.[0-9]{1,3})?");
		Matcher m = p.matcher(requestValue);
		return m.matches();
	}
	
	/**
	 * 去掉"<"和">"之间HTML代码  (去掉&quot;&lt;&quot;和&quot;&gt;&quot;之间HTML代码)
	 * @param str
	 * @return
	 */
	 public static String HTMLRemove(String str)
     {
		 if(!StringHelper.isNullOrEmpty(str)){
			 String ragex = "<[^>]*>";
		     return  str.replaceAll(ragex, "");	 
		 }else{
			 return "";
		 }
     }
//	
	
    /**
     * 把对象转成json字符串
     */
    public static String GetJsonString(Object obj){
    	Gson gson=new Gson();
    	return gson.toJson(obj);
    }
    
    /**
     *  分隔字符串去空
     * @param str
     * @param fgf
     * @return
     */
    public static String RemoveEmptyStr(String str,String fgf){
    	str = setDefaultString(str, "");
    	String[] strArr = str.split(fgf);
    	str = "";
    	for (String s : strArr) {
			if(!isNullOrEmpty(s)){
				str += s + fgf;
			}
		}
    	return str;
    }
	
    
    public static List<String> getImg(String content){
        List<String> list = new ArrayList<String>();
        Pattern p_img = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)");
        Matcher m_img = p_img.matcher(content);
        boolean result_img = m_img.find();
        if (result_img) {
            while (result_img) {
                String str_img = m_img.group(2);
                Pattern p_src = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
                Matcher m_src = p_src.matcher(str_img);
                if (m_src.find()) {
                    String str_src = m_src.group(3);
                    list.add(str_src);
                }
                result_img = m_img.find();
            }
        }
        return list;
    }
    
    public static String getFirstImg(String content){
    	List<String> list=getImg(content);
    	if(list.size()>0){
    		return list.get(0);
    	}
    	return "";
    }
    
    public static String getAHref(String url,String paramStr){
    	if(isNullOrEmpty(url)){
    		return "javascript:;";
    	}
    	if(url.indexOf("?")>-1){
    		return url+"&"+paramStr;
    	}else{
    		return url+"?"+paramStr;
    	}
    }
    
    public static List<String> getAHrefs(String content){
        List<String> list = new ArrayList<String>();
        if(!isNullOrEmpty(content)){
        	 Pattern p_a = Pattern.compile("<(a)(.*?)(/>|></a>|>)");
             Matcher m_a = p_a.matcher(content);
             boolean result_a = m_a.find();
             if (result_a) {
                 while (result_a) {
                     String str_a = m_a.group(2);
                     Pattern p_href = Pattern.compile("(href)=(\"|\')(.*?)(\"|\')");
                     Matcher m_href = p_href.matcher(str_a);
                     if (m_href.find()) {
                         String str_href = m_href.group(3);
                         list.add(str_href);
                     }
                     result_a = m_a.find();
                 }
             }
    	}
        return list;
    }
}
