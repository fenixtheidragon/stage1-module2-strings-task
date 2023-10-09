package com.epam.mjc;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String delimiter : delimiters) {
            stringBuilder.append(delimiter);
            stringBuilder.append("|");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return Arrays.stream(source.split(stringBuilder.toString()))
                .filter(a->!a.isEmpty())
                .collect(Collectors.toList());

    }
}
