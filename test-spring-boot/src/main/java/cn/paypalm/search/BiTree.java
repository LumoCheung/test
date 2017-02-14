package cn.paypalm.search;

 /** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年6月14日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年6月14日	新建文件.
 * 
 * </pre>
 */
public class BiTree extends Base {

	/**
	 * <p>Description:</p>
	 * @see cn.paypalm.search.Base#searchChild(int[], int) 
	 */ 
	@Override
	public boolean searchChild(int[] t, int key) {
		Node root=null;
		for(int i=0;i<t.length;i++){
			root=insertNode(root, t[i]);
		}
		Node node=search(root, key);
		System.out.println(toString(root));
		for(int i=0;i<t.length;i++){
			root=remove(root, t[i]);
			System.out.println((root==null?"":root.data)+""+root+"//"+toString(root));
		}
		if(node==null)
			return false;
		else
			return true;
	}
	
	class Node{
		int data;
		Node lNode;
		Node rNode;
	}
	
	public Node insertNode(Node root,int key){
		
		Node node=new Node();
		node.data=key;
		if(root==null){
			root=node;
			return root;
		}
		Node n=root;
		while(true){
			
			if(n.data<key&&n.rNode!=null){
				n=n.rNode;
			}
			else if(n.data>=key&&n.lNode!=null){
				n=n.lNode;
			}
			else if(n.data<key){
				n.rNode=node;
				break;
			}
			else {
				n.lNode=node;
				break;
			}
			
		}
		return root;
	}
	
	public Node search(Node root,int key){
		Node n=root;
		while(n!=null){
			if(n.data==key){
				return n;
			}
		    else if(n.data<key){
				n=n.rNode;
			}
			else if(n.data>key){
				n=n.lNode;
			}
		}
		return n;
	}
	
	public Node remove(Node root,int key){
		
		Node n=root;
		Node pre=null;
		while(n!=null){
			if(n.data==key){
				//find
				Node reNode=n;
				if(n.lNode==null){
					n=n.rNode;
				}
				else if(n.rNode==null){
					n=n.lNode;
				}
				else {
					//左子树的最右边的子节点
					n=n.lNode;
					Node t=null;
					for(;n.rNode!=null;n=n.rNode){
						t=n;
					}
					if(t!=null){
						//取出子节点，子节点的左子树赋给父节点的右指针
						t.rNode=n.lNode;
						//
						n.lNode=reNode.lNode;
					}
					n.rNode=reNode.rNode;
				}
				//free remove node
				if(null==pre){
					//root
					root=n;
				}
				else if(reNode==pre.lNode){
					pre.lNode=n;
				}else if(reNode==pre.rNode){
					pre.rNode=n;
				}
				reNode=null;
				break;
			}
		    else if(n.data<key){
		    	pre=n;
				n=n.rNode;
			}
			else if(n.data>key){
				pre=n;
				n=n.lNode;
			}
		}
		
		return root;
	}
	
	public String toString(Node root){
		StringBuilder str=new StringBuilder("[");		
		str=loop(root,str).append("]");
		return str.toString().replace(",]", "]");
	}
	
	private StringBuilder loop(Node node,StringBuilder str){
		if(node!=null){
			loop(node.lNode,str);
			str=str.append(node.data+",");
			loop(node.rNode,str);
		}
		return str;
	}

}
