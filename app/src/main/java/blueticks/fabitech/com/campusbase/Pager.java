package blueticks.fabitech.com.campusbase;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

public class Pager extends FragmentPagerAdapter {

    private int tabCount;
    Context mcontext;

    Pager(Context context, android.support.v4.app.FragmentManager fm, int tabCount) {
        super( fm );
        this.mcontext = context;
        this.tabCount = tabCount;
    }

    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                LoginActivity loginFragment=  new LoginActivity();
                break;
            case 1:
                RegisterActivity registerFragment= new RegisterActivity();
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
