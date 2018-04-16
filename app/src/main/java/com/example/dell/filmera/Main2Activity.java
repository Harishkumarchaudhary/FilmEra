package com.example.dell.filmera;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;

import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,RecycleViewAdapter.onClick,AdapterForTopRated.onClick,AdapterForUpcoming.onClick  {
 android.support.v7.widget.SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView imageView=findViewById(R.id.imageView);
     //   Picasso.get().load("http://www.barrons.com/articles/the-picture-darkens-for-movie-theater-operators-1500094336").fit().into(imageView);
        setSupportActionBar(toolbar);
        searchView=findViewById(R.id.searchview);
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent j=new Intent(Main2Activity.this,SearchActivity.class);
                j.putExtra("query",query);
                startActivity(j);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        BlankFragment blankFragment=new BlankFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,blankFragment).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main2, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            BlankFragment blankFragment=new BlankFragment();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.framelayout,blankFragment).commit();
        } else if (id == R.id.nav_gallery) {
            TvBlankFragment tvBlankFragment=new TvBlankFragment();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.framelayout,tvBlankFragment).commit();

        } else if (id == R.id.nav_slideshow) {
            Intent j=new Intent(this,FavouriteShowing.class);
            startActivity(j);
        }

//        } else if (id == R.id.nav_manage) {
//
         else if (id == R.id.nav_share) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SENDTO);
            shareIntent.setData(Uri.parse("mailto:harishkumar211998@gmail.com"));

            shareIntent.putExtra(Intent.EXTRA_SUBJECT,"What do you like about this app :)");
            shareIntent.putExtra(Intent.EXTRA_TEXT,"Write Here");

            startActivity(Intent.createChooser(shareIntent,"Open Mail"));
        }

         else if (id == R.id.nav_send) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SENDTO);
            shareIntent.setData(Uri.parse("mailto:harishkumar211998@gmail.com"));

            shareIntent.putExtra(Intent.EXTRA_SUBJECT,"What do you like about this app :)");
            shareIntent.putExtra(Intent.EXTRA_TEXT,"Write Here");

            startActivity(Intent.createChooser(shareIntent,"Open Mail"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void do_it(Api_class_for_popular.Data position) {
//        if(landscapemode){
        //THE CODE WHEN I AM SENDING DATA TO DETAIL FRAGMENT WHICH WAS HAVING IMAGEVIEW AND TEXTVIEW INT ITS XML FILE
        //AND SETTING THE TEXT AND IMAGE IN DETAIL FRAGMENT
//            Bundle bundle=new Bundle();
//            bundle.putString("name", position.title);
//            bundle.putString("avatar", position.poster_path);
//            DetailFragment detailFragment=new DetailFragment();
//            detailFragment.setArguments(bundle);
//            android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
//            android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.container,detailFragment).commit();

//           Bundle bundle=new Bundle();
//           bundle.putInt("movie_id",position.id);
//            DetailFragment detailFragment=new DetailFragment();
//           detailFragment.setArguments(bundle);
//            android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
//           android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//           fragmentTransaction.replace(R.id.container,detailFragment).commit();
//
//
//        }
////         Intent j=new Intent(this,GetReviews.class);
////         j.putExtra("movie_id",arrayList.get(position).id);
////         startActivity(j);
//        if(!landscapemode) {
//            Intent j = new Intent(this, VideoActivity.class);
//            j.putExtra("movie_id",position.id);
//            startActivity(j);
//        }
        //}
        Intent j=new Intent(this,NewDetailofMovie.class);
        j.putExtra("poster_path",position.poster_path);
        j.putExtra("id",position.id);
        j.putExtra("title",position.title);
        startActivity(j);
    }

    @Override
    public void do_that( Api_class_for_topRated.Data2 position) {
        Intent j=new Intent(this,NewDetailofMovie.class);
        j.putExtra("poster_path",position.poster_path);
        j.putExtra("id",position.id);
        j.putExtra("title",position.original_title);
        startActivity(j);
    }

    @Override
    public void do_up(ApiClassForUpcoming.UpcomingData position) {
        Intent j=new Intent(this,NewDetailofMovie.class);
        j.putExtra("poster_path",position.poster_path);
        j.putExtra("id",position.id);
        j.putExtra("title",position.title);
        startActivity(j);
    }

}
