package id.indosw.pascol;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import id.indosw.igdownloader.util.ClipboardListener;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class MainActivity extends  AppCompatActivity  {

	private  ClipboardManager clipBoard;
	private  MainActivity activity;
	private String CopyKey = "";
	private String CopyValue = "";
	String[] permissions = new String[]{
			Manifest.permission.WRITE_EXTERNAL_STORAGE,
			Manifest.permission.READ_EXTERNAL_STORAGE
	};

	private LinearLayout linear1;
	private ImageView imageview1;

	private final Intent i = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize();
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
				|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		} else if (requestCode == 101) {
			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				_callInstaActivity();
			}
		}
	}

	private void initialize() {

		linear1 = findViewById(R.id.linear1);
		imageview1 = findViewById(R.id.imageview1);

		imageview1.setOnClickListener(_view -> {
			i.setClass(getApplicationContext(), InstagramActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
		});
	}

	private void initializeLogic() {
		activity = this;
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); getWindow().setStatusBarColor(Color.TRANSPARENT);
		linear1.setBackground(new GradientDrawable(GradientDrawable.Orientation.BR_TL,new int[] {0xFF261B40,0xFF131418}));
		_initView();


	}

	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
	}


	@Override
	public void onResume() {
		super.onResume();
		clipBoard = (ClipboardManager) activity.getSystemService(CLIPBOARD_SERVICE);
	}
	public void _initView () {
		clipBoard = (ClipboardManager) activity.getSystemService(CLIPBOARD_SERVICE);
		if (activity.getIntent().getExtras() != null) {
			for (String key : activity.getIntent().getExtras().keySet()) {
				CopyKey = key;
				String value = activity.getIntent().getExtras().getString(CopyKey);
				if (CopyKey.equals("android.intent.extra.TEXT")) {
					CopyValue = activity.getIntent().getExtras().getString(CopyKey);
				} else {
					CopyValue = "";
				}
				_callText(value);
			}
		}
		if (clipBoard != null) {
			clipBoard.addPrimaryClipChangedListener(new ClipboardListener() {
				@Override
				public void onPrimaryClipChanged() {
					try {
						showNotification(Objects.requireNonNull(clipBoard.getPrimaryClip().getItemAt(0).getText()).toString());
					} catch (
							Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}


	public void _callText (final String _CopiedText) {
		try {
			if (_CopiedText.contains("instagram.com")) {
				if (Build.VERSION.SDK_INT >= 23) {
					_checkPermissions(101);
				} else {
					_callInstaActivity();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void _callInstaActivity () {
		Intent i = new Intent(activity, InstagramActivity.class);
		i.putExtra("CopyIntent", CopyValue);
		startActivity(i);
	}


	public void _checkPermissions (final int _type) {
		int result;
		List<String> listPermissionsNeeded = new ArrayList<>();
		for (String p : permissions) {
			result = ContextCompat.checkSelfPermission(activity, p);
			if (result != PackageManager.PERMISSION_GRANTED) {
				listPermissionsNeeded.add(p);
			}
		}
		if (!listPermissionsNeeded.isEmpty()) {
			ActivityCompat.requestPermissions(activity,
					listPermissionsNeeded.toArray(new String[0]), _type);
		} else {
			if (_type == 101) {
				_callInstaActivity();
			}
		}
	}


	public void _libsNotification () {
	}
	public void showNotification(String Text) {
		if (Text.contains("instagram.com")) {
			Intent intent = new Intent(activity, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("Notification", Text);
			PendingIntent pendingIntent = PendingIntent.getActivity(activity, 0, intent, PendingIntent.FLAG_ONE_SHOT);
			NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
				NotificationChannel mChannel = new NotificationChannel(getResources().getString(R.string.app_name),
						getResources().getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH);
				mChannel.enableLights(true);
				mChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
				notificationManager.createNotificationChannel(mChannel);
			}
			NotificationCompat.Builder notificationBuilder;
			notificationBuilder = new NotificationCompat.Builder(activity, getResources().getString(R.string.app_name))
					.setAutoCancel(true)
					.setSmallIcon(R.mipmap.ic_launcher_round)
					.setColor(getResources().getColor(R.color.black))
					.setLargeIcon(BitmapFactory.decodeResource(activity.getResources(),
							R.mipmap.ic_launcher_round))
					.setDefaults(Notification.DEFAULT_ALL)
					.setPriority(NotificationCompat.PRIORITY_HIGH)
					.setContentTitle("Copied text")
					.setContentText(Text)
					.setChannelId(getResources().getString(R.string.app_name))
					.setFullScreenIntent(pendingIntent, true);
			notificationManager.notify(1, notificationBuilder.build());
		}
	}
	private void foo(){
	}



}
