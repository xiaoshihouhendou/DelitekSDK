package com.example.dlksdk.http.parser;



import com.alibaba.fastjson.JSON;
import com.example.dlksdk.http.CommonEntity;
import com.example.dlksdk.http.entity.XcEnity;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import org.xutils.common.util.ParameterizedTypeUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;
import org.xutils.x;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 2 on 2018/12/24.
 */

public class XcParser implements ResponseParser {
    @Override
    public void checkResponse(UriRequest request) throws Throwable {
        Logger.i(request.toString());
    }

    @Override
    public Object parse(Type resultType, Class<?> resultClass, String result) throws Throwable {
        try {
            XcEnity xcEnity =  JSON.parseObject(result, XcEnity.class);
            if(xcEnity.getCode()!=0){
                throw new Exception(xcEnity.getMessage());
            }
            if (resultClass == List.class) {
                return JSON.parseArray(xcEnity.getContent(), (Class<?>) ParameterizedTypeUtil.getParameterizedType(resultType, List.class, 0));
            } else if(resultClass == CommonEntity.class){
                return new CommonEntity(xcEnity.getContent());
            }else {
                return JSON.parseObject(xcEnity.getContent(), resultClass);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    public static class Sync<T>{
        public Type mType;
        public Sync() {
            mType = getSuperclassTypeParameter(getClass());
        }
        private static Type getSuperclassTypeParameter(Class<?> subclass) {
            //得到带有泛型的类
            Type superclass = subclass.getGenericSuperclass();
            if (superclass instanceof Class) {
                throw new RuntimeException("Missing type parameter.");
            }
            //取出当前类的泛型
            ParameterizedType parameter = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameter.getActualTypeArguments()[0]);
        }
        public T doGet(String url){
            RequestParams requestParams = new RequestParams(url);
            try {
                String string =  x.http().getSync(requestParams, String.class);
                XcEnity xcEnity =  JSON.parseObject(string, XcEnity.class);
                if(xcEnity.getCode()!=0){
                    throw new Exception(xcEnity.getMessage());
                }
                return new Gson().fromJson(xcEnity.getContent(),new TypeToken<T>(){}.getType());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return null;
        }
    }
}
