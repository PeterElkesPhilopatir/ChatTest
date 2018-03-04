package com.example.peter.chattest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;


public class MainActivity extends AppCompatActivity implements Serializable {


    User user1, user2, loggedUser; // as a login simulation
    Button u1Button, u2Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        u1Button = (Button) findViewById(R.id.u1login);
        u2Button = (Button) findViewById(R.id.u2login);

        u1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loggedUser = user1;
                u1Button.setVisibility(View.INVISIBLE);
                u2Button.setVisibility(View.INVISIBLE);
                startChatting(loggedUser);
            }
        });


        u2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loggedUser = user2;
                u1Button.setVisibility(View.INVISIBLE);
                u2Button.setVisibility(View.INVISIBLE);
                startChatting(loggedUser);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        user1 = new User(1, "user1", "test@email.com");
        user2 = new User(2, "user2", "test2@email.com");
    }

    public void msg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void startChatting(User USER) {

        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(ChatActivity.KEY,USER);
        startActivity(intent);
    }
}