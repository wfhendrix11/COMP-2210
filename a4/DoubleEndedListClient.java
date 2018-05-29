import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;


public class DoubleEndedListClient<T> implements DoubleEndedList<T> {   
        
    //////////////////////////
    // FIELDS + CONSTRUCTOR //
    //////////////////////////

    /** References to the first node of the list. */
    Node front;

    /** The number of nodes in the list. */
    int size;

    /**
     * Instantiates an empty LinkedSet.
     */
    public DoubleEndedListClient() {
        front = null; 
        size = 0;
    }

	/**
     * Returns the number of elements in this list.
     */ 
	public int size() { 
		return size;
	}

	/**
     * Returns true if this list contains no elements, false otherwise.
     */
	public boolean isEmpty() { 
		return size == 0;
	}

	 /**
     * Creates and returns an iterator over the elements of this list.
     */
	public Iterator<T> iterator() { 
		return new DoubleEndedIterator();
	}

   
   /**
    * Adds element to the front of the list. If element is null,
    * this method throws an IllegalArgumentException.
    */
    public void addFirst(T element) {
        if (element == null) { 
            throw new IllegalArgumentException();
        }
        
        Node current = new Node(element);
        current.next = front;
        front = current;
        size++; 
    } 
   
   /**
    * Adds element to the end of the list. If element is null,
    * this method throws an IllegalArgumentException.
    */
    public void addLast(T element) {
        Node current = front;
        
        if (element == null) { 
            throw new IllegalArgumentException();
        }
        
        if (isEmpty()) { 
            addFirst(element);
            return;
        }
        else { 
            for (int i = 0; i < size - 1; i++) { 
                current = current.next; 
            } 
            current.next = new Node(element);
            size++;
        }             
    } 
      
   /**
    * Delete and return the element at the front of the list.
    * If the list is empty, this method returns null.
    */
    public T removeFirst() {
        Node current = new Node();
        Node result = new Node();
        
        if (size == 0) { 
            return null; 
        } 
        if (size == 1) {
            current = front; 
            front = null;
            size--;
            return current.element;
        }
        result = front;
        current = front.next;
        front = front.next;
        size--;
        return result.element;
    } 
   
   /**
    * Delete and return the element at the end of the list.
    * If the list is empty, this method returns null.
    */
    public T removeLast() {
        Node current = front; 
        Node result = new Node();
            
        if (size == 0) { 
            return null; 
        }
        
        else if (size == 1) {
            result = front; 
            front = null;
            size--;
        }
        
        else {
            for (int i = 0; i < size - 2; i++) { 
                current = current.next; 
            } 
            result = current.next;
            current.next = null;
            size--;
        }        
        return result.element; 
    }
   
    ///////////////////////
    // NESTED NODE CLASS //
    ///////////////////////

    /**
     * Defines a node class for a linked list.
     */
    private class Node {
        /** the value stored in this node. */
        T element;
        /** a reference to the node after this node. */
        Node next;

        /**
         * Instantiate an empty node.
         */
        public Node() {
            element = null;
            next = null;
        }

        /**
         * Instantiate a node that containts element
         * and with no node before or after it.
         */
        public Node(T e) {
            element = e;
            next = null;
        }
    }
    
    /////////////////////
    // NESTED ITERATOR //
    /////////////////////
    
    /**
     * Defines a iterator class for a linked list.
     */
    private class DoubleEndedIterator implements Iterator<T> {
        public Node current = front;  
       
        /**
         * Returns true if this iterator has at least one more element
         * to deliver in the iteration.
         *
         * @return  true if at least one more element, false otherwise
         *
         */
        public boolean hasNext() {
            return current != null;
        }     
          
        /**
         * Returns the next element in the iteration. If there are no
         * more elements in this iteration, a NoSuchElementException is
         * thrown.
         *
         * @return  the next element in the iteration
         *
         */
        public T next() {
            if (!hasNext()) { 
                throw new NoSuchElementException();
            }
             
            T result = current.element; 
            current = current.next;
            return result;   
        }      
    }
    
}