package com.example.dell.filmera;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements RecycleViewAdapter.onClick,AdapterForTopRated.onClick,AdapterForUpcoming.onClick {
    //    TextView text;
    FrameLayout frameLayout;
    //boolean landscapemode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        text=findViewById(R.id.texty);
       // frameLayout = findViewById(R.id.container);
        //  if (frameLayout != null) {
        //  landscapemode = true;
        // }
        BlankFragment blankFragment=new BlankFragment();
        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction=manager.beginTransaction();
        transaction.addToBackStack("hey").replace(R.id.contain,blankFragment).commit();
    }
//    public void show_toprated(View view){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            text.setTextColor(getResources(). getColor(R.color.colorAccent,null));
//        }
//        Intent j=new Intent(this,TopRatedShowing.class);
//        startActivity(j);
//    }


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.necessary_menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.favourites){

        }
        else if(item.getItemId()==R.id.feedback){
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SENDTO);
            shareIntent.setData(Uri.parse("mailto:harishkumar211998@gmail.com"));

            shareIntent.putExtra(Intent.EXTRA_SUBJECT,"What do you like about this app :)");
            shareIntent.putExtra(Intent.EXTRA_TEXT,"Write Here");

            startActivity(Intent.createChooser(shareIntent,"Open Mail"));
        }
        else if(item.getItemId()==R.id.report){

        }
        return super.onOptionsItemSelected(item);
    }
}
