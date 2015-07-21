/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffmancompression;

/**
 *
 * @author Abdul Wakeel Khan
 */
public  class huffmanTree implements Comparable<huffmanTree>{
    huffNode newNode;
    
    public huffmanTree(){
        
    }
    //Constructor for creating leaf nodes 
     public huffmanTree(char Char,int Frequency){
        newNode=new huffNode(Char,Frequency);//calling constructor of huffNode class
    }
     //COnstructor for creating Root with its childs
    public huffmanTree(huffmanTree ht1,huffmanTree ht2){
        newNode=new huffNode();
        newNode.leftChild=ht1.newNode;//set left child
        newNode.rightChild=ht2.newNode;//set right child
        newNode.Frequency=ht1.newNode.Frequency+ht2.newNode.Frequency;//set frequency (sum of freq of childs)
    }
    
   // public huffmanTree(char Char,String code,int Frequency){
     //   newNode=new huffNode(Char,code,Frequency);
    //}

    public huffmanTree(char Char, String code) {
        huffNode root=new huffNode(Char,code);
    }
   
    
    /*
    Overridden method of Comparable Interface
    Since the comparison is based on the frequency of each node and
    we want to remove the item with lowest frequency first so in the method 
    the compare order is reversed in order to remove lowest frequency item first.
    */
    
    @Override
   public int compareTo(huffmanTree obj){
        
        if(newNode.Frequency<obj.newNode.Frequency)
            return 1;
        else if(newNode.Frequency==obj.newNode.Frequency)
            return 0;
        else
            return -1;
    }
  
    
 
   
   
    
    





  //Sub class huffNode   
   /*
   This is the sub class of huffmanTree class which is for the leaf nodes of the huffmanTree.
   Since the leaf nodes have certain properties espacially in this case so
   the huffnode class have the following properties:
    *Contains character type Char for storing the character
    *Integer type Frequency for storing the occurenc of the character
    *huffNode type leftChild for seting left sub tree
    *huffNode type rightChild for seting right sub tree
    *String type code for storing appropriate code
   */
   class huffNode {
    char Char;
    int Frequency;
    huffNode leftChild;
    huffNode rightChild;
    String code="";
    
    //Default Constructor of huffNode class
    public huffNode(){
        
    }
    //constructor for leaf nodes
    public huffNode(char Char,int Frequency){
        this.Char=Char;
        this.Frequency=Frequency;
    }
    
    public huffNode(char Char,String code){
        this.Char=Char;
        this.code=code;
        
    }

   }
}



    

   

   

