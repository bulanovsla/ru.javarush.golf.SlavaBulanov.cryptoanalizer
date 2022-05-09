import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filepathIn;
        String filepathOut;
        int select;
        int key;
        boolean isWorker = true;

        System.out.println("Это криптоанализатор. Пожалуйста, выберите, что Вы хотите сделать:");

        while (isWorker) {
            System.out.println("1. Зашифровать текст");
            System.out.println("2. Расшифровать текст, используя ключ");
            System.out.println("3. Взломать зашифрованный текст методом \"грубой силы\"");
            System.out.println("4. Выйти");

            select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1:
                    System.out.println("Пожалуйста, введите путь к файлу:");
                    filepathIn = scanner.nextLine();
                    System.out.println("Пожалуйста, введите ключ:");
                    key = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Пожалуйста, введите путь, для записи файла:");
                    filepathOut = scanner.nextLine();
                    try (BufferedReader reader = new BufferedReader(new FileReader(filepathIn));
                         BufferedWriter writer = new BufferedWriter(new FileWriter(filepathOut, true))) {

                        while (reader.ready()) {
                            String words = reader.readLine();
                            String encode = EncodingDecoding.encrypt(words, key);
                            writer.write(encode);
                            writer.write("\n");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    System.out.println("Пожалуйста, введите путь к зашифрованному файлу:");
                    filepathIn = scanner.nextLine();
                    System.out.println("Пожалуйста, введите ключ:");
                    key = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Пожалуйста, введите путь, для записи расшифрованного текста:");
                    filepathOut = scanner.nextLine();
                    try (BufferedReader reader = new BufferedReader(new FileReader(filepathIn));
                         BufferedWriter writer = new BufferedWriter(new FileWriter(filepathOut, true))) {

                        while (reader.ready()) {
                            String words = reader.readLine();
                            String decode = EncodingDecoding.decoding(words, key);
                            writer.write(decode);
                            writer.write("\n");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    System.out.println("Пожалуйста, введите путь к зашифрованному файлу:");
                    filepathIn = scanner.nextLine();

                    System.out.println("Пожалуйста, введите путь, для записи расшифрованного файла:");
                    filepathOut = scanner.nextLine();
                    try (BufferedReader reader = new BufferedReader(new FileReader(filepathIn));
                         BufferedWriter writer = new BufferedWriter(new FileWriter(filepathOut))) {
                        byte[] encoded = Files.readAllBytes(Paths.get("C:\\Users\\User\\IdeaProjects\\ru.javarush.cryptoanalizer\\dictionary.txt"));
                        String dictionary = new String(encoded, StandardCharsets.UTF_8);

                        while (reader.ready()) {
                            String words = reader.readLine();
                            String decode = BrutForce.hack(words, dictionary);
                            writer.write(decode);
                            writer.write("\n");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    isWorker = false;
                    break;

            }

        }
        scanner.close();
    }
}
