package com.oe.sdk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.oe.sdk.connection.SMSCConnection;
import com.oe.sdk.connection.SMSCConnectionFactory;
import com.oe.sdk.exceptions.SMSCException;
import com.oe.sdk.exceptions.SMSCRemoteException;
import com.oe.sdk.model.Credit;
import com.oe.sdk.model.SMS;
import com.oe.sdk.model.SMSType;
import com.oe.sdk.model.SendResult;
import com.oe.sdk.model.Subaccount;
import com.oe.sdk.model.Subaccount.SUBACCOUNT_TYPE;

public class Main implements Runnable {
	private static final int ST = 10;
//	private static final int ST = 1000;

	static Random r = new Random();
	public static void main(final String[] args) throws InterruptedException {
		List<Thread> tt = new ArrayList<Thread>();
		Thread t = null;
		for (int i=0;i<20;i++) {
//		for (int i=0;i<1;i++) {
			t = new Thread(new Main());
			tt.add(t);
			t.start();
			Thread.sleep(500);
			System.out.println("thread " + i + " started");
		}
		t.join();
	}

	public void run() {
		while (true) {
		try {
			SMSCConnection connection = SMSCConnectionFactory.openConnection("xxx", "xxx");
			// connection.lockSubaccount("MaTtIaS_001");
			// subs = connection.getSubaccounts();
			// for (Subaccount sub : subs) {
			// Obj.printObject(sub, System.out);
			// }
			for (int j = 0; j < 5; j++) {
				SMS sms = new SMS();
				for (int i = 0; i < 10; i++) {
					sms.addSmsRecipient("+3934" + String.valueOf(1000 + j) + String.valueOf(1000 + i));
					sms.addSmsRecipient("+39347905" + (1000 + j));
				}
				sms.setSms_type(SMSType.GOLD_PLUS);
				sms.setMessage("àèòéóíú");
				sms.setSms_sender("pippo");
				sms.setImmediate();
				// sms.setOrder_id("FFFAAABBBC");
				SendResult result = connection.sendSMS(sms);
				System.out.println("SendResult: " + result.toString());
				try {
					Thread.sleep(2500);
				} catch (Exception e) {
				}
			}
			List<Subaccount> subs = connection.getSubaccounts();
			connection.getCredits();
			connection.getMessageStatus("asdf");
			connection.getSMS_MOById(1);
			for (Subaccount s : subs) {
				s.setSubaccountType(Subaccount.SUBACCOUNT_TYPE.COMPANY);
//				System.out.println("------------------------");
//				Obj.printObject(s, System.out);
				List<Credit> credits = connection.getSubaccountCredits(s);
//				for (Credit c : credits)
//					Obj.printObject(c, System.out);
			}
			try {
				Thread.sleep(ST + r.nextInt(ST));
			} catch (InterruptedException e) { }
		} catch (final SMSCRemoteException smscre) {
//			System.out.println("Exception! Message: " + smscre.getMessage());
//			smscre.printStackTrace();
		} catch (final SMSCException smsce) {
//			smsce.printStackTrace();
		}
		}
	}

}
