package com.okrawczy.restaurantsfinder.utils.converters;

import com.okrawczy.restaurantsfinder.domain.Client;
import com.okrawczy.restaurantsfinder.tos.ClientTO;
import org.springframework.stereotype.Component;

/**
 * Created by Olaf on 2017-11-06.
 */

@Component
public class ClientTOConverter implements DomainTOConverter<Client, ClientTO> {

    @Override
    public ClientTO convertToTO(Client entity) {

        ClientTO result = new ClientTO();
        result.setId(entity.getId());
        result.setFirstName(entity.getFirstName());
        result.setLastName(entity.getLastName());
        result.setEmailAddress(entity.getEmailAddress());
        result.setPhoneNumber(entity.getPhoneNumber());

        return result;
    }
}
