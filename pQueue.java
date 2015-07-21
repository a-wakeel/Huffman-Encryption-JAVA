/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffmancompression;

import java.util.ArrayList;

/**
 *
 * @author Abdul Wakeel Khan
 */
public class pQueue<E extends Comparable>{
    private ArrayList<E> list=new ArrayList<E>();
    
    public pQueue(){
        
    }
    
   /*
    addItem() method add elements to the list which is array list at the end of it
    
    working:
    first it adds element at the end of the list
    then check its frequency with itme which comes first 
    if its frequency is less than the item come first it swaps both with each other
    */
 public void addItem(E item){
     list.add(item);
     int pos=list.size()-1;//get index of inserted item
     
     while(pos>0 && list.get(pos).compareTo(list.get(pos-1))>0){//if inserted item is not the first item and its frequency is less than the item come first than it
         E temp=list.get(pos-1);// swap items
         list.set(pos-1, list.get(pos));
         list.set(pos,temp);
     }
 }
 
 
 /*
 removeItem() method return first element of the list
 working:
 first it save the first element in temp var
 then removes it
 then it shifts all the elements one position back on the list
 at end it return removed elemnt
 */
 public E removeItem(){
     if(list.size()==0)//if list is empty
         return null;
     E removedItem=list.get(0);//get first item
     list.remove(0);//remove first item from list
     int i=0;
     while(i<list.size()){//shift all items one position back
         list.set(i, list.get(i++));
         i++;
     }
     return removedItem;
 }
 
 //returns size of the list
 public int getSize(){
     return list.size();
 }
    
}
