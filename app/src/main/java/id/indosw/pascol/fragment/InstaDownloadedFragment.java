package id.indosw.pascol.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import id.indosw.igdownloader.interfaces.FileListClickInterface;
import id.indosw.pascol.R;
import id.indosw.pascol.activity.FullViewActivity;
import id.indosw.pascol.InstagramActivity;
import id.indosw.pascol.adapter.FileListAdapter;
import id.indosw.pascol.databinding.FragmentHistoryBinding;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import static androidx.databinding.DataBindingUtil.inflate;
import static id.indosw.igdownloader.util.Utils.RootDirectoryInstaShow;

@SuppressWarnings("unused")
public class InstaDownloadedFragment extends Fragment implements FileListClickInterface {
    private FragmentHistoryBinding binding;
    private ArrayList<File> fileArrayList;
    private InstagramActivity activity;
    public static InstaDownloadedFragment newInstance(String param1) {
        InstaDownloadedFragment fragment = new InstaDownloadedFragment();
        Bundle args = new Bundle();
        args.putString("m", param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(@NotNull Context _context) {
        super.onAttach(_context);
        activity = (InstagramActivity) _context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString("m");
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        activity = (InstagramActivity) getActivity();
        getAllFiles();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = inflate(inflater, R.layout.fragment_history, container, false);
        initViews();
        return binding.getRoot();
    }
    private void initViews(){
        binding.swiperefresh.setOnRefreshListener(() -> {
            getAllFiles();
            binding.swiperefresh.setRefreshing(false);
        });
    }

    private void getAllFiles(){
        fileArrayList = new ArrayList<>();
        File[] files = Objects.requireNonNull(RootDirectoryInstaShow).listFiles();
        if (files!=null) {
            Collections.addAll(fileArrayList, files);
            FileListAdapter fileListAdapter = new FileListAdapter(activity, fileArrayList, InstaDownloadedFragment.this);
            binding.rvFileList.setAdapter(fileListAdapter);
        }
    }
    @Override
    public void getPosition(int position, File file) {
        Intent inNext = new Intent(activity, FullViewActivity.class);
        inNext.putExtra("ImageDataFile", fileArrayList);
        inNext.putExtra("Position", position);
        activity.startActivity(inNext);
    }
}