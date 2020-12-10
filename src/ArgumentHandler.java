import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ArgumentHandler {

    public void handleArgument(String[] arguments) {
        if (arguments.length == 0) {
            System.out.println(getInstructions());
        } else {
            switch (arguments[0]) {
                case "-l": {
                    getTasks();
                    break;
                }
                case "-a": {
                    addTasks(arguments);
                    break;
                }
                case "-r 2": {
                    removeTasks(arguments);
                    break;
                }
                default: {
                    System.out.println("invalid arguments");
                    break;
                }
            }
        }
    }

    private static void removeTasks(String indexToRemove) {
        int indexToRemoveInt = Integer.parseInt(indexToRemove);
        List<String> tasks = new ArrayList<>();
        try {
            Path filePath = Paths.get("/Users/ntttomi/greenfox/tttnguyen9472_handle-todo-app/data/tasks.txt");
            tasks = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println("File is not available.");
        }
        tasks.remove(indexToRemoveInt - 1);
        try {
            Path filePath = Paths.get("/Users/ntttomi/greenfox/tttnguyen9472_handle-todo-app/data/tasks.txt");
            Files.write(filePath, tasks, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private static void addTasks(String[] arguments) {
        if (arguments.length == 1) {
            System.out.println("Unable to add: no task provided");
        }
        List<String> tasks = new ArrayList<>();
        try {
            for (int i = 0; i < arguments.length - 1; i++) {
                tasks.add(arguments[i + 1]);
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Unable to add: no task provided");
        }
        try {
            Path filePath = Paths.get("/Users/ntttomi/greenfox/tttnguyen9472_handle-todo-app/data/tasks.txt");
            tasks = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println("File is not available");
        }
    }

    private String getInstructions() {
        Path instructionPath = Paths.get("/Users/ntttomi/greenfox/tttnguyen9472_handle-todo-app/data/instructions");
        List<String> content = new ArrayList<>();
        try {
            content = Files.readAllLines(instructionPath);
        } catch (IOException e) {
            System.out.println("Sry, instructions not available, have fun experimenting");
        }
        StringBuilder contentAsString = new StringBuilder();
        for (String line : content) {
            contentAsString.append(line);
            contentAsString.append("\n");
        }
        return contentAsString.toString();

    }

    private static void getTasks() {
        Path instructionPath = Paths.get("/Users/ntttomi/greenfox/tttnguyen9472_handle-todo-app/data/tasks.txt");
        List<String> contentOfThings = new ArrayList<>();
        if (contentOfThings.size() == 0) {
            System.out.println("No todos for today! :)");
        }
        try {
            contentOfThings = Files.readAllLines(instructionPath);
        } catch (IOException e) {
            System.out.println("Sorry, the tasks are not available.");
        }
        for (int i = 0; i < contentOfThings.size(); i++) {
            System.out.println((i + 1) + " - " + contentOfThings.get(i));
        }
    }
}
