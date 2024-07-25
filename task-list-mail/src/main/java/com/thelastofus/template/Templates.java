package com.thelastofus.template;

import com.thelastofus.dto.mail.Message;
import io.quarkus.mailer.MailTemplate;
import io.quarkus.qute.CheckedTemplate;

@CheckedTemplate
public class Templates {
    public static native MailTemplate.MailTemplateInstance registration(Message message);
    public static native MailTemplate.MailTemplateInstance remainder(Message message);
}
