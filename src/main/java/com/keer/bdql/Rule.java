package com.keer.bdql;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Rule {
    private Object realData;
    private Object expectData;
    private String rule;
    private String conn;

    private ScriptEngineManager scriptEngineManager;
    private ScriptEngine scriptEngine;

    public Rule() {
    }

    public Rule(Object realData, Object expectData, String rule, String conn) {
        this.realData = realData;
        this.expectData = expectData;
        this.rule = rule;
        if (conn.equals("or")) {
            this.conn = "|";
        } else {
            this.conn = "&";
        }
        this.scriptEngineManager = new ScriptEngineManager();
        this.scriptEngine = scriptEngineManager.getEngineByName("js");


    }

    public Boolean run() {

        String real = "";
        String exp = "";
        if (realData.getClass().equals(int.class)) {
            real = Integer.toString((int) realData, 10);
        } else {
            real = realData.toString();
        }

        if (expectData.getClass().equals(int.class)) {
            exp = Integer.toString((int) expectData, 10);
        } else {
            exp = expectData.toString();
        }

        boolean result = false;
        if (this.rule.equals("like")) {
            result=real.matches(".*" + exp + ".*");
        } else if (this.rule.equals("not like")) {
            result=!real.matches(".*" + exp + ".*");
        } else {
            String str = real + rule + exp;
            try {
                result = (boolean) this.scriptEngine.eval(str);
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    public Boolean run(Rule otherRule) {
        Boolean first = this.run();
        Boolean second = otherRule.run();
        String res = first.toString() + " " + this.conn + " " + second;

        boolean result = false;
        try {
            result = (boolean) scriptEngine.eval(res);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return result;

    }

    public Object getRealData() {
        return realData;
    }

    public void setRealData(Object realData) {
        this.realData = realData;
    }

    public Object getExpectData() {
        return expectData;
    }

    public void setExpectData(Object expectData) {
        this.expectData = expectData;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }


    public String getConn() {
        return conn;
    }

    public void setConn(String conn) {
        this.conn = conn;
    }
}
