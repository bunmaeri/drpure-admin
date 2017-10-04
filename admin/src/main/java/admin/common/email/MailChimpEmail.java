package admin.common.email;

import java.util.ArrayList;
import java.util.Map;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;

import admin.common.controller.CodeController;
import admin.common.util.ObjectUtils;

public class MailChimpEmail {
	// 공통코드 로드
		public static CodeController code = new CodeController();
			
		public static void run(Map<String,Object> info) throws Exception {
			MandrillApi mandrillApi = new MandrillApi(code.getValue("mailchimp_api_key"));

			try {
				// create your message
				MandrillMessage message = new MandrillMessage();
				message.setSubject(ObjectUtils.null2void(info.get("subject")));
				message.setHtml(ObjectUtils.null2void(info.get("html")));
				message.setAutoText(true);
				message.setFromEmail(code.getValue("mailchimp_send_email"));
				message.setFromName(code.getValue("config_comapny_name"));
				// add recipients
				ArrayList<Recipient> recipients = new ArrayList<Recipient>();
				Recipient recipient = new Recipient();
				recipient.setEmail(ObjectUtils.null2void(info.get("recipient_email")));
				recipient.setName(ObjectUtils.null2void(info.get("recipient_name")));
				recipients.add(recipient);
				message.setTo(recipients);
				message.setPreserveRecipients(true);

				// then ... send
				MandrillMessageStatus[] messageStatusReports = mandrillApi.messages().send(message, true);
				if(null!=messageStatusReports) {
					int size = messageStatusReports.length;
					for(int i=0;i<size;i++) {
						System.err.println("MailChimpEmail: "+messageStatusReports[i].getStatus());
						System.err.println("Reject Reason: "+messageStatusReports[i].getRejectReason());
					}
				}
			} catch(final MandrillApiError e) {
				e.getMandrillErrorAsJson();
			}
			
		}
	}
