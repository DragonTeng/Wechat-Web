package com.ilong;
import com.ilong.*;

import java.util.Arrays;
import java.util.Scanner;

public class Test {
	public static final int Max = 100;
	//��λ�ý�������
		public static boolean swapRows(int i,int j,AdjacencyMatrix A){
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
		
		public static boolean swapColumns(int currentLayer,int i,int j,AdjacencyMatrix A){  
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
			
	public static void main(String[] args){ 
		AdjacencyMatrix A = new AdjacencyMatrix() ;
		AdjacencyMatrix B = new AdjacencyMatrix() ;
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
	    int Aweight[] = new int[Max];  
	    int Bweight[] = new int[Max];  
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
	    //qsort(Aweight,A.points,sizeof(Aweight[0]),cmp());//����ϵͳ���������㷨  
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
	    //qsort(Bweight,B.points,sizeof(Bweight[0]),cmp);//����ϵͳ���������㷨  
	    //quickSort(Bweight, 0 , Bweight.length-1);
	    Arrays.sort(Bweight);
	    Arrays.sort(B.weight);
	    
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
	                    swapRows(i,j,A);  
	                }  
	                  
	                //�����н���  
	                if(i!=j){  	                  
	                    if(swapColumns(i,i,j,A)==false){  
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
	                        if(swapColumns(i,list[k],list[k+1],A)==false){  
	                            System.out.println("�޷���������ͬ���ڽӾ��󣡲�ͬ����");  
	                            return ;  
	                        }  
	                        swapRows(list[k],list[k+1],A);  
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
