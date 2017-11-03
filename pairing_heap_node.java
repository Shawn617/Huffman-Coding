/**
 * Created by xiyaoma on 3/29/17.
 */
public class pairing_heap_node {
    int key;
    String value;
    pairing_heap_node child;
    pairing_heap_node prev;
    pairing_heap_node next;
    pairing_heap_node leftchild = null;
    pairing_heap_node rightchild = null;
    boolean isleaf;

    public pairing_heap_node(int key, String value, pairing_heap_node child, pairing_heap_node prev, pairing_heap_node next){

        this.key = key;
        this.value = value;
        this.child = child;
        this.prev = prev;
        this.next = next;
        this.child.prev =this;
        this.next.prev = this;
        this.isleaf = true;
    }

    public pairing_heap_node(int key, String value){
        this.key = key;
        this.value = value;
        this.child = null;
        this.prev = null;
        this.next = null;
        this.isleaf = true;
    }

    public pairing_heap_node(){
        this.isleaf =true;

    }
    public boolean hasNext() {
        if (!this.next.equals(this)) {
            return true;
        } else {
            return false;
        }
    }
}
