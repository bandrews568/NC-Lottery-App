package us.brandonandrews.nclottery.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import us.brandonandrews.nclottery.R;
import us.brandonandrews.nclottery.fragments.AllGamesFragment;
import us.brandonandrews.nclottery.fragments.Cash5Fragment;
import us.brandonandrews.nclottery.fragments.LuckyForLifeFragment;
import us.brandonandrews.nclottery.fragments.MegaMillionsFragment;
import us.brandonandrews.nclottery.fragments.Pick3Fragment;
import us.brandonandrews.nclottery.fragments.Pick4Fragment;
import us.brandonandrews.nclottery.fragments.PowerballFragment;
import us.brandonandrews.nclottery.fragments.RandomFragment;
import us.brandonandrews.nclottery.fragments.SettingsFragment;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAIN ACTIVITY";

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
        drawerToggle = setupDrawerToggle();
        drawer.addDrawerListener(drawerToggle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_activity_fragment_placeholder, new AllGamesFragment());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (item.getItemId() == R.id.action_settings) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_activity_fragment_placeholder, new SettingsFragment())
                    .addToBackStack(null)
                    .commit();
            setTitle("Settings");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        String fragmentTag;

        switch(menuItem.getItemId()) {
            case R.id.nav_all_games:
                fragmentClass = AllGamesFragment.class;
                fragmentTag = "all";
                break;
            case R.id.nav_pick3:
                fragmentClass = Pick3Fragment.class;
                fragmentTag = "pick3";
                break;
            case R.id.nav_Pick4:
                fragmentClass = Pick4Fragment.class;
                fragmentTag = "pick4";
                break;
            case R.id.nav_cash5:
                fragmentClass = Cash5Fragment.class;
                fragmentTag = "cash5";
                break;
            case R.id.nav_lucky_for_life:
                fragmentClass = LuckyForLifeFragment.class;
                fragmentTag = "luckyForLife";
                break;
            case R.id.nav_mega_millions:
                fragmentClass = MegaMillionsFragment.class;
                fragmentTag = "megaMillions";
                break;
            case R.id.nav_powerball:
                fragmentClass = PowerballFragment.class;
                fragmentTag = "powerball";
                break;
            case R.id.nav_random:
                fragmentClass = RandomFragment.class;
                fragmentTag = "random";
                break;
            default:
                fragmentClass = AllGamesFragment.class;
                fragmentTag = "all";
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            Log.e(TAG, "selectDrawerItem: " + fragmentTag);
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_activity_fragment_placeholder, fragment, fragmentTag)
                .addToBackStack(null)
                .commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawer.closeDrawers();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,  R.string.navigation_drawer_close);
    }
}
