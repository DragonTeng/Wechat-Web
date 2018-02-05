package com.ilong.com;

import java.io.BufferedReader;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.util.ArrayList;  
  
import ucas.iie.graph.bean.EdgeBean;  
import ucas.iie.graph.bean.GraphBean;  
import ucas.iie.graph.bean.VertexBean;  

public class IsomorphismImpl {
	 private ArrayList<GraphBean> query_g;// ��ѯ����ͼ  
	    private ArrayList<GraphBean> mydb_g;// ͼ��������  
	  
	    public IsomorphismImpl() {  
	        query_g = new ArrayList<GraphBean>();  
	        mydb_g = new ArrayList<GraphBean>();  
	    }  
	  
	    /** 
	     *  
	     * @param query 
	     * @param db 
	     * @return ���س�ʼ�ľ���M0 
	     */  
	    public int[][] getMatrixM(GraphBean query, GraphBean db) {  
	        int row = query.getvList().size();  
	        int column = db.getvList().size();  
	        int[][] M0 = new int[row][column];  
	        // System.out.println("M0:");  
	        for (int i = 0; i < row; i++) {  
	            for (int j = 0; j < column; j++) {  
	                String vi = query.getvList().get(i).getVertex();  
	                String vj = db.getvList().get(j).getVertex();  
	                if (db.getVDegree(vj) >= query.getVDegree(vi))  
	                    M0[i][j] = 1;  
	                else  
	                    M0[i][j] = 0;  
	                // System.out.print(M0[i][j] + " ");  
	            }  
	            // System.out.println("");  
	        }  
	        return M0;  
	    }  
	  
	    public ArrayList<GraphBean> getQuery_g() {  
	        return query_g;  
	    }  
	  
	    public void setQuery_g(ArrayList<GraphBean> query_g) {  
	        this.query_g = query_g;  
	    }  
	  
	    public ArrayList<GraphBean> getMydb_g() {  
	        return mydb_g;  
	    }  
	  
	    public void setMydb_g(ArrayList<GraphBean> mydb_g) {  
	        this.mydb_g = mydb_g;  
	    }  
	  
	    /** 
	     *  
	     * @param queryFile 
	     *            ��ѯ��ͼ��·�� 
	     * @param dbFile 
	     *            ͼ��������·�� 
	     * @throws IOException 
	     */  
	    public void initGraphDB(String queryFile, String dbFile) throws IOException {  
	        // ��ȡ��ѯ��ͼ������  
	        BufferedReader q_br = new BufferedReader(new InputStreamReader(  
	                new FileInputStream(queryFile)));  
	        String lineData = q_br.readLine();  
	        GraphBean qgb;  
	        if (lineData.startsWith("t #")) {// ��һ����ͼ  
	            qgb = new GraphBean();  
	            while ((lineData = q_br.readLine()) != null) {  
	                if (lineData.startsWith("t #")) {  
	                    this.query_g.add(qgb);  
	                    qgb = new GraphBean();  
	                    continue;  
	                } else if (lineData.startsWith("v")) { // ����  
	                    String vs[] = lineData.split(" ");  
	                    VertexBean vb = new VertexBean();  
	                    vb.setVertex(vs[1]);  
	                    vb.setLabel(vs[2]);  
	                    qgb.getvList().add(vb);  
	                } else { // ��  
	                    String es[] = lineData.split(" ");  
	                    EdgeBean eb = new EdgeBean();  
	                    eb.setVertex_i(es[1]);  
	                    eb.setVertex_j(es[2]);  
	                    eb.setLabel_e(es[3]);  
	                    qgb.geteList().add(eb);  
	                }  
	            }  
	        }  
	  
	        // ��ȡͼ����  
	        BufferedReader db_br = new BufferedReader(new InputStreamReader(  
	                new FileInputStream(dbFile)));  
	        lineData = db_br.readLine();  
	        GraphBean dbgb;  
	        if (lineData.startsWith("t #")) {//  
	            dbgb = new GraphBean();  
	            while ((lineData = db_br.readLine()) != null) {  
	                if (lineData.startsWith("t #")) {  
	                    this.mydb_g.add(dbgb);  
	                    dbgb = new GraphBean();  
	                    continue;  
	                } else if (lineData.startsWith("v")) { // ����  
	                    String vs[] = lineData.split(" ");  
	                    VertexBean vb = new VertexBean();  
	                    vb.setVertex(vs[1]);  
	                    vb.setLabel(vs[2]);  
	                    dbgb.getvList().add(vb);  
	                } else if (lineData.startsWith("e")) { // ��  
	                    String es[] = lineData.split(" ");  
	                    EdgeBean eb = new EdgeBean();  
	                    eb.setVertex_i(es[1]);  
	                    eb.setVertex_j(es[2]);  
	                    eb.setLabel_e(es[3]);  
	                    dbgb.geteList().add(eb);  
	                }  
	            }  
	        }  
	    }  
	  
