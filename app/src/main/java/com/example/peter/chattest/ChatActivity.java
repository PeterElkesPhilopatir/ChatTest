package com.example.peter.chattest;

import android.content.Intent;
import android.graphics.Movie;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
public class ChatActivity extends AppCompatActivity {


    public static String KEY = "12340";
    private FirebaseListAdapter<Message> adapter;

    List<Message> messages;
    ImageView sendBtn;
    EditText editText;
    Intent intent;

    User loggedUser;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        intent = getIntent();

        if (intent != null && intent.hasExtra(ChatActivity.KEY)) {

            loggedUser = (User) intent.getSerializableExtra(ChatActivity.KEY);
            msg(loggedUser.getName());
            setTitle(loggedUser.getName());
        }

        messages = new ArrayList<>();
        editText = (EditText) findViewById(R.id.editText);

        editText.setHint("Type Message here");

        sendBtn = (ImageView) findViewById(R.id.submit_button);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message messageTosend = new Message(editText.getText().toString(),loggedUser.getName());
                send(messageTosend);
            }
        });

        showMsgs();

    }

    public boolean send(Message message) {
        try {
            FirebaseDatabase.getInstance().getReference().push().setValue(message);
            editText.setText("");
            editText.requestFocus();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void msg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void showMsgs() {

        ListView listOfMessage = (ListView)findViewById(R.id.list_of_message);
        adapter = new FirebaseListAdapter<Message>(this,Message.class,R.layout.list_item,FirebaseDatabase.getInstance().getReference())
        {
            @Override
            protected void populateView(View v, Message model, int position) {

                TextView messageText, messageUser, messageTime;
                messageText = (TextView) v.findViewById(R.id.message_text);
                messageUser = (TextView) v.findViewById(R.id.message_user);
                messageTime = (TextView) v.findViewById(R.id.message_time);

                messageText.setText(model.getMsg());
                messageUser.setText(model.getUser_id());
//                messageUser.setText("USER ID IS HERE");
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));

            }
        };
        listOfMessage.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            if(resultCode == RESULT_OK)
            {
                Snackbar.make(relativeLayout,"Successfully signed in.Welcome!", Snackbar.LENGTH_SHORT).show();
                showMsgs();
            }
            else{
                Snackbar.make(relativeLayout,"We couldn't sign you in.Please try again later", Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}