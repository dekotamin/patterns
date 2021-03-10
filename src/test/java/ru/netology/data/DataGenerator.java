package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    public DataGenerator() {}

    public static class Registration {
        private Registration() {}

        public static RegistrationByDate generateByDate(String local) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationByDate(
                    LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    faker.phoneNumber().phoneNumber()
            );
        }

        public static String generateValidCity() {
            String[] validCity = {"Москва", "Казань"};
            Random random = new Random();
            int randomIndex = random.nextInt(validCity.length);
            return validCity[randomIndex];
        }

        public static String generateInvalidCity() {
            String[] invalidCity = {"Сочи", "Новороссийск"};
            Random random = new Random();
            int randomIndex = random.nextInt(invalidCity.length);
            return invalidCity[randomIndex];
        }

        public static String generateValidName() {
            String[] validName = {"Воробьева Анна", "Василий Дегин"};
            Random random = new Random();
            int randomIndex = random.nextInt(validName.length);
            return validName[randomIndex];
        }

        public static String generateInvalidName() {
            String[] invalidName = {"Vorobyeva Anna", "Alyona Antonova"};
            Random random = new Random();
            int randomIndex = random.nextInt(invalidName.length);
            return invalidName[randomIndex];
        }

    }

}




