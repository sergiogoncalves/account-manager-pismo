package com.pismo.accountmanager.util;

import com.pismo.accountmanager.dto.IdDto;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Optional;

/**
 *  Utility class to standardize requests and responses
 */
public class ControllerTools {

    public static ResponseEntity getResponseEntitySuccess(Optional optionalObject) {
        if (optionalObject.isPresent()) {
            return ResponseEntity.ok(optionalObject.get());
        } else return ResponseEntity.ok().build();
    }

    public static ResponseEntity getResponseEntityCreated(Optional<IdDto> optionalObject, String pathRedirect) {
        if (optionalObject.isPresent()) {
            return ResponseEntity.created(URI.create(pathRedirect + "/" + optionalObject.get().id())).body(optionalObject.get());
        } else return ResponseEntity.created(null).build();

    }

    /**
     *  Controls attribute scope for Json
     */
    public static class JsonViews {
        public interface None {}

        public interface Update {}

        public interface Create extends Update {}

        public interface Select extends Create {}

    }
}
