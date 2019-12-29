<!doctype html>
<html>
<head>
    <title></title>
    <style type="text/css">
        /* CLIENT-SPECIFIC STYLES */
        body, table, td, a { -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; }
        table, td { mso-table-lspace: 0pt; mso-table-rspace: 0pt; }
        img { -ms-interpolation-mode: bicubic; }

        /* RESET STYLES */
        img { border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; }
        table { border-collapse: collapse !important; }
        body { height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important; }

        /* iOS BLUE LINKS */
        a[x-apple-data-detectors] {
            color: inherit !important;
            text-decoration: none !important;
            font-size: inherit !important;
            font-family: inherit !important;
            font-weight: inherit !important;
            line-height: inherit !important;
        }

        /* MOBILE STYLES */
        @media screen and (max-width: 600px) {
            .mobile-padding {
                padding-left: 5% !important;
                padding-right: 5% !important;
            }
        }

        /* ANDROID CENTER FIX */
        div[style*="margin: 16px 0;"] { margin: 0 !important; }
    </style>

</head>
<body style="margin: 0 !important; padding: 0; !important background-color: #ffffff;" bgcolor="#f6f6f6">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td align="center" valign="top" width="100%" bgcolor="#3b4a69" style="background: #3b4a69; background-size: cover; padding: 50px 15px;" class="mobile-padding">
            <!--[if (gte mso 9)|(IE)]>
            <table align="center" border="0" cellspacing="0" cellpadding="0" width="600">
                <tr>
                    <td align="center" valign="top" width="600">
            <![endif]-->
            <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:600px;">
                <tr>
                    <td align="center" valign="top" style="padding: 0; font-family: Open Sans, Helvetica, Arial, sans-serif;">
                        <h1 style="font-size: 40px; color: #ffffff;">Приглашение на мероприятие</h1>
                        <h1 style="font-size: 25px; color: #ffffff;">${eventName}</h1>
                    </td>
                </tr>
                <tr>
                    <td align="center" valign="top" style="padding: 0 0 35px 0; font-family: Open Sans, Helvetica, Arial, sans-serif;">
                        <table align="center" border="0" cellpadding="0" cellspacing="0" width="80%" style="max-width: 200px;">
                            <tr>
                                <td align="center" bgcolor="red" style="color: #ffffff; font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 14px; padding: 10px; border-radius: 3px 3px 0 0;">
                                    ${eventMonth}
                                </td>
                            </tr>
                            <tr>
                                <td align="center" bgcolor="#ffffff" style="color: #444444; font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 48px; padding: 15px; border-radius: 0 0 3px 3px;">
                                    ${eventDay}
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td align="center" valign="top" style="font-family: Open Sans, Helvetica, Arial, sans-serif; padding-top: 0;">
                        <p style="color: #b7bdc9; font-size: 16px; line-height: 24px; margin: 0;">
                            <strong>Здравствуйте, ${recipientFirstName} ${recipientLastName}!</strong><br/>
                            ${eventDescription}
                        </p>
                    </td>
                </tr>
            </table>
            <!--[if (gte mso 9)|(IE)]>
            </td>
            </tr>
            </table>
            <![endif]-->
        </td>
    </tr>
    <tr>
        <td align="center" height="100%" valign="top" width="100%" bgcolor="#f6f6f6" style="padding: 40px 15px;">
            <!--[if (gte mso 9)|(IE)]>
            <table align="center" border="0" cellspacing="0" cellpadding="0" width="600">
                <tr>
                    <td align="center" valign="top" width="600">
            <![endif]-->
            <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:600px;">
                <tr>
                    <td align="center" valign="top" style="padding: 0; font-family: Open Sans, Helvetica, Arial, sans-serif; color: #999999;">
                        <p style="font-size: 14px; line-height: 20px;">
                            Письмо отправлено через "Отправитель приглашений на мероприятия"
                        </p>
                    </td>
                </tr>
            </table>
            <!--[if (gte mso 9)|(IE)]>
            </td>
            </tr>
            </table>
            <![endif]-->
        </td>
    </tr>
</table>
</body>
</html>
