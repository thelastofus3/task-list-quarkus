package com.thelastofus;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class MailApplication {
    public static void main(String[] args) {
        Quarkus.run(args);
    }
}
