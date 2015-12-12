package com.denver.lionfriend.main;

import com.denver.lionfriend.client.ClientDriver;

import java.rmi.RemoteException;

/**
 * Created by Malindu Warapitiya on 12/12/15.
 */
public class ClientMain {

    public static void main(String[] args) {
        ClientDriver a = null;
        try {
            a = new ClientDriver();
            a.registerUserAndSendMessage();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
