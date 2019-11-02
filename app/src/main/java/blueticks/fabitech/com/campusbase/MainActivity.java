package blueticks.fabitech.com.campusbase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class MainActivity extends AppCompatActivity {

    FlipperLayout flipperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        flipperLayout = findViewById( R.id.imageslider );
        setLayout();

    }

    private void setLayout() {
        String url[] = new String[]{
                "dfgfgfghrthtnjtj", "httewstseg","fghffncjfg",
        };
        for (int i=0; i<3; i++ ){
            FlipperView view = new FlipperView(getBaseContext());
        view.setImageUrl( url[i] );
    }

        int num_of_pages = 3;
        for (int i = 0; i < num_of_pages; i++) {
            FlipperView view = new FlipperView(getBaseContext());
            view.setImageUrl("<valid image url>")
                    .setImageDrawable(R.drawable.gtulogo) // Use one of setImageUrl() or setImageDrawable() functions, otherwise IllegalStateException will be thrown
                    .setImageScaleType( ImageView.ScaleType.CENTER_CROP) //You can use any ScaleType
                    .setDescription("Description")
                    .setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                        @Override
                        public void onFlipperClick(FlipperView flipperView) {
                            //Handle View Click here
                        }
                    });
            flipperLayout.setScrollTimeInSec(5); //setting up scroll time, by default it's 3 seconds
            flipperLayout.getScrollTimeInSec(); //returns the scroll time in sec
            flipperLayout.getCurrentPagePosition(); //returns the current position of pager
            flipperLayout.addFlipperView(view);
        }
     }
}
