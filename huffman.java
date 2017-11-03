import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by xiyaoma on 3/30/17.
 */
public class huffman {

    binary_heap bh = new binary_heap();
    four_way_heap wh = new four_way_heap();
    pairing_heap ph = new pairing_heap();
    HashMap<String, Integer> freq_table = new HashMap<String, Integer>();


    /**
     * build a huffman tree with pairing heap
     *
     * @return
     * @throws IOException
     */
    public pairing_heap_node build_huffman_pairingheap() throws IOException {
        pairing_heap_node root = null;
        while (ph.size > 1) {
            //build huffman tree using binary_heap data structure
            pairing_heap_node least1 = ph.remove_min();
//            System.out.println("delete1"+"key: " + least1.key+"  value: "+least1.value);
            pairing_heap_node least2 = ph.remove_min();
//            System.out.println("delete2"+"key: " + least2.key+"  value: "+least2.value);
            pairing_heap_node combine = new pairing_heap_node(least1.key + least2.key, null);
//            System.out.println("combine key: " + combine.key+"  value: "+combine.value);
            ph.insert(combine);
            combine.isleaf = false;

//            int i = 0;
//            while (i<=(bh.binaryheap.size()-1)) {
//                System.out.println("Index: "+ i +"  Key: "+ bh.binaryheap.get(i).key +"  Value:" + bh.binaryheap.get(i).value);
//                i++;
//            }
        }
        root = ph.remove_min();
        return root;
    }


    /**
     * build a huffman tree with 4-way heap
     *
     * @return
     * @throws IOException
     */
    public arraylist_node build_huffman_4wayheap() throws IOException {
        arraylist_node root = null;
        while (wh.fourwayheap.size() > 4) {
            //build huffman tree using binary_heap data structure
            arraylist_node least1 = wh.remove_min();
//            System.out.println("delete1"+"key: " + least1.key+"  value: "+least1.value);
            arraylist_node least2 = wh.remove_min();
//            System.out.println("delete2"+"key: " + least2.key+"  value: "+least2.value);
            int new_key = least1.key + least2.key;
            arraylist_node combine = new arraylist_node(new_key, null, least1, least2);
//            System.out.println("combine key: " + combine.key+"  value: "+combine.value);
            wh.insert(combine);
            combine.isleaf = false;
        }
        root = wh.remove_min();
        return root;
    }


    /**
     * build a huffman tree with binary heap
     *
     * @return
     * @throws IOException
     */
    public arraylist_node build_huffman_binaryheap() throws IOException {
        arraylist_node root = null;
        while (bh.binaryheap.size() > 1) {
            //build huffman tree using binary_heap data structure
            arraylist_node least1 = bh.remove_min();
//            System.out.println("delete1"+"key: " + least1.key+"  value: "+least1.value);
            arraylist_node least2 = bh.remove_min();
//            System.out.println("delete2"+"key: " + least2.key+"  value: "+least2.value);
            arraylist_node combine = new arraylist_node(least1.key + least2.key, null, least1, least2);
//            System.out.println("combine key: " + combine.key+"  value: "+combine.value);
            bh.insert(combine);
            combine.isleaf = false;

//            int i = 0;
//            while (i<=(bh.binaryheap.size()-1)) {
//                System.out.println("Index: "+ i +"  Key: "+ bh.binaryheap.get(i).key +"  Value:" + bh.binaryheap.get(i).value);
//                i++;
//            }
        }
        root = bh.remove_min();
        return root;
    }


//            fileReader.close();
////        int size = stringBuffer.capacity();
//            int end_index = 0;
//            char tmp;
//            arraylist_node pointer = x;
//            while (stringBuffer.length() > 0) {
//                assert end_index <= stringBuffer.length();
//                tmp = stringBuffer.charAt(end_index);
//                if (tmp == '0') {
//                    pointer = pointer.leftchild;
//                    if (pointer.value != null) {
//                        bw.write(pointer.key + "");
//                        bw.write("\n");
//                        pointer = x;
//                        stringBuffer.delete(0, end_index + 1);
//                        System.out.println("end_index: " + end_index + "   size: " + stringBuffer.length());
//                        end_index = 0;
//                    } else {
//                        end_index++;
//                        continue;
//                    }
//
//                } else {
//                    pointer = pointer.rightchild;
//                    if (pointer.value != null) {
//                        bw.write(pointer.key + "");
//                        bw.write("\n");
//                        pointer = x;
//                        stringBuffer.delete(0, end_index + 1);
//                        end_index = 0;
//                    } else {
//                        end_index++;
//                        continue;
//                    }
//                }
//            }
//            bw.close();
//            System.out.println("Decoding complete.");
//        }

