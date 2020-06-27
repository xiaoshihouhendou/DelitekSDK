package com.example.dlksdk.until;

import android.os.Environment;
import android.util.Log;

import com.example.dlksdk.until.JsonFormatTool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class CreateFileUtil {
    /**
     * 生成.json格式文件
     */
    public static File createJsonFile(String jsonString) {
        // 标记文件生成是否成功
        boolean flag = true;
        Log.i("----", "createJsonFile: >>>>"+jsonString);
        // 拼接文件完整路径
        String fullPath = Environment.getExternalStorageDirectory() + File.separator  + "updata.json";
        File file = new File(fullPath);
        // 生成json格式文件
        try {
            // 保证创建一个新文件

            if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
                file.getParentFile().mkdirs();
            }
            if (file.exists()) { // 如果已存在,删除旧文件
                file.delete();
            }
            file.createNewFile();

            if(jsonString.indexOf("'")!=-1){
                //将单引号转义一下，因为JSON串中的字符串类型可以单引号引起来的
                jsonString = jsonString.replaceAll("'", "\\'");
            }
            if(jsonString.indexOf("\"")!=-1){
                //将双引号转义一下，因为JSON串中的字符串类型可以单引号引起来的
                jsonString = jsonString.replaceAll("\"", "\\\"");
            }

            if(jsonString.indexOf("\r\n")!=-1){
                //将回车换行转换一下，因为JSON串中字符串不能出现显式的回车换行
                jsonString = jsonString.replaceAll("\r\n", "\\u000d\\u000a");
            }
            if(jsonString.indexOf("\n")!=-1){
                //将换行转换一下，因为JSON串中字符串不能出现显式的换行
                jsonString = jsonString.replaceAll("\n", "\\u000a");
            }

            // 格式化json字符串
            jsonString = JsonFormatTool.formatJson(jsonString);

            // 将格式化后的字符串写入文件
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            write.write(jsonString);
            write.flush();
            write.close();
        } catch (Exception e) {
            flag = false;
            Log.i("----", "createJsonFile: >>>>"+e.getMessage());
            e.printStackTrace();
        }

        // 返回是否成功的标记
        Log.i("----", "createJsonFile: >>>>>"+file.length());
        return file;
    }

}