package com.example.oppari2.frame.development;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * Custom Logger to easily stop logging without commenting every message in codebase.
 */
public class Dev {

    public static final String MAIN_TOPIC     = "oppari2-";
    public static final String TOPIC_DEFAULT  = "dev";
    public static final List<String> filterTopics  = Arrays.asList("nan");
    public static final List<String> filterSenders = Arrays.asList("nan");
    public static final int columnWidth       = 30;

    /**
     * Print to logcat and filter topics. If DEVMODE_LOG is false, nothing is logged.
     *
     * @param topic   Is merged with main topic to form tag for Log
     * @param sender  Forms first part of Log message
     * @param message Message main content
     * @param isError If true Lod.e is used, otherwise Lod.d
     */
    protected static void printToLogCat(String topic, String sender, String message, boolean isError) {



        // Do not log, if topic is in filterlist
        for (String filter : filterTopics) {
            if (filter.equals(topic)) {
                return;
            }
        }

        // Do not log, if sender is in filterlist
        for (String filter : filterSenders) {
            if (filter.equals(sender)) {
                return;
            }
        }

        String tag = MAIN_TOPIC + topic;

        int spaces = columnWidth - sender.length();

        String content = "          ";
        if (spaces > 0) {
            String spaceStr = new String(new char[spaces]).replace("\0", " ");
            content += "| " + sender + spaceStr + " -> " + message;
        } else {
            content += "| " + sender + " -> " + message;
        }

        try {
            if (isError) {
                Log.e(tag, content);
            } else {
                Log.d(tag, content);
            }
        } catch (Exception e) {
            Log.e(MAIN_TOPIC + "log", e.toString());
        }


    }

    /**
     * Log message.
     *
     * @param topic   Is merged with main topic to form tag for Log
     * @param sender  Forms first part of Log message
     * @param message Message main content
     * @param isError If true Lod.e is used, otherwise Lod.d
     */
    public static void m(String topic, String sender, String message, boolean isError) {
        printToLogCat(topic, sender, message, isError);
    }

    /**
     * Log message with default topic.
     *
     * @param sender  Forms first part of Log message
     * @param message Message main content
     * @param isError If true Lod.e is used, otherwise Lod.d
     */
    public static void m(String sender, String message, boolean isError) {
        printToLogCat(TOPIC_DEFAULT, sender, message, isError);
    }

    /**
     * Log non-error message with default topic.
     *
     * @param sender  Forms first part of Log message
     * @param message Message main content
     */
    public static void m(String sender, String message) {
        printToLogCat(TOPIC_DEFAULT, sender, message, false);
    }

    /**
     * Log error message with default topic.
     *
     * @param sender  Forms first part of Log message
     * @param message Message main content
     */
    public static void em(String sender, String message) {
        printToLogCat(TOPIC_DEFAULT, sender, message, true);
    }

    /**
     * Log non-error message.
     *
     * @param topic   Is merged with main topic to form tag for Log
     * @param sender  Forms first part of Log message
     * @param message Message main content
     */
    public static void m(String topic, String sender, String message) {
        printToLogCat(topic, sender, message, false);
    }

    /**
     * Log error message.
     *
     * @param topic   Is merged with main topic to form tag for Log
     * @param sender  Forms first part of Log message
     * @param message Message main content
     */
    public static void em(String topic, String sender, String message) {
        printToLogCat(topic, sender, message, true);
    }
}
