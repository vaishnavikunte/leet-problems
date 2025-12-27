package com.example.trendgraphics;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Createdesign extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout dr;
    Button b1,b2;
    NavigationView nv;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CHECK", "Poster Activity Loaded");

        setContentView(R.layout.activity_createdesign);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        nv = findViewById(R.id.navview);
        if (nv == null) {
            Log.e("CHECK", "Navigation View is NULL");
        }



        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dr=findViewById(R.id.drawerlayout);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) NavigationView nv=findViewById(R.id.navview);
        nv.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,dr,toolbar,R.string.open_nav,R.string.close_nav);
        dr.addDrawerListener(toggle);
        toggle.syncState();
        nv.bringToFront();
        nv.setNavigationItemSelectedListener(this);
        nv.bringToFront();

        /*if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new settingfragment()).commit();
            nv.setCheckedItem(R.id.navhome);
        }*/
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Createdesign.this, buy.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Createdesign.this, Sampleposter.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int ID=item.getItemId();
        if(ID==R.id.navhome){
            Intent intent = new Intent(Createdesign.this, select.class);
            startActivity(intent);
        }
        if(ID==R.id.navcustomer){
            Intent intent = new Intent(Createdesign.this, customer.class);
            startActivity(intent);
        }
        if(ID==R.id.navabout){
            Intent intent = new Intent(Createdesign.this, about.class);
            startActivity(intent);
        }
        if(ID==R.id.navlogout){
            if(ID==R.id.navlogout){
                SharedPreferences sharedPreferences = getSharedPreferences("LoginSession", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();  // Clear the session
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Toast.makeText(Createdesign.this, "You have been logged out", Toast.LENGTH_SHORT).show();

                finish();  // Finish current activity
            }

        }
        dr.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if(dr.isDrawerOpen(GravityCompat.START)){
            dr.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int ID=item.getItemId();
        if(ID==R.id.buy){
            Intent intent = new Intent(Createdesign.this, buy.class);
            startActivity(intent);

        }
        if(ID==R.id.logo){
            Intent intent = new Intent(Createdesign.this, Createdesign2.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
