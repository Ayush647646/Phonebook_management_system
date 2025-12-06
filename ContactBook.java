import java.io.*;
import java.util.*;

public class ContactBook {
    private ContactNode head;
    private final Scanner scanner;
    private final File storageFile = new File("contactbook.txt");

    public ContactBook(Scanner scanner) {
        this.head = null;
        this.scanner = scanner;
        loadFromFile();
    }

    // Add a contact at the end
    public void addContact() {
        System.out.print("Name of contact: ");
        String name = scanner.nextLine().trim();
        System.out.print("Phone number: ");
        long number = readLong();

        ContactNode newer = new ContactNode(name, number);
        if (head == null) {
            head = newer;
        } else {
            ContactNode temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newer;
            newer.prev = temp;
        }
        System.out.println("Contact added\n");
        saveToFile();
    }

    // Display contacts (sorted)
    public void display() {
        if (head == null) {
            System.out.println("No Contacts... Add some contacts\n");
            return;
        }
        bubbleSort();
        System.out.println("  Name          Number:\n");
        ContactNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            System.out.printf(" %-15s %d\n", temp.name, temp.phoneNumber);
            temp = temp.next;
        }
        System.out.println("\nTotal contacts: " + count + "\n");
    }

    // Search by name or number
    public void search() {
        if (head == null) {
            System.out.println("No contacts to search.\n");
            return;
        }
        System.out.println("********************");
        System.out.println("Press 1 to search by name.");
        System.out.println("Press 2 to search by number.");
        System.out.print("Enter the command: ");
        int command = readInt();

        if (command == 1) {
            System.out.print("Enter the name to search: ");
            String name = scanner.nextLine().trim();
            ContactNode temp = head;
            boolean found = false;
            while (temp != null) {
                if (temp.name.equalsIgnoreCase(name)) {
                    System.out.println("********************");
                    System.out.println("Name : " + temp.name);
                    System.out.println("Phone Number: " + temp.phoneNumber);
                    System.out.println("********************\n");
                    found = true;
                    break;
                }
                temp = temp.next;
            }
            if (!found) System.out.println("Name not found.\n");
        } else if (command == 2) {
            System.out.print("Enter the number to search: ");
            long number = readLong();
            ContactNode temp = head;
            boolean found = false;
            while (temp != null) {
                if (temp.phoneNumber == number) {
                    System.out.println("********************");
                    System.out.println("Name : " + temp.name);
                    System.out.println("Phone Number: " + temp.phoneNumber);
                    System.out.println("********************\n");
                    found = true;
                    break;
                }
                temp = temp.next;
            }
            if (!found) System.out.println("Number not found.\n");
        } else {
            System.out.println("Invalid command.\n");
        }
    }

    // Delete all contacts
    public void deleteAllContacts() {
        if (head == null) {
            System.out.println("Contact book is already empty.\n");
            return;
        }
        head = null;
        saveToFile();
        System.out.println("Successfully deleted all contacts.\n");
    }

    // Delete by name/number
    public void deleteContactBySearch() {
        if (head == null) {
            System.out.println("No contacts to delete.\n");
            return;
        }
        System.out.println("*************");
        System.out.println("Press 1 to delete by name");
        System.out.println("Press 2 to delete by number");
        System.out.print("Enter the command: ");
        int command = readInt();

        if (command == 1) {
            System.out.print("Enter the name to delete: ");
            String name = scanner.nextLine().trim();
            ContactNode temp = head;
            boolean found = false;
            while (temp != null) {
                if (temp.name.equalsIgnoreCase(name)) {
                    printContact(temp);
                    found = true;
                    break;
                }
                temp = temp.next;
            }
            if (!found) {
                System.out.println("Contact with this name not found.\n");
                return;
            }
            System.out.print("Press 1 to confirm delete: ");
            int confirm = readInt();
            if (confirm == 1) {
                deleteNode(temp);
                saveToFile();
                System.out.println("Contact deleted successfully.\n");
            } else {
                System.out.println("Delete cancelled.\n");
            }

        } else if (command == 2) {
            System.out.print("Enter the number to delete: ");
            long number = readLong();
            ContactNode temp = head;
            boolean found = false;
            while (temp != null) {
                if (temp.phoneNumber == number) {
                    printContact(temp);
                    found = true;
                    break;
                }
                temp = temp.next;
            }
            if (!found) {
                System.out.println("Contact with this number not found.\n");
                return;
            }
            System.out.print("Press 1 to confirm delete: ");
            int confirm = readInt();
            if (confirm == 1) {
                deleteNode(temp);
                saveToFile();
                System.out.println("Contact deleted successfully.\n");
            } else {
                System.out.println("Delete cancelled.\n");
            }
        } else {
            System.out.println("Invalid command.\n");
        }
    }

    // Edit contact
    public void editContact() {
        if (head == null) {
            System.out.println("No contacts to edit.\n");
            return;
        }
        System.out.println("********************");
        System.out.println("Press 1 to search by name");
        System.out.println("Press 2 to search by number");
        System.out.print("Enter the command: ");
        int command = readInt();

        ContactNode temp = head;
        boolean found = false;
        if (command == 1) {
            System.out.print("Enter the name to edit: ");
            String name = scanner.nextLine().trim();
            while (temp != null) {
                if (temp.name.equalsIgnoreCase(name)) {
                    printContact(temp);
                    found = true;
                    break;
                }
                temp = temp.next;
            }
        } else if (command == 2) {
            System.out.print("Enter the number to edit: ");
            long number = readLong();
            while (temp != null) {
                if (temp.phoneNumber == number) {
                    printContact(temp);
                    found = true;
                    break;
                }
                temp = temp.next;
            }
        } else {
            System.out.println("Invalid command.\n");
            return;
        }

        if (!found) {
            System.out.println("Contact not found.\n");
            return;
        }

        System.out.print("Press 1 to edit this contact: ");
        int confirm = readInt();
        if (confirm != 1) {
            System.out.println("Edit cancelled.\n");
            return;
        }

        System.out.print("Enter new name: ");
        String newName = scanner.nextLine().trim();
        System.out.print("Enter new number: ");
        long newNumber = readLong();

        temp.name = newName;
        temp.phoneNumber = newNumber;
        saveToFile();
        System.out.println("Contact edited successfully.\n");
    }

    // Utilities
    private void printContact(ContactNode node) {
        System.out.println("********************");
        System.out.println("Name : " + node.name);
        System.out.println("Phone Number: " + node.phoneNumber);
        System.out.println("********************\n");
    }

    private void deleteNode(ContactNode node) {
        if (node == head) {
            head = node.next;
            if (head != null) head.prev = null;
            return;
        }
        if (node.next == null) {
            node.prev.next = null;
            return;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Bubble sort by name
    private void bubbleSort() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            ContactNode cur = head;
            while (cur.next != null) {
                if (cur.name.compareToIgnoreCase(cur.next.name) > 0) {
                    // swap data
                    String tname = cur.name;
                    long tnum = cur.phoneNumber;
                    cur.name = cur.next.name;
                    cur.phoneNumber = cur.next.phoneNumber;
                    cur.next.name = tname;
                    cur.next.phoneNumber = tnum;
                    swapped = true;
                }
                cur = cur.next;
            }
        } while (swapped);
    }

    // File IO: save as "name|number" per line
    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(storageFile))) {
            ContactNode temp = head;
            while (temp != null) {
                pw.println(escapePipe(temp.name) + "|" + temp.phoneNumber);
                temp = temp.next;
            }
        } catch (IOException e) {
            System.out.println("Failed to save contacts: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        if (!storageFile.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(storageFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|", 2);
                String name = unescapePipe(parts[0]);
                long number = 0L;
                if (parts.length > 1) {
                    try {
                        number = Long.parseLong(parts[1].trim());
                    } catch (NumberFormatException ex) {
                        number = 0L;
                    }
                }
                ContactNode newer = new ContactNode(name, number);
                if (head == null) head = newer;
                else {
                    ContactNode temp = head;
                    while (temp.next != null) temp = temp.next;
                    temp.next = newer;
                    newer.prev = temp;
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to load contacts: " + e.getMessage());
        }
    }

    private String escapePipe(String s) {
        return s.replace("|", "\\|");
    }

    private String unescapePipe(String s) {
        return s.replace("\\|", "|");
    }

    // Helper reading integer and long safely
    private int readInt() {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid integer: ");
            }
        }
    }

    private long readLong() {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Long.parseLong(line);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    // Menu
    public void menu(String userName) {
        System.out.println("********************");
        System.out.println(" " + userName + " Welcome To ContactBook");
        System.out.println("********************\n");

        while (true) {
            System.out.println("********************");
            System.out.println("1. Add Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Search Contact");
            System.out.println("5. Display All Contacts");
            System.out.println("6. Delete All Contacts");
            System.out.println("7. Exit");
            System.out.println("********************");
            System.out.print("Enter the command: ");
            int cmd = readInt();
            switch (cmd) {
                case 1 -> addContact();
                case 2 -> editContact();
                case 3 -> deleteContactBySearch();
                case 4 -> search();
                case 5 -> display();
                case 6 -> deleteAllContacts();
                case 7 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("You entered wrong command... Try again\n");
            }
        }
    }
}