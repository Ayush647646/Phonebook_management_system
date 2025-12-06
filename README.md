# 📒 Phonebook (Java Console Application)

A simple **Console-Based Phonebook Application** built in **Java** using a **Doubly Linked List**.  
It supports adding, editing, deleting, searching, displaying contacts, and saving them persistently in a text file.

---

## 🚀 Features

### 📌 Core Features
- Add a new contact (name + phone number)
- Display all contacts (sorted alphabetically by name)
- Search contact by **name** or **number**
- Edit an existing contact
- Delete a contact by search
- Delete all contacts
- Contacts automatically **save to a file** (`contactbook.txt`)
- Contacts load automatically on startup

### 🧠 Internal Implementation
- Uses a **Doubly Linked List** (`ContactNode`) to store contacts
- Uses **Bubble Sort** to sort contacts by name
- Uses safe input handlers (`readInt()`, `readLong()`) to avoid crashes
- Uses file handling (`PrintWriter`, `BufferedReader`) for storage

---

## 📂 Project Structure

ContactBook/
│
├── ContactNode.java // Node structure for doubly linked list
├── ContactBook.java // Main logic: add, edit, delete, sort, search, file I/O
├── Main.java // Entry point (runs the menu)
└── contactbook.txt // Auto-created storage file for contacts

yaml
Copy code

---

## 🖥️ How to Run the Project

### **1. Clone the repo**
```sh
git clone [https://github.com/your-username/contact-book.git](https://github.com/Ayush647646/Phonebook_management_system)
cd contact-book
2. Compile the Java files
sh
Copy code
javac Main.java
3. Run the program
sh
Copy code
java Main
📘 Usage
When running the program, you will see a menu:

markdown
Copy code
********************
1. Add Contact
2. Edit Contact
3. Delete Contact
4. Search Contact
5. Display All Contacts
6. Delete All Contacts
7. Exit
********************
Enter the command:
Just enter the number corresponding to the action you want to perform.

💾 File Storage Format
Contacts are stored in contactbook.txt in this format:

pgsql
Copy code
Name|PhoneNumber
John Doe|9876543210
Alice|9123456780
Pipes (|) are escaped automatically in case a name contains the symbol.

🧹 Sorting
The contact list is sorted alphabetically using Bubble Sort every time you display contacts.

📎 Key Classes and Responsibilities
Class	Responsibility
ContactNode	Represents each contact (name, number, next, prev)
ContactBook	Entire application logic + file I/O + menu
Main	Starts the program and loads menu

🛠️ Technologies Used
Java (Core)

File Handling

Doubly Linked List

Console-Based UI

# Notes & improvements you can make:
- Switch to JSON storage (Gson / Jackson) for more robust storage.
- Add validation for phone number length/format.
- Make searches show multiple matches instead of stopping at first.
- Convert to a GUI (Swing/JavaFX or Android) if desired.

📜 License
This project is free to use and modify for learning or personal use.

❤️ Contributing
Feel free to fork the repo and submit pull requests with improvements!

👨‍💻 Author
Ayushman Gupta

Enjoy coding! 🚀
