package com.example.oncuoiki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtTienKT;
    EditText edtTienKC;
    TextView tt;
    Button btn_add;
    ListView listView;
    DoanhThuAdapter doanhThuAdapter;
    ArrayList<DoanhThu> list =new ArrayList<>();
    int id = 0;
    int tong =0;
    FirebaseDatabase db =FirebaseDatabase.getInstance();
    DatabaseReference data =db.getReference("DoanhThu");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tt = findViewById(R.id.tt_doanhthu);
        listView = findViewById(R.id.listview);
        btn_add = findViewById(R.id.btn_add);
        edtTienKC = findViewById(R.id.edt_TienKC);
        edtTienKT = findViewById(R.id.edt_TienKT);


        doanhThuAdapter = new DoanhThuAdapter(this, R.layout.item_kc, list, new DoanhThuAdapter.IClickLisner() {
            @Override
            public void onClickUpdateIteam(DoanhThu dt) {
                String tienkc = edtTienKC.getText().toString();
                int tienkc2 = Integer.parseInt(tienkc);
                String tienkt = edtTienKT.getText().toString();
                int tienkt2 = Integer.parseInt(tienkt);
                dt.setTienkc(tienkc2);
                dt.setTienkt(tienkt2);
                data.child(String.valueOf(dt.getTienkc())).updateChildren(dt.toMap());
            }

            @Override
            public void onClickDeleteIteam(DoanhThu dt) {
                data.child(String.valueOf(dt.getTienkc())).removeValue();
            }
        });
        listView.setAdapter(doanhThuAdapter);

        getallDT();
        themDT();
    }
    private void themDT() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tienkc = edtTienKC.getText().toString();
                int tienkc2 = Integer.parseInt(tienkc);
                String tienkt = edtTienKT.getText().toString();
                int tienkt2 = Integer.parseInt(tienkt);
                DoanhThu dt = new DoanhThu(tienkc2,tienkt2);
                String pathObject = String.valueOf(dt.getTienkc());
                data.child(pathObject).setValue(dt);
                Toast.makeText(getApplicationContext(),"Invalid contact number",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getallDT() {
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tong = 0;
                list.clear();
                for (DataSnapshot s : snapshot.getChildren()){
                    DoanhThu dt =s.getValue(DoanhThu.class);
                    list.add(dt);
                    int kchi = dt.getTienkc();
                    int kthu = dt.getTienkt();
                    tong += kchi + kthu;
                }
                tt.setText(""+tong+"đ");
                doanhThuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "get fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}