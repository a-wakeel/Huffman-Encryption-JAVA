/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffmancompression;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Abdul Wakeel Khan
 */
public class HuffmanCompression {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
   
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException,NoSuchElementException{
  //  project_design();
     char ch = 0;
     huffmanCode hc=new huffmanCode();
     Scanner input=new Scanner(System.in);
     do{
         System.out.println("Select Options:");
         System.out.println("1. Compress File");
         System.out.println("2. De-Compress File");
         System.out.println("3. About Project");
         System.out.println("4. Exit");
         int choice=input.nextInt();
         switch(choice)
         {
             case 1:
                 hc.compressFile();
                 break;
             
             case 2:
                 hc.decodeFile();
                 break;
                 
             case 3:
                 project_design();
                 break;
                 
             case 4:
                 System.exit(0);
                 
             default:
                 System.out.println("[-] ERROR: Wrong Entry\n");
                 break;
         }
         System.out.println("\nDo you want to continue? (Y/N)");
         ch=input.next().charAt(0);
     }while(ch=='Y' || ch=='y');
     
        
        
      }
    
    public static void project_design(){
        System.out.println("**********************************************************************");
        System.out.println("* ****************************************************************** *");
        System.out.println("* *      #######         ####     #########  ###                   * *");
        System.out.println("* *    ##       ##      ##  ##     ##     ##  ##                   * *");
        System.out.println("* *   ##        ###    ##    ##    ##      ## ##                   * *");
        System.out.println("* *   ##              ##      ##   ##     ##  ##                   * *");
        System.out.println("* *   ##             ## ###### ##  ## # ##    ##                   * *");
        System.out.println("* *   ##        ### ##          ## ##     ##  ##                   * *");
        System.out.println("* *    ##       ## ##           ## ##      ## ##         #         * *");
        System.out.println("* *      #######   ##           ## ##      ## ############         * *");
        System.out.println("* *                                            CORPORATION         * *");
        System.out.println("* *                                    DESIGNED BY: ABDUL WAKEEL   * *");
        System.out.println("* *                                    EMAIL: awkhan978@gmail.com  * *");
        System.out.println("* *                                    MOBILE: 00923039421355      * *");
        System.out.println("* ****************************************************************** *");
        System.out.println("**********************************************************************");
    }
}
    
   
                

    
  
    

