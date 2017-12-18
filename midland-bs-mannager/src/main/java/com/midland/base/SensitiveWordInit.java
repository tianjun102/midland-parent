package com.midland.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;


/**
 *  
 * @ClassName: SensitiveWordInit
 * @Description:初始化敏感词库，将敏感词加入到HashMap中，构建DFA算法模型
 * @author 师海明
 * @date 2016年7月4日 下午2:49:46
 * 
*/

public class SensitiveWordInit {
    private String ENCODING = "UTF-8";    //字符编码
    @SuppressWarnings("rawtypes")
    public HashMap sensitiveWordMap;
    private static final Logger log = LoggerFactory.getLogger(SensitiveWordInit.class);
    
    public SensitiveWordInit(){
        super();
    }
    
    @SuppressWarnings("rawtypes")
    public Map initKeyWord(String foldName){
        try {
            //读取敏感词库
            Set<String> keyWordSet = readSensitiveWordFile(foldName);
            //将敏感词库加入到HashMap中
            addSensitiveWordToHashMap(keyWordSet);
            //spring获取application，然后application.setAttribute("sensitiveWordMap",sensitiveWordMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sensitiveWordMap;
    }

 @SuppressWarnings("rawtypes")
    public Map initKeyWord(Set<String> keyWordSet){
        try {
            //读取敏感词库
            //将敏感词库加入到HashMap中
            addSensitiveWordToHashMap(keyWordSet);
            //spring获取application，然后application.setAttribute("sensitiveWordMap",sensitiveWordMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sensitiveWordMap;
    }

    /**
     * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
     * 中 = {
     *      isEnd = 0
     *      国 = {<br>
     *           isEnd = 1
     *           人 = {isEnd = 0
     *                民 = {isEnd = 1}
     *                }
     *           男  = {
     *                  isEnd = 0
     *                   人 = {
     *                        isEnd = 1
     *                       }
     *               }
     *           }
     *      }
     *  五 = {
     *      isEnd = 0
     *      星 = {
     *          isEnd = 0
     *          红 = {
     *              isEnd = 0
     *              旗 = {
     *                   isEnd = 1
     *                  }
     *              }
     *          }
     *      }
     * @param keyWordSet  敏感词库
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
        sensitiveWordMap = new HashMap(keyWordSet.size());     //初始化敏感词容器，减少扩容操作
        String key = null;  
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        //迭代keyWordSet
        Iterator<String> iterator = keyWordSet.iterator();
        while(iterator.hasNext()){
            key = String.valueOf(iterator.next());    //关键字
            nowMap = sensitiveWordMap;
            for(int i = 0 ; i < key.length() ; i++){
                char keyChar = key.charAt(i);       //转换成char型
                Object wordMap = nowMap.get(keyChar);       //获取
                
                if(wordMap != null){        //如果存在该key，直接赋值
                    nowMap = (Map) wordMap;
                }
                else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<String,String>();
                    newWorMap.put("isEnd", "0");     //不是最后一个
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }
                
                if(i == key.length() - 1){
                    nowMap.put("isEnd", "1");    //最后一个
                }
            }
        }
    }

    
    
    /**  
     * 读取敏感词库中的内容，将内容添加到set集合中  
     * @return  
     * @version 1.0  
     */  
    private Set<String> readSensitiveWordFile(String foldName) throws Exception {  
        Set<String> set = new HashSet<String>();  
        InputStreamReader read = null;  
        BufferedReader bufferedReader = null;  
        try {  
            File file = new File(foldName);    //读取文件
            if (file.isDirectory()) {  
                File[] files = file.listFiles();  
                if (null != files && files.length > 0) {  
                    for (File sensitivefile : files) {  
                        read = new InputStreamReader(new FileInputStream(sensitivefile), ENCODING);  
                        bufferedReader = new BufferedReader(read);  
      
                        String txt = null;  
                        while ((txt = bufferedReader.readLine()) != null) { // 读取文件，将文件内容放入到set中  
                            if(txt.split("\\|").length > 0){
                                set.add(txt.split("\\|")[0]);  
                            }
                        }  
                    }  
                } else {
                    log.error("敏感词库文件不存在");
                }  
            } else {
                log.debug("敏感词库文件不存在");
            }  
        } catch (Exception e) {  
            throw e;  
        } finally {  
            if (null != read) {  
                read.close(); // 关闭文件流  
            }  
        }
        log.debug("从文件中读取到敏感词的数量："+set.size());
        return set;  
    }  
}