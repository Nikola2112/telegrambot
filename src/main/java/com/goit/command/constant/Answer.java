package com.goit.command.constant;

import com.vdurmont.emoji.EmojiParser;

public class Answer {

        private Answer() {
                throw new UnsupportedOperationException();
        }

        public static final String BLUSH = EmojiParser.parseToUnicode(":blush:");
        public static final String MESSAGE_START_COMMAND = "Hello, %s %s!%nWelcome to our bot. " +
                "To get started, use the /help command and explore the available options.";
        public static final String MESSAGE_HELP_COMMAND =
                "To book a ticket, please follow these steps:%n" +
                        "/myContact - Enter your phone number%n" +
                        "/date_of_visit - Specify your preferred date and time (format: dd/mm/yyyy hh:mm)";
        public static final String MESSAGE_REGISTERED = "You are already registered!";
        public static final String MESSAGE_DATE_VALID = "Great! Your visit date has been saved.";
        public static final String MESSAGE_PAST_DATE = "Sorry, but selecting a past date is not allowed.";
        public static final String MESSAGE_DATE_INVALID = "The entered date is invalid: %s%n" +
                "Please use the /help command to check the correct date format.";
        public static final String MESSAGE_CONTACT_VALID = "Excellent! Your phone number has been saved.";
        public static final String MESSAGE_CONTACT_INVALID = "The entered phone number is invalid: %s%n " +
                "Please use the /help command to check the correct phone number format.";
}
