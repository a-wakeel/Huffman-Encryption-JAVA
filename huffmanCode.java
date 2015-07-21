/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffmancompression;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Abdul Wakeel Khan
 */
public class huffmanCode {
    //public static final boolean DEBUG = false;
    // Default Constructor
    public huffmanCode(){
        
    }
    
    /*
    The getCodes method returns an array of strings conatining binary codes for each character on its corressponding position.
    It uses another method assignCodes method which assign code 0 to left child and 1 to right child.
    */
    public  String[] getCodes(huffmanTree.huffNode node){
        if(node==null) return null;//if no tree return null
        String []codes=new String[256];//String Array of size 256
        assignCodes(node,codes);//calling assignCode method
        return codes;//return array
    }
    
    /*
    The assignCodes method require two inputs, the address of the root of the huffman tree and an array of strings 
    
    ***Working***
    This method work recusively.
    First it checks if roots childs exsists or not
    if exists then it will set the left child node's code to 0 and right's to 1
    and continue recursively till leaf node and then stores the codes in codes string.
    */
    public void assignCodes(huffmanTree.huffNode node,String[] codes){
        if(node==null) return;
        if(node.leftChild!=null){   //base condition for recursion 
            node.leftChild.code=node.code+"0"; //set left child code to 0
            assignCodes(node.leftChild,codes); //recursive call
            
            node.rightChild.code=node.code+"1"; //set right child code to 1
            assignCodes(node.rightChild,codes); //recursive call
        }
        else{
            codes[(int)node.Char]=node.code; 
        }
    }
    
    
    /*
    The method getHuffmanTree require an integer array and returns address of root of a tree called huffmanTree.
    
    ***Working***
    First create an object of priority queue passing huffmanTree as parameter (SInce it has to store the addresses of huffmanTree nodes)
    Thean it scans the integer array and checks for each location if value at that location is greater than zero then add it to the queue.
    In the while loop it constructs the tree
    Removes the first and second element and set them as left and right child of a new node and add new node back to the queue
    The process continues untill it get only one elements address in queue and return it to the user.
    */
    public  huffmanTree getHuffmanTree(int[] counts){
        //create priority queue huffman tree as its parameter
        pQueue<huffmanTree> pq=new pQueue<huffmanTree>();
        for(int i=0;i<counts.length;i++){
            if(counts[i]>0)
                pq.addItem(new huffmanTree((char)i,counts[i]));//if frequency>0 add to the queue
        }
       
        while(pq.getSize()>1){
            huffmanTree ht1=pq.removeItem();//remove 1st element from queue
            huffmanTree ht2=pq.removeItem();//remove 2nd element from queue
            pq.addItem(new huffmanTree(ht1,ht2));//set first as left 2nd as right and add node back to the queue
        }
        return pq.removeItem();//return address of root node
        
    }
    
  /*
    The getCharFrequency() method require the file name as its input and open it using
    Buffered Input Stream.
    Create an integer array of size 256
    It will read from the file until the end of the file reached.
    During the reading process when a char appears the value in int array is increamented by one
    on its logical position. (logical position->ascii of char)
    */  
    
    public  int[] getCharFrequency(String fileName) throws FileNotFoundException, IOException{
        BufferedInputStream fileInput=new BufferedInputStream(new FileInputStream(new File(fileName)));//open the file
        int [] counts=new int[256];
        int Read;
        while((Read=fileInput.read())!=-1){ //if it is not the end of file
            counts[Read]++;//if char appears increase value at its coressponding position
        }
        fileInput.close();
        return counts;
    }
    
    /*
    The method generateHuffmanTable() prints a table containing character, its ascii value and its binary code
    and create a text file containing the same table in present directory.
    Previous methods are used in it for its functionality.
    */
    public  void generateHuffmanTable(String fileName) throws FileNotFoundException, IOException{
        int[] counts=getCharFrequency(fileName);//char frequency array
        System.out.printf("%-15s%-15s%-15s%-15s\n","ASCII Code", "Character", "Frequency", "Code");
        huffmanTree t=getHuffmanTree(counts);//build huffman tree
        String[] codes=getCodes(t.newNode);//get codes array
        for(int i=0;i<codes.length;i++){//print table
            if(counts[i]!=0)
                System.out.printf("%-15d%-15s%-15d%-15s\n",i,(char)i+"",counts[i],codes[i]);
        }
       
        
    }
    
 /*
    The compressFile() method is used for compressing the file it uses other methods for its working.
    
    ***Working***
    The method get file name to be compressed as input from user
    It calls getCharFrequency() method and store the frequencies in integer array
    It calls getHuffmanTree() method to construct the tree and return its root address
    It calls getCodes() method by passing root of tree and return string arry containing codes
    then it calls the encode() method to encode the file
    */
    
