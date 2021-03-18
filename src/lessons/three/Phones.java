package lessons.three;

import lessons.three.phonebook.PhoneBook;

import java.util.Set;

/**
 * Task 2
 */
public class Phones {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        //Equals records
        phoneBook.add("Petrovich", "+79236546747");
        phoneBook.add("Petrovich", "+79236546747");

        phoneBook.add("Petrovich", "+79236877213");
        phoneBook.add("Ivanov", "+7923236747");
        phoneBook.add("Sidorov", "+7923777777");


        Set<String> findResult = phoneBook.get("Petrovich");
        System.out.println("Phones of Petrovich:");
        System.out.println(findResult);
        System.out.println();

        findResult = phoneBook.get("Ivanov");
        System.out.println("Phones of Ivanov:");
        System.out.println(findResult);
        System.out.println();

        findResult = phoneBook.get("Sidorov");
        System.out.println("Phones of Sidorov:");
        System.out.println(findResult);
        System.out.println();
    }
}
