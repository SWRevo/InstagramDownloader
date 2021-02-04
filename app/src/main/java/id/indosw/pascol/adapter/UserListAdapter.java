package id.indosw.pascol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import id.indosw.igdownloader.interfaces.UserListInterface;
import id.indosw.igdownloader.model.story.TrayModel;
import id.indosw.pascol.R;
import id.indosw.pascol.databinding.ItemUserListBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Objects;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<TrayModel> trayModelArrayList;
    private final UserListInterface userListInterface;

    public UserListAdapter(Context context, ArrayList<TrayModel> list, UserListInterface listInterface) {
        this.context = context;
        this.trayModelArrayList = list;
        this.userListInterface = listInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        return new ViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_user_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        viewHolder.binding.realName.setText(Objects.requireNonNull(trayModelArrayList.get(position).getUser()).getFull_name());
        Glide.with(context).load(Objects.requireNonNull(trayModelArrayList.get(position).getUser()).getProfile_pic_url())
                .thumbnail(0.2f).into(viewHolder.binding.storyIcon);

        viewHolder.binding.RLStoryLayout.setOnClickListener(view -> userListInterface.userListClick(position,trayModelArrayList.get(position)));

    }
    @Override
    public int getItemCount() {
        return trayModelArrayList == null ? 0 : trayModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
         ItemUserListBinding binding;
        public ViewHolder(ItemUserListBinding mbinding) {
            super(mbinding.getRoot());
            this.binding = mbinding;
        }
    }
}