package id.indosw.pascol;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@SuppressWarnings({"unused", "deprecation"})
public class SketchwareUtil {
		public static int TOP = 1;
		public static int CENTER = 2;
		public static int BOTTOM = 3;

		@SuppressWarnings("deprecation")
		public static void CustomToast(Context context, String message, int textColor, int textSize, int bgColor, int radius, int gravity) {
				Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
				View view = toast.getView(); 
				TextView msg = view.findViewById(android.R.id.message); 
				msg.setTextSize(textSize); 
				msg.setTextColor(textColor); 
				msg.setGravity(Gravity.CENTER); 
				GradientDrawable shape = new GradientDrawable();
				shape.setColor(bgColor);
				shape.setCornerRadius(radius); 
				view.setBackgroundDrawable(shape); 
				view.setPadding(15,10,15,10);
				view.setElevation(10);
				switch(gravity){
						case 1:
								toast.setGravity(Gravity.TOP, 0,150);
								break;
						case 2:
								toast.setGravity(Gravity.CENTER, 0,0);
								break;
						case 3:
								toast.setGravity(Gravity.BOTTOM, 0,150);
								break;
				}
				toast.show();
		}

		public static void CustomToastWithIcon(Context context, String message, int textColor, int textSize, int bgColor, int radius, int gravity, int icon) {
				Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT); 
				View view = toast.getView(); 
				TextView msg = view.findViewById(android.R.id.message);
				msg.setTextSize(textSize); 
				msg.setTextColor(textColor); 
				msg.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0); 
				msg.setGravity(Gravity.CENTER); 
				msg.setCompoundDrawablePadding(10);
				GradientDrawable shape = new GradientDrawable();
				shape.setColor(bgColor);
				shape.setCornerRadius(radius); 
				view.setBackgroundDrawable(shape); 
				view.setPadding(10,10,10,10);
				view.setElevation(10);
				switch(gravity){
						case 1:
								toast.setGravity(Gravity.TOP, 0,150);
								break;
						case 2:
								toast.setGravity(Gravity.CENTER, 0,0);
								break;
						case 3:
								toast.setGravity(Gravity.BOTTOM, 0,150);
								break;
				}
				toast.show();
		}

	@SuppressWarnings("ConstantConditions")
	public static void sortListMap(final ArrayList<HashMap<String, Object>> listMap, final String key, final boolean isNumber, final boolean ascending) {
		Collections.sort(listMap, (_compareMap1, _compareMap2) -> {
			if (isNumber) {
				int _count1 = Integer.parseInt(Objects.requireNonNull(_compareMap1.get(key)).toString());
				int _count2 = Integer.parseInt(Objects.requireNonNull(_compareMap2.get(key)).toString());
				if (ascending) {
					return _count1 < _count2 ? -1 : _count1 < _count2 ? 1 : 0;
				}
				else {
					return _count1 > _count2 ? -1 : _count1 > _count2 ? 1 : 0;
				}
			}
			else {
				if (ascending) {
					return (_compareMap1.get(key).toString()).compareTo(_compareMap2.get(key).toString());
				}
				else {
					return (_compareMap2.get(key).toString()).compareTo(_compareMap1.get(key).toString());
				}
			}
		});
	}

		public static void CropImage(Activity a, String s, int i){
				try {
						Intent cropIntent = new Intent("com.android.camera.action.CROP");
						File f = new File(s);
						Uri contentUri = Uri.fromFile(f);
						cropIntent.setDataAndType(contentUri, "image/*");
						cropIntent.putExtra("crop", "true");
						cropIntent.putExtra("aspectX", 1);
						cropIntent.putExtra("aspectY", 1);
						cropIntent.putExtra("outputX", 280);
						cropIntent.putExtra("outputY", 280);
						cropIntent.putExtra("return-data", false);
						a.startActivityForResult(cropIntent, i);
				} catch (ActivityNotFoundException anfe) {
						String errorMessage = "Your device doesn't support the crop action!";
						Toast toast = Toast.makeText(a, errorMessage, Toast.LENGTH_SHORT);
						toast.show();
				}
		}

		public static boolean isConnected(Context a) {
				ConnectivityManager connectivityManager = (android.net.ConnectivityManager) 
						a.getSystemService(Activity.CONNECTIVITY_SERVICE);
				NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
				return activeNetworkInfo != null && activeNetworkInfo.isConnected();
		}

		public static String copyFromInputStream(InputStream inputStream) {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				int i;
				try {
						while ((i = inputStream.read(buf)) != -1){
								outputStream.write(buf, 0, i);
						}
						outputStream.close();
						inputStream.close();
				} catch (IOException ignored) {

				}
				return outputStream.toString();
		}

	public static void hideKeyboard(Context c){
		InputMethodManager imm = (InputMethodManager) 
		c.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
	}
	
	public static void showKeyboard(Context c){
		InputMethodManager imm = (InputMethodManager)
		c.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
	}
	
		public static void showMessage(Context _context, String _s) {
				Toast.makeText(_context, _s, Toast.LENGTH_SHORT).show();
		}

		public static int getLocationX(View _view) {
				int[] _location = new int[2];
				_view.getLocationInWindow(_location);
				return _location[0];
		}

		public static int getLocationY(View _view) {
				int[] _location = new int[2];
				_view.getLocationInWindow(_location);
				return _location[1];
		}

		public static int getRandom(int _min, int _max) {
				Random random = new Random();
				return random.nextInt(_max - _min + 1) + _min;
		}

		public static ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
				ArrayList<Double> _result = new ArrayList<>();
				SparseBooleanArray _arr = _list.getCheckedItemPositions();
				for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
						if (_arr.valueAt(_iIdx))
								_result.add((double) _arr.keyAt(_iIdx));
				}
				return _result;
		}

		public static float getDip(Context _context, int _input) {
				return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, _context.getResources().getDisplayMetrics());
		}

		public static int getDisplayWidthPixels(Context _context) {
				return _context.getResources().getDisplayMetrics().widthPixels;
		}

		public static int getDisplayHeightPixels(Context _context) {
				return _context.getResources().getDisplayMetrics().heightPixels;
		}

		@SuppressWarnings("rawtypes")
		public static void getAllKeysFromMap(Map<String, Object> map, ArrayList<String> output) {
				if (output == null) return;
				output.clear();
				if (map == null || map.size() <= 0) return;
			for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
				output.add((String) ((Map.Entry) stringObjectEntry).getKey());
			}
		}
}