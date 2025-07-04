package com.vocabulary.vocabulary.Util;

import com.vocabulary.vocabulary.Common.DefaultCheckable;
import com.vocabulary.vocabulary.exception.ResourceErrorException;

import java.util.List;

public class DefaultValidatorUtil {

    public static <T extends DefaultCheckable> void validateNotDefault(List<T> entries) {
        List<T> defaultEntries = entries.stream()
                .filter(T::isDefaultValue)
                .toList();

        if (!defaultEntries.isEmpty()) {
            throw new IllegalStateException("Can't delete this Row: " + defaultEntries.get(0).getName());
        }
    }

    public static void checkNotDefault(DefaultCheckable entry, String action) {
        if (entry.isDefaultValue()) {
            throw new IllegalStateException("Can't " + action + " this Row: " + entry.getName());
        }
    }
    public static void checkNameDuplicate(long countName) {
        if(countName > 0)
        {
            throw new ResourceErrorException("Can't use this name");
        }
    }

}
