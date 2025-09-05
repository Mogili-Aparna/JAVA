import java.util.HashMap;
import java.util.Scanner;

public class TaskManagement {

    public static void main(String[] args) {
        HashMap<String, Task> taskBook = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTask Management Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. View Task by Name");
            System.out.println("3. Update Task Status");
            System.out.println("4. List All Tasks");
            System.out.println("5. Delete Task");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter task name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter priority (1=Low, 2=Medium, 3=High): ");
                    int priority;
                    try {
                        priority = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        priority = 1; // default priority
                    }

                    System.out.print("Enter status: ");
                    String status = scanner.nextLine();

                    Task newTask = new Task(name, description, priority, status);
                    taskBook.put(name, newTask);
                    System.out.println("Task added successfully.");
                    break;

                case "2":
                    System.out.print("Enter task name to view: ");
                    String viewName = scanner.nextLine();
                    Task taskToView = taskBook.get(viewName);
                    if (taskToView != null) {
                        System.out.println("Name: " + taskToView.getName());
                        System.out.println("Description: " + taskToView.getDescription());
                        System.out.println("Priority: " + taskToView.getPriority());
                        System.out.println("Status: " + taskToView.getStatus());
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;

                case "3":
                    System.out.print("Enter task name to update status: ");
                    String updateName = scanner.nextLine();
                    Task taskToUpdate = taskBook.get(updateName);
                    if (taskToUpdate != null) {
                        System.out.print("Enter new status: ");
                        String newStatus = scanner.nextLine();
                        taskToUpdate.setStatus(newStatus);
                        System.out.println("Status updated successfully.");
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;

                case "4":
                    if (taskBook.isEmpty()) {
                        System.out.println("No tasks to display.");
                    } else {
                        System.out.println("All Tasks:");
                        for (Task t : taskBook.values()) {
                            System.out.println("----------------------");
                            System.out.println("Name: " + t.getName());
                            System.out.println("Description: " + t.getDescription());
                            System.out.println("Priority: " + t.getPriority());
                            System.out.println("Status: " + t.getStatus());
                        }
                    }
                    break;

                case "5":
                    System.out.print("Enter task name to delete: ");
                    String deleteName = scanner.nextLine();
                    if (taskBook.containsKey(deleteName)) {
                        taskBook.remove(deleteName);
                        System.out.println("Task deleted successfully.");
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;

                case "6":
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}