package id.indosw.pascol.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import java.io.File;
import java.util.ArrayList;

import id.indosw.igdownloader.util.Utils;
import id.indosw.pascol.R;
import id.indosw.pascol.adapter.ShowImagesAdapter;
import id.indosw.pascol.databinding.ActivityFullViewBinding;

import static id.indosw.igdownloader.util.Utils.shareImage;
import static id.indosw.igdownloader.util.Utils.shareImageVideoOnWhatsapp;
import static id.indosw.igdownloader.util.Utils.shareVideo;

public class FullViewActivity extends AppCompatActivity {
    private ActivityFullViewBinding binding;
    private FullViewActivity activity;
    private ArrayList<File> fileArrayList;
    private int Position = 0;
    ShowImagesAdapter showImagesAdapter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_full_view);
        activity = this;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fileArrayList = (ArrayList<File>) getIntent().getSerializableExtra("ImageDataFile");
            Position = getIntent().getIntExtra("Position",0);
        }
        initViews();

    }

    @SuppressWarnings("RedundantIfStatement")
    public void initViews(){
        showImagesAdapter=new ShowImagesAdapter(this, fileArrayList,FullViewActivity.this);
        binding.vpView.setAdapter(showImagesAdapter);
        binding.vpView.setCurrentItem(Position);

        binding.vpView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                Position=arg0;
                System.out.println("Current position=="+Position);
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int num) {
            }
        });

        binding.imDelete.setOnClickListener(view -> {

            AlertDialog.Builder ab = new AlertDialog.Builder(activity);
            ab.setPositiveButton(getResources().getString(R.string.yes), (dialog, id) -> {
                boolean b = fileArrayList.get(Position).delete();
                if (b) {
                    deleteFileAA(Position);
                }
            });
            ab.setNegativeButton(getResources().getString(R.string.no), (dialog, id) -> dialog.cancel());
            AlertDialog alert = ab.create();
            alert.setTitle(getResources().getString(R.string.do_u_want_to_dlt));
            alert.show();
        });
        binding.imShare.setOnClickListener(view -> {
            if (fileArrayList.get(Position).getName().contains(".mp4")){
                Log.d("SSSSS", "onClick: "+fileArrayList.get(Position)+"");
                shareVideo(activity,fileArrayList.get(Position).getPath());
            }else {
                shareImage(activity,fileArrayList.get(Position).getPath());
            }
        });
        binding.imWhatsappShare.setOnClickListener(view -> {
            if (fileArrayList.get(Position).getName().contains(".mp4")){
                shareImageVideoOnWhatsapp(activity,fileArrayList.get(Position).getPath(),true);
            }else {
                shareImageVideoOnWhatsapp(activity,fileArrayList.get(Position).getPath(),false);
            }
        });
        binding.imClose.setOnClickListener(v -> onBackPressed());
    }
    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }
    public void deleteFileAA(int position){
        fileArrayList.remove(position);
        showImagesAdapter.notifyDataSetChanged();
        Utils.setToast(activity,getResources().getString(R.string.file_deleted));
        if (fileArrayList.size()==0){
            onBackPressed();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
