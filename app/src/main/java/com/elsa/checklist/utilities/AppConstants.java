package com.elsa.checklist.utilities;

public final class AppConstants {

    public static final int NULL_INDEX = -1;
    public static final String PREF_NAME = "yeloo.pref";
    public static final int STATUS_CODE_LOCAL_ERROR = 0;
    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    private AppConstants() {
        // This utility class is not publicly instantiable
    }

    public enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
