import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;


public class RandomizedListClient<T> implements RandomizedList<T> {
    
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
    public RandomizedListClient() {
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
		return new RandomizedIterator();
	}

	/**
     * Adds the specified element to this list. If the element is null, this
     * method throws an IllegalArgumentException.
     */
   	public void add(T element) {
        if (element == null) { 
            throw new IllegalArgumentException();
        }
        Node current = new Node(element);
        current.next = front; 
        front = current;
        size++;   
   	}
      
    /**
     * Selects and removes an element selected uniformly at random from the
     * elements currently in the list. If the list is empty this method returns
     * null.
     */
    public T remove() {
        Random rn = new Random();
        
        Node element = front;
        Node current = null;
        
        if (isEmpty()) { 
            return null;
        }
        
        int j = rn.nextInt(size);
        for (int i = 0; i < j; i++) {
            current = element;
            element = element.next; 
        }
        
        // if only one element
        if (size == 1) {
            element = front; 
            front = null;
            size--;
            return element.element;
        }
        
        // if removing last element
        else if (element.next == null) { 
            current.next = null;
            size--;
            return element.element;
        }
        
        // if removing first element
        else if (current == null) { 
            front = element.next;
            size--;
            return element.element;
        }
        
        // if removing any other element
        else {
            current.next = element.next;
        } 
        size--;
        return element.element;     
   	}
    
    
    /**
     * Selects but does not remove an element selected uniformly at random from
     * the elements currently in the list. If the list is empty this method
     * return null.
     */
  	public T sample() {
        Random rn = new Random();
        
        Node element = front; 
        
        if (isEmpty()) { 
            return null;
        }
        
        int j = rn.nextInt(size);
        for (int i = 0; i < j; i++) {
            element = element.next; 
        }
        
        // if sampling last element
        if (element.next == null) { 
            return element.element;
        }
              
    	return element.element;
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
    
    /**
     * Defines a iterator class for a linked list.
     */
    private class RandomizedIterator implements Iterator<T> {
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