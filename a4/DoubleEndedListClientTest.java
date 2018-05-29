public class DoubleEndedListClientTest<T> { 
     
    public static void main(String args[]) { 
        
        // test addFirst method
         DoubleEndedListClient<Integer> n = new DoubleEndedListClient<Integer>();
          
          n.addFirst(5);
          n.addFirst(7);
          n.addFirst(8);
          n.addFirst(25);
          System.out.println(n.removeFirst());
          System.out.println(n.removeFirst());
          System.out.println(n.removeFirst());
          System.out.println(n.removeFirst());
          n.addFirst(5);
          n.addFirst(7);
          n.addFirst(8);
          n.addFirst(25);
          System.out.println(n.removeFirst());
          System.out.println(n.removeFirst());
          System.out.println(n.removeFirst());
          System.out.println(n.removeFirst());
          n.addFirst(5);
          n.addFirst(7);
          n.addFirst(8);
          n.addFirst(25);
          System.out.println(n.removeFirst());
          System.out.println(n.removeFirst());
          System.out.println(n.removeFirst());
          System.out.println(n.removeFirst());
          
          DoubleEndedListClient<Integer> m = new DoubleEndedListClient<Integer>();
          m.addLast(5);
          m.addLast(7);
          m.addLast(8);
          m.addLast(25);
          System.out.println(m.removeLast());
          System.out.println(m.removeLast());
          System.out.println(m.removeLast());
          System.out.println(m.removeLast());
          m.addLast(5);
          m.addLast(7);
          m.addLast(8);
          m.addLast(25);
          System.out.println(m.removeLast());
          System.out.println(m.removeLast());
          System.out.println(m.removeLast());
          System.out.println(m.removeLast()); 
    }
}