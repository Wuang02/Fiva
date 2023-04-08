package com.soft.fivamusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.soft.fivamusic.common.loginsignup.RetailerStartupScreen;
import com.soft.fivamusic.homepageactivity.Setting;
import com.soft.fivamusic.menu.About;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Animation topAnim, bottomAnim;
    TextView logo, slogan;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fullscreen background
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView);
        slogan = findViewById(R.id.textView2);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        //Tool bar
        setSupportActionBar(toolbar);

        //Hidden Item
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false);
        menu.findItem(R.id.nav_profile).setVisible(false);


        //Navigation drawer menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //Home page default when click back
        navigationView.setCheckedItem(R.id.nav_home);
    }


    //Click back to page
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    //Switch page
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_aboutus:
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);
                break;
            case R.id.nav_setting:
                Intent intent1 = new Intent(MainActivity.this, Setting.class);
                startActivity(intent1);
                break;
            case R.id.nav_playlist:
            case R.id.playlist1:
                Intent intent2 = new Intent(MainActivity.this, Playlist.class);
                startActivity(intent2);
                break;
            /*---------------------------------test---------------------------------*/
//            case R.id.nav_setting:
//                Toast.makeText(this, "Version 1.0.0 by Group 5", Toast.LENGTH_SHORT).show();
//                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    public void callRetailerScreen(View view){
        startActivity(new Intent(getApplicationContext(), RetailerStartupScreen.class));
    }
}