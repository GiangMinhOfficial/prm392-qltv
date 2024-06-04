package id.vn.minhlamdev.menuqltv;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.vn.minhlamdev.domain.Member;
import id.vn.minhlamdev.domain.MemberAdapter;

public class MenuManagement extends AppCompatActivity {

    private RecyclerView memberRecycleView;
    private ArrayList<Member> members = new ArrayList<>();
    private MemberAdapter memberAdapter;

    ImageButton btnMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanhvien_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        memberRecycleView = findViewById(R.id.rvMember);
        memberRecycleView.setHasFixedSize(true);
        registerForContextMenu(memberRecycleView);

        init();
        event();

    }

    private void event() {
        memberAdapter = new MemberAdapter(members, MenuManagement.this);
        memberRecycleView.setAdapter(memberAdapter);
        memberRecycleView.setLayoutManager(new LinearLayoutManager(MenuManagement.this, LinearLayoutManager.VERTICAL, false));
    }

    private void init() {
        members.add(new Member(1, R.drawable._1, "vượng", "con khỉ"));
        members.add(new Member(2, R.drawable._2, "cat", "người mèo"));
        members.add(new Member(3, R.drawable._3, "huma", "nhân vật trong avatar 2"));
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = memberAdapter.GetClickedPosition();

        int itemId = item.getItemId();
        if (itemId == R.id.action_edit) {
            Toast.makeText(this, "sửa nè", Toast.LENGTH_LONG).show();

            Member newMember = memberAdapter.getClickedMem();
            Member olderMember = members.get(position);
            olderMember.setName(newMember.getName());
            olderMember.setDescription(newMember.getDescription());

            updateAlert("Do you want to Update???", position, olderMember);
            return true;

        } else if (itemId == R.id.action_delete) {
            Toast.makeText(this, "xóa nè", Toast.LENGTH_LONG).show();
            deleteAlert(position);
            return true;
        }

        return super.onContextItemSelected(item);
    }

    public void deleteAlert(int pos) {
        AlertDialog.Builder al = new AlertDialog.Builder(this);
        al.setTitle("Do you want to delete " + members.get(pos));

        al.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                members.remove(pos);
                memberAdapter.notifyDataSetChanged();
                Toast.makeText(MenuManagement.this, "Yes DELETE succeed", Toast.LENGTH_SHORT).show();
            }
        });
        al.setNegativeButton("NoNo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MenuManagement.this, "y picked No", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        al.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        al.create().show();
    }

    private void updateAlert(String mess, int pos, Member member) {
        AlertDialog.Builder al = new AlertDialog.Builder(this);
        al.setTitle(mess);
        al.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                members.set(pos, member);
                memberAdapter.notifyDataSetChanged();
            }
        });
        al.setNegativeButton("NoNo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MenuManagement.this, "y picked No", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        al.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        al.create().show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_manage, menu);
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_manage, menu);
//        return true;
//    }
}
