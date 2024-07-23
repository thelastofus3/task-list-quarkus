package com.thelastofus.template;

import io.quarkus.mailer.MailTemplate;
import io.quarkus.qute.CheckedTemplate;

@CheckedTemplate
public class Templates {
    public static native MailTemplate.MailTemplateInstance registration(String username);
}
