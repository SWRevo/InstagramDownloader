package id.indosw.pascol;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import org.jetbrains.annotations.NotNull;

import id.indosw.pascol.fragment.InstaDownloadedFragment;

public class InstagramActivity extends  AppCompatActivity  {


	private LinearLayout base;
	@SuppressWarnings("SpellCheckingInspection")
	private TabLayout tablayout1;
	private ViewPager viewpager1;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.instagram);
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
		}
	}
	
	private void initialize() {
		
		base = findViewById(R.id.base);
		tablayout1 = findViewById(R.id.tablayout1);
		viewpager1 = findViewById(R.id.viewpager1);
	}
	
	@SuppressLint("ObsoleteSdkInt")
	private void initializeLogic() {
		base.setBackground(new GradientDrawable(GradientDrawable.Orientation.BR_TL,new int[] {0xFF261B40,0xFF131418}));
		
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			Window w =InstagramActivity.this.getWindow();
			w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFFEA005E);
		}
		ViewPagerAdapter vpa = new ViewPagerAdapter(getSupportFragmentManager());
		vpa.addFragment(new InstaDlFragmentActivity(), "InstaSave");
		vpa.addFragment(new InstaDownloadedFragment(), "Downloaded");
		viewpager1.setAdapter(vpa);
		tablayout1.setupWithViewPager(viewpager1);
		tablayout1.setTabTextColors(0xFF131418, 0xFFF5FFFC);
		tablayout1.setTabRippleColor(new ColorStateList(new int[][]{new int[]{android.R.attr.state_pressed}},
		
		new int[] {0xFFF5FFFC}));
		tablayout1.setSelectedTabIndicatorColor(0xFFF5FFFC);
		//noinspection deprecation
		tablayout1.setSelectedTabIndicatorHeight(5);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			    getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.bottomNavigation));
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
	}
	
	
}
