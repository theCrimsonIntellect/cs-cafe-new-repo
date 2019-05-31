public class CustomerNode<Customer> {
    Customer c;
    CustomerNode<Customer> next;

    CustomerNode(Customer cust) {
        c = cust;
    }
}
