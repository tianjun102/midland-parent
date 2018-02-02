package com.midland.base;

import com.midland.web.util.JsonMapReader;
import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 'ms.x' on 2017/7/27.
 */
public class InitSensitive extends PropertiesEditor {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Set<String> set = new HashSet<>();
        Arrays.asList(JsonMapReader.getSensitive("sensitive").split(",")).forEach(e1 ->
                set.add(e1)
        );
        SensitivewordFilter filter = new SensitivewordFilter(set);
        Set<String> set1 = filter.getSensitiveWord(text, 2);
        if (set1.size() > 0) {
            throw new IllegalArgumentException("包含敏感词汇");
        }
        setValue(text);
    }

    @Override
    public String getAsText() {
        return null;
    }
}
