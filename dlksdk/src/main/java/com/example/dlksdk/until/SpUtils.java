package com.example.dlksdk.until;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {
    private Context context;
    private SharedPreferences.Editor editor;
    private SharedPreferences sp;
    private String SP_NAME = "bimapp_config";
    private static SpUtils instance;

    private SpUtils(Context context) {
        this.context = context;
    }

    private SpUtils() {

    }

    public static SpUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (SpUtils.class) {
                if (instance == null) {
                    instance = new SpUtils(context);
                }
            }
        }
        return instance;
    }

    private SharedPreferences getSP() {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp;
    }

    private SharedPreferences.Editor getEditor() {
        if (editor == null) {
            editor = getSP().edit();
        }
        return editor;
    }


    private void setLongValue(String key, long value) {
        getEditor().putLong(key, value);
        getEditor().commit();
    }

    private void setStringValue(String key, String value) {
        getEditor().putString(key, value);
        getEditor().commit();
    }

    private long getLongValue(String key) {
        return getSP().getLong(key, -1);
    }

    private String getStringValue(String key) {
        return getSP().getString(key, "");
    }

    private void setValue(String key, String value) {
        getEditor().putString(key, value);
        getEditor().commit();
    }

    public void clear() {
        getEditor().clear();
        getEditor().commit();
    }

    public void remove(String key) {
        getEditor().remove(key);
        getEditor().commit();
    }

    private void setBooleanValue(String key, boolean value) {
        getEditor().putBoolean(key, value);
        getEditor().commit();
    }

    private boolean getBooleanValue(String key) {
        return getSP().getBoolean(key, false);

    }

    private void setIntValue(String key, int value) {
        getEditor().putInt(key, value);
        getEditor().commit();
    }

    private int getIntValue(String key) {
        return getSP().getInt(key, -1);
    }

    private String getValue(String key) {
        return getSP().getString(key, "");
    }

    //保存用户名
    public void setUserID(int userID) {
        setIntValue("userId", userID);
    }

    //获取用户名
    public int getUserID() {
        return getIntValue("userId");
    }

    //保存用户名
    public void setUserName(String userName) {
        setValue("username", userName);
    }

    //获取用户名
    public String getUserName() {
        return getValue("username");
    }

    //保存用户密码
    public void setUserPassword(String password) {
        setValue("userPassword", password);
    }

    //获取用户密码
    public String getUserPassword() {
        return getValue("userPassword");
    }

    //保存登录状态
    public void setLoginState(boolean state) {
        setBooleanValue("loginState", state);
    }

    //获取登录状态
    public boolean isLoginState() {
        return getBooleanValue("loginState");
    }

    //保存手机号
    public void setPhoneNum(String phoneNum) {
        setValue("userPhone", phoneNum);
    }

    //获取手机号
    public String getPhone() {
        return getValue("userPhone");
    }

    //保存用户角色
    public void setUserRole(String role) {
        setValue("userRole", role);
    }

    //获取用户角色
    public String getUserRole() {
        return getValue("userRole");
    }

}
