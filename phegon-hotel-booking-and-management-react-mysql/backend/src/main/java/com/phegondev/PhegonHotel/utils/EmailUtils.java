package com.phegondev.PhegonHotel.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.phegondev.PhegonHotel.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailUtils {
	
	@Autowired
	private EmailService emailService;
	
	
	@Value("${spring.mail.username}")
	private String from;
	
	@Value("${hotel.name}")
	private String hotelName;
	
	
	public  void sendBookingConfirmationMail(final String toMail,final String userName,final String confirmationCode,String checkInDate,String checkOutDate,String roomType,int noOfGuests) {
		String subject ="Your Hotel Booking Confirmation -PhegonHotel";
		try {
			
			String emailTemplate =getConfirmationMailTemplate();
			emailTemplate =emailTemplate.replace("${GUEST_NAME}", userName);
			emailTemplate =emailTemplate.replace("${BOOKING_CONFIRMATION_CODE}", confirmationCode);
			emailTemplate =emailTemplate.replace("${CHECK_IN_DATE}", checkInDate);
			emailTemplate =emailTemplate.replace("${CHECK_OUT_DATE}", checkOutDate);
			emailTemplate =emailTemplate.replace("${ROOM_TYPE}", roomType);
			emailTemplate =emailTemplate.replace("${NO_OF_GUESTS}", noOfGuests+"");
			
			emailTemplate =emailTemplate.replace("${HOTEL_NAME}", hotelName);
			emailTemplate =emailTemplate.replace("${HOTEL_PHONE_NUMBER}", "XXXXXXX77");
			emailTemplate =emailTemplate.replace("${HOTEL_EMAIL_ADDRESS}", "customercare@"+hotelName+".com");
			emailTemplate =emailTemplate.replace("${[HOTEL_WEBSITE}", "www."+hotelName+".com");
			
//            SimpleMailMessage registrationEmail = new SimpleMailMessage();
//            registrationEmail.setTo(toMail);
//            registrationEmail.setSubject("Your Hotel Booking Confirmation -PhegonHotel");
//            registrationEmail.setText(emailTemplate);
//            registrationEmail.setFrom(from);

            emailService.sendHtmlEmail(toMail, subject, emailTemplate);
			log.info("Mail sent successfully...............!");
		}catch (Exception e) {
		   e.printStackTrace();
		}
	}
	
	
	public void sendRegistractionConfirmationMail(final String toMail,final String userName) {
	  String subject ="Your Registration Confirmation Mail @PhegonHotel";

		try {
			String emailTemplate =sendRegistractionConfirmationMailTemplate();
			 emailTemplate=emailTemplate.replace("${LOGIN_URL}", "http://localhost:3000/login");
			 emailTemplate=emailTemplate.replace("${YOUR_HOTEL_NAME}", hotelName);
			 emailTemplate=emailTemplate.replace("${USER_NAME}", userName);
			 emailService.sendHtmlEmail(toMail, subject, emailTemplate);
			log.info("Mail sent successfully...............!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	private static String sendRegistractionConfirmationMailTemplate() {
		return "<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Registration Successful</title>\r\n"
				+ "    <style>\r\n"
				+ "        body {\r\n"
				+ "            font-family: 'Segoe UI', sans-serif;\r\n"
				+ "            background-color: #f0f3f5;\r\n"
				+ "            padding: 0;\r\n"
				+ "            margin: 0;\r\n"
				+ "        }\r\n"
				+ "        .container {\r\n"
				+ "            max-width: 600px;\r\n"
				+ "            margin: 50px auto;\r\n"
				+ "            padding: 0;\r\n"
				+ "            background-color: #ffffff;\r\n"
				+ "            border-radius: 12px;\r\n"
				+ "            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);\r\n"
				+ "        }\r\n"
				+ "        .header {\r\n"
				+ "            background-color: #673ab7;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            color: white;\r\n"
				+ "            border-radius: 12px 12px 0 0;\r\n"
				+ "            text-align: center;\r\n"
				+ "        }\r\n"
				+ "        .header h1 {\r\n"
				+ "            margin: 0;\r\n"
				+ "            font-size: 24px;\r\n"
				+ "        }\r\n"
				+ "        .content {\r\n"
				+ "            padding: 30px;\r\n"
				+ "            text-align: center;\r\n"
				+ "        }\r\n"
				+ "        h2 {\r\n"
				+ "            font-size: 22px;\r\n"
				+ "            color: #333333;\r\n"
				+ "            margin-bottom: 10px;\r\n"
				+ "        }\r\n"
				+ "        p {\r\n"
				+ "            color: #666666;\r\n"
				+ "            font-size: 16px;\r\n"
				+ "            line-height: 1.6;\r\n"
				+ "            margin: 0 0 20px;\r\n"
				+ "        }\r\n"
				+ "        .button {\r\n"
				+ "            background-color: #ff5722;\r\n"
				+ "            color: white;\r\n"
				+ "            padding: 12px 25px;\r\n"
				+ "            text-decoration: none;\r\n"
				+ "            border-radius: 6px;\r\n"
				+ "            font-size: 16px;\r\n"
				+ "            margin-top: 20px;\r\n"
				+ "            display: inline-block;\r\n"
				+ "        }\r\n"
				+ "        .button:hover {\r\n"
				+ "            background-color: #e64a19;\r\n"
				+ "        }\r\n"
				+ "        .footer {\r\n"
				+ "            background-color: #f5f5f5;\r\n"
				+ "            padding: 15px;\r\n"
				+ "            font-size: 14px;\r\n"
				+ "            color: #888888;\r\n"
				+ "            text-align: center;\r\n"
				+ "            border-radius: 0 0 12px 12px;\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <div class=\"container\">\r\n"
				+ "        <div class=\"header\">\r\n"
				+ "            <h1>Welcome to ${YOUR_HOTEL_NAME}!</h1>\r\n"
				+ "        </div>\r\n"
				+ "        <div class=\"content\">\r\n"
				+ "            <h2>Hi ${USER_NAME},</h2>\r\n"
				+ "            <p>Your registration has been successfully completed. We are excited to have you onboard!</p>\r\n"
				+ "            <p>You can now log in to explore rooms, manage bookings, and more.</p>\r\n"
				+ "            <a href=\"${LOGIN_URL}\" class=\"button\">Log in to your account</a>\r\n"
				+ "        </div>\r\n"
				+ "        <div class=\"footer\">\r\n"
				+ "            <p>If this wasn't you, please <a href=\"#\">contact our support</a> immediately.</p>\r\n"
				+ "        </div>\r\n"
				+ "    </div>\r\n"
				+ "</body>\r\n"
				+ "</html>\r\n";
	}
	
	
	private static String getConfirmationMailTemplate() {
		return "<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Booking Confirmation</title>\r\n"
				+ "    <style>\r\n"
				+ "        body {\r\n"
				+ "            font-family: Arial, sans-serif;\r\n"
				+ "            margin: 0;\r\n"
				+ "            padding: 0;\r\n"
				+ "            background-color: #f4f4f4;\r\n"
				+ "        }\r\n"
				+ "        .email-container {\r\n"
				+ "            max-width: 600px;\r\n"
				+ "            margin: 0 auto;\r\n"
				+ "            background: #fff;\r\n"
				+ "            border-radius: 8px;\r\n"
				+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
				+ "            overflow: hidden;\r\n"
				+ "        }\r\n"
				+ "        .header {\r\n"
				+ "            background: linear-gradient(135deg, #4CAF50, #8BC34A);\r\n"
				+ "            color: white;\r\n"
				+ "            text-align: center;\r\n"
				+ "            padding: 20px;\r\n"
				+ "        }\r\n"
				+ "        .header h1 {\r\n"
				+ "            margin: 0;\r\n"
				+ "        }\r\n"
				+ "        .content {\r\n"
				+ "            padding: 20px;\r\n"
				+ "            color: #333;\r\n"
				+ "        }\r\n"
				+ "        .content h2 {\r\n"
				+ "            color: #FF6F00;\r\n"
				+ "        }\r\n"
				+ "        .details {\r\n"
				+ "            background-color: #e3f2fd;\r\n"
				+ "            padding: 15px;\r\n"
				+ "            border-radius: 8px;\r\n"
				+ "            margin-top: 10px;\r\n"
				+ "            border-left: 5px solid #42a5f5;\r\n"
				+ "        }\r\n"
				+ "        .details p {\r\n"
				+ "            margin: 5px 0;\r\n"
				+ "        }\r\n"
				+ "        .btn {\r\n"
				+ "            display: inline-block;\r\n"
				+ "            background-color: #ff4081;\r\n"
				+ "            color: white;\r\n"
				+ "            padding: 10px 20px;\r\n"
				+ "            text-decoration: none;\r\n"
				+ "            border-radius: 5px;\r\n"
				+ "            margin-top: 20px;\r\n"
				+ "        }\r\n"
				+ "        .btn:hover {\r\n"
				+ "            background-color: #f50057;\r\n"
				+ "        }\r\n"
				+ "        .footer {\r\n"
				+ "            background-color: #009688;\r\n"
				+ "            color: white;\r\n"
				+ "            text-align: center;\r\n"
				+ "            padding: 10px;\r\n"
				+ "            font-size: 14px;\r\n"
				+ "        }\r\n"
				+ "        .footer p {\r\n"
				+ "            margin: 5px 0;\r\n"
				+ "        }\r\n"
				+ "        ul {\r\n"
				+ "            padding-left: 20px;\r\n"
				+ "            list-style: none;\r\n"
				+ "        }\r\n"
				+ "        ul li {\r\n"
				+ "            background: url('https://img.icons8.com/color/48/000000/checked.png') no-repeat left center;\r\n"
				+ "            background-size: 16px;\r\n"
				+ "            padding-left: 25px;\r\n"
				+ "            margin-bottom: 8px;\r\n"
				+ "        }\r\n"
				+ "        @media only screen and (max-width: 600px) {\r\n"
				+ "            .email-container {\r\n"
				+ "                width: 100%;\r\n"
				+ "                box-shadow: none;\r\n"
				+ "            }\r\n"
				+ "            .content, .header, .footer {\r\n"
				+ "                padding: 15px;\r\n"
				+ "            }\r\n"
				+ "            .details {\r\n"
				+ "                padding: 10px;\r\n"
				+ "            }\r\n"
				+ "            .btn {\r\n"
				+ "                padding: 10px 15px;\r\n"
				+ "            }\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <div class=\"email-container\">\r\n"
				+ "        <div class=\"header\">\r\n"
				+ "            <h1>Booking Confirmation</h1>\r\n"
				+ "        </div>\r\n"
				+ "        <div class=\"content\">\r\n"
				+ "            <h2>Hello, ${GUEST_NAME}</h2>\r\n"
				+ "            <p>We are thrilled to confirm your reservation at <strong>${HOTEL_NAME}</strong>!</p>\r\n"
				+ "\r\n"
				+ "            <div class=\"details\">\r\n"
				+ "                <h3>Booking Details:</h3>\r\n"
				+ "                <p><strong>Confirmation Code:</strong> ${BOOKING_CONFIRMATION_CODE}</p>\r\n"
				+ "                <p><strong>Check-in:</strong> ${CHECK_IN_DATE}</p>\r\n"
				+ "                <p><strong>Check-out:</strong> ${CHECK_OUT_DATE}</p>\r\n"
				+ "                <p><strong>Room Type:</strong> ${ROOM_TYPE}</p>\r\n"
				+ "                <p><strong>Guests:</strong> ${NO_OF_GUESTS}</p>\r\n"
				+ "            </div>\r\n"
				+ "\r\n"
				+ "            <p>Contact us at <strong>${HOTEL_PHONE_NUMBER}</strong> or <strong>${HOTEL_EMAIL_ADDRESS}</strong> for special requests.</p>\r\n"
				+ "            <p>We can't wait to host you! Enjoy:</p>\r\n"
				+ "            <ul>\r\n"
				+ "                <li>Complimentary Wi-Fi</li>\r\n"
				+ "                <li>Restaurant and bar</li>\r\n"
				+ "                <li>Fitness center</li>\r\n"
				+ "                <li>Airport shuttle (on request)</li>\r\n"
				+ "            </ul>\r\n"
				+ "\r\n"
				+ "            <p>Need assistance? <a href=\"mailto:${HOTEL_EMAIL_ADDRESS}\" class=\"btn\">Contact Us</a></p>\r\n"
				+ "        </div>\r\n"
				+ "        <div class=\"footer\">\r\n"
				+ "            <p>&copy; 2024 ${HOTEL_NAME}, All Rights Reserved.</p>\r\n"
				+ "            <p>${HOTEL_PHONE_NUMBER} | ${HOTEL_WEBSITE}</p>\r\n"
				+ "        </div>\r\n"
				+ "    </div>\r\n"
				+ "</body>\r\n"
				+ "</html>\r\n";
	}

}