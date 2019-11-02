package blueticks.fabitech.com.campusbase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


public class AccessActivity extends AppCompatActivity implements  TabLayout.OnTabSelectedListener {

    ViewPager mViewPager;
    public Pager pagerViewer;
    TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_access );
        mViewPager = (ViewPager) findViewById( R.id.container );
        mViewPager.setAdapter( pagerViewer );

        tabLayout = (TabLayout) findViewById( R.id.tabs );
        tabLayout.setupWithViewPager( mViewPager );
        //pagerViewer = new PagerViewer(getApplicationContext(), getSupportFragmentManager(), );
        tabLayout.addTab( tabLayout.newTab().setText( "Login" ) );
        tabLayout.addTab( tabLayout.newTab().setText( "Register" ) );
        mViewPager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener( tabLayout ) );
        tabLayout.addOnTabSelectedListener( new TabLayout.ViewPagerOnTabSelectedListener( mViewPager ) );

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem( tab.getPosition() );

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}