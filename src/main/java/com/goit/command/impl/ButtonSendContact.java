package com.goit.command.impl;

import com.goit.command.input.CustomerInput;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

class ButtonSendContact {

   private ButtonSendContact() {
       throw new UnsupportedOperationException();
   }

   public static SendMessage buttonSendContact(CustomerInput customerInput) {
       KeyboardButton contactButton = new KeyboardButton("Send contact");
       contactButton.setRequestContact(true);

       KeyboardRow row = new KeyboardRow();
       row.add(contactButton);

       List<KeyboardRow> keyboard = new ArrayList<>();
       keyboard.add(row);

       ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
       markup.setKeyboard(keyboard);
       markup.setResizeKeyboard(true);

       SendMessage sendMessage = new SendMessage();
       sendMessage.setChatId(customerInput.getChatId());
       sendMessage.setText("Click the \"Send contact\" button to register!");
       sendMessage.setReplyMarkup(markup);
       return sendMessage;
   }
}
