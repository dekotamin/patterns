package ru.netology.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor

public class RegistrationByDate {
    private final String dataOfMeeting;
    private final String anotherDataOfMeeting;
    private final String phoneNumber;
}
