package com.okrawczy.restaurantsfinder.utils.converters;

import com.okrawczy.restaurantsfinder.domain.Client;
import com.okrawczy.restaurantsfinder.tos.ClientTO;

/**
 * Created by Olaf on 2017-11-06.
 */
public class ClientTOConverter implements DomainTOConverter<Client, ClientTO> {

    @Override
    public ClientTO convertToTO(Client entity) {

        ClientTO result = new ClientTO();
        result.setFirstName(entity.getFirstName());
        result.setLastName(entity.getLastName());
        result.setEmailAddress(entity.getEmailAddress());
        result.setPhoneNumber(entity.getPhoneNumber());

        return result;
    }
}
