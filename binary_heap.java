/**
 * Created by xiyaoma on 3/28/17.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class binary_heap {

    /**
     * Constructs a new BinaryHeap.
     */
    public ArrayList<arraylist_node> binaryheap;
    public binary_heap () {

        binaryheap = new ArrayList<>();
    }

    /**
     * transform the frequency table into ordered arraylist via min binary heap
     * @param hash_map
     * @throws IOException
     */
    public void heapsort(HashMap<String,Integer> hash_map) throws IOException{

        Iterator it = hash_map.keySet().iterator();
        while (it.hasNext()) {
            String hashkey = it.next().toString();
            Integer hashvalue = hash_map.get(hashkey);
            try {
                //here need to swap key and value, the value of hashmap is the key of binaryheap, which is also the frequency
                arraylist_node new_ele = new arraylist_node(hashvalue,hashkey);
                this.insert(new_ele);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
//        System.out.println("after heap sort");
//        int i = 0;
//        while (i<=(binaryheap.size()-1)) {
//            System.out.println("Index: "+ i +"  Key: "+ this.binaryheap.get(i).key +"  Value:" + this.binaryheap.get(i).value);
//            i++;
//        }
    }

    /**
     * Insert a node to the min heap.
     * @param item
     */
    public void insert(arraylist_node item) {
        binaryheap.add(item);
        bubbleUp();
    }

    /**
     * Delete the min element from min heap
     * @return the min tree
     */
    public  arraylist_node remove_min() {
        arraylist_node min_ele = binaryheap.get(0); // read min element
//        System.out.println("old min key: "+min_ele.key+"  value: "+min_ele.value + "  size: "+ binaryheap.size());

        if (binaryheap.size() > 0) {
            binaryheap.set(0, binaryheap.get(binaryheap.size() - 1)); //replace with last element
//            System.out.println("new min key: "+ binaryheap.get(0).key+"  value: "+ binaryheap.get(0).value+"  size: "+ binaryheap.size());
            binaryheap.remove(binaryheap.size() -1);  //remove last element

            bubbledown();
//            int i = 0;
//            while (i<=(binaryheap.size()-1)) {
//                System.out.println("Index: "+ i +"  Key: "+ this.binaryheap.get(i).key +"  Value:" + this.binaryheap.get(i).value);
//                i++;
//            }
        }
        return min_ele;
    }

    /**
     * bubble up the last element
     */
    private void bubbleUp(){

        int index = binaryheap.size()-1; //calculate index, since index starts from 0, not 1
        while (this.hasParent(index)) {
            int p = (int) Math.floor((double) index / 2 - 0.5);
            arraylist_node parent_node = binaryheap.get(p);
            arraylist_node up_node = binaryheap.get(index);
            if (parent_node.key > up_node.key) {
                //swap
                swap(index,p);
//                binaryheap.set(index, parent_node);
//                binaryheap.set(p, up_node);
                //update index of current node
                index = p;
            } else {
                break;
            }
        }
    }

    /**
     * bubble down the root element
     * @throws NullPointerException
     */
    private void bubbledown() throws NullPointerException{
        int index = 0;

        // bubble down
        while (hasLeftChild(index)) {
            // which of my children is smaller?
            arraylist_node p = binaryheap.get(index);
//            arraylist_node lc = binaryheap.get(this.leftIndex(index));
            int smallerchild_index = this.leftIndex(index);  //default is leftchild
            arraylist_node smallerchild = binaryheap.get(smallerchild_index);

            if (this.hasRightChild(index)) {
                arraylist_node rc = binaryheap.get(this.rightIndex(index));
                if (rc.key < smallerchild.key) {
                    smallerchild_index = this.rightIndex(index);
                }
                if (rc.key == smallerchild.key) {
                    if (rc.isleaf == true) {
                        smallerchild_index = this.rightIndex(index);
                    }
                }
                smallerchild = binaryheap.get(smallerchild_index);
            }

            // bubble with the smaller child, if I have a smaller child
            if (p.key > smallerchild.key) {
                this.swap(index, smallerchild_index);
                index = smallerchild_index; //update index to smaller child index
                continue;
            }
            if (p.key == smallerchild.key) {
                if (smallerchild.isleaf ==true) {
                    this.swap(index, smallerchild_index);
                    index = smallerchild_index; //update index to smaller child index
                    continue;
                }
            }
            break;
        }
    }

    /**
     * swap two node in binary heap
     * @param index1
     * @param index2
     */
    protected void swap(int index1, int index2){
        arraylist_node node1 = binaryheap.get(index1);
        arraylist_node node2 = binaryheap.get(index2);
        //swap
        binaryheap.set(index1,node2);
        binaryheap.set(index2,node1);
    }

    /**
     * calculate the index of the leftchild of node with index i
     * @param i
     * @return leftchild index
     */
    protected int leftIndex(int i) {
        return i * 2 + 1;
    }

    /**
     * calculate the index of the rightchild of node with index i
     * @param i
     * @return rightchild index
     */
    protected int rightIndex(int i) {
        return i * 2 + 2;
    }

    /**
     * judge if there is a leftchild of the node with index i
     * @param i
     * @return ture: has leftchild
     *         false: do not have leftchild
     */
    protected boolean hasLeftChild(int i) {
        return leftIndex(i) <= binaryheap.size()-1;
    }

    /**
     * judge if there is a rightchild of the node with index i
     * @param i
     * @return ture: has rightchild
     *         false: do not have rightchild
     */
    protected boolean hasRightChild(int i) {
        return rightIndex(i) <= binaryheap.size()-1;
    }

    /**
     * judge if there is a parent of the node with index i
     * @param i
     * @return ture: has parent
     *         false: do not have parent
     */
    protected boolean hasParent(int i) {
        return i > 0;
    }




}