package id.vn.minhlamdev.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.vn.minhlamdev.menuqltv.R;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {
    private ArrayList<Member> items;
    private Context context;
    private int clickedPosition;
    private Member clickedMem;

    public int GetClickedPosition() {
        return clickedPosition;
    }

    public void setClickedPosition(int clickedPosition) {
        this.clickedPosition = clickedPosition;
    }

    public Member getClickedMem() {
        return clickedMem;
    }

    public void setClickedMem(Member clickedMem) {
        this.clickedMem = clickedMem;
    }

    public MemberAdapter(ArrayList<Member> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public MemberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.thanhvien_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MemberAdapter.ViewHolder holder, int position) {
        Member member = items.get(position);
        Picasso.with(context).load(member.getImage()).into(holder.ivAvatar);
        holder.etName.setText(member.getName());
        holder.etDescription.setText(member.getDescription());


//        holder.ivAvatar.setOnClickListener(v -> {
//            RecyclerView recyclerView = (RecyclerView) holder.ivAvatar.getParent().getParent();
//            recyclerView.showContextMenuForChild(holder.ivAvatar);
//            setClickedPosition(position);
//        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivAvatar;
        EditText etName;
        EditText etDescription;
        TextView tvMemberId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivAvatar = itemView.findViewById(R.id.avatar);
            etName = itemView.findViewById(R.id.name);
            etDescription = itemView.findViewById(R.id.description);

//            event();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            RecyclerView recyclerView = (RecyclerView) v.getParent();
            recyclerView.showContextMenuForChild(ivAvatar);

            setClickedMem(new Member(etName.getText().toString(), etDescription.getText().toString()));

            int pos = getAdapterPosition();
            setClickedPosition(pos);
        }

//        private void event() {
//            ivAvatar.setOnLongClickListener(v -> {
//                RecyclerView recyclerView = (RecyclerView) v.imageView.getParent();
//                recyclerView.showContextMenuForChild(holder.imageView);
//
//                return true;
//            });
//        }


    }
}