	    /** 
	     * ���رߵ�label 
	     * @param i 
	     * @param j 
	     * @param ebList 
	     * @return 
	     */  
	    public String getLabel(String i, String j, ArrayList<EdgeBean> ebList) {  
	        for (int k = 0; k < ebList.size(); k++) {  
	            EdgeBean eb = ebList.get(k);  
	            String vi = eb.getVertex_i();  
	            String vj = eb.getVertex_j();  
	            if (i.equals(vi) && j.equals(vj))  
	                return eb.getLabel_e();  
	            else if (j.equals(vi) && i.equals(vj))  
	                return eb.getLabel_e();  
	        }  
	        return null;  
	    }  
	  
	    public boolean isIsomorphism(GraphBean subgraph, GraphBean graphdb) {  
	        int M0[][] = getMatrixM(subgraph, graphdb);  
	        int MA[][] = subgraph.getMatrix();  
	        int MB[][] = graphdb.getMatrix();  
	        ArrayList<EdgeBean> ebq = subgraph.geteList();  
	        ArrayList<EdgeBean> ebdb = graphdb.geteList();  
	        // �������MA[i][x] = 1 ==> ����y M[x][y]MB[y][j] = 1 ����M0[i][j] = 0  
	        for (int i = 0; i < subgraph.getvList().size(); i++) {  
	            for (int j = 0; j < graphdb.getvList().size(); j++) {  
	                if (M0[i][j] == 1) {  
	                    String ilabel = subgraph.getvList().get(i).getLabel();  
	                    String jlabel = graphdb.getvList().get(j).getLabel();  
	                    if (ilabel.equals(jlabel)) {  
	                        for (int x = 0; x < subgraph.getvList().size(); x++) {  
	                            boolean tag = false;  
	                            if (MA[i][x] == 1) {  
	                                String label_ix = getLabel(String.valueOf(i),  
	                                        String.valueOf(x), ebq);  
	                                for (int y = 0; y < graphdb.getvList().size(); y++) {  
	                                    if (M0[x][y] * MB[y][j] == 1) {  
	                                        String label_yj = getLabel(  
	                                                String.valueOf(y),  
	                                                String.valueOf(j), ebdb);  
	                                        if (label_ix.equals(label_yj)) {///�Ƚ϶���֮��ı��ϵı�ǩ�����Ƿ���ȡ�  
	                                            tag = true;  
	                                            break;  
	                                        }  
	                                    }  
	                                }// break  
	                                if (!tag) {  
	                                    M0[i][j] = 0;  
	                                    break;  
	                                }  
	                            }  
	                        }// break  
	                    } else {// if(ilabel.equals(jlabel))  
	                        M0[i][j] = 0;  
	                    }  
	                }// if (M0[i][j] == 1)  
	            }  
	        }  
	        // System.out.println("M':");  
	        for (int i = 0; i < subgraph.getvList().size(); i++) {  
	            int sumi = 0;  
	            for (int j = 0; j < graphdb.getvList().size(); j++) {  
	                // System.out.print(M0[i][j] + " ");  
	                sumi += M0[i][j];  
	            }  
	            if (sumi == 0) {  
	                // System.out.println("M0��һ��Ԫ�ض���0");  
	                return false;  
	            }// ��һ�е�Ԫ�ض���0��ֱ���˳�  
	                // System.out.println();  
	        }  
	        int raw = subgraph.getvList().size();  
	        int col = graphdb.getvList().size();  
	        int F[] = new int[col];// F[i] = 1 ,��ʾ��i���Ѿ��ù���  
	        int H[] = new int[raw];// H[d] = k ,��ʾ��d��ѡ���ǵ�k��  
	        int d = 0;// M0�ĵ�d��  
	        int k = 0;// M0�ĵ�k��  
	        int[][][] matrixList = new int[raw][][];// ������¼ÿ��d����Ӧ��M0  
	        for (int i = 0; i < F.length; i++) {  
	            F[i] = -1;  
	        }  
	        for (int i = 0; i < H.length; i++) {  
	            H[i] = -1;  
	        }  
	  
	        // //////////////////////////////  
	        while (true) {  
	  
	            if (H[d] == -1) {  
	                k = 0;  
	                matrixList[d] = M0;  
	            } else {// ����  
	                k = H[d] + 1;  
	                F[H[d]] = -1;  
	                M0 = matrixList[d];  
	  
	            }  
	  
	            while (k < col) {// Ҳ����M0����  
	                if (M0[d][k] == 1 && F[k] == -1) {// ���������ҵ�����������������ڻ��ݵ�ʱ���ٱ���  
	                    break;  
	                }  
	                k++;  
	            }  
	  
	            if (k == col) {// ��d����û��������������,���ݵ���һ��  
	                H[d] = -1;  
	                d--;  
	            } else {// M0[d][k]=1,��d�е�����Ԫ�ض�Ϊ0  
	                for (int j = 0; j < col; j++) {  
	                    M0[d][j] = 0;  
	                }  
	                M0[d][k] = 1;  
	                H[d] = k;  
	                F[k] = 1;  
	                d++;  
	            }  
	  
	            if (d == -1) {  
	                // System.out.println("��ͬ��");  
	                return false;  
	            }  
	  
	            if (d == raw) {// ���ҵ���һ��M0�������������֤  
	                if (this.isTrueFor(MA, MB, M0)) {// ��������  
	                    // System.out.println("ͬ��");  
	                    return true;  
	                } else {// ����  
	                    d = raw - 1;  
	                }  
	  
	            }// if  
	        }// while  
	    }  
	  
