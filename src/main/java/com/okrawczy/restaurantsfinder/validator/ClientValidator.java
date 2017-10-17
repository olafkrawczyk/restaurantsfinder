package com.okrawczy.restaurantsfinder.validator;

import com.okrawczy.restaurantsfinder.domain.Client;
import com.okrawczy.restaurantsfinder.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Olaf on 2017-10-09.
 */

@Component("beforeCreateClientValidator")
public class ClientValidator implements Validator {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return Client.class.equals(aClass);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {
        Client aClient = (Client) o;
        if (this.clientRepository.findClientByEmailAddressIgnoreCase(aClient.getEmailAddress()) != null) {
            errors.rejectValue("emailAddress", "emailAddress.alreadyExists");
        }
    }
}
