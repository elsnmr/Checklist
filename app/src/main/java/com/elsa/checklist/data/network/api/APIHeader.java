package com.elsa.checklist.data.network.api;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class APIHeader {

    private final ProtectedApiHeader mProtectedApiHeader;

    @Inject
    public APIHeader(ProtectedApiHeader protectedApiHeader) {
        mProtectedApiHeader = protectedApiHeader;
    }

    public ProtectedApiHeader getProtectedApiHeader() {
        return mProtectedApiHeader;
    }

    public static final class ProtectedApiHeader {

        private String mToken;

        public ProtectedApiHeader(String mToken) {
            this.mToken = "Bearer " + mToken;
        }

        public String getToken() {
            return mToken;
        }

        public void setToken(String token) {
            mToken = "Bearer " + token;
        }
    }
}
