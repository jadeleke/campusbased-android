package blueticks.fabitech.com.campusbase;


import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;


public class ConnectionDetector {

    private Context context;


    ConnectionDetector(Context context) {
        this.context = context;
    }
    


    public boolean isConnected(){

        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Service.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {

            NetworkInfo info = connectivityManager.getActiveNetworkInfo();

            if (info != null)
               return info.getState() == NetworkInfo.State.CONNECTED;
        }

        return false;
    }


    public void alertDialog(Context context, String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
