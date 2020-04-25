package com.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMS {
	public static final String ACCOUNT_SID = "AC6444898123c47140151d8a6f9d6ccabb";
	public static final String AUTH_TOKEN = "cd84eead336cb9e4a9a5038ea5d5b6dd";
	public static void main(String[] args) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		
		Message message = Message.creator(new PhoneNumber("+940714302153"), new PhoneNumber("+16623301290"), "Fire Alarm alert !").create();
		
		System.out.println(message.getSid());
	}
}
