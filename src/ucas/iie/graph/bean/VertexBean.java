package ucas.iie.graph.bean;

public class VertexBean {	//��v i j�� ��ʾͼ�Ķ���i��label��j   
    /** ͼ�����ݸ�ʽ 
     *  t # 0 ��ʾ��0��ͼ 
        v 0 2 
        v 1 2 
        v 2 2 
        v 3 2 
        v 4 2 
        e 0 1 2 
        e 1 2 2 
        e 2 3 2 
        e 3 4 2 
     */  
    private String vertex;  
    private String label;  
    public String getVertex() {  
        return vertex;  
    }  
    public void setVertex(String vertex) {  
        this.vertex = vertex;  
    }  
    public String getLabel() {  
        return label;  
    }  
    public void setLabel(String label) {  
        this.label = label;  
    }  

}
