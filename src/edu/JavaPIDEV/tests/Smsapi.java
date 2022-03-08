/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3a16.tests;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import com.twilio.Twilio;

/**
 *
 * @author 21627
 */
public class Smsapi {
     public static final String ACCOUNT_SID = "ACd06d24c0fa4d6d559915c16db34d2a0c";
    public static final String AUTH_TOKEN = "23438a0f6fc538f3fe5c766a9a8cd05d";

    public static void sendSMS(String num, String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(num),new PhoneNumber("17164543421"), msg).create();

        System.out.println(message.getSid());
//
    }
    
}
