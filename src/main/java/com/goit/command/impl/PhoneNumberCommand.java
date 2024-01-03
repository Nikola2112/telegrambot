package com.goit.command.impl;

import com.goit.command.constant.Answer;
import com.goit.dto.UpdatePhoneCustomerDto;
import com.goit.service.CustomerService;
import com.goit.validator.PhoneNumberValidator;
import org.springframework.stereotype.Component;
import com.goit.command.input.CustomerInput;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class PhoneNumberCommand extends MainCommand {
        public static final String COMMAND_NAME = "/myContact";
        private final CustomerService customerService;
        private final PhoneNumberValidator  phoneNumberValidator;

        public PhoneNumberCommand(CustomerService customerService, PhoneNumberValidator phoneNumberValidator) {
            this.customerService = customerService;
            this.phoneNumberValidator = phoneNumberValidator;
        }

    @Override
    public boolean supports(String name) {
        return super.supports(name);
    }

    @Override
        public SendMessage handle(CustomerInput customerInput) {
            String customerValue = String.valueOf(customerInput.getValue());

            if (customerValue == null || phoneNumberValidator.isValid(customerValue)) {
                String error = String.format(Answer.MESSAGE_CONTACT_INVALID, customerValue);
                return new SendMessage(String.valueOf(customerInput.getChatId()), error);
            }

            customerService.updatePhoneNumber(new UpdatePhoneCustomerDto(customerInput.getChatId(), customerValue));
            return new SendMessage(String.valueOf(customerInput.getChatId()), Answer.MESSAGE_CONTACT_VALID);
        }

        @Override
        protected String getCommandName() {
            return COMMAND_NAME;
        }
    }
