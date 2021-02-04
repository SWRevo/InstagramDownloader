@file:Suppress("DEPRECATION")

package id.indosw.igdownloader.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.app.DownloadManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.media.MediaScannerConnection
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import id.indosw.igdownloader.R
import java.io.File

class Utils(@field:SuppressLint("StaticFieldLeak") private var context: Context?) {
    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    companion object {
        private var customDialog: Dialog? = null
        @kotlin.jvm.JvmField
        var RootDirectoryInsta: String? = "/StatusSaver/Insta/"
        @kotlin.jvm.JvmField
        var RootDirectoryInstaShow: File? = File(Environment.getExternalStorageDirectory().toString() + "/Download/StatusSaver/Insta")
        @kotlin.jvm.JvmStatic
        fun setToast(_mContext: Context?, str: String?) {
            val toast = Toast.makeText(_mContext, str, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }

        @kotlin.jvm.JvmStatic
        fun createFileFolder() {
            if (!RootDirectoryInstaShow!!.exists()) {
                RootDirectoryInstaShow!!.mkdirs()
            }
        }

        @kotlin.jvm.JvmStatic
        fun showProgressDialog(activity: Activity?) {
            println("Show")
            if (customDialog != null) {
                customDialog!!.dismiss()
                customDialog = null
            }
            customDialog = activity?.let { Dialog(it) }
            val inflater = LayoutInflater.from(activity)
            @SuppressLint("InflateParams") val mView = inflater.inflate(R.layout.progress_dialog, null)
            customDialog!!.setCancelable(false)
            customDialog!!.setContentView(mView)
            if (!customDialog!!.isShowing && !activity!!.isFinishing) {
                customDialog!!.show()
            }
        }

        @kotlin.jvm.JvmStatic
        fun hideProgressDialog() {
            println("Hide")
            if (customDialog != null && customDialog!!.isShowing) {
                customDialog!!.dismiss()
            }
        }

        @kotlin.jvm.JvmStatic
        @SuppressLint("ObsoleteSdkInt")
        fun startDownload(downloadPath: String?, destinationPath: String?, context: Context?, FileName: String?) {
            setToast(context, context!!.resources.getString(R.string.download_started))
            val uri = Uri.parse(downloadPath) // Path where you want to download file.
            val request = DownloadManager.Request(uri)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI) // Tell on which network you want to download file.
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED) // This will show notification on top when downloading the file.
            request.setTitle(FileName + "") // Title for notification.
            request.setVisibleInDownloadsUi(true)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, destinationPath + FileName) // Storage directory path
            (context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager).enqueue(request) // This will start downloading
            try {
                if (Build.VERSION.SDK_INT >= 19) {
                    MediaScannerConnection.scanFile(context, arrayOf(File(Environment.DIRECTORY_DOWNLOADS + "/" + destinationPath + FileName).absolutePath),
                            null) { _: String?, _: Uri? -> }
                } else {
                    context.sendBroadcast(Intent("android.intent.action.MEDIA_MOUNTED", Uri.fromFile(File(Environment.DIRECTORY_DOWNLOADS + "/" + destinationPath + FileName))))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @kotlin.jvm.JvmStatic
        fun shareImage(context: Context?, filePath: String?) {
            try {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_TEXT, context!!.resources.getString(R.string.share_txt))
                val path = MediaStore.Images.Media.insertImage(context.contentResolver, filePath, "", null)
                val screenshotUri = Uri.parse(path)
                intent.putExtra(Intent.EXTRA_STREAM, screenshotUri)
                intent.type = "image/*"
                context.startActivity(Intent.createChooser(intent, context.resources.getString(R.string.share_image_via)))
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        @kotlin.jvm.JvmStatic
        fun shareImageVideoOnWhatsapp(context: Context?, filePath: String?, isVideo: Boolean) {
            val imageUri = Uri.parse(filePath)
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.setPackage("com.whatsapp")
            shareIntent.putExtra(Intent.EXTRA_TEXT, "")
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri)
            if (isVideo) {
                shareIntent.type = "video/*"
            } else {
                shareIntent.type = "image/*"
            }
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            try {
                context!!.startActivity(shareIntent)
            } catch (e: Exception) {
                setToast(context, context!!.resources.getString(R.string.whatsapp_not_installed))
            }
        }

        @kotlin.jvm.JvmStatic
        fun shareVideo(context: Context?, filePath: String?) {
            val mainUri = Uri.parse(filePath)
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "video/mp4"
            sharingIntent.putExtra(Intent.EXTRA_STREAM, mainUri)
            sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            try {
                context!!.startActivity(Intent.createChooser(sharingIntent, "Share Video using"))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, context!!.resources.getString(R.string.no_app_installed), Toast.LENGTH_LONG).show()
            }
        }

        @Suppress("FunctionName")
        @kotlin.jvm.JvmStatic
        fun OpenApp(context: Context?, Package: String?) {
            val launchIntent = Package?.let { context!!.packageManager.getLaunchIntentForPackage(it) }
            if (launchIntent != null) {
                context!!.startActivity(launchIntent)
            } else {
                setToast(context, context!!.resources.getString(R.string.app_not_available))
            }
        }

        fun isNullOrEmpty(s: String?): Boolean {
            return s == null || s.isEmpty() || s.equals("null", ignoreCase = true) || s.equals("0", ignoreCase = true)
        }
    }
}