    public void compressFile() throws FileNotFoundException, IOException{
         System.out.print("[*] Enter file name:");
         Scanner input=new Scanner(System.in);
         String file=input.next();//get file name from user
         System.out.println("[+] Generating codes");
         generateHuffmanTable(file);//print huffman table to the screen
         System.out.println("[+] Compressing file");
         int[] codes=getCharFrequency(file);//get frequencies of characters
         huffmanTree ht=getHuffmanTree(codes);//build huffman tree
         String[] assignCodes=getCodes(ht.newNode);//assign codes to nodes of tree
         System.out.println("[!] Please wait....");
         encode(file,assignCodes);// encode the file
         
         System.out.println("-------Compression DONE-------");
         PrintWriter ouput=new PrintWriter(new File("Table.fr"));//write frequency table to the file for future usage
         for(int i=0;i<codes.length;i++){
             ouput.write(String.valueOf(codes[i]));
             ouput.println();
         }
         ouput.flush();
         ouput.close();
     }
    
    /*
    The encode() method takes file name and array of strings as parameters
    ***Working***
    First it opens the user desired file
    then open a new file for storing compressed codes
    then it iterate the input file till the end and writes codes in output file 
    by writeBit() method of BitOutput class.
    */
    public  void encode(String filename, String[] codes) throws IOException {
    BufferedInputStream fileInput = new BufferedInputStream(
      new FileInputStream(new File(filename)));//open the input file
    BitOutput output = new BitOutput(new File(filename + ".cmp"));         
    int r;
    while ((r = fileInput.read()) != -1 ) {//iterate the input file till the end and read byte by byte
      output.writeBit(codes[r]);//write codes in file via output stream
    }
    
   
  }
    
    /*
    The decodeFile() is a bit lengthy method due to its operations
    ***Working***
    First it get name of compressed file from user
    then it opens the frequency table file for that compressed file
    then it copy the frequencies to a string array to its corresponding array
    then its converts the string array to an integer array
    then it calls the getHuffmanTree method to construct the tree and assign codes to its nodes
    then it opens the compressed file and decode it by using getLeaves() functions and save to the new file.
    */
    public void decodeFile() throws FileNotFoundException, IOException{
        System.out.print("[*] Enter the file name:");
        Scanner scan=new Scanner(System.in);
        String compFile=scan.next();        //get compressed file name/path
        System.out.println("[+] Opening Frequency Table: Table.fr");
      BufferedReader br=new BufferedReader(new FileReader("Table.fr"));// open frequency table
      String line;
      String[] fr=new String[256];
      int counter=0;
      while((line=br.readLine())!=null){//read frequency table file till end and save contents in string array
          if(!line.matches("null")){
              fr[counter]=line;
          }
          counter++;
      }
      int [] codes=new int[256];
      for(int j=0;j<256;j++){// convert string array to integer array 
          codes[j]=Integer.parseInt(fr[j]);
      }
      System.out.println("[+] Constructing Huffman Tree......");
      huffmanTree ht=getHuffmanTree(codes);// build huffmanTree
      System.out.println("[+] Setting code paths.....");
      String[] Codes=getCodes(ht.newNode);// assign codes to nodes of tree
      System.out.println("[+] Opening Encoded file");
      BitInput bitInput=new BitInput(new File(compFile));      
      String test="";
      System.out.println("[*] Decoding");
      System.out.println("[!] Please Wait......");
      try{
      while(true){// call the getLeaves() method till the end of compressed file
          test=test+getLeaves(ht.newNode,bitInput);
      }
      }catch(EOFException e){}
      System.out.println("----DONE-----\n Decoded:");
      PrintWriter output=new PrintWriter(new File("Decoded.txt"));//open new file for restoring 
      output.write(test);// write decoded text to the file
      output.close();
      System.out.println(test);// print decoded text to the screen
      
     
    }
    /*
    The getLeaves() method is a recursive method which return the value of a leaf node
    ***Wroking***
    This method get address of root and object of BitInput class as parameters.
    the base condition for this method is if root is null or is leaf node if it is leaf node then add char to a string.
    else get a bit from file by readBit() of BitInput class and if the bit is true (1) go to the right sub tree else goto
    the left subtree and return the character.
    */
  public String getLeaves(huffmanTree.huffNode root,BitInput bitInput) throws IOException{
     String text="";
     if(root==null)//base condition 1
         return null;
     else if(root.leftChild==null && root.rightChild==null)//base condition 2
         text=text+root.Char;//save char in string
     else{
         if(bitInput.readBit()==true)//read 1 bit from file if it is 1
             text=text+getLeaves(root.rightChild,bitInput);//if bit is 1 goto right
         else//if bit is 0
             text=text+getLeaves(root.leftChild,bitInput);//if bit is 0 goto left
     }
     return text;
 
 
  }}
  
  
 

