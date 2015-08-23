package com.ashwin.smsreceiver;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class AfterNotication extends ActionBarActivity {

    DataOutputStream DOS;
    DataInputStream IOS;
    public String gas_valve_close;
    public String gas_valve_open;
    Button shutoff;
    Button shuton;

    Socket socket;

    public AfterNotication()
    {
        gas_valve_close = "setstate,1:1,1\r\n";
        gas_valve_open = "setstate,1:1,0\r\n";
        socket = null;
        DOS = null;
        IOS = null ;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_notication);
        StrictMode.setThreadPolicy((new android.os.StrictMode.ThreadPolicy.Builder()).permitAll().build());
        try
        {
            socket = new Socket("192.168.1.203", 4998);
        }
        catch (UnknownHostException unknownhostexception)
        {
            System.out.println("UnknownHostException");
        }
        catch (IOException ioexception)
        {
            System.out.println((new StringBuilder("IOException")).append(ioexception.getMessage()).toString());
        }
        if (socket != null)
        {
            if (socket.isConnected())
            {
                Toast.makeText(this, "Socket connection true", Toast.LENGTH_SHORT).show();
                try
                {
                    DOS = new DataOutputStream(socket.getOutputStream());
                }
                catch (IOException ioexception1)
                {
                    ioexception1.printStackTrace();
                }
            } else
            {
                Toast.makeText(this, "Socket Not Connected..", Toast.LENGTH_SHORT).show();
            }
        } else
        {
            Toast.makeText(this, "Unable to Connect to remnote host..!!", Toast.LENGTH_SHORT).show();
        }
        shutoff=(Button)findViewById(R.id.off);
        shuton=(Button)findViewById(R.id.on);

        shutoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try
                {
                    DOS.writeBytes(gas_valve_close);
             try {
                        Thread.sleep(1000);
                        DOS.writeBytes(gas_valve_open);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                catch (IOException ioexception2)
                {
                    ioexception2.printStackTrace();
                }
            }
        });
        shuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    DOS.writeBytes(gas_valve_open);
                    return;
                }
                catch (IOException ioexception2)
                {
                    ioexception2.printStackTrace();
                }
            }
        });
    }


}
