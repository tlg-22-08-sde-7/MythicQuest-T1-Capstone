import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class InitialDisplay {
    public static void main(String[] args) {

        displayTitle();
    }

    public static void displayTitle() {
        // surrounded with try / catch in case file cannot be fount or read.
        try {
            String welcomeTo = Files.readString(Path.of("resources/Banners/welcomeTo.txt"));
            String welcomeBanner = Files.readString(Path.of("resources/Banners/welcomeBanner.txt"));
            String instructions = Files.readString(Path.of("resources/Banners/instructions.txt"));
            //System.out.flush();

            System.out.println(welcomeTo);
            Thread.sleep(1500);
            System.out.println(welcomeBanner);

            System.out.println(instructions);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}