package com.zendo.vidufirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ValueEventListener {

    Button btnDuLieu;
    TextView txtReadData;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("users");
        databaseReference.addValueEventListener(this);

        btnDuLieu = (Button) findViewById(R.id.btnThemDuLieu);
        txtReadData = (TextView) findViewById(R.id.txtHienThi);
        btnDuLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                databaseReference.child("users").child("user1").child("name").setValue("pham thanh duc 1"); // add truyen thong
//                databaseReference.child("users").child("user2").child("name").setValue("pham thanh duc 2");
//                User user = new User("Pham Thanh Duc",true,"20"); //add nhiu gia tri cung 1 luc
//                User user1 = new User("Pham Thanh A",true,"20");
//                User userD = new User("Pham Thanh D",true,"20");
//                databaseReference.child("users").child("user3").setValue(user);

                List<User> users = new ArrayList<>();
//                users.add(user);
//                users.add(user1);
//                users.add(userD);

                databaseReference.child("uses4").setValue(users);
            }
        });
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            txtReadData.setText(dataSnapshot.getValue().toString());

         Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();

         for(DataSnapshot dataSnapshot1 : dataSnapshots){
             User user = dataSnapshot1.getValue(User.class);
             txtReadData.setText(user.getHoten().toString());
         }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}

class User{

    String hoten;

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Long getTuoi() {
        return tuoi;
    }

    public void setTuoi(Long tuoi) {
        this.tuoi = tuoi;
    }

    boolean gioitinh;
    Long tuoi;

    public User(){

    }

    public User(String hoten, boolean gioitinh, Long tuoi) {
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.tuoi = tuoi;
    }
}
