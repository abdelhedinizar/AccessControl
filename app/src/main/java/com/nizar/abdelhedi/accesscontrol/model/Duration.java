package com.nizar.abdelhedi.accesscontrol.model;

/**
 * Created by abdelhedi on 18/07/2016.
 */
public class Duration {

    private Long seconds;
    final static long NEMBRE_OF_SECONDE_IN_HOUR=3600;
    final static long NEMBRE_OF_SECONDE_IN_MINUTE=60;

    public Duration(Long seconds) {
        this.seconds = seconds;
    }

    public Long getSeconds() {
        return seconds;
    }

    public void setSeconds(Long seconds) {
        this.seconds = seconds;
    }

    public long ofMinutes() {
        return (seconds - ofHours() * NEMBRE_OF_SECONDE_IN_HOUR) / NEMBRE_OF_SECONDE_IN_MINUTE;
    }

    public long ofHours() {
        return seconds / NEMBRE_OF_SECONDE_IN_HOUR;
    }

    public long ofSeconds() {
        return seconds - ofMinutes() * NEMBRE_OF_SECONDE_IN_MINUTE - ofHours() * NEMBRE_OF_SECONDE_IN_HOUR;
    }

    @Override
    public String toString() {
        return ofHours() + "h"
                + ofMinutes() + "m" + ofSeconds() +"s"
                ;
    }
}
