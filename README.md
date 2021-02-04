# InstagramDownloader
[![](https://jitpack.io/v/SWRevo/InstagramDownloader.svg)](https://jitpack.io/#SWRevo/InstagramDownloader)

Implementation Instagram Feed & Story Downloader.

## Requirements

- Java 1.8
- This library made with kotlin language

## Gradle

Add the following to your `build.gradle` to use:
```
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.SWRevo:InstagramDownloader:1.1.0'
    
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'com.squareup.okhttp3:okhttp:4.9.0'

    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.7.1'
    implementation 'com.squareup.retrofit2:converter-gson:2.7.1'
    implementation 'com.squareup.retrofit2:retrofit:2.7.1'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.3.1'
    implementation 'com.squareup.okhttp3:okhttp:4.9.0'


    implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
    implementation 'io.reactivex.rxjava2:rxjava:2.2.8'

    implementation 'org.apache.commons:commons-lang3:3.8.1'
    implementation 'commons-io:commons-io:2.6'
    implementation 'org.jsoup:jsoup:1.13.1'
}
```

## Usage
## Activity Downloader :

```java
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import id.indosw.igdownloader.api.CommonClassForAPI;
import id.indosw.igdownloader.interfaces.UserListInterface;
import id.indosw.igdownloader.model.Edge;
import id.indosw.igdownloader.model.EdgeSidecarToChildren;
import id.indosw.igdownloader.model.ResponseModel;
import id.indosw.igdownloader.model.story.FullDetailModel;
import id.indosw.igdownloader.model.story.StoryModel;
import id.indosw.igdownloader.model.story.TrayModel;
import id.indosw.igdownloader.util.SharePrefs;
import id.indosw.igdownloader.util.Utils;
import id.indosw.pascol.adapter.StoriesListAdapter;
import id.indosw.pascol.adapter.UserListAdapter;
import io.reactivex.observers.DisposableObserver;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;
import static id.indosw.igdownloader.util.Utils.RootDirectoryInsta;
import static id.indosw.igdownloader.util.Utils.createFileFolder;
import static id.indosw.igdownloader.util.Utils.startDownload;

@SuppressWarnings({"FieldCanBeLocal", "unused", "ConstantConditions"})
public class InstaDlFragmentActivity extends  Fragment implements UserListInterface {


	private CommonClassForAPI commonClassForAPI;
	private String PhotoUrl = "";
	private String VideoUrl = "";
	private UserListAdapter userListAdapter;
	private StoriesListAdapter storiesListAdapter;
	private InstaDlFragmentActivity activity;
	private ClipboardManager clipBoard;

	private ScrollView scrll;
	private LinearLayout linear1;
	private CircleImageView c_imageviewDummy;
	private LinearLayout linear2;
	private TextInputLayout textinputlayout1;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private ImageView imgInstagram;
	private TextView textview1;
	private LinearLayout linear5;
	private TextView tvNoteLoginNeeded;
	@SuppressLint("UseSwitchCompatOrMaterialCode")
	private Switch SwitchLogin;
	private EditText link;
	private MaterialButton btnDownload;
	private MaterialButton btnPaste;
	private RecyclerView RVUserList;
	private ProgressBar prLoadingBar;
	private TextView textview2;
	private RecyclerView RVStories;

	private String CopyIntent ="";
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.insta_dl_fragment, _container, false);
		initialize(_view);
		initializeLogic();
		return _view;
	}

	private void initialize(View _view) {

		scrll = _view.findViewById(R.id.scrll);
		linear1 = _view.findViewById(R.id.linear1);
		c_imageviewDummy = _view.findViewById(R.id.c_imageviewDummy);
		linear2 = _view.findViewById(R.id.linear2);
		textinputlayout1 = _view.findViewById(R.id.textinputlayout1);
		linear3 = _view.findViewById(R.id.linear3);
		linear4 = _view.findViewById(R.id.linear4);
		imgInstagram = _view.findViewById(R.id.imgInstagram);
		textview1 = _view.findViewById(R.id.textview1);
		linear5 = _view.findViewById(R.id.linear5);
		tvNoteLoginNeeded = _view.findViewById(R.id.tvNoteLoginNeeded);
		SwitchLogin = _view.findViewById(R.id.SwitchLogin);
		link = _view.findViewById(R.id.link);
		btnDownload = _view.findViewById(R.id.btnDownload);
		btnPaste = _view.findViewById(R.id.btnPaste);
		RVUserList = _view.findViewById(R.id.RVUserList);
		prLoadingBar = _view.findViewById(R.id.prLoadingBar);
		textview2 = _view.findViewById(R.id.textview2);
		RVStories = _view.findViewById(R.id.RVStories);

		imgInstagram.setOnClickListener(_view14 -> Utils.OpenApp(Objects.requireNonNull(getActivity()),"com.instagram.android"));

		SwitchLogin.setOnClickListener(_view13 -> {
			if (SwitchLogin.isChecked()) {
				if (!Objects.requireNonNull(SharePrefs.getInstance(getActivity())).getBoolean(SharePrefs.ISINSTALOGIN)) {
					Intent intent = new Intent(getActivity(),
							LoginActivity.class);
					startActivityForResult(intent, 100);
				}else {
					AlertDialog.Builder ab = new AlertDialog.Builder(getActivity());
					ab.setPositiveButton(getResources().getString(R.string.yes), (dialog, id) -> {
						Objects.requireNonNull(SharePrefs.getInstance(getActivity())).putBoolean(SharePrefs.ISINSTALOGIN, false);
						Objects.requireNonNull(SharePrefs.getInstance(getActivity())).putString(SharePrefs.COOKIES, "");
						Objects.requireNonNull(SharePrefs.getInstance(getActivity())).putString(SharePrefs.CSRF, "");
						Objects.requireNonNull(SharePrefs.getInstance(getActivity())).putString(SharePrefs.SESSIONID, "");
						Objects.requireNonNull(SharePrefs.getInstance(getActivity())).putString(SharePrefs.USERID, "");

						if (Objects.requireNonNull(SharePrefs.getInstance(getActivity())).getBoolean(SharePrefs.ISINSTALOGIN)) {
							SwitchLogin.setChecked(true);
						} else {
							SwitchLogin.setChecked(false);
							RVUserList.setVisibility(View.GONE);
							RVStories.setVisibility(View.GONE);
							tvNoteLoginNeeded.setVisibility(View.VISIBLE);
						}
						dialog.cancel();

					});
					ab.setNegativeButton(getResources().getString(R.string.cancel), (dialog, id) -> dialog.cancel());
					AlertDialog alert = ab.create();
					alert.setTitle(getResources().getString(R.string.do_u_want_to_download_media_from_pvt));
					alert.show();
				}
			}
			else {
				Objects.requireNonNull(SharePrefs.getInstance(getContext())).putBoolean(SharePrefs.ISINSTALOGIN, false);
				Objects.requireNonNull(SharePrefs.getInstance(getContext())).putString(SharePrefs.COOKIES, "");
				Objects.requireNonNull(SharePrefs.getInstance(getContext())).putString(SharePrefs.CSRF, "");
				Objects.requireNonNull(SharePrefs.getInstance(getContext())).putString(SharePrefs.SESSIONID, "");
				Objects.requireNonNull(SharePrefs.getInstance(getContext())).putString(SharePrefs.USERID, "");
			}
		});

		btnDownload.setOnClickListener(_view12 -> _onClickButtonDownload());

		btnPaste.setOnClickListener(_view1 -> _PasteText());
	}

	private void initializeLogic() {
		_onCreateGetInstanceCommonApi();
		_initRecyclerViewUserList();
		_iniRecyclerViewStory();
		activity = this;
		scrll.setVerticalScrollBarEnabled(false);
		scrll.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
		CopyIntent = Objects.requireNonNull(getActivity()).getIntent().getStringExtra("CopyIntent");
		clipBoard = (ClipboardManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CLIPBOARD_SERVICE);
	}


	@SuppressWarnings("unused")
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			super.onActivityResult(requestCode, resultCode, data);
			if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
				String requiredValue = data.getStringExtra("key");
				if (Objects.requireNonNull(SharePrefs.getInstance(getActivity())).getBoolean(SharePrefs.ISINSTALOGIN)){
					SwitchLogin.setChecked(true);
					_layoutCondition();
					_callStoriesApi();
				} else {
					SwitchLogin.setChecked(false);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}


	@Override
	public void onResume() {
		super.onResume();
		activity = this;
		CopyIntent = Objects.requireNonNull(getActivity()).getIntent().getStringExtra("CopyIntent");
		clipBoard = (ClipboardManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CLIPBOARD_SERVICE);
		_PasteText();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		instaObserver.dispose();
	}
	public void _getInstagramData () {
		try {
			createFileFolder();
			URL url = new URL(link.getText().toString());
			String host = url.getHost();
			Log.e("initViews: ", host);
			if (host.equals("www.instagram.com")) {
				_callDownload(link.getText().toString());
			} else {
				Utils.setToast(getActivity(), getResources().getString(R.string.enter_valid_url));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void _callDownload (final String _Url) {
		String UrlWithoutQP = _getUrlWithoutParameters(_Url);
		UrlWithoutQP = UrlWithoutQP + "?__a=1";

		try {
			Utils utils = new Utils(getActivity());
			if (utils.isNetworkAvailable()) {
				if (commonClassForAPI != null) {
					Utils.showProgressDialog(getActivity());
					commonClassForAPI.callResult(instaObserver, UrlWithoutQP, "ds_user_id="+ Objects.requireNonNull(SharePrefs.getInstance(getActivity())).getString(SharePrefs.USERID) +"; sessionid="+ Objects.requireNonNull(SharePrefs.getInstance(getActivity())).getString(SharePrefs.SESSIONID));
				}
			} else {
				Utils.setToast(getActivity(), getResources().getString(R.string.no_net_conn));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String _getUrlWithoutParameters (final String _url) {
		try {
			URI uri = new URI(_url);
			return new URI(uri.getScheme(),
					uri.getAuthority(),
					uri.getPath(),
					null, // Ignore the query part of the input url

					uri.getFragment()).toString();
		} catch (Exception e) {
			e.printStackTrace();
			Utils.setToast(getActivity(), getResources().getString(R.string.enter_valid_url));
			return "";
		}
	}


	@SuppressWarnings("unused")
	public void _library () {
	}
	private final DisposableObserver<JsonObject> instaObserver = new DisposableObserver<JsonObject>() {
		@Override
		public void onNext(@NotNull JsonObject versionList) {
			Utils.hideProgressDialog();
			try {
				Log.e("onNext: ", versionList.toString());
				Type listType = new TypeToken<ResponseModel>() {}.getType();
				ResponseModel responseModel = new Gson().fromJson(versionList.toString(), listType);
				EdgeSidecarToChildren edgeSidecarToChildren = Objects.requireNonNull(Objects.requireNonNull(responseModel.getGraphql()).getShortcode_media()).getEdge_sidecar_to_children();
				if (edgeSidecarToChildren != null) {
					List<Edge> edgeArrayList = edgeSidecarToChildren.getEdges();
					for (int i = 0; i < Objects.requireNonNull(edgeArrayList).size(); i++) {
						if (Objects.requireNonNull(edgeArrayList.get(i).getNode()).isIs_video()) {
							VideoUrl = Objects.requireNonNull(edgeArrayList.get(i).getNode()).getVideo_url();
							startDownload(VideoUrl, RootDirectoryInsta, getActivity(), _getVideoFilenameFromURL(VideoUrl));
							link.setText("");
							VideoUrl = "";
						} else {
							PhotoUrl = Objects.requireNonNull(Objects.requireNonNull(edgeArrayList.get(i).getNode()).getDisplay_resources()).get(Objects.requireNonNull(Objects.requireNonNull(edgeArrayList.get(i).getNode()).getDisplay_resources()).size() - 1).getSrc();
							startDownload(PhotoUrl, RootDirectoryInsta, getActivity(), _getImageFilenameFromURL(PhotoUrl));
							PhotoUrl = "";
							link.setText("");
						}
					}
				} else {
					boolean isVideo = responseModel.getGraphql().getShortcode_media().isIs_video();
					if (isVideo) {
						VideoUrl = responseModel.getGraphql().getShortcode_media().getVideo_url();
						//new DownloadFileFromURL().execute(VideoUrl,getFilenameFromURL(VideoUrl));
						startDownload(VideoUrl, RootDirectoryInsta, getActivity(), _getVideoFilenameFromURL(VideoUrl));
						VideoUrl = "";
					} else {
						PhotoUrl = Objects.requireNonNull(responseModel.getGraphql().getShortcode_media().getDisplay_resources()).get(responseModel.getGraphql().getShortcode_media().getDisplay_resources().size() - 1).getSrc();
						startDownload(PhotoUrl, RootDirectoryInsta, getActivity(), _getImageFilenameFromURL(PhotoUrl));
						PhotoUrl = "";
						// new DownloadFileFromURL().execute(PhotoUrl,getFilenameFromURL(PhotoUrl));
					}
					link.setText("");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Override
		public void onError(Throwable e) {
			Utils.hideProgressDialog();
			e.printStackTrace();
		}
		@Override
		public void onComplete() {
			Utils.hideProgressDialog();
		}
	};
	@SuppressWarnings("unused")
	private void foo(){
	}


	public String _getVideoFilenameFromURL (final String _url) {
		try {
			return new File(new URL(_url).getPath()).getName();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return System.currentTimeMillis() + ".mp4";
		}
	}


	public String _getImageFilenameFromURL (final String _url) {
		try {
			return new File(new URL(_url).getPath()).getName();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return System.currentTimeMillis() + ".png";
		}
	}


	public void _onClickButtonDownload () {
		String LL = link.getText().toString();
		if (LL.equals("")) {
			Utils.setToast(getActivity(), getResources().getString(R.string.enter_url));
		} else if (!Patterns.WEB_URL.matcher(LL).matches()) {
			Utils.setToast(getActivity(), getResources().getString(R.string.enter_valid_url));
		} else {
			_getInstagramData();
		}
	}


	public void _onCreateGetInstanceCommonApi () {
		commonClassForAPI = CommonClassForAPI.getInstance();
		createFileFolder();
	}


	public void _initRecyclerViewUserList () {
		GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
		RVUserList.setLayoutManager(mLayoutManager);
		RVUserList.setNestedScrollingEnabled(false);
		mLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
		if (Objects.requireNonNull(SharePrefs.getInstance(getActivity())).getBoolean(SharePrefs.ISINSTALOGIN)) {
			_layoutCondition();
			_callStoriesApi();
			SwitchLogin.setChecked(true);
		}else {
			SwitchLogin.setChecked(false);
		}
	}


	@SuppressWarnings("unused")
	public void _LibsStoryObserver () {
	}
	private final DisposableObserver<StoryModel> storyObserver = new DisposableObserver<StoryModel>() {
		@Override
		public void onNext(@NotNull StoryModel response) {
			RVUserList.setVisibility(View.VISIBLE);
			prLoadingBar.setVisibility(View.GONE);
			try {
				userListAdapter = new UserListAdapter(getActivity(), response.getTray(), activity);
				RVUserList.setAdapter(userListAdapter);
				userListAdapter.notifyDataSetChanged();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onError(Throwable e) {
			prLoadingBar.setVisibility(View.GONE);
			e.printStackTrace();
		}

		@Override
		public void onComplete() {
			prLoadingBar.setVisibility(View.GONE);
		}
	};
	@SuppressWarnings("unused")
	private void libsStoryObserverFoo() {
	}


	public void _iniRecyclerViewStory () {
		GridLayoutManager mLayoutManager1 = new GridLayoutManager(getContext(), 3);
		RVStories.setLayoutManager(mLayoutManager1);
		RVStories.setNestedScrollingEnabled(false);
		mLayoutManager1.setOrientation(RecyclerView.VERTICAL);
	}


	public void _layoutCondition () {
		tvNoteLoginNeeded.setVisibility(View.GONE);
	}


	public void _callStoriesApi () {
		try {
			Utils utils = new Utils(getActivity());
			if (utils.isNetworkAvailable()) {
				if (commonClassForAPI != null) {
					prLoadingBar.setVisibility(View.VISIBLE);
					commonClassForAPI.getStories(storyObserver, "ds_user_id=" + Objects.requireNonNull(SharePrefs.getInstance(getActivity())).getString(SharePrefs.USERID)
							+ "; sessionid=" + Objects.requireNonNull(SharePrefs.getInstance(getActivity())).getString(SharePrefs.SESSIONID));
				}
			} else {
				Utils.setToast(getActivity(), Objects.requireNonNull(getActivity()).getResources().getString(R.string.no_net_conn));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@SuppressWarnings("unused")
	public void _LibsStoryDetailObserver () {
	}
	@Override
	public void userListClick(int position, TrayModel trayModel) {
		_callStoriesDetailApi(String.valueOf(Objects.requireNonNull(Objects.requireNonNull(trayModel).getUser()).getPk()));
	}
	private final DisposableObserver<FullDetailModel> storyDetailObserver = new DisposableObserver<FullDetailModel>() {
		@Override
		public void onNext(@NotNull FullDetailModel response) {
			RVUserList.setVisibility(View.VISIBLE);
			prLoadingBar.setVisibility(View.GONE);
			try {
				storiesListAdapter = new StoriesListAdapter(getActivity(), Objects.requireNonNull(response.getReel_feed()).getItems());
				RVStories.setAdapter(storiesListAdapter);
				storiesListAdapter.notifyDataSetChanged();
			} catch (Exception e) {
				e.printStackTrace();
				SketchwareUtil.showMessage(getContext(), e.getMessage());
			}
		}

		@Override
		public void onError(Throwable e) {
			prLoadingBar.setVisibility(View.GONE);
			e.printStackTrace();
			SketchwareUtil.showMessage(getContext(), e.getMessage());
		}

		@Override
		public void onComplete() {
			prLoadingBar.setVisibility(View.GONE);
		}
	};
	@SuppressWarnings("unused")
	private void storyDetailObserverFoo() {
	}


	public void _callStoriesDetailApi (final String _UserId) {
		try {
			Utils utils = new Utils(getActivity());
			if (utils.isNetworkAvailable()) {
				if (commonClassForAPI != null) {
					prLoadingBar.setVisibility(View.VISIBLE);
					commonClassForAPI.getFullDetailFeed(storyDetailObserver, _UserId, "ds_user_id=" + Objects.requireNonNull(SharePrefs.getInstance(getContext())).getString(SharePrefs.USERID)
							+ "; sessionid=" + Objects.requireNonNull(SharePrefs.getInstance(getContext())).getString(SharePrefs.SESSIONID));
				}
			} else {
				Utils.setToast(getActivity(), activity
						.getResources().getString(R.string.no_net_conn));
			}
		} catch (Exception e) {
			e.printStackTrace();
			SketchwareUtil.showMessage(getContext(), e.getMessage());
		}
	}


	public void _PasteText () {
		try {
			link.setText("");
			//CopyIntent = Objects.requireNonNull(getActivity()).getIntent().getStringExtra("CopyIntent");
			if (CopyIntent.equals("")) {
				//noinspection StatementWithEmptyBody
				if (!(clipBoard.hasPrimaryClip())) {

				} else if (!(clipBoard.getPrimaryClipDescription().hasMimeType(MIMETYPE_TEXT_PLAIN))) {
					if (clipBoard.getPrimaryClip().getItemAt(0).getText().toString().contains("instagram.com")) {
						link.setText(clipBoard.getPrimaryClip().getItemAt(0).getText().toString());
					}

				} else {
					ClipData.Item item = clipBoard.getPrimaryClip().getItemAt(0);
					if (item.getText().toString().contains("instagram.com")) {
						link.setText(item.getText().toString());
					}

				}
			} else {
				if (CopyIntent.contains("instagram.com")) {
					link.setText(CopyIntent);
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}

```

## License

    Copyright 2021 indosw

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

