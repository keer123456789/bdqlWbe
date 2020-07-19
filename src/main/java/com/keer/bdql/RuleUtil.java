package com.keer.bdql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class RuleUtil {
    public static void main(String[] args) {
        String data = readFileContent("1.json");
        System.out.println("data:"+data);
        String rule = readFileContent("2.json");
        System.out.println("rule:"+rule);

        JSONArray datas = JSON.parseArray(data);
        JSONArray rules = JSON.parseArray(rule);
        for (int i = 0; i < datas.size(); i++) {
            Map map1 = (Map) datas.get(i);
            Rule rule1=new Rule(1,1,"==","and");
            boolean b=false;
            for (int j = 0; j < rules.size(); j++) {
                Map map2 = (Map) rules.get(j);
                Rule rule2=new Rule(map1.get(map2.get("field")),map2.get("data"),(String)map2.get("rule"),(String)map2.get("conn"));
                b=rule1.run(rule2);
                rule1=rule2;
            }
            if(b){
                System.out.println(map1.toString());
            }
        }




    }

    public static String readFileContent(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }
}
