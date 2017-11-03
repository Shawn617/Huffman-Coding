import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by xiyaoma on 3/29/17.
 */
public class four_way_heap {
    /**
     * Constructs a new min_4_way_heap.
     */
    public ArrayList<arraylist_node> fourwayheap;

    public four_way_heap () {

        fourwayheap = new ArrayList<>();
        fourwayheap.add(new arraylist_node(-1, null));
        fourwayheap.add(new arraylist_node(-1, null));
        fourwayheap.add(new arraylist_node(-1, null));
    }

    /**
     * transform the frequency table into ordered arraylist via min 4-way heap
     * @param hash_map
     * @throws IOException
     */
    public void heapsort(HashMap<String,Integer> hash_map) throws IOException {

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
    }

    /**
     * Insert a node to the min_4_way_heap.
     * @param item
     */
    public void insert(arraylist_node item) {
        fourwayheap.add(item);
        bubbleUp();
    }

    /**
     * Delete the min element from min heap
     * @return  the min element
     */
    public  arraylist_node remove_min() {
        arraylist_node min_ele = fourwayheap.get(3); // read min element
        if (fourwayheap.size() > 3) {

            fourwayheap.set(3, fourwayheap.get(fourwayheap.size() - 1)); //replace with last element
            fourwayheap.remove(fourwayheap.size() -1);  //remove last element
            bubbledown();
        }
        return min_ele;
    }

    /**
     * bubble down the root element
     * @throws NullPointerException
     */
    private void bubbledown() throws NullPointerException{
        int index = 3;

        // bubble down
        while (haschild1(index)) {
            // which of my children is smaller?
            arraylist_node p = fourwayheap.get(index);
//            arraylist_node c1 = fourwayheap.get(this.child1Index(index));
            int smallerchild_index = this.child1Index(index);  //default is leftchild
            arraylist_node smallerchild = fourwayheap.get(smallerchild_index);

            if (this.haschild2(index)) { //compare with child2
                arraylist_node c2 = fourwayheap.get(this.child2Index(index));
                if (c2.key < smallerchild.key) {
                    smallerchild_index = this.child2Index(index);
                }
                if (c2.key == smallerchild.key) {
                    if (c2.isleaf == true) {
                        smallerchild_index = this.child2Index(index);
                    }
                }
                smallerchild = fourwayheap.get(smallerchild_index);
                if (this.haschild3(index)) {
                    arraylist_node c3 = fourwayheap.get(this.child3Index(index));
                    if (c3.key < smallerchild.key) {
                        smallerchild_index = this.child3Index(index);
                    }
                    if (c3.key == smallerchild.key) {
                        if (c3.isleaf ==true) {
                            smallerchild_index = this.child3Index(index);
                        }
                    }
                    smallerchild = fourwayheap.get(smallerchild_index);
                    if (this.haschild4(index)) {
                        arraylist_node c4 = fourwayheap.get(this.child4Index(index));
                        if (c4.key < smallerchild.key) {
                            smallerchild_index = this.child4Index(index);
                        }
                        if (c4.key == smallerchild.key) {
                            if (c4.isleaf ==true) {
                                smallerchild_index = this.child4Index(index);
                            }
                        }
                        smallerchild = fourwayheap.get(smallerchild_index);
                    }
                }
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
     * bubble up the last element
     */
    private void bubbleUp(){

        int index = fourwayheap.size()-1; //calculate index, since index starts from 3, not 0, since cache optimized
        while (this.hasParent(index)) {
            int p = (int) Math.floor((double) index / 4 + 2);
            arraylist_node parent_node = fourwayheap.get(p);
            arraylist_node up_node = fourwayheap.get(index);
            if (parent_node.key > up_node.key) {
                //swap
                swap(index,p);
                //update index of current node
                index = p;
            } else {
                break;
            }
        }
    }
    /**
     * swap two node in binary heap
     * @param index1
     * @param index2
     */
    protected void swap(int index1, int index2){
        arraylist_node tmp = fourwayheap.get(index1);
        fourwayheap.set(index1,fourwayheap.get(index2));
        fourwayheap.set(index2,tmp);
    }

    /**
     * calculate the index of the firstchild of node with index i
     * @param i
     * @return firstchild index
     */
    protected int child1Index(int i) {
        return i * 4 - 8;
    }

    /**
     * calculate the index of the secondchild of node with index i
     * @param i
     * @return secondechild index
     */
    protected int child2Index(int i) {
        return i * 4 - 7;
    }

    /**
     * calculate the index of the thirdchild of node with index i
     * @param i
     * @return thirdchild index
     */
    protected int child3Index(int i) {
        return i * 4 - 6;
    }
    /**
     * calculate the index of the forthchild of node with index i
     * @param i
     * @return forthchild index
     */
    protected int child4Index(int i) {
        return i * 4 - 5;
    }

    /**
     * judge if there is a firstchild of the node with index i
     * @param i
     * @return ture: has firstchild
     *         false: do not have firstchild
     */
    protected boolean haschild1(int i) {
        return child1Index(i) <= fourwayheap.size()-1;
    }

    /**
     * judge if there is a secondchild of the node with index i
     * @param i
     * @return ture: has secondchild
     *         false: do not have secondchild
     */
    protected boolean haschild2(int i) {
        return child2Index(i) <= fourwayheap.size()-1;
    }

    /**
     * judge if there is a thirdchild of the node with index i
     * @param i
     * @return ture: has thirdchild
     *         false: do not have thirdchild
     */
    protected boolean haschild3(int i) {
        return child3Index(i) <= fourwayheap.size()-1;
    }

    /**
     * judge if there is a forthchild of the node with index i
     * @param i
     * @return ture: has forthchild
     *         false: do not have forthchild
     */
    protected boolean haschild4(int i) {
        return child4Index(i) <= fourwayheap.size()-1;
    }

    /**
     * judge if there is a parent of the node with index i
     * @param i
     * @return ture: has parent
     *         false: do not have parent
     */
    protected boolean hasParent(int i) {
        return i > 3;
    }




}
