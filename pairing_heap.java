import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by xiyaoma on 3/29/17.
 */
public class pairing_heap {

    pairing_heap_node root = new pairing_heap_node();
    int size;

    public pairing_heap() {
        size = 0;
        this.root = null;
    }

    /**
     * transform the frequency table into pairing heap
     * @param hash_map
     * @return
     * @throws IOException
     */
    public pairing_heap_node heapsort(HashMap<String,Integer> hash_map) throws IOException {

        Iterator it = hash_map.keySet().iterator();
        while (it.hasNext()) {
            String hashkey = it.next().toString();
            Integer hashvalue = hash_map.get(hashkey);
            try {
                //here need to swap key and value, the value of hashmap is the key of binaryheap, which is also the frequency
                pairing_heap_node new_ele = new pairing_heap_node(hashvalue,hashkey);
                root = this.insert(new_ele);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return root;
    }

    /**
     * insert a new heap into pairing heap
     * @param item
     * @return root
     */
    public pairing_heap_node insert(pairing_heap_node item) {
        if (this.size == 0) {
            root = item;
//            System.out.println(root.key);
        } else {
            root = compare_and_meld(root, item);
        }
        size++;
        return root;
    }

    public pairing_heap_node remove_min() {
        if (this.size <= 0) {
            System.out.println("Error, empty heap");
            return null;
        } else {
            pairing_heap_node rootNode = root;
            int num_child = num_child(root);

            if (num_child == 0) {
                this.root = null;
            } else if (num_child == 1) {
                this.root = root.child;
            } else {
                pairing_heap_node[] nodeSet = new pairing_heap_node[num_child];
                pairing_heap_node firstNode = root.child;
                for(int i = 0; i < num_child; i++) {
                    nodeSet[i] = firstNode;
                    firstNode = firstNode.next;
                }

                if (num_child % 2 == 0) {
                    for (int i = 0; i < num_child/2; i++) {
                        nodeSet[i] = compare_and_meld(nodeSet[2 * i], nodeSet[2 * i + 1]);
                    }
                    for(int i = num_child/2 - 1; i > 0; i--) {
                        nodeSet[i - 1] = compare_and_meld(nodeSet[i], nodeSet[i - 1]);
                    }
                }

                if (num_child % 2 == 1) {
                    for (int i = 0; i < num_child/2; i++) {
                        nodeSet[i] = compare_and_meld(nodeSet[2 * i], nodeSet[2 * i + 1]);
                    }
                    nodeSet[num_child / 2] = nodeSet[num_child - 1];
                    for (int i = num_child /2; i > 0; i--) {
                        nodeSet[i - 1] = compare_and_meld(nodeSet[i], nodeSet[i - 1]);
                    }
                }

                this.root = nodeSet[0];
            }

            size--;
            return rootNode;
        }
    }
    private pairing_heap_node compare_and_meld( pairing_heap_node node1, pairing_heap_node node2 )
    {
        if( node2 == null )
            return node1;

        node1.prev = node1;
        node1.next = node1;
        node2.prev = node2;
        node2.next = node2;

        if (node1.key < node2.key || (node1.key == node2.key && node2.isleaf == false)) {
            if (node1.child == null) {
                node1.child = node2;
                return node1;
            } else {
                node1.child.prev = node2;
                node2.next = node1.child;
                node1.child = node2;
                return node1;
            }
        } else {
            if (node2.child == null) {
                node2.child = node1;
                return node2;
            } else {
                node2.child.prev = node1;
                node1.next = node2.child;
                node2.child = node1;
                return node2;
            }
        }
//        if (node1.key > node2.key) {
//
//            // Attach node1 as leftmost child of node2
//            node2.leftsibling = null;
//            node2.rightsibling = null;
//            node1.leftsibling = node2;
//            node1.rightsibling = node2.child;
//            if (node1.rightsibling != null)
//                node1.rightsibling.leftsibling = node1;
//            node1.rightsibling = node2.child;
//            node2.child = node1;
//            return node2;
//        } else {
//            // Attach second as leftmost child of first
//            node1.leftsibling = null;
//            node1.rightsibling = null;
//            node2.leftsibling = node1;
//            node2.rightsibling = node1.child;
//            if (node2.rightsibling != null)
//                node2.rightsibling.leftsibling = node2;
//            node2.rightsibling = node1.child;
//            node1.child = node2;
//            return node1;
//        }
    }
    public int num_child(pairing_heap_node node) {
        if (node.child == null) {
            return 0;
        } else {
            int num = 1;
            pairing_heap_node next = node.child;
            while (next.hasNext()) {
                num++;
                next = next.next;
            }
            return num;
        }
    }
}
