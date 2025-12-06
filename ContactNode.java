public class ContactNode {
    String name;
    long phoneNumber;
    ContactNode next;
    ContactNode prev;

    public ContactNode(String name, long phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.next = null;
        this.prev = null;
    }
}