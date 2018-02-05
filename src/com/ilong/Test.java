package com.ilong;
import com.ilong.*;

import java.util.Arrays;
import java.util.Scanner;

public class Test {
	public static final int Max = 100;
	//行位置交换函数
		public static boolean swapRows(int i,int j,AdjacencyMatrix A){
			int k;
			int temp;
			//进行行交换
			for(k = 0;k<A.points;k++){
				temp = A.Matrix[i][k];
				A.Matrix[i][k] = A.Matrix[j][k];
				A.Matrix[j][k] = temp;
			}
			//进行度交换
			temp = A.weight[i];
			A.weight[i] = A.weight[j];
			A.weight[j] = temp;
			return true;
		}
		
		public static boolean swapColumns(int currentLayer,int i,int j,AdjacencyMatrix A){  
		    int k;  
		    //判断是否能交换  
		    for(k=0;k<currentLayer;k++){  
		        if(A.Matrix[k][i]!=A.Matrix[k][j]){  
		            //无法交换，因为交换后会影响先前调整的结果，故而不同构  
		            return false;   
		        }  
		    }  
		    //进行列交换  
		    for(k=0;k<A.points;k++){  
		        int temp;  
		        temp =A.Matrix[k][i];  
		        A.Matrix[k][i]= A.Matrix[k][j];  
		        A.Matrix[k][j]= temp;  
		    }   
		    return true;  
		}   
			
	public static void main(String[] args){ 
		AdjacencyMatrix A = new AdjacencyMatrix() ;
		AdjacencyMatrix B = new AdjacencyMatrix() ;
		int ta,tb;
	    System.out.println("请输入两个图的阶数（顶点数）：");  
	    Scanner in = new Scanner(System.in);
	    A.points = in.nextInt();
	    B.points = in.nextInt();
	      
	    //判断第一个必要条件  
	    if(A.points!=B.points){  
	        System.out.println("阶数不同！不同构！");
	        return;  
	    }  
	      
	    System.out.println("请输入第1个图的邻接矩阵：");  
	    A.edges = 0;  
	    B.edges = 0;  
	      
	    //用邻接矩阵方式输入A、B矩阵  
	    int i,j,k,y;  
	    for(i=0;i<A.points;i++){  
	        for(j=0;j<A.points;j++){  
	            A.Matrix[i][j] = in.nextInt();  
	            if(A.Matrix[i][j]==1){  
	                A.edges++;  
	            }     
	        }  
	    }  
	      
	    System.out.println("请输入第2个图的邻接矩阵：");  
	    for(i=0;i<B.points;i++){  
	        for(j=0;j<B.points;j++){  
	            B.Matrix[i][j] = in.nextInt();  
	            if(B.Matrix[i][j]==1){  
	                B.edges++;  
	            }     
	        }  
	    }  
	      
	    //判断第二个必要条件  
	    if(A.edges!=B.edges){  
	        System.out.println("边的条数不同！不同构！");  
	        return;     
	    }   
	      
	    //因为是邻接矩阵，所以边的条数（即邻接矩阵非零点个数/2）  
	    A.edges =A.edges/2;  
	    B.edges =B.edges/2;  
	    int Aweight[] = new int[Max];  
	    int Bweight[] = new int[Max];  
	    //判断第三个必要条件  
	    int x=0;  
	    for(k=0;k<A.points;k++){  
	        int count=0;  
	        for(y=0;y<A.points;y++){  
	            if(A.Matrix[k][y]==1){  
	                count++;  
	            }  
	        }  
	        Aweight[x]= count;  
	        A.weight[x++]=count;  
	    }   
	    //qsort(Aweight,A.points,sizeof(Aweight[0]),cmp());//调用系统快速排序算法  
	    //quickSort(Aweight, 0 , Aweight.length-1);
	    Arrays.sort(Aweight);
	    Arrays.sort(A.weight);
	    
	    x=0;  
	    for(k=0;k<B.points;k++){  
	        int count=0;  
	        for(y=0;y<B.points;y++){  
	            if(B.Matrix[k][y]==1){  
	                count++;  
	            }  
	        }  
	        Bweight[x]= count;  
	        B.weight[x++]=count;  
	    }  
	    //qsort(Bweight,B.points,sizeof(Bweight[0]),cmp);//调用系统快速排序算法  
	    //quickSort(Bweight, 0 , Bweight.length-1);
	    Arrays.sort(Bweight);
	    Arrays.sort(B.weight);
	    
	    for(k=0;k<A.points;k++){  
	        if(Aweight[k]!=Bweight[k]){  
	            System.out.println("边的度数不同！不同构！");  
	            return;  
	        }  
	    }   
	      
	      
	    //调整A矩阵成B   
	    for(i=0;i<B.points;i++){  
	        for(j=i;j<A.points;j++){  
	            //找到度相同  
	            if(B.weight[i] == A.weight[j]){  
	                //进行行交换  
	                if(i!=j){  
	                    swapRows(i,j,A);  
	                }  
	                  
	                //进行列交换  
	                if(i!=j){  	                  
	                    if(swapColumns(i,i,j,A)==false){  
	                        System.out.println("无法调整成相同的邻接矩阵！不同构！");  
	                        return ;  
	                    }         
	                      
	                    int list[] = new int [100];  
	                    x=0;  
	                    //判断非零顶点所处列的位置是否相同  
	                    for(k=0;k<A.points;k++){//找出位置不同的点放入list   
	                        if(A.Matrix[i][k]!=B.Matrix[i][k]){  
	                            list[x++]=k;  
	                        }  
	                    }  
	                      
	                    for(k=0;k<x;k=k+2){  
	                        if(swapColumns(i,list[k],list[k+1],A)==false){  
	                            System.out.println("无法调整成相同的邻接矩阵！不同构！");  
	                            return ;  
	                        }  
	                        swapRows(list[k],list[k+1],A);  
	                    }  
	                }  
	                break;   
	            }   
	        }  
	    }  
	    System.out.println("经过检测，两图同构！");    
	   return;
	}   
	
}
