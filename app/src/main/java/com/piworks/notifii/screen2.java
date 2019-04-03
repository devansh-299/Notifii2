package com.piworks.notifii;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;



import  android.text.format.DateFormat;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;




import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class screen2 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    Button b1 ;
    int day=0;  int  month=0;  int year=0; int hour=0 ; int minute = 0;
    int dayfinal=0 ;int monthfinal=0 ;int yearfinal= 0; int hourfinal=0;int minutefinal= 0;
   // static String timeString;
    EditText description;
    static String descriptionString;
    static int i = 0;
    String toParse ;
    Long intervalS;
    Context ctx = getApplicationContext();

    String filename = "Notifii_file";
    //String fileContents = "Hello world!";

    /*
    private static SharedPreferences mpreferences;
    private  String sharedPrefFile = "com.piworks.devansh.sharedpreferencesfile2";

    //Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);            CANNOT ADD THIS BCZ APP CRASHES AS SOON AS THIS LINE IS UNCOMMENTED!!
*/
    AlarmManager myalarm ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        b1 = (Button)findViewById(R.id.pick);
        //mpreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        myalarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                description = (EditText)findViewById(R.id.description);
                DatePickerDialog datePickerDialog = new DatePickerDialog(screen2.this,screen2.this , year,month,day);
                datePickerDialog.show();
                ///



            }
        });
    }

/*
    @Override
    protected  void onPause() {
        super.onPause();
        SharedPreferences.Editor preferenceseditor = mpreferences.edit();
        preferenceseditor.putString(Long.toString(intervalS),descriptionString);
        preferenceseditor.apply();
    }
*/
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearfinal = i ;
        monthfinal = i1+1 ;
        dayfinal = i2 ;

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(screen2.this ,  screen2.this, hour , minute,DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i , int i1) {
        hourfinal = i ;
        minutefinal = i1 ;

    }
    //String str = monthfinal +" "+ dayfinal + " "+ yearfinal+" " + hourfinal+":" +minutefinal+ " UTC";
    //SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm zzz");
    //Date date = df.parse(str);
    //long epoch = date.getTime();





     // time and date have been picked   and  they reside in dayfinal month final , year final , hour final and minute final



    public void doSomething(View view) {
        //view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.onclick) );     APP CRASHES


        ////////////////////////////
        toParse = dayfinal+"-"+monthfinal+"-"+yearfinal+" " + hourfinal+":"+minutefinal;// Results in "2-5-2012 20:43"
        Toast.makeText(this,"Your reminder is set for "+toParse,Toast.LENGTH_SHORT).show();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mmm"); // I assume d-M, you may refer to M-d for month-day instead.
        Date date = null; // You will need try/catch around this
        try {
            date = formatter.parse(toParse);
        } catch (Exception e) {
            Toast.makeText(this, " An Error Occured!",Toast.LENGTH_SHORT);
            //vibe.vibrate(100);
        }

        intervalS = date.getTime();
/*
        timeString = Long.toString(new Date().getTime());
        HashMap<String,String> map = new HashMap<String, String>();
        descriptionString = description.getText().toString();
        map.put(Long.toString(intervalS),descriptionString);

*/


        //ArrayList<String> myarray = new ArrayList<String>();
        //myarray.add(descriptionString);
        //descriptionString1 = myarray.get(i);
        //i++;


        //Toast.makeText(this,"Your reminder is set"+Long.toString(intervalS),Toast.LENGTH_SHORT).show();

        ////////////// SAVING FILE!! ///////////////////////////////////////////
/*
        Gson gson = new Gson();
        String hashMapString = gson.toJson(map);
        SharedPreferences prefs = getSharedPreferences("descriptionfile", MODE_PRIVATE);
        prefs.edit().putString("hashmap_string", hashMapString).apply();
*/

        //////////////////////////////////////////

        Intent iy = new Intent();
        iy.setAction("piworks.notifii.reminder");
        PendingIntent pd = PendingIntent.getBroadcast(this, 0,iy,0);
        myalarm.set(
                AlarmManager.RTC_WAKEUP, intervalS, pd);


       // Toast.makeText(this,"Your reminder is set"+Long.toString(intervalS),Toast.LENGTH_SHORT).show();
    }

    public void opensettings(View view) {
        Intent iz = new Intent(screen2.this,settings.class);
        //vibe.vibrate(100);                       // CANNOT ADD BCZ APP CRASHESS
        startActivity(iz);

    }

 @Override
    public void onBackPressed(){
        Intent  screen2tomainscreen = new Intent(this ,MainActivity.class);
        startActivity(screen2tomainscreen);
    }
}

