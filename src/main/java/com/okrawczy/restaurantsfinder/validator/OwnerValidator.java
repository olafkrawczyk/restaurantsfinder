package com.okrawczy.restaurantsfinder.validator;

import com.okrawczy.restaurantsfinder.domain.Owner;
import com.okrawczy.restaurantsfinder.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Olaf on 2017-10-17.
 */

@Component("beforeCreateOwnerValidator")
public class OwnerValidator implements Validator {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return Owner.class.equals(aClass);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {
        Owner aOwner = (Owner) o;
        if (this.ownerRepository.findByEmailAddressIgnoreCase(aOwner.getEmailAddress()) != null) {
            errors.rejectValue("emailAddress", "emailAddress.alreadyExists");
        }
    }
}
