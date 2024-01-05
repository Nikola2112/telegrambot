package com.goit.utils;

import java.time.LocalDateTime;

public class RenameFiles {

    private RenameFiles() {
        throw new UnsupportedOperationException();
    }
    public static String renameFile(String fullName, LocalDateTime dateOfVisit) {
        String dateTimeFileFormatter = DateTimeFormatters.localDateTimeFileFormatter(dateOfVisit);
        String str = fullName.replace(" ", "");
        return str + dateTimeFileFormatter +".txt";
    }

}
