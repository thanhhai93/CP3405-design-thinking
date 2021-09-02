package au.edu.jcu.cp3405.designthinkingiii;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Contract {
    // empty constructor so no one else can use it
    public Contract(){

    }
    // creating content authority and path

    //your package name insert in contentauthority
    public static final String CONTENT_AUTHORITY = "au.edu.jcu.cp3406.designthinkingiii";
    public static final Uri BASE_URI = Uri.parse("content://"+ CONTENT_AUTHORITY);
    // path name should be similar to your table name
    public static final String PATH_CONTACTS = "mycontacts";

    public static abstract class ContactEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI, PATH_CONTACTS);

        public static final String TABLE_NAME = "mycontacts";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PICTURE = "picture";
        public static final String COLUMN_PHONENUMBER = "number";
        public static final String COLUMN_TYPEOFCONTACT = "type";


        public static final String TYPEOFCONTACT_WORK = "Work";
        public static final String TYPEOFCONTACT_HOME = "Home";
        public static final String TYPEOFCONTACT_PERSONAL = "Personal";

        public static boolean isValidType (String type) {
            return type.equals(TYPEOFCONTACT_HOME) || type.equals(TYPEOFCONTACT_PERSONAL) || type.equals(TYPEOFCONTACT_WORK);
        }
    }
}
