package org.polesmih.db;

import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserConnection {

    @SneakyThrows
    public static void userAccounting(UserModel userModel) {

        String insert = "INSERT INTO " + DbConst.TABLE
                + "(" + DbConst.DATE + ", "
                + DbConst.TG_ID + ", "
                + DbConst.FIRST_NAME + ", "
                + DbConst.IMT + ")"
                + "VALUES (?, ?, ?, ?)";

        try {

            PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(insert);
            preparedStatement.setString(1, String.valueOf(userModel.getDate()));
            preparedStatement.setString(2, String.valueOf(userModel.getUserTgId()));
            preparedStatement.setString(3, userModel.getFirstName());
            preparedStatement.setString(4, userModel.getUserImt());

            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
