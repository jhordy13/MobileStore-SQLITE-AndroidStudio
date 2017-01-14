package utilitarios;

import android.provider.BaseColumns;

/**
 * Created by Jhordy on 13/01/2017.
 */

public class LawyersContract {

    public static abstract class LawyerEntry implements BaseColumns{
        public static final String TABLE_NAME ="lawyer";

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String SPECIALTY = "specialty";
        public static final String PHONE_NUMBER = "phoneNumber";
        public static final String AVATAR_URI = "avatarUri";
        public static final String BIO = "bio";
    }

}