    /**
     * build a hushmap and calculate the frequency
     * @param s
     */
    public void add(String s) {
        if (freq_table.containsKey(s)) {
            Integer i =freq_table.get(s);
            freq_table.put(s, i + 1);
        } else {
            freq_table.put(s,1);
        }
    }

    /***
     * read input txt file and transform it into frequency table via hashmap
     * @param fpath
     * @throws IOException
     */
    public void readtxtfile(String fpath) throws IOException {
        try {
            File file = new File(fpath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                add(line);
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            fileReader.close();
//            System.out.println("Contents of file:");
//            System.out.println(stringBuffer.toString());
//            System.out.println(freq_table);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * output a txt file
     * @param code_table
     * @param fpath
     * @throws IOException
     */
    public void write_txtfile(String[] code_table, String fpath) throws IOException {
        File file = new File(fpath);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        int i;
        for (i=0; i<1100000;i++) {
            if (code_table[i] != null) {
                bw.write(i +" "+code_table[i]);
                bw.write("\n");
            }
        }
//        Iterator iterator = hash_map.keySet().iterator();
//        while (iterator.hasNext()) {
//            String hashkey = iterator.next().toString();
//            String hashvalue = hash_map.get(hashkey);
//            bufferedWriter.write(hashkey+" ==> "+ hashvalue);
//            bufferedWriter.write("\n");
//        }
        bw.close();
    }


//    /**
//     * 1.make a decode table by converting a code table
//     * 2.decode
//     */
//    public void decode() throws IOException {
//        //make a decode table by converting a code table
//        HashMap<String, String> decode_table = new HashMap<>();
//        Iterator iterator = this.codetable.keySet().iterator();
//        while (iterator.hasNext()) {
//            String hashkey = iterator.next().toString();
//            String hashvalue = this.codetable.get(hashkey);
//            decode_table.put(hashvalue,hashkey);
//        }
//        System.out.println("decode table is "+decode_table);
//        //decode
//        File file = new File("encoded.bin");
//        FileReader fr = new FileReader(file);
//        BufferedReader br = new BufferedReader(fr);
//        StringBuffer read_stringbuffer = new StringBuffer();
//
//        File write_file = new File("decoded.txt");
//        FileWriter fw = new FileWriter(write_file);
//        BufferedWriter bw = new BufferedWriter(fw);
//        StringBuffer write_stringBuffer = new StringBuffer();
//
//        String section;
//        int size;
//        read_stringbuffer.append(br.readLine());
//        fr.close();
////        System.out.println(" decoding string is "+ read_stringbuffer.toString());
//        size = read_stringbuffer.capacity();
//        int end_index = 0;
//
//        while (size > 0) {
//            assert end_index <=read_stringbuffer.length();
//            section = read_stringbuffer.substring(0, end_index);
//
//            if (decode_table.containsKey(section)) { //if match
//                write_stringBuffer.append(decode_table.get(section));
//                write_stringBuffer.append("\n");
//                read_stringbuffer.delete(0, end_index);//delete the section that has been decoded
//                end_index = 0;
//                if (read_stringbuffer.length() == 0) {
//                    break;
//                }
//                continue;
//            } else {
//                end_index++;
//
//            }
//        }
//        bw.write(write_stringBuffer.toString());
//        bw.close();
//        System.out.println("Decoding complete.");
//    }


}

