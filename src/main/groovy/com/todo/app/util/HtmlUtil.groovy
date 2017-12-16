package com.todo.app.util

class HtmlUtil {

    static String mailHtml = "<!-- THIS EMAIL WAS BUILT AND TESTED WITH LITMUS http://litmus.com -->\n" +
            "<!-- IT WAS RELEASED UNDER THE MIT LICENSE https://opensource.org/licenses/MIT -->\n" +
            "<!-- QUESTIONS? TWEET US @LITMUSAPP -->\n" +
            "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <title></title>\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
            "    <style type=\"text/css\">\n" +
            "        /* FONTS */\n" +
            "        @media screen {\n" +
            "            @font-face {\n" +
            "                font-family: 'Lato';\n" +
            "                font-style: normal;\n" +
            "                font-weight: 400;\n" +
            "                src: local('Lato Regular'), local('Lato-Regular'), url(https://fonts.gstatic.com/s/lato/v11/qIIYRU-oROkIk8vfvxw6QvesZW2xOQ-xsNqO47m55DA.woff) format('woff');\n" +
            "            }\n" +
            "\n" +
            "            @font-face {\n" +
            "                font-family: 'Lato';\n" +
            "                font-style: normal;\n" +
            "                font-weight: 700;\n" +
            "                src: local('Lato Bold'), local('Lato-Bold'), url(https://fonts.gstatic.com/s/lato/v11/qdgUG4U09HnJwhYI-uK18wLUuEpTyoUstqEm5AMlJo4.woff) format('woff');\n" +
            "            }\n" +
            "\n" +
            "            @font-face {\n" +
            "                font-family: 'Lato';\n" +
            "                font-style: italic;\n" +
            "                font-weight: 400;\n" +
            "                src: local('Lato Italic'), local('Lato-Italic'), url(https://fonts.gstatic.com/s/lato/v11/RYyZNoeFgb0l7W3Vu1aSWOvvDin1pK8aKteLpeZ5c0A.woff) format('woff');\n" +
            "            }\n" +
            "\n" +
            "            @font-face {\n" +
            "                font-family: 'Lato';\n" +
            "                font-style: italic;\n" +
            "                font-weight: 700;\n" +
            "                src: local('Lato Bold Italic'), local('Lato-BoldItalic'), url(https://fonts.gstatic.com/s/lato/v11/HkF_qI1x_noxlxhrhMQYELO3LdcAZYWl9Si6vvxL-qU.woff) format('woff');\n" +
            "            }\n" +
            "        }\n" +
            "\n" +
            "        /* CLIENT-SPECIFIC STYLES */\n" +
            "        body, table, td, a { -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; }\n" +
            "        table, td { mso-table-lspace: 0pt; mso-table-rspace: 0pt; }\n" +
            "        img { -ms-interpolation-mode: bicubic; }\n" +
            "\n" +
            "        /* RESET STYLES */\n" +
            "        img { border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; }\n" +
            "        table { border-collapse: collapse !important; }\n" +
            "        body { height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important; }\n" +
            "\n" +
            "        /* iOS BLUE LINKS */\n" +
            "        a[x-apple-data-detectors] {\n" +
            "            color: inherit !important;\n" +
            "            text-decoration: none !important;\n" +
            "            font-size: inherit !important;\n" +
            "            font-family: inherit !important;\n" +
            "            font-weight: inherit !important;\n" +
            "            line-height: inherit !important;\n" +
            "        }\n" +
            "\n" +
            "        /* MOBILE STYLES */\n" +
            "        @media screen and (max-width:600px){\n" +
            "            h1 {\n" +
            "                font-size: 32px !important;\n" +
            "                line-height: 32px !important;\n" +
            "            }\n" +
            "        }\n" +
            "\n" +
            "        /* ANDROID CENTER FIX */\n" +
            "        div[style*=\"margin: 16px 0;\"] { margin: 0 !important; }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body style=\"background-color: #f4f4f4; margin: 0 !important; padding: 0 !important;\">\n" +
            "\n" +
            "<!-- HIDDEN PREHEADER TEXT -->\n" +
            "<div style=\"display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: 'Lato', Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;\">\n" +
            "    We're thrilled to have you here! Get ready to dive into your new account.\n" +
            "</div>\n" +
            "\n" +
            "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
            "    <!-- LOGO -->\n" +
            "    <tr>\n" +
            "        <td bgcolor=\"#FFA73B\" align=\"center\">\n" +
            "            <!--[if (gte mso 9)|(IE)]>\n" +
            "            <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\">\n" +
            "                <tr>\n" +
            "                    <td align=\"center\" valign=\"top\" width=\"600\">\n" +
            "            <![endif]-->\n" +
            "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\" >\n" +
            "                <tr>\n" +
            "                    <td align=\"center\" valign=\"top\" style=\"padding: 40px 10px 40px 10px;\">\n" +
            "                        <a href=\"http://litmus.com\" target=\"_blank\">\n" +
            "                            <img alt=\"Logo\" src=\"http://litmuswww.s3.amazonaws.com/community/template-gallery/ceej/logo.png\" width=\"40\" height=\"40\" style=\"display: block; width: 40px; max-width: 40px; min-width: 40px; font-family: 'Lato', Helvetica, Arial, sans-serif; color: #ffffff; font-size: 18px;\" border=\"0\">\n" +
            "                        </a>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </table>\n" +
            "            <!--[if (gte mso 9)|(IE)]>\n" +
            "            </td>\n" +
            "            </tr>\n" +
            "            </table>\n" +
            "            <![endif]-->\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <!-- HERO -->\n" +
            "    <tr>\n" +
            "        <td bgcolor=\"#FFA73B\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">\n" +
            "            <!--[if (gte mso 9)|(IE)]>\n" +
            "            <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\">\n" +
            "                <tr>\n" +
            "                    <td align=\"center\" valign=\"top\" width=\"600\">\n" +
            "            <![endif]-->\n" +
            "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\" >\n" +
            "                <tr>\n" +
            "                    <td bgcolor=\"#ffffff\" align=\"center\" valign=\"top\" style=\"padding: 40px 20px 20px 20px; border-radius: 4px 4px 0px 0px; color: #111111; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; letter-spacing: 4px; line-height: 48px;\">\n" +
            "                        <h1 style=\"font-size: 48px; font-weight: 400; margin: 0;\">Welcome!</h1>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </table>\n" +
            "            <!--[if (gte mso 9)|(IE)]>\n" +
            "            </td>\n" +
            "            </tr>\n" +
            "            </table>\n" +
            "            <![endif]-->\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <!-- COPY BLOCK -->\n" +
            "    <tr>\n" +
            "        <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">\n" +
            "            <!--[if (gte mso 9)|(IE)]>\n" +
            "            <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\">\n" +
            "                <tr>\n" +
            "                    <td align=\"center\" valign=\"top\" width=\"600\">\n" +
            "            <![endif]-->\n" +
            "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\" >\n" +
            "                <!-- COPY -->\n" +
            "                <tr>\n" +
            "                    <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 20px 30px 40px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\" >\n" +
            "                        <p style=\"margin: 0;\">We're excited to have you get started.</p>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <!-- BULLETPROOF BUTTON -->\n" +
            "                <tr>\n" +
            "                    <td bgcolor=\"#ffffff\" align=\"left\">\n" +
            "                        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "                            <tr>\n" +
            "                                <td bgcolor=\"#ffffff\" align=\"center\" style=\"padding: 20px 30px 60px 30px;\">\n" +
            "                                    <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "                                    </table>\n" +
            "                                </td>\n" +
            "                            </tr>\n" +
            "                        </table>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <!-- COPY -->\n" +
            "                <tr>\n" +
            "                    <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 0px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\" >\n" +
            "                        <p style=\"margin: 0;\">If that doesn't work, copy and paste the following link in your browser:</p>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <!-- COPY -->\n" +
            "                <!-- COPY -->\n" +
            "                <tr>\n" +
            "                    <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 20px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\" >\n" +
            "                        <p style=\"margin: 0;\">If you have any questions, just reply to this email—we're always happy to help out.</p>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <!-- COPY -->\n" +
            "                <tr>\n" +
            "                    <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 40px 30px; border-radius: 0px 0px 4px 4px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\" >\n" +
            "                        <p style=\"margin: 0;\">Cheers,<br>The Ceej Team</p>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </table>\n" +
            "            <!--[if (gte mso 9)|(IE)]>\n" +
            "            </td>\n" +
            "            </tr>\n" +
            "            </table>\n" +
            "            <![endif]-->\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <!-- SUPPORT CALLOUT -->\n" +
            "    <tr>\n" +
            "        <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 30px 10px 0px 10px;\">\n" +
            "            <!--[if (gte mso 9)|(IE)]>\n" +
            "            <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\">\n" +
            "                <tr>\n" +
            "                    <td align=\"center\" valign=\"top\" width=\"600\">\n" +
            "            <![endif]-->\n" +
            "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\" >\n" +
            "                <!-- HEADLINE -->\n" +
            "                <tr>\n" +
            "                    <td bgcolor=\"#FFECD1\" align=\"center\" style=\"padding: 30px 30px 30px 30px; border-radius: 4px 4px 4px 4px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\" >\n" +
            "                        <h2 style=\"font-size: 20px; font-weight: 400; color: #111111; margin: 0;\">Need more help?</h2>\n" +
            "                        <p style=\"margin: 0;\"><a href=\"http://litmus.com\" target=\"_blank\" style=\"color: #FFA73B;\">We&rsquo;re here, ready to talk</a></p>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </table>\n" +
            "            <!--[if (gte mso 9)|(IE)]>\n" +
            "            </td>\n" +
            "            </tr>\n" +
            "            </table>\n" +
            "            <![endif]-->\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <!-- FOOTER -->\n" +
            "    <tr>\n" +
            "        <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">\n" +
            "            <!--[if (gte mso 9)|(IE)]>\n" +
            "            <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\">\n" +
            "                <tr>\n" +
            "                    <td align=\"center\" valign=\"top\" width=\"600\">\n" +
            "            <![endif]-->\n" +
            "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\" >\n" +
            "                <!-- NAVIGATION -->\n" +
            "                <tr>\n" +
            "                    <td bgcolor=\"#f4f4f4\" align=\"left\" style=\"padding: 30px 30px 30px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; line-height: 18px;\" >\n" +
            "                        <p style=\"margin: 0;\">\n" +
            "                            <a href=\"http://litmus.com\" target=\"_blank\" style=\"color: #111111; font-weight: 700;\">Dashboard</a> -\n" +
            "                            <a href=\"http://litmus.com\" target=\"_blank\" style=\"color: #111111; font-weight: 700;\">Billing</a> -\n" +
            "                            <a href=\"http://litmus.com\" target=\"_blank\" style=\"color: #111111; font-weight: 700;\">Help</a>\n" +
            "                        </p>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <!-- PERMISSION REMINDER -->\n" +
            "                <tr>\n" +
            "                    <td bgcolor=\"#f4f4f4\" align=\"left\" style=\"padding: 0px 30px 30px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; line-height: 18px;\" >\n" +
            "                        <p style=\"margin: 0;\">You received this email because you just signed up for a new account. If it looks weird, <a href=\"http://litmus.com\" target=\"_blank\" style=\"color: #111111; font-weight: 700;\">view it in your browser</a>.</p>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <!-- UNSUBSCRIBE -->\n" +
            "                <tr>\n" +
            "                    <td bgcolor=\"#f4f4f4\" align=\"left\" style=\"padding: 0px 30px 30px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; line-height: 18px;\" >\n" +
            "                        <p style=\"margin: 0;\">If these emails get annoying, please feel free to <a href=\"http://litmus.com\" target=\"_blank\" style=\"color: #111111; font-weight: 700;\">unsubscribe</a>.</p>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "                <!-- ADDRESS -->\n" +
            "                <tr>\n" +
            "                    <td bgcolor=\"#f4f4f4\" align=\"left\" style=\"padding: 0px 30px 30px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; line-height: 18px;\" >\n" +
            "                        <p style=\"margin: 0;\">Ceej - 1234 Main Street - Anywhere, MA - 56789</p>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </table>\n" +
            "            <!--[if (gte mso 9)|(IE)]>\n" +
            "            </td>\n" +
            "            </tr>\n" +
            "            </table>\n" +
            "            <![endif]-->\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "</table>\n" +
            "\n" +
            "</body>\n" +
            "</html>"
}
