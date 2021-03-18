package lessons.three.phonebook;

import java.util.*;

public class PhoneBook {

    private final Set<PhoneBookRecord> phoneBookData = new HashSet<>();

    public void add(String surname, String phone) {
        phoneBookData.add(new PhoneBookRecord(surname, phone));
    }

    public Set<String> get(String surname) {
        Set<String> result = new HashSet<>();
        for (PhoneBookRecord record: phoneBookData) {
            if (record.getSurname().equals(surname)){
                result.add(record.getPhone());
            }
        }
        return result;
    }
}
