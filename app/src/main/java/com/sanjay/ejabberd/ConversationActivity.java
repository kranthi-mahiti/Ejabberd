package com.sanjay.ejabberd;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.sanjay.ejabberd.service.XMPP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class ConversationActivity extends AppCompatActivity {
    Button cht_btn, grp_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cht_btn = findViewById(R.id.chat_btn);
        grp_btn = findViewById(R.id.group_btn);

        cht_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ConversationActivity.this,ChatActivity.class);
                i.putExtra("","");
                startActivity(i);
            }
        });

        grp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ConversationActivity.this,GroupChatActivity.class);
                i.putExtra("group","oelp-teachers@conference.206.189.136.186");
                i.putExtra("username", XMPP.getInstance().getUser().toString());
                startActivity(i);
            }
        });

    }

}
