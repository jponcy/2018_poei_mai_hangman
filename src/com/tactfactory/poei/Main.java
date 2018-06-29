
package com.tactfactory.poei;

import java.util.Random;
import java.util.Scanner;

import com.tactfactory.poei.database.DatabaseManager;
import com.tactfactory.poei.database.WordRepository;

public class Main {

    public static void main(String[] args) {
        DatabaseManager manager = DatabaseManager.instance();
        WordRepository repository = manager.getRepository(WordRepository.class);

        int index = new Random().nextInt(repository.count());
        String word = repository.findByIndex(index);
        String input = null;

        System.out.println("Trouvera tu le mot " + word + " ?");

        try (Scanner scanner = new Scanner(System.in)) {
            input = scanner.nextLine();
        }

        if (word.equals(input)) {
            System.out.println("T'es vraiment super fort !!!");
        } else {
            System.out.println("C'était dur aussi ...");
        }
    }
}
