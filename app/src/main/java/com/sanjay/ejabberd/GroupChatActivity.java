package com.sanjay.ejabberd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.sanjay.ejabberd.app.Constants;
import com.sanjay.ejabberd.service.XMPP;
import com.sanjay.ejabberd.utils.Logger;

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

public class GroupChatActivity extends AppCompatActivity {
    String name;
    String username;
    private String TAG=GroupChatActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        if(intent!=null){
            name = intent.getStringExtra("group");
            username=intent.getStringExtra("username");
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
        EntityBareJid mucJid = (EntityBareJid) JidCreate.bareFrom(name);

        // Create the nickname.
        Resourcepart nickname = Resourcepart.from(XMPP.getInstance().getUserLocalPart(this));
        Logger.logD(TAG,"nickname:"+nickname);
// Create a MultiUserChat using an XMPPConnection for a room
        MultiUserChat muc2 = manager.getMultiUserChat(mucJid);
        try {
            muc2.join(nickname);
            Logger.logD(TAG,"memeberlist size:"+muc2.getMembers().size());
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
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
