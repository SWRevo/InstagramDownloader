package id.indosw.pascol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import id.indosw.igdownloader.model.story.ItemModel;
import id.indosw.pascol.R;
import id.indosw.pascol.databinding.ItemsWhatsappViewBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Objects;

import static id.indosw.igdownloader.util.Utils.RootDirectoryInsta;
import static id.indosw.igdownloader.util.Utils.startDownload;

public class StoriesListAdapter extends RecyclerView.Adapter<StoriesListAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<ItemModel> storyItemModelList;

    public StoriesListAdapter(Context context, ArrayList<ItemModel> list) {
        this.context = context;
        this.storyItemModelList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        return new ViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.items_whatsapp_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ItemModel itemModel = storyItemModelList.get(position);
        try {
            if (itemModel.getMedia_type()==2) {
                viewHolder.binding.ivPlay.setVisibility(View.VISIBLE);
            } else {
                viewHolder.binding.ivPlay.setVisibility(View.GONE);
            }
            Glide.with(context)
                    .load(Objects.requireNonNull(Objects.requireNonNull(itemModel.getImage_versions2()).getCandidates()).get(0).getUrl())
                    .into(viewHolder.binding.pcw);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        viewHolder.binding.tvDownload.setOnClickListener(v -> {
            if (itemModel.getMedia_type()==2) {
                startDownload(Objects.requireNonNull(itemModel.getVideo_versions()).get(0).getUrl(),
                        RootDirectoryInsta, context,"story_"+itemModel.getId()+".mp4" );
            }else {
                startDownload(Objects.requireNonNull(Objects.requireNonNull(itemModel.getImage_versions2()).getCandidates()).get(0).getUrl(),
                        RootDirectoryInsta, context, "story_"+itemModel.getId()+".png");
            }
        });


    }
    @Override
    public int getItemCount() {
        return storyItemModelList == null ? 0 : storyItemModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
         ItemsWhatsappViewBinding binding;
        public ViewHolder(ItemsWhatsappViewBinding mbinding) {
            super(mbinding.getRoot());
            this.binding = mbinding;
        }
    }
}