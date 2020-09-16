package com.pillikan.store.util;

import com.pillikan.store.exception.NotFoundException;
import com.pillikan.store.model.HasId;

public class ValidationUtil {
    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void assureIdConsistent(HasId object, int id) {
        if (object.isNew()) {
            object.setId(id);
        } else if(object.getId() != id) {
            throw new IllegalArgumentException("Entity must be with id=" + id);
        }
    }

    public static void checkNew(HasId object) {
        if (!object.isNew()) {
            throw new IllegalArgumentException("Entity must be new id = null");
        }
    }
}
