package com.piworks.notifii;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import java.util.Date;

//import static android.content.Context.MODE_PRIVATE;
import static com.piworks.notifii.screen2.descriptionString;
import static com.piworks.notifii.settings.vibrateStringfinal;


public class MyReceiver extends BroadcastReceiver {
    public MyReceiver(){}
    static String timeString;
    database mydatabase;
    //private static SharedPreferences mpreferences;
    //private static String sharedPrefFile = "com.piworks.devansh.sharedpreferencesfile2";
    String descriptionString="you have a reminder";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        // for getting sounds
        Uri alarmsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //
        /*
        SharedPreferences prefs = getSharedPreferences(context);
        String storedHashMapString = prefs.getString("hashmap_string", "oopsDintWork");
        java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>(){}.getType();
        Gson gson = new Gson();
        HashMap<String, String> testHashMap2 = gson.fromJson(storedHashMapString, type);
*/
        mydatabase = new database(context);
////

        timeString = Long.toString(new Date().getTime());
        ///////////// GETTING REMINDER MESSAGE FROM DATABASE //////////////////////

        descriptionString= mydatabase.getreminder(timeString);

        //Map<String,String> map = new HashMap<String, String>();
        //descriptionStringfinal = MyReceiver.getUsername(context);
        //map.put(timeString,descriptionString);


        NotificationManagerCompat mymanager = NotificationManagerCompat.from(context);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
        NotificationCompat.Builder mynoti = new NotificationCompat.Builder(context,NOTIFICATION_CHANNEL_ID);
        mynoti.setContentTitle("You have a reminder !");
        mynoti.setContentText(descriptionString);
        mynoti.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        mynoti.setSmallIcon(R.drawable.notification_icon2);
        mynoti.setPriority(mymanager.IMPORTANCE_HIGH);     // FOR POP-UP NOTIFICATION
        Intent ix = new Intent(context,screen2.class);
        PendingIntent pd = PendingIntent.getActivity(context , 0,ix,0);
        mynoti.setContentIntent(pd);
        if (vibrateStringfinal =="true") {
            mynoti.setVibrate(new long[]{1000, 1000});
        }
        mynoti.setSound(alarmsound);

        mynoti.setAutoCancel(true);
        mymanager.notify(1,mynoti.build());


    }

/*
   private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("descprictionfile", Context.MODE_PRIVATE);
    }

*/

}
