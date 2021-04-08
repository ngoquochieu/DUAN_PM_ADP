
import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Matrix implements ActionListener 
{
     private static int col, row;  //dimentions
     
     private static ArrayList< ArrayList<Double> > myMatrix;
     
     private static ArrayList< ArrayList<Double> > tempMatrix;
     
     private static JTextField inputField [][];
     private static int result;
     private static JButton minusB, plusB, rank,
     						multiplyB,getValueB, showMatrix,
     						solution,newMatrix;
     private static JPanel choosePanel [] = new JPanel[8];
     private static int lastCol , lastRow ;
      
     
     
     Matrix ()
     {
         col = row = 0;
         myMatrix = new ArrayList<>();
         ChooseOperation();
     }
     
     
     //prompting for matrix's dimensions
     
     
     public static void setup(ArrayList< ArrayList<Double> > a,int n,int m)
     {
    	 a.clear();
    	 ArrayList<Double> ad = new ArrayList<Double>(m);
    	 for(int i = 0; i < m; i++)
    		 ad.add(0.0);
    	 
    	 for(int i = 0; i < n; i++)
    	 {
    		 a.add(ad);
    	 }
     }
     private static void getDimension() 
    {
      JTextField lField = new JTextField(5); //lenght field 
      JTextField wField = new JTextField(5); //col field
      
      //design input line
      JPanel choosePanel [] = new JPanel [2];
       choosePanel [0] = new JPanel();
       choosePanel [1] = new JPanel();
      choosePanel[0].add(new JLabel("Enter Dimensitions") );
      choosePanel[1].add(new JLabel("Rows:"));
      choosePanel[1].add(lField);
      choosePanel[1].add(Box.createHorizontalStrut(15)); // a spacer
      choosePanel[1].add(new JLabel("Cols:"));
      choosePanel[1].add(wField);
        
      result = JOptionPane.showConfirmDialog(null, choosePanel, 
               null,JOptionPane.OK_CANCEL_OPTION, 
               JOptionPane.PLAIN_MESSAGE); // kh dung icon;
        
      //save last dimensions
      lastCol = col;
      lastRow = row;
      
      //ok option
       if(result == 0)
       {
         
	         if(wField.getText().equals(""))
	             col = 0;
	         else
	         {
	             if(isInt(wField.getText()))
	             {
	                 col = Integer.parseInt(wField.getText());
	             }
	             else
	             {
	                 JOptionPane.showMessageDialog(null, "Wrong Dimensions");
	                 col = lastCol;
	                 row = lastRow;
	                 return;
	             }
	            
	             if(isInt(lField.getText()))
	             {
	                 row = Integer.parseInt(lField.getText());
	             }
	             else
	             {
	                 JOptionPane.showMessageDialog(null, "Wrong Dimensions");
	                 col = lastCol;
	                 row = lastRow;
	                 return;
	             }
	          
	         }
	       if(col < 1 || row < 1)
	       {
	           JOptionPane.showConfirmDialog(null, "You entered wrong dimensions", 
	                   "Error",JOptionPane.PLAIN_MESSAGE); // question ?
	           col  = lastCol;
	           row = lastRow;
	          
	       }
	       else
	       {
	           tempMatrix = myMatrix;
	           //myMatrix = new double [row][col];
	           setup(myMatrix,row,col);
	           
	            if(!setElements(myMatrix, "Fill your new matrix")) //filling the new matrix
	            {
	                //backup
	                
	                myMatrix = tempMatrix;
	            }
	       }
       }
       else if(result == 1)
       {
           col = lastCol;
           row = lastRow;
       }
     }//end get Dimension
     
   //---------------------------------------------------------------------------------------------------------------   
     //setting a matrix's elementis
    private static boolean setElements(/*double matrix [][]*/ ArrayList< ArrayList<Double> > matrix, String title )
    {
        int temp, temp1;             //temprature variable
        String tempString;
        
       JPanel choosePanel [] = new JPanel [row+2]; //format;
       
       // edit format;
       choosePanel[0] = new JPanel();
       choosePanel[0].add(new Label(title ));
       choosePanel[choosePanel.length-1] = new JPanel();
       choosePanel[choosePanel.length-1].add(new Label("consider space field as zeros"));
       
       
       // create input field;
       inputField  = new JTextField [matrix.size()][matrix.get(0).size()];
        
       
       //lenght loop
       for(temp = 1; temp <= matrix.size(); temp++)
       {
           choosePanel[temp] = new JPanel();
           for(temp1 = 0; temp1 < matrix.get(0).size(); temp1++)
           {
               inputField [temp-1][temp1] = new JTextField(3);
               choosePanel[temp].add(inputField [temp-1][temp1]);
               
               if(temp1 < matrix.get(0).size() -1)
               {
            	   choosePanel[temp].add(Box.createHorizontalStrut(15)); // a spacer
               }
               
           }//end col loop
           
       }//end row loop
       
       result = JOptionPane.showConfirmDialog(null, choosePanel, 
               null, JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);   // create 
     
      
      if(result == 0)
      {
          checkTextField(inputField);
       for(temp = 0; temp < matrix.size(); temp++)
       {
    	   	ArrayList<Double> tempMatrix1 = new ArrayList<Double>();
	        for(temp1 = 0; temp1 < matrix.get(0).size(); temp1++)
	            {
	        		
	                tempString = inputField[temp][temp1].getText(); // Convert to string from inputfield;
	                
	                
	                 if(isDouble(tempString))  // Check if Number?
	                 {
	                	 double x = Double.parseDouble(inputField[temp][temp1].getText());  // parse to number;
	                	 tempMatrix1.add(x); // add to tmp matrix;
	                 }
	                 else
	                 { 
	                    JOptionPane.showMessageDialog(null, "You entered wrong elements");
	                    
	                    //backup
	                    col = lastCol;
	                    row = lastRow;
	                    
	                    return false;
	                 }                      
	            }
        //System.out.println();
	        matrix.set(temp, tempMatrix1); // add to;
       }
       return true;
    }
      else
          return false;
    
      
    }//end get Inputs
  //---------------------------------------------------------------------------------------------------------------   
    //for setting spaced fields as zeros
     private static void checkTextField (JTextField field [][] ) // convert from "" to zero;
     {
         for(int temp = 0; temp < field.length; temp++)
         {
             for(int temp1 = 0; temp1 < field[0].length; temp1++)
             {
                 if(field[temp][temp1].getText().equals(""))
                 field[temp][temp1].setText("0");
             }
         }
     }//end reset
   //---------------------------------------------------------------------------------------------------------------    
    private void ChooseOperation ()
    {
        int temp;
        
        
        for(temp = 0; temp < choosePanel.length; temp++) // To panel ?
        {
            choosePanel [temp] = new JPanel ();
        }
        
        ImageIcon chooseImage = new ImageIcon(getClass().getResource
                ("choose-button.png")) ;
        JLabel chooseLabel = new JLabel (chooseImage);
        choosePanel[0].add(chooseLabel);
        
        choosePanel[1].add(Box.createHorizontalStrut(15)); // a spacer
        
      //  choosePanel[6].add(Box.createHorizontalStrut(15)); // a spacer
        
        
        
        
        
        showMatrix = new JButton ("Show Matrix");
        showMatrix.setPreferredSize(new Dimension(275,35));
        showMatrix.addActionListener(this);
        choosePanel[2].add(showMatrix);
        
        plusB = new JButton ("Plusing with Matrix");
        plusB.setPreferredSize(new Dimension(175,35));
        plusB.addActionListener(this);
        choosePanel[3].add(plusB);
        
        minusB = new JButton ("Subtracting with Matrix");
        minusB.setPreferredSize(new Dimension(175,35));
        minusB.addActionListener(this);
        choosePanel[3].add(minusB);
        
        multiplyB = new JButton ("Multiplying by matrix");
        multiplyB.setPreferredSize(new Dimension(175,35));
        multiplyB.addActionListener(this);
        choosePanel[3].add(multiplyB);
        
        
        
        solution = new JButton ("Solution");
        solution.setPreferredSize(new Dimension(175,35));
        solution.addActionListener(this);
        choosePanel[4].add(solution);
        
        
        
        
           getValueB = new JButton ("Get Value");
           getValueB.setPreferredSize(new Dimension(175,35));
           getValueB.addActionListener(this);
           choosePanel[4].add(getValueB);
           
        rank = new JButton ("Get Rank");
        rank.setPreferredSize(new Dimension(175,35));
        rank.addActionListener(this);
        choosePanel[4].add(rank);
                   
        
        
        newMatrix = new JButton("New Matrix");
        newMatrix.setPreferredSize(new Dimension(275,35));
        newMatrix.addActionListener(this);
        choosePanel[5].add(newMatrix);
        
        JOptionPane.showConfirmDialog(null, choosePanel, null,
               JOptionPane.CLOSED_OPTION , JOptionPane.PLAIN_MESSAGE);
         
    }
   
  //---------------------------------------------------------------------------------------------------------------  
    @Override
    public  void actionPerformed(ActionEvent e) 
    {
        
        if(e.getSource() == showMatrix)
        {
            showMatrix( myMatrix, "Your Matrix");
        }
        if(e.getSource() == plusB)
        {
            matrixPlusMatrix();
        }
        
        else if(e.getSource() == minusB)
        {
            matrixMinusMatrix();
        }
         
        else    if(e.getSource() == multiplyB)
        {
            multiplyByMatrix();
        }
        else   if(e.getSource() == rank)
        {
        	guiGetRank();
        }
        else   if(e.getSource() == solution )
        {
        	 guiSolution();
        }
        else   if(e.getSource() == getValueB)
        {
            guiGetValue();
        }
        
        else   if(e.getSource() == newMatrix)
        {
            newMatrix();
        }
    }//end action performed

  //---------------------------------------------------------------------------------------------------------------   
    private static void showMatrix(ArrayList< ArrayList<Double> > matrix, String title )
    {
        
       
    if(col == 0 || row == 0)
    {
        JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
    }
    else
    {
    	int temp, temp1;             //temprature variable
        
        JPanel choosePanel [] = new JPanel [matrix.size()+1];
        choosePanel[0] = new JPanel ();
        choosePanel[0].add( new JLabel (title) ); // Set title;
    
        for(temp = 1; temp <= matrix.size(); temp++)
        {
     	   
            choosePanel[temp] = new JPanel(); // a new panel for every row;
            
            
            for(temp1 = 0; temp1 < matrix.get(0).size(); temp1++)
            {
                if(matrix.get(temp - 1).get(temp1) == -0.0)
                {
                   matrix.get(temp - 1).set(temp1, 0.0); 
                }
                
                choosePanel[temp].add(new JLabel(String.format("%.2f", matrix.get(temp - 1).get(temp1)))); // add to number to it.
                
                if(temp1 < matrix.get(0).size() -1)
                {
             	   choosePanel[temp].add(Box.createHorizontalStrut(15)); // a spacer to it;
                }
                
            }//end col loop
        }//end row loop
        
	    JOptionPane.showMessageDialog(null, choosePanel, null, 
	            JOptionPane.PLAIN_MESSAGE, null); // show to;
    }  
    }//end show Matrix
  //---------------------------------------------------------------------------------------------------------------
  private static void matrixPlusMatrix ()
	{
		if(myMatrix.size() < 1)
		{
		       JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
		}
		else
		{
			
				ArrayList< ArrayList<Double> > m2 = new ArrayList<ArrayList<Double>>(); setup(m2,row, col);
				ArrayList< ArrayList<Double> > sum = new ArrayList<ArrayList<Double>>(); setup(sum,row,col);
			
			    if(setElements(m2, "Fill Aditional Matrix"))  // check if input is valid;
			    {
			
					for(int i = 0; i < row; i++)
					{
						ArrayList<Double> tempMatrix1 = new ArrayList<>();
						for(int j = 0; j < col; j++)
						{
						    tempMatrix1.add(myMatrix.get(i).get(j) + m2.get(i).get(j));
						}
						sum.set(i,tempMatrix1);
					}
				    showMatrix(sum, "Summition Result");
			    }
		}//end else checking
	}//end plus matrix
    
  //---------------------------------------------------------------------------------------------------------------
	private static void matrixMinusMatrix ()
	{
		if(myMatrix.size() < 1)
		{
			JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
		}
		else
		{
			ArrayList< ArrayList<Double> > m2 = new ArrayList<ArrayList<Double>>(); setup(m2,row, col);
			ArrayList< ArrayList<Double> > mi = new ArrayList<ArrayList<Double>>(); setup(mi,row,col);
		    if(setElements(m2, "Fill Aditional Matrix"))
		    {
		
				for(int i=0;i<row;i++)
				{
					ArrayList<Double> tempMatrix1 = new ArrayList<>();
					for(int j=0;j<col;j++)
					{
					    //sum[i][j]=myMatrix[i][j]+m2[i][j];
					    tempMatrix1.add(myMatrix.get(i).get(j) - m2.get(i).get(j));
					}
					mi.set(i,tempMatrix1);
				}
			    showMatrix(mi, "Summition Result");
		    }
		        }//end else checking
	}//end plus matrix

	//---------------------------------------------------------------------------------------------------------------
	private static void multiplyByMatrix ()
	{
	    
	      JTextField wField = new JTextField(5); //col field
	      int col2 = 0;
	      //double m2 [][] , results[][];
	      double sum;
	      
	      if(myMatrix.size() < 1)
	      {
	    	  JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
	      }
	      else
	      {
	      
		      //design input line
		      JPanel choosePanel [] = new JPanel [2];
		       choosePanel [0] = new JPanel();
		       choosePanel [1] = new JPanel();
		       
		      choosePanel[0].add(new JLabel("Enter Dimensitions") );
		      
		      choosePanel[1].add(new JLabel("Rows:"));
		      choosePanel[1].add(new JLabel(""+col));
		      choosePanel[1].add(Box.createHorizontalStrut(15)); // a spacer
		      choosePanel[1].add(new JLabel("Cols:"));
		      choosePanel[1].add(wField);
		        
		      
		      result = JOptionPane.showConfirmDialog(null, choosePanel, 
		               null,JOptionPane.PLAIN_MESSAGE, 
		               JOptionPane.PLAIN_MESSAGE);
		      
		      if(result == 0)
		      {
		          if(wField.getText().equals(""))
		          {
		              col2 = 0;
		          }
		          else
		              if(isInt(wField.getText()) )
		              {
		            	  col2 = Integer.parseInt(wField.getText());   
		              }
		          
		          ArrayList< ArrayList<Double> > m2 = new ArrayList<ArrayList<Double>>(); setup(m2,col, col2);
		          ArrayList< ArrayList<Double> > results = new ArrayList<ArrayList<Double>>(); setup(results,row,col2);
			      if(setElements(m2, "Fill multiplying matrix"))
			      {
			      
				      for(int i = 0 ; i < row ; i++)
				         {
				    	  	ArrayList<Double> tempMatrix1 = new ArrayList<Double>();
				            for(int j = 0 ; j < col2 ; j++ )
				            {   
				                sum = 0;
				               
				               for(int k = 0 ; k < col ; k++ )
				               {
				            	   sum += myMatrix.get(i).get(k)*m2.get(k).get(j);  // n m and m k, with the result is n k with 
				            	   													//for(i=0 to n,j=0 to k,k=0 to m) and a[i][j] = sum(a[i][k]*b[k][j]); 
				                  
				               }
				 
				               tempMatrix1.add(sum);
				             
				            }
				            results.set(i,tempMatrix1);
				         }
				 
				     showMatrix(results, "Mulitiplication Result"); // show matrix;
			      }//elements checking
		      }//dimensions checking
		      else
		          return;
	        }//end else of checking
	}//end multiply by matrix method
	//---------------------------------------------------------------------------------------------------------------
    private static void guiGetValue ()
    {
        if(myMatrix.size() < 1)
        {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
        }
        else if(col != row)
        {
            JOptionPane.showMessageDialog(null, "You must enter square matrix");
        }
        else
        {
        double result = getValue(myMatrix);
            
    JOptionPane.showMessageDialog(null, String.format("Determination's Value = %.2f", 
            result)  , null, 
            JOptionPane.PLAIN_MESSAGE, null);
        }
    }//end Gui get Value
  //---------------------------------------------------------------------------------------------------------------    
    private static void swap (ArrayList<Double> res1, ArrayList<Double> res2)
    {
        ArrayList<Double> temp = res1;
        res1 = res2;
        res2 = temp;
        
    }
  //---------------------------------------------------------------------------------------------------------------   
    private static double getValue (ArrayList< ArrayList<Double> > matrix)
    {
        int temp;
        double result = 1;
        
        ArrayList< ArrayList<Double> > res = echelonMatrix(myMatrix);
        
        
        for(temp = 0; temp < res.size(); temp++)
        {
            result *= res.get(temp).get(temp);
        }
        
        return result;
     }//end getValue
  //---------------------------------------------------------------------------------------------------------------   
    private static void guiGetRank()
    {
        if(myMatrix.size() < 1)
        {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
        }
        else
        {
        int result = getRank(myMatrix);
        System.out.println("Res=" + result);
    JOptionPane.showMessageDialog(null, String.format("Rank = %d", 
            result)  , null, 
            JOptionPane.PLAIN_MESSAGE, null);
        }
    }//end gui get Value
  //---------------------------------------------------------------------------------------------------------------
    
    public static ArrayList< ArrayList<Double> > echelonMatrix(ArrayList< ArrayList<Double> > matrix) {
		 // Khoi tao matrix
		ArrayList< ArrayList<Double> > ans = new ArrayList<ArrayList<Double>>(); // arraylist;
		 // a la to mymatrix;
		//matrix thi tra ve ans;
		 for(int i = 0; i < matrix.size(); i ++) 
		 {
			 ArrayList<Double> elem = new ArrayList<Double>();
			 for(int j = 0; j < matrix.get(0).size(); j ++)
				 elem.add(matrix.get(i).get(j));
			 ans.add(elem);
		 }	 
		 
		 
		//Nhập ma trận A, cấp N, hàng thứ k, cột thứ p
		 for(int i = 0; i < ans.size() - 1; i ++) 
		 {			
			 if(ans.get(i).get(i) != 0); //B: 1.2 -> Buoc 3
			 if(ans.get(i).get(i) == 0) //B: 1.1 -> Buoc 2
			 {
				 //Buoc 2
				 boolean check = false;
				 int j = i + 1;
				 for(; j < ans.size() ; j ++ ) 
				 {				 
					 if(ans.get(j).get(i) == 0) // B: 2.1 
						 check = true;
					 else 
					 {
						check = false; // B: 2.2
						break;
					 }
				 }				 
				 if(check == false) // B: 2.2 -> Hoan doi tat ca phan tu cua dong j cho i sang buoc 3
				 {						 
					 ArrayList<Double> tmp = new ArrayList<Double>();
					 //swap hang i va hang j cho nhau;
					 tmp = ans.get(i);
					 ans.set(i, ans.get(j));
					 ans.set(j, tmp);
					 //swap(ans.get(i),ans.get(j));
				 }
				 else // B: 2.1 Nhay den buoc 4
				 {
					 // Buoc 4
					 continue;					 
				 }
			}
			 //buoc 3:
			 for(int j = i + 1; j < ans.size(); j++)
			 {
				 double x = ans.get(j).get(i) / ans.get(i).get(i);
				 for(int z = 0; z < ans.get(i).size(); z++)
				 {
					 double hj = ans.get(j).get(z) - x * ans.get(i).get(z);
					 ans.get(j).set(z, hj);
				 } 
			 }
				 
		 }
		 
		 
		
		 return ans;
	}
  //--------------------------------------------------------------------------------------------------------------- 
	 public static int  getRank(ArrayList< ArrayList<Double> > matrix) {
		 
		 ArrayList< ArrayList<Double> > ans = echelonMatrix(matrix);
		 int rank = 0;
		 //Duyệt tất cả các phần tử của hàng, nếu có một phần tử khác 0 thì tăng rank (A) lên một đơn vị
		 for(int i = 0; i < ans.size(); i ++)
			 for(int j = 0; j < ans.get(i).size(); j ++)
				 if (ans.get(i).get(j) != 0){
					 rank++;
					 break;
				 }
		
		 
		return rank;
	 }
	//---------------------------------------------------------------------------------------------------------------
	 public static void guiSolution()
	 {
		
		 
		 	if(myMatrix.size() < 1)
	        {
	            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
	        }
	        else
	        {
	        	 ArrayList<Double> ans = PTTT();
	        	 
	    		 String res ="";
	        	 if(ans.get(0) == -1)
	    		 {
	    			 res = "Have unlimit Solution";
	    		 }
	    		 else
	    			 if(ans.get(0) == 0)
	    			 {
	    				 res = "Don't Have any Solution";
	    			 }
	    			 else
	    			 {
	    				 res = "Here is Solution:\n";
	    				 for(int i = 1; i < ans.size(); i++)
	    				 {
	    					 res += "X" + String.valueOf(i) +" = "+ans.get(i).toString() + "\n";
	    					 
	    				 }
	    				 
	    				
	    			 }
	        	 
	        	 JOptionPane.showMessageDialog(null, res);
	        }
		
		
	 }
	//---------------------------------------------------------------------------------------------------------------
	 public static ArrayList<Double> PTTT (){
		 ArrayList<ArrayList<Double>> matrix_B = new ArrayList<ArrayList<Double>>();
			// Them tung phan tu cot - 1 vao b. De thu ve ma tran he so 
			for(int i = 0; i < myMatrix.size(); i++)
			{
				ArrayList<Double> elem = new ArrayList<Double>();
				for(int j = 0; j < myMatrix.get(i).size() - 1; j ++) 			
					elem.add(myMatrix.get(i).get(j));
				matrix_B.add(elem);
			}
			ArrayList<Double> nghiem = new ArrayList<Double>();
			ArrayList<ArrayList<Double> > matrix_A = echelonMatrix(myMatrix); // Chuyen ma tran mo rong A ve ma tran bac thang
			int rank_A = getRank(matrix_A); // Rank cua tran mo rong A
			int rank_B = getRank(matrix_B); // Rank cua ma tran he so B
			
		//Dau vao Ma tran mo rong A Voi so dong = n va so cot = n + 1
			int dong = matrix_A.size(); // So dong cua ma tran
			int cot  = dong + 1; // So cot cua ma tran mo rong
			
			// Neu rank cua ma tran he so B < rank cua ma tran mo rong A thi HPT vo nghiem
			if(rank_B < rank_A)
				nghiem.add(0.0);
			else
				//Neu rank cua ma tran he so B = rank cua ma tran mo rong A va < so an thi HPT vo so nghiem
				if(rank_B == rank_A && rank_A < dong )
					nghiem.add(-1.0);
				else
				//Neu rank cua ma tran he so B = rank cua ma tran mo rong A va = so an nhay den b3
				{
				// Buoc 3:
				/*Tinh nghiem Xn tai vi tri A[n][n] sau do tinh nghiem Xn-1 tai vi tri
				 A[n-1][n-1], tinh den nghiem X1
				*/
					nghiem.add(1.0);
					nghiem.add(matrix_A.get(dong - 1).get(cot - 1) / matrix_A.get(dong - 1).get(dong - 1));
					for(int i = dong - 2; i >= 0; i --)
					{
						double s = matrix_A.get(i).get(cot - 1);
						for(int j = 1 ; j < nghiem.size() ; j ++)
						{
							s = s - nghiem.get(j) * matrix_A.get(i).get(cot - 1 - j);
						}
						s = s / matrix_A.get(i).get(i);
						nghiem.add(s);
						nghiem.set(0, (double)nghiem.size() - 1);
						
					}
			}
			return nghiem;		 
		}
	//--------------------------------------------------------------------------------------------------------------- 	
   private static boolean isInt (String str)
   {
       int temp;
       if (str.length() == '0')
           return false;
       
       for(temp = 0; temp < str.length();temp++)
       {
           if(str.charAt(temp) != '+' && str.charAt(temp) != '-'
                   && !Character.isDigit(str.charAt(temp)))
           {
               return false;
           }
       }
       return true;
   }
 //---------------------------------------------------------------------------------------------------------------
    private static boolean isDouble (String str)
    {
       int temp;
       if (str.length() == '0')
           return false;
       
       for(temp = 0; temp < str.length();temp++)
       {
           if(str.charAt(temp) != '+' && str.charAt(temp) != '-'
                   && str.charAt(temp) != '.'
                   && !Character.isDigit(str.charAt(temp))
                   )
           {
               return false;
           }
       }
       return true;
   }
  //---------------------------------------------------------------------------------------------------------------   
    private static void newMatrix ()
    {        
        getDimension();
    }
    
     public static void main (String [] args)
    {
        Matrix m1 = new Matrix ();
        
    }
}//end class

