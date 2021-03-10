package com.mycompany.assigmentweb.util;

import com.mycompany.assigmentweb.model.FileBucket;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FileValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return FileBucket.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        FileBucket file = (FileBucket) obj;

        if (file.getFile() != null) {

            if (file.getFile().getSize() == 0) {
                errors.rejectValue("file", "missing.file");

                //type photo
            } else if (!file.getFile().getContentType().equals("image/jpeg") && !file.getFile().getContentType().equals("image/gif") && !file.getFile().getContentType().equals("image/png")) {
                errors.rejectValue("file", "type.file");

                // size photo 5MB
            } else if (file.getFile().getSize() > 5242880) {
                errors.rejectValue("file", "size.file");
            }
        }
    }
}
