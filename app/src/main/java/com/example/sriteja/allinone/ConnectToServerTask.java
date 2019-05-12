package com.example.sriteja.allinone;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;


public class ConnectToServerTask extends AsyncTask<String, Integer, Long> {
//    @Override
    protected Long doInBackground(String... urls) {
//        int count = urls.length;
//        long totalSize = 0;
//        for (int i = 0; i < count; i++) {
//            totalSize += Downloader.downloadFile(urls[i]);
//            publishProgress((int) ((i / (float) count) * 100));
//            // Escape early if cancel() is called
//            if (isCancelled()) break;
//        }
//        return totalSize;

        String serverName = "192.168.43.4";
        String cmd = urls[0];
        System.out.println("Command is " + cmd);
        int port = 6066;
        try {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);

            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

//            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            out.writeUTF(cmd);
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

//        System.out.println("Server says " + in.readUTF());
//        MainActivity mActivity = new MainActivity();
//        mActivity.send_ntfcn_msg("Server says " + in.readUTF());
            String msg = in.readUTF();
            client.close();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void send_ntfn_msg() {

    }

    protected void onProgressUpdate(Integer... progress) {
//        setProgressPercent(progress[0]);
    }

    protected void onPostExecute(String result) {
        System.out.println(result);

//        showDialog("Downloaded " + result + " bytes");
    }
}