	    /** 
	     * for all element int MA[i][j]=1 ==> MC=M*{(M*MB)^T},MC[i][j]=1 
	     *  
	     * @param MA 
	     * @param MB 
	     * @param M 
	     * @return 
	     */  
	    public boolean isTrueFor(int[][] MA, int[][] MB, int M[][]) {  
	        boolean flag = true;  
	        int raw = M.length;  
	        int column = MB[0].length;  
	        int tmp[][] = new int[raw][column];// tmp[][]=M*MB  
	        for (int i = 0; i < raw; i++) {// raws  
	            for (int j = 0; j < column; j++) {// columns  
	                for (int k = 0; k < M[0].length; k++) {  
	                    tmp[i][j] += M[i][k] * MB[k][j];  
	                }  
	            }  
	        }  
	        int tmp_t[][] = new int[column][raw];// ת��  
	        for (int i = 0; i < raw; i++) {// raws  
	            for (int j = 0; j < column; j++) {// columns  
	                tmp_t[j][i] = tmp[i][j];  
	            }  
	        }  
	        int MC[][] = new int[MA.length][MA[0].length];  
	        // System.out.println("MC:");  
	        for (int i = 0; i < MA.length; i++) {// raws  
	            for (int j = 0; j < MA[0].length; j++) {// columns  
	                for (int k = 0; k < M[0].length; k++) {  
	                    MC[i][j] += M[i][k] * tmp_t[k][j];  
	                }  
	                // System.out.print(MC[i][j] + " ");  
	            }  
	            // System.out.println();  
	        }  
	        for (int i = 0; i < MA.length; i++) {// raws  
	            for (int j = 0; j < MA[0].length; j++) {// columns  
	                if (MA[i][j] == 1) {  
	                    if (MC[i][j] == 1)  
	                        continue;  
	                    else  
	                        return false;  
	                }  
	            }  
	  
	        }  
	        return flag;  
	    }  
	  
	    public static void main(String[] args) {  
	        IsomorphismImpl ii = new IsomorphismImpl();  
	        String queryFile = "C:\\Users\\Fernando\\Desktop\\Q24.my";  
	        String dbFile = "C:\\Users\\Fernando\\Desktop\\mygraphdb.data";  
	        try {  
	            ii.initGraphDB(queryFile, dbFile);  
	            ArrayList<GraphBean> query_g = ii.getQuery_g();  
	            System.out.println("��ͼ(size)��" + query_g.size());  
	            ArrayList<GraphBean> db_g = ii.getMydb_g();  
	            System.out.println("��ͼ(size)��" + db_g.size());  
	            for (int i = 0; i < query_g.size(); i++) {  
	                for (int j = 0; j < db_g.size(); j++) {  
	                    GraphBean tq = query_g.get(i);  
	                    GraphBean tdb = db_g.get(j);  
	                    if (ii.isIsomorphism(tq, tdb)) {  
	                        System.err.println("t # " + i + " ��  T # " + j + " ͬ��");  
	                    }  
	                }  
	            }  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	    }  
}
