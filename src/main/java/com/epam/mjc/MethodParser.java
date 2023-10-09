package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        StringBuilder stringBuilder = new StringBuilder(signatureString);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String[] halves = stringBuilder.toString().split("\\(");

        List<MethodSignature.Argument> argumentsList = new ArrayList<>();
        String argumentHalf;
        if (halves.length > 1) {
            argumentHalf = halves[1];
            String[] arguments = argumentHalf.split(", ");

            for (String argument : arguments) {
                String[] argumentParts = argument.split(" ");
                argumentsList.add(new MethodSignature.Argument(argumentParts[0], argumentParts[1]));
            }
        }

        MethodSignature methodSignature = new MethodSignature("", argumentsList);

        String[] firstHalf = halves[0].split(" ");
        int returnTypeIndex = 0;
        int methodNameIndex = 1;
        if (firstHalf.length == 3) {
            methodSignature.setAccessModifier(firstHalf[0]);
            returnTypeIndex = 1;
            methodNameIndex = 2;
        }
        methodSignature.setMethodName(firstHalf[methodNameIndex]);
        methodSignature.setReturnType(firstHalf[returnTypeIndex]);

        return methodSignature;
    }
}
