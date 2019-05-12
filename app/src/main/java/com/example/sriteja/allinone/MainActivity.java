package com.example.sriteja.allinone;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.*;
import java.io.*;
//import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.sriteja.allinone.MESSAGE";
//    private String notification_msg = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    public MainActivity set_ntfcn_msg(String msg) {
////        this.notification_msg = msg;
//        return this;
//    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void openPhoneInformation(View view) {
        Intent intent = new Intent(this, PhoneStatusActivity.class);
        startActivity(intent);
    }

    //    public void send_ntfcn_msg(String msg) {
    //        createNotificationChannel();
    //        Intent intent = new Intent(this, MainActivity.class);
    //        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    //        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
    //
    //        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "hello")
    //                .setSmallIcon(R.mipmap.ic_launcher)
    //                .setContentTitle("Test")
    //                .setContentText(msg)
    //                .setContentIntent(pendingIntent)
    //                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    //                .setAutoCancel(true);
    //        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
    //    }

    public void sendNotification(View view) {
        createNotificationChannel();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "hello")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Test")
                .setContentText("Hi!, my first notification ")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("hello", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void openWhatsApp(View view) {
        EditText editText = (EditText) findViewById(R.id.editText3);
        String smsNumber = "91" + editText.getText().toString();
//        String smsNumber = ; //without '+'
        try {
            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net");
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } catch(Exception e) {
            Toast.makeText(this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

//    public static void networkManager(String [] args) {
//        String serverName = args[0];
//        int port = Integer.parseInt(args[1]);
//        try {
//            System.out.println("Connecting to " + serverName + " on port " + port);
//            Socket client = new Socket(serverName, port);
//
//            System.out.println("Just connected to " + client.getRemoteSocketAddress());
//            OutputStream outToServer = client.getOutputStream();
//            DataOutputStream out = new DataOutputStream(outToServer);
//
//            out.writeUTF("Hello from " + client.getLocalSocketAddress());
//            InputStream inFromServer = client.getInputStream();
//            DataInputStream in = new DataInputStream(inFromServer);
//
//            System.out.println("Server says " + in.readUTF());
//            client.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void networkManager(View view) {
        URL url = null;
        new ConnectToServerTask().execute(url);
    }

}