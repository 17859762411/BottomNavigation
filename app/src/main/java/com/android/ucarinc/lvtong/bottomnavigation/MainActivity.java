package com.android.ucarinc.lvtong.bottomnavigation;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * @author 22939
 */
public class MainActivity extends AppCompatActivity {

    private FirstFragment fragment1;
    private SecondFragment fragment2;
    private ThirdFragment fragment3;
    private Fragment[] fragments;
    private BottomNavigationView bottomNavigationView;
    private int mCurrentFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            if (mCurrentFragment != 0) {
                                switchFragment(mCurrentFragment, 0);
                                mCurrentFragment = 0;
                            }
                            return true;
                        case R.id.navigation_dashboard:
                            if (mCurrentFragment != 1) {
                                switchFragment(mCurrentFragment, 1);
                                mCurrentFragment = 1;
                            }
                            return true;
                        case R.id.navigation_notifications:
                            if (mCurrentFragment != 2) {
                                switchFragment(mCurrentFragment, 2);
                                mCurrentFragment = 2;
                            }
                            return true;
                        default:
                    }
                    return false;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }

    private void initFragment() {

        fragment1 = new FirstFragment();
        fragment2 = new SecondFragment();
        fragment3 = new ThirdFragment();
        fragments = new Fragment[]{fragment1, fragment2, fragment3};
        mCurrentFragment = 0;
        getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.ll_fragment, fragment1)
                                   .show(fragment1)
                                   .commit();
        bottomNavigationView = findViewById(R.id.nav_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void switchFragment(int lastFragment, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastFragment]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.ll_fragment, fragments[index]);
        }
        transaction.show(fragments[index])
                   .commitAllowingStateLoss();
    }
}
