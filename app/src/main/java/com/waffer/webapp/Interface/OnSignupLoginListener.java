package com.waffer.webapp.Interface;

import com.waffer.webapp.Model.Login.User;

/**
 * Created by Tregix on 12/24/2017.
 */

public interface OnSignupLoginListener  {
    void onSignup(User data);
    void onLoginUser(User data);
    void OnError(String error);
}
