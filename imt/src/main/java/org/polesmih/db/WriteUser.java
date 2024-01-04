package org.polesmih.db;

import java.time.LocalDateTime;

public class WriteUser {

    public static void writeUserIntoDb (LocalDateTime date, Long id, String firstName, String userImt) {

        UserModel user = new UserModel();

        user.setDate(date);
        user.setUserTgId(id);
        user.setFirstName(firstName);
        user.setUserImt(userImt);

        UserModel userModel = new UserModel(date, id, firstName, userImt);

        UserConnection.userAccounting(userModel);

    }

}
