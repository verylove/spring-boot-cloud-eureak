package com.example.ribbonconsumer;

import java.text.SimpleDateFormat;
import java.util.Date;


public class test {
        public static void main(String argsp[]) throws Exception{
            String time="2017-12-13 13:25:03";

            Date date=null;
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            date=formatter.parse("2017-12-13T13:25:03+08:00");
            long ts = date.getTime();
            String res = String.valueOf(ts);
            System.out.println(res);
        }

}
