public class CustomerLinkedList<Customer> {
    int size;
    CustomerNode<Customer> head, tail;

    CustomerLinkedList(){
        size = 0;
        head = null;
        tail = null;
    }
    public int addFirst(Customer c){
        CustomerNode<Customer> newNode = new CustomerNode<Customer>(c);
        newNode.next = head;
        head = newNode;
        size++;
        if (tail == null){
            tail = head;
        }
        return size -1;
    }

    public int addLast (Customer c){
        CustomerNode<Customer> newNode = new CustomerNode<Customer>(c);
        if (tail == null){
            head = tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = tail.next;
        }
        size++;
        return size-1;
    }

    public int add (int index, Customer c) {
        if (index == 0) addFirst(c);
        else if (index >= size) addLast(c);
        else {
            CustomerNode<Customer> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            CustomerNode<Customer> temp = current.next;
            current.next = new CustomerNode<Customer>(c);
            (current.next).next = temp;
            size++;
        }
        return size-1;

    }

    public void removeFirst(){
        if (size != 0) {
            CustomerNode<Customer> temp = head;
            head = head.next;
            size--;
            if (head == null) tail = null;
        }
    }

    public void removeLast(){
        if (size > 1) {
            CustomerNode<Customer> current = head;
            for (int i = 0; i < size -2; i++){
                current = current.next;
            }
            CustomerNode <Customer> temp = tail;
            tail = current;
            tail.next = null;
            size--;
        }
        else if (size == 1){
            CustomerNode<Customer> temp = head;
            head = tail = null;
            size = 0;
        }
    }


    public void remove(int index) {
        if (index == 0) removeFirst();
        else if (index == size-1) removeLast();
        else {
           CustomerNode<Customer> previous = head;
           for (int i = 1; i < index; i++){
               previous = previous.next;
           }
           CustomerNode<Customer> current = previous.next;
           previous.next = current.next;
           size--;
        }
    }

    public Customer getCustomer (int index){
        Customer returnCustomer;
        CustomerNode<Customer> pointer = head;
        for (int i = 0; i < index; i++){
            pointer = pointer.next;
        }
        returnCustomer = pointer.c;
        return returnCustomer;
    }


    public int getSize(){
        return size;
    }






}
