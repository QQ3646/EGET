//package com.twodauns.eget;
//
//import android.net.Uri;
//
//import java.io.IOException;
//import java.net.URL;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class HttpConnect extends OkHttpClient {
//        public String HttpCon(URL url, int VariantCount, String nSubject, String[] params){
//            OkHttpClient client = new OkHttpClient();
//
//            Request request = new Request.Builder()
//                    .url(url)
//                    .get()
//                    .build();
//
//            try {
//                Response response = client.newCall(request).execute();
//                String result = response.body().string();
//                return result;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//}
