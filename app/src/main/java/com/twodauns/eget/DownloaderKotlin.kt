package com.twodauns.eget

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader

import java.io.IOException
import java.net.URL

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.FileOutputStream

class DownloaderKotlin() {
    //    fun HttpCon(url: URL, VariantCount: Int, nSubject: String, params: Array<String>): String? {
//        val client = OkHttpClient()
//
//        val request = Request.Builder()
//                .url(url)
//                .get()
//                .build()
//
//        try {
//            val response = client.newCall(request).execute()
//            return response.body()!!.string()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//
//        return null
//    }
//
//    fun saveCanvasImage(resourses : Resources, filesDir : File) {
//
//        Log.d("bitmap", "strm")
//        val bm = resourses.assets.open("7427.png")
//        var bitm = BitmapFactory.decodeStream(bm)
//
//        val fPath = filesDir.absolutePath
//        var f: File?
//        f = File(fPath, "7427.png")
//
//        try {
//            val strm = FileOutputStream(f)
//            bitm.compress(Bitmap.CompressFormat.PNG, 80, strm)
//            strm.close()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//
//    }
    fun download(url: String, dirPath: String, fileName: String) {
        val downloadId = PRDownloader.download(url, dirPath, fileName)
                .build()
                .setOnStartOrResumeListener { }
                .setOnPauseListener { }
                .setOnCancelListener { }
                .setOnProgressListener { }
                .start(object : OnDownloadListener {
                    override fun onDownloadComplete() {

                    }

                    override fun onError(error: Error) {

                    }
                })
    }
    fun changeAllScrInHTMLFile(file : File, filesPath : String){ //useless mb
        var str = file.inputStream().readBytes().toString(Charsets.UTF_8)
        str.replace("c=\"","c=\"file://$filesPath/")
        file.createNewFile()
        file.writeBytes(str.toByteArray())
    }
}
