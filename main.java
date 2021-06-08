import java.util.Hashtable;

import TextIO.TextIO;

public class hashTable {


	public static void main(String[] args) {
		Hashtable table = new Hashtable(2);  // Initial size of table is 2.
		String key,value;
		while (true) {
			System.out.println("\nMenu:");
			System.out.println("   1. test put(key,value)");
			System.out.println("   2. test get(key)");
			System.out.println("   3. test containsKey(key)");
			System.out.println("   4. test remove(key)");
			System.out.println("   5. Exit");
			System.out.print("Enter your command:  ");
			switch ( TextIO.getlnInt()) {
			case 1:
				System.out.print("\n   Key = ");
				key = TextIO.getln();
				System.out.print("   Value = ");
				value = TextIO.getln();
				table.put(key,value);
				break;         
			case 2:
				System.out.print("\n   Key = ");
				key = TextIO.getln();
				System.out.println("   Value is " + table.get(key));
				break;         
			case 3:
				System.out.print("\n   Key = ");
				key = TextIO.getln();
				System.out.println("   containsKey(" + key + ") is " 
						+ table.containsKey(key));
				break;         
			case 4:
				System.out.print("\n   Key = ");
				key = TextIO.getln();
				table.remove(key);
				break;         
			case 5:
				return;  // End program by returning from main()         
			default:
				System.out.println("   Illegal command.");
				break;
			}
			System.out.println("\nHash table size is " + table.size());
		}

	}

	/**
	 * Keys that have the same hash code are placed together in a linked list.  
	 * This private nested class is used internally to implement linked lists.  
	 * A ListNode holds a (key,value) pair.  
	 */
	private static class Node{
		String value;
		String key;
		Node next;
	}
	private Node[] linkedList;
	private int count;  // The number of (key,value) pairs in the
	// hash table
	/**
	 * Create a hash table with an initial size of 50.
	 */
	public hashTable() {
		linkedList = new Node[50];
	}

	/**
	 * Create a hash table with a specified initial size.
	 * Precondition: initalSize > 0.
	 */
	public hashTable(int initialSize) {
		if (initialSize <= 0)
			throw new IllegalArgumentException("Illegal table size");
		linkedList = new Node[initialSize];
	}



	/**
	 *  computes the hash code for a key 
	 */
	public int hashCode(Object key){
		return (Math.abs(key.hashCode())) % linkedList.length; 
	}

	/**
	 * This method is NOT part of the usual interface for a hash table.  
	 * It is here only to be used for testing purposes, and should be 
	 * removed before the class is released for general use.  This 
	 * lists the (key,value) pairs in each location of the table.
	 */


	// Test whether the specified key has an associated value in the table.
	public boolean containsKey(String key){
		int pointer = hashCode(key);
		Node list = linkedList[pointer];  // For traversing the list.
		while (list != null) {
			// If the key in this node, return true.
			if (list.key.equals(key))
				return true;
			list = list.next;
		}
		// the key is not found return false 
		return false;    
	}

	public void putKey(String key, String value)
	{
		int pointer = hashCode(key);
		Node list = linkedList[pointer];  // For traversing the list.
		while (list != null) {
			// If the key in this node, return true.
			if (list.key.equals(key))
				break;
			list = list.next;
		}
		// At this point, either list is null, or list.key.equals(key).

		if (list != null) {
			// Since list is not null, we have found the key.
			// Just change the associated value.
			list.value = value;
		}
		else {
			// Since list == null, the key is not already in the list.
			// Add a new node at the head of the list to contain the
			// new key and its associated value.	      
			Node newNode = new Node();
			newNode.key = key;
			newNode.value = value;
			newNode.next = linkedList[pointer];
			linkedList[pointer] = newNode;
			count++;  // Count the newly added key.
		}   
	}

	/**
	 * Remove the key and its associated value from the table,
	 * if the key occurs in the table.  If it does not occur,
	 * then nothing is done.
	 */

	public void removeKey(String key)
	{
		int pointer = hashCode(key);
		if ( linkedList[pointer] == null ) {
			// The list is empty, so it certainly doesn't contain removeKey.
			return;
		}
		if (linkedList[pointer].key.equals(key)) {
			// If the key is the first node on the list, then
			// linkedList[pointer] must be changed to eliminate the
			// first node from the list.
			linkedList[pointer] = linkedList[pointer].next;
			count--; // Remove new number of items in the table.
			return;
		}
		// Use a pointer to traverse
		// the list, looking for a node that contains the
		// specified key, and remove it if it is found.
		Node previous = linkedList[pointer];   
		Node current = previous.next;   
		while (current != null && ! current.key.equals(key)) {
			current = current.next;
			previous = current;
		}
		// If we get to this point, the item does not exist in the list.

		if (current != null) {
			previous.next = current.next;
			count--;  // Record new number of items in the table.
		}
	}
	public String getKey(String key){
		int pointer = hashCode(key);
		Node list = linkedList[pointer]; 
		while (list != null) {
			// Check if the specified key is in the node that
			// list points to.  If so, return the associated value.
			if (list.key.equals(key))
				return list.value;
			list = list.next;  // Move on to next node in the list.
		}
		return null;

	}
	public int size()
	{
		return count;
	}
}
