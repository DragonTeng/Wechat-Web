package com.ilongtest;

import java.util.Arrays;
import java.util.Scanner;

public class AdjacencyMatrix {
	public static final int Max = 100;
	public  int points;
	public  int edges;
	public  int Matrix[][] = new int[Max][Max];
	public  int weight[] = new int[Max];
		
	public  AdjacencyMatrix(){
		
	}
	
	public static AdjacencyMatrix A = new AdjacencyMatrix() ;
	public static AdjacencyMatrix B = new AdjacencyMatrix() ;
	
	//��λ�ý�������
	public static boolean swapRows(int i,int j){
		int k;
		int temp;
		//�����н���
		for(k = 0;k<A.points;k++){
			temp = A.Matrix[i][k];
			A.Matrix[i][k] = A.Matrix[j][k];
			A.Matrix[j][k] = temp;
		}
		//���жȽ���
		temp = A.weight[i];
		A.weight[i] = A.weight[j];
		A.weight[j] = temp;
		return true;
	}
	
	public static boolean swapColumns(int currentLayer,int i,int j){  
	    int k;  
	    //�ж��Ƿ��ܽ���  
	    for(k=0;k<currentLayer;k++){  
	        if(A.Matrix[k][i]!=A.Matrix[k][j]){  
	            //�޷���������Ϊ�������Ӱ����ǰ�����Ľ�����ʶ���ͬ��  
	            return false;   
	        }  
	    }  
	    //�����н���  
	    for(k=0;k<A.points;k++){  
	        int temp;  
	        temp =A.Matrix[k][i];  
	        A.Matrix[k][i]= A.Matrix[k][j];  
	        A.Matrix[k][j]= temp;  
	    }   
	    return true;  
	}   
		
	public static void maoPaoPaiXu(int[] a){
		int temp = 0;
		for(int i=a.length-1;i>=0;i--){
			for(int j=0;j<i;j++){
				if(a[j]>a[j+1]){
					temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}
	}
			
	public static void main(String[] args){ 
		int ta,tb;
	    System.out.println("����������ͼ�Ľ���������������");  
	    Scanner in = new Scanner(System.in);
	    A.points = in.nextInt();
	    B.points = in.nextInt();
	      
	    //�жϵ�һ����Ҫ����  
	    if(A.points!=B.points){  
	        System.out.println("������ͬ����ͬ����");
	        return;  
	    }  
	      
	    System.out.println("�������1��ͼ���ڽӾ���");  
	    A.edges = 0;  
	    B.edges = 0;  
	      
	    //���ڽӾ���ʽ����A��B����  
	    int i,j,k,y;  
	    for(i=0;i<A.points;i++){  
	        for(j=0;j<A.points;j++){  
	            A.Matrix[i][j] = in.nextInt();  
	            if(A.Matrix[i][j]==1){  
	                A.edges++;  
	            }     
	        }  
	    }  
	      
	    System.out.println("�������2��ͼ���ڽӾ���");  
	    for(i=0;i<B.points;i++){  
	        for(j=0;j<B.points;j++){  
	            B.Matrix[i][j] = in.nextInt();  
	            if(B.Matrix[i][j]==1){  
	                B.edges++;  
	            }     
	        }  
	    }  
	      
	    //�жϵڶ�����Ҫ����  
	    if(A.edges!=B.edges){  
	        System.out.println("�ߵ�������ͬ����ͬ����");  
	        return;     
	    }   
	    
	    //��Ϊ���ڽӾ������Աߵ����������ڽӾ����������/2��  
	    A.edges =A.edges/2;  
	    B.edges =B.edges/2;  
	    int Aweight[] = new int[A.points];  
	    int Bweight[] = new int[B.points];  
	    //�жϵ�������Ҫ����  
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
	    maoPaoPaiXu(Aweight);
	    //maoPaoPaiXu(A.weight);	    	    
	    
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
	    maoPaoPaiXu(Bweight);	  	  
	    //maoPaoPaiXu(B.weight);
	    
	    for(k=0;k<A.points;k++){ 	    	
	        if(Aweight[k]!=Bweight[k]){  	        	
	            System.out.println("�ߵĶ�����ͬ����ͬ����");  
	            return;  
	        }  
	    }   
	      
	      
	    //����A�����B   
	    for(i=0;i<B.points;i++){  
	        for(j=i;j<A.points;j++){  
	            //�ҵ�����ͬ  
	            if(B.weight[i] == A.weight[j]){  
	                //�����н���  
	                if(i!=j){  
	                    swapRows(i,j);  
	                }  
	                  
	                //�����н���  
	                if(i!=j){  	                  
	                    if(swapColumns(i,i,j)==false){  
	                        System.out.println("�޷���������ͬ���ڽӾ��󣡲�ͬ����");  
	                        return ;  
	                    }         
	                      
	                    int list[] = new int [100];  
	                    x=0;  
	                    //�жϷ��㶥�������е�λ���Ƿ���ͬ  
	                    for(k=0;k<A.points;k++){//�ҳ�λ�ò�ͬ�ĵ����list   
	                        if(A.Matrix[i][k]!=B.Matrix[i][k]){  
	                            list[x++]=k;  
	                        }  
	                    }  
	                      
	                    for(k=0;k<x;k=k+2){  
	                        if(swapColumns(i,list[k],list[k+1])==false){  
	                            System.out.println("�޷���������ͬ���ڽӾ��󣡲�ͬ����");  
	                            return ;  
	                        }  
	                        swapRows(list[k],list[k+1]);  
	                    }  
	                }  
	                break;   
	            }   
	        }  
	    }  
	    System.out.println("������⣬��ͼͬ����");    
	   return;
	}   
	
}
