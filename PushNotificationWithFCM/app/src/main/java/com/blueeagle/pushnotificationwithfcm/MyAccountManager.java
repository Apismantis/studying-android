package com.blueeagle.pushnotificationwithfcm;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

/**
 * Created by apismantis on 07/09/2016.
 */

public class MyAccountManager {

    Context context;

    public MyAccountManager(Context mContext) {
        context = mContext;
    }

    public String getGoogleAccount() {

        Account[] accounts = AccountManager.get(context).getAccountsByType("com.google");

        if (accounts.length == 0)
            return null;

        return accounts[0].name;
    }
}
