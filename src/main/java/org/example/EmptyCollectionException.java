package org.example;

import java.util.Collection;

public class EmptyCollectionException extends RuntimeException {
     EmptyCollectionException(Collection collection) {
         super("Collection " + collection + " is empty");
     }
}
