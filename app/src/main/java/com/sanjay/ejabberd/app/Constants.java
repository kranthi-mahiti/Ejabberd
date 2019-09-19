package com.sanjay.ejabberd.app;

import com.sanjay.ejabberd.app.EjabberdApplication;
import com.sanjay.ejabberd.database.EjabberdDatabaseHelper;

import org.jivesoftware.smack.tcp.XMPPTCPConnection;

public class Constants {

    public static final int PORT = 5222;

    private static String TAG = "Ejabberd";
    public static final String ACTION_LOGGED_IN = "liveapp.loggedin";
    public static String HOST = "206.189.136.186";
    public static final String UI_AUTHENTICATED = "com.blikoon.rooster.uiauthenticated";
    public static final String SEND_MESSAGE = "com.blikoon.rooster.sendmessage";
    public static final String BUNDLE_MESSAGE_BODY = "b_body";
    public static final String BUNDLE_TO = "b_to";

    public static final String NEW_MESSAGE = "com.blikoon.rooster.newmessage";
    public static final String BUNDLE_FROM_JID = "b_from";

    public static EjabberdDatabaseHelper ejabberdDatabaseHelper = new EjabberdDatabaseHelper(EjabberdApplication.getContext());
    // ------------ Contact Table -------------------------------

    public static final String TABLENAME_CONTACTS = "contacts";

    public static final String SYSTEMNAME = "systemname";
    public static final String SERVERNAME = "servername";
    public static final String JID = "jid";
    public static final String OPTIONS = "options";
    public static final String SYSTEMACCOUNT = "systemaccount";
    public static final String PHOTOURI = "photouri";
    public static final String KEYS = "pgpkey";
    public static final String ACCOUNT = "accountUuid";
    public static final String AVATAR = "avatar";
    public static final String LAST_PRESENCE = "last_presence";
    public static final String LAST_TIME = "last_time";
    public static final String GROUPS = "groups";

    public static final String TABLENAME_ACOUNTS = "accounts";


    //------------ Message Table-----------------------

    public static final String TABLE_NAME_MSG = "messages";
    public static final String CONVERSATION = "conversationUuid";
    public static final String COUNTERPART = "counterpart";
    public static final String TRUE_COUNTERPART = "trueCounterpart";
    public static final String BODY = "body";
    public static final String TIME_SENT = "timeSent";
    public static final String ENCRYPTION = "encryption";
    public static final String STATUS = "status";
    public static final String TYPE = "type";
    public static final String CARBON = "carbon";
    public static final String OOB = "oob";
    public static final String EDITED = "edited";
    public static final String REMOTE_MSG_ID = "remoteMsgId";
    public static final String SERVER_MSG_ID = "serverMsgId";
    public static final String RELATIVE_FILE_PATH = "relativeFilePath";
    public static final String FINGERPRINT = "axolotl_fingerprint";
    public static final String READ = "read";
    public static final String DELETED = "deleted";
    public static final String ERROR_MESSAGE = "errorMsg";
    public static final String READ_BY_MARKERS = "readByMarkers";
    public static final String MARKABLE = "markable";
    public static final String FILE_DELETED = "file_deleted";
    public static final String ME_COMMAND = "/me";
    public static final String ERROR_MESSAGE_CANCELLED = "de.pixart.messenger.cancelled";
    public static final String UUID = "UUID";


}
