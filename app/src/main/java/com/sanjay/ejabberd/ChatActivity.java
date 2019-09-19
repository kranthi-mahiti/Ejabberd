package com.sanjay.ejabberd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.sanjay.ejabberd.app.Constants;
import com.sanjay.ejabberd.service.XMPP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.jivesoftware.smack.ReconnectionManager;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.MultiUserChatManager;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.jid.parts.Resourcepart;
import org.jxmpp.stringprep.XmppStringprepException;

import java.io.IOException;

import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = ChatActivity.class.getSimpleName();
    private String contactJid;
    private ChatView mChatView;
    private BroadcastReceiver mBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    mChatView =(ChatView) findViewById(R.id.rooster_chat_view);

        mChatView.setOnSentMessageListener(new ChatView.OnSentMessageListener(){
        @Override
        public boolean sendMessage(ChatMessage chatMessage){
            // perform actual message sending
            if (XMPP.getInstance().isConnected()) {
                Log.d(TAG, "The client is connected to the server,Sending Message");
                //Send the message to the server

                Intent intent = new Intent(Constants.SEND_MESSAGE);
                intent.putExtra(Constants.BUNDLE_MESSAGE_BODY,
                        mChatView.getTypedMessage());
                intent.putExtra(Constants.BUNDLE_TO, contactJid);

                sendBroadcast(intent);
                sendMessagetochat(chatMessage.getMessage(),"admin@13.232.231.174");

            } else {
                Toast.makeText(getApplicationContext(),
                        "Client not connected to server ,Message not sent!",
                        Toast.LENGTH_LONG).show();
            }
            //message sending ends here
            return true;
        }
    });
    Intent intent = getIntent();
    contactJid = intent.getStringExtra("EXTRA_CONTACT_JID");
    setTitle(contactJid);
}
    private void sendMessagetochat ( String body ,String toJid)
    {
        Log.d(TAG,"Sending message to :"+ toJid);

        EntityBareJid jid = null;


        ChatManager chatManager = null;
        try {
            chatManager = ChatManager.getInstanceFor(XMPP.getInstance().getConnection());
        } catch (XMPPException e) {
            e.printStackTrace();
        } catch (SmackException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            jid = JidCreate.entityBareFrom(toJid);
        } catch (XmppStringprepException e) {
            e.printStackTrace();
        }
        Chat chat = chatManager.chatWith(jid);
        try {
            Message message = new Message(jid, Message.Type.chat);
            message.setBody(body);
            chat.send(message);

        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean createJoinGroup(String message) throws InterruptedException, IOException, SmackException, XMPPException {
        MultiUserChatManager manager = MultiUserChatManager.getInstanceFor(XMPP.getInstance().getConnection());

//        try {
//            muc.create(StringUtils.parseName(XMPP.getInstance().getConnection(getActivity()).getUser()));
//            muc.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
//        } catch (XMPPException e) {
//        } catch (SmackException.NoResponseException e) {
//            e.printStackTrace();
//        } catch (SmackException e) {
//            e.printStackTrace();
//        }

//        for (int i = 0; i < alSelectedContacts.size(); i++) {
//
//            Log.e("tag", "group chating purpose1 ::" + alSelectedContacts.get(i).get("id"));
//            try {
//                muc.invite((alSelectedContacts.get(i).get("id") + "_user") + "@" + XMPP.HOST,
//                        alSelectedContacts.get(i).get("id") + "_user");
//            } catch (SmackException.NotConnectedException e) {
//                e.printStackTrace();
//            }
//
//        }

        // Create the XMPP address (JID) of the MUC.
        EntityBareJid mucJid = (EntityBareJid) JidCreate.bareFrom("g1@conference.206.189.136.186");

        // Create the nickname.
        Resourcepart nickname = Resourcepart.from("anusha");

// Create a MultiUserChat using an XMPPConnection for a room
        MultiUserChat muc2 = manager.getMultiUserChat(mucJid);
        try {
            muc2.join(nickname);
            muc2.sendMessage(message);
        } catch (SmackException.NotConnectedException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    protected void onResume() {
        super.onResume();
        try {
            ChatManager.getInstanceFor(XMPP.getInstance().getConnection()).addIncomingListener(new IncomingChatMessageListener() {
                @Override
                public void newIncomingMessage(EntityBareJid messageFrom, Message message, Chat chat) {
                    ///ADDED
                    Log.d(TAG,"message.getBody() :"+message.getBody());
                    Log.d(TAG,"message.getFrom() :"+message.getFrom());

                    String from = message.getFrom().toString();

                    String contactJid="";
                    if ( from.contains("/"))
                    {
                        contactJid = from.split("/")[0];
                        Log.d(TAG,"The real jid is :" +contactJid);
                        Log.d(TAG,"The message is from :" +from);
                    }else
                    {
                        contactJid=from;
                    }

                    //Bundle up the intent and send the broadcast.
                    Intent intent = new Intent(Constants.NEW_MESSAGE);
                    intent.setPackage(getApplication().getPackageName());
                    intent.putExtra(Constants.BUNDLE_FROM_JID,contactJid);
                    intent.putExtra(Constants.BUNDLE_MESSAGE_BODY,message.getBody());
                    getApplication().sendBroadcast(intent);
                    Log.d(TAG,"Received message from :"+contactJid+" broadcast sent.");
                    ///ADDED

                }
            });
        } catch (XMPPException e) {
            e.printStackTrace();
        } catch (SmackException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ReconnectionManager reconnectionManager = null;
        try {
            reconnectionManager = ReconnectionManager.getInstanceFor(XMPP.getInstance().getConnection());
        } catch (XMPPException e) {
            e.printStackTrace();
        } catch (SmackException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reconnectionManager.setEnabledPerDefault(true);
        reconnectionManager.enableAutomaticReconnection();

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                switch (action)
                {
                    case Constants.NEW_MESSAGE:
                        String from = intent.getStringExtra(Constants.BUNDLE_FROM_JID);
                        String body = intent.getStringExtra(Constants.BUNDLE_MESSAGE_BODY);

                        if ( from.equals(contactJid))
                        {
                            ChatMessage chatMessage = new ChatMessage(body,System.currentTimeMillis(), ChatMessage.Type.RECEIVED);
                            mChatView.addMessage(chatMessage);

                        }else
                        {
                            Log.d(TAG,"Got a message from jid :"+from);
                        }

                        return;
                }

            }
        };

        IntentFilter filter = new IntentFilter(Constants.NEW_MESSAGE);
        registerReceiver(mBroadcastReceiver,filter);


    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBroadcastReceiver);
    }
}

