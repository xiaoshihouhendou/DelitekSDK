package com.example.dlksdk.until;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class RequestServer {

        public static final String CRLF="\r\n";
        public static final String BLANK=" ";
        ServerSocket server=null;
        Socket client=null;

        public void start() throws IOException{
            server=new ServerSocket(7777);
            receive();
        }
        private void receive() {
            try {
                client=server.accept();
                //获取客户端的请求连接信息，一定要有
                byte[] data=new byte[20480];
                int len=client.getInputStream().read(data);
                String requestInfo=new String(data,0,len);
                System.out.println(requestInfo);//可以不打印，只是为了方便观察是否已经连接
                //构建正文
                StringBuilder con=new StringBuilder();
                con.append("<html><head><title>HTTP响应示例</title>" +
                        "</head><body>欢迎访问网页</body></html>");
                //构建http相应的格式
                StringBuilder response =new StringBuilder();
                //1)  HTTP协议版本、状态代码、描述
                response.append("HTTP/1.1").append(BLANK).append("200").append(BLANK).append("OK").append(CRLF);
                //2)  响应头(Response Head)
                response.append("Date:").append(new Date()).append(CRLF);
                response.append("Content-type:text/html;charset=GBK").append(CRLF);
                //正文长度 ：字节长度
                response.append("Content-Length:").append(con.toString().getBytes().length).append(CRLF);
                //3)正文之前
                response.append(CRLF);
                response.append(con);
                //返回客户端的处理请求后的信息
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                bw.write(response.toString());
                bw.flush();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

}
