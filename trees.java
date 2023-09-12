package dsa;

import java.util.*;

public class trees {
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }
    static class BinaryTree{
        static int idx=-1;
        public static Node builttree(int n[]){
            idx++;
            if(n[idx]==-1){
                return null;
            }
            Node nn=new Node(n[idx]);
            nn.left=builttree(n);
            nn.right=builttree(n);
            return nn;

        }
        public static void preoreder(Node root){
            if(root==null){
                return;
            }
            System.out.println(root.data);
            preoreder(root.left);
            preoreder(root.right);
        }
        public static void inorder(Node root){
            if(root==null){
                return;
            }
            inorder(root.left);
            System.out.println(root.data);
            inorder(root.right);


        }
        public static void postorder(Node root){
            if(root==null){
                return;
            }
            postorder(root.left);
            postorder(root.right);
            System.out.println(root.data);
        }
        public static void levelorder(Node root){
            if(root==null){
                return;
            }
            Queue<Node> q1=new LinkedList<>();
            q1.add(root);
            q1.add(null);
            while(!q1.isEmpty()){
                Node c=q1.remove();
                if(c==null){
                    System.out.println();
                    if(q1.isEmpty()){
                        break;
                    }
                    else{
                        q1.add(null);
                    }
                }
                else{
                    System.out.print(c.data);
                    if(c.left!=null){
                        q1.add(c.left);
                    }
                    if(c.right!=null){
                        q1.add(c.right);
                    }
                }
            }
        }
        public static int height(Node root){
            if(root==null){
                return 0;
            }
            int lh=height(root.left);
            int rh=height(root.right);
            return Math.max(lh,rh)+1;
        }
        public static int count(Node root){
            if(root==null){
                return 0;
            }
            int lc=count(root.left);
            int rc=count(root.right);
            return lc+rc+1;
        }
        public static int sum(Node root){
            if (root == null) {
                return 0;
            }
            int lfs=sum(root.left);
            int rs=sum(root.right);
            return lfs+rs+root.data;
        }
        public static int diameter1(Node r){
            if (r==null){
                return 0;
            }
            int ld=diameter1(r.left);
            int rd=diameter1(r.right);
            int lh=height(r.left);
            int rh=height(r.right);
            return Math.max(rh+lh+1,Math.max(ld,rd));
        }
    }
    static class info{
        int dia;
        int hei;
        info(int dia,int hei){
            this.dia=dia;
            this.hei=hei;
        }
    }
    public static info diameter(Node r){
        if(r==null){
            return new info(0,0);
        }
        info ls=diameter(r.left);
        info rs=diameter(r.right);
        int dia=Math.max(Math.max(ls.dia,rs.dia),ls.hei+rs.hei+1);
        int hei=Math.max(ls.hei,rs.hei)+1;
        return new info(dia,hei);

    }
    public static boolean isidentical(Node r,Node s){
        if(r==null&&s==null){
            return true;
        }
        if(r==null||s==null||r.data!=s.data){
            return false;
        }
        if(!isidentical(r.left,s.left)){
            return false;
        }
        if(!isidentical(r.right,s.right)){
            return false;
        }
        return true;
    }
    public static boolean issubtree(Node r,Node s){
        if(r==null){
            return false;
        }
        if(r.data==s.data){
           if(isidentical(r,s)){
               return true;
           }

        }
        return issubtree(r.left,s)||issubtree(r.right,s);

    }
    static class i{
        Node n;
        int id;
        i(int id,Node n){
            this.id=id;
            this.n=n;

        }
    }
    public static void topview(Node r){
        Queue<i> q1=new LinkedList<>();
        HashMap<Integer,Node> h1=new HashMap<>();
        q1.add(new i(0,r));
        q1.add(null);
        int min=0,max=0;
        while(!q1.isEmpty()){
            i c= q1.remove();
            if(c==null){
                if(!q1.isEmpty()){
                    q1.add(null);
                }
                else{
                    return;
                }
            }
            else{
                if(!h1.containsKey(c.id)){
                    h1.put(c.id,c.n);
                }
                if(c.n.left!=null){
                    q1.add(new i(c.id-1,c.n.left));
                    min=Math.min(min,c.id-1);
                }
                if(c.n.right!=null){
                    q1.add(new i(c.id+1,c.n.right));
                    max=Math.max(max,c.id+1);
                }
            }
        }
        for(int i=min;i<=max;i++){
            System.out.println(h1.get(i).data);
        }

    }
    public static void kthlev(Node r,int l){
        Queue<Node> q1=new LinkedList<>();
        q1.add(r);
        q1.add(null);
        while(!q1.isEmpty()){
            Node c=q1.remove();
            if(c==null){
                l--;
                if(q1.isEmpty()){
                    break;
                }
                else{
                    q1.add(null);
                }

            }
            else{
                if(l==1) {
                    System.out.println(c.data);
                }
                if(c.right!=null){
                    q1.add(c.right);
                }
                if(c.left!=null){
                    q1.add(c.left);
                }
            }
        }
    }
    public static void ktlrv2(Node r,int k,int rl){
        if(r==null){
            return;
        }
        if(k==rl){
            System.out.println(r.data);
            return ;
        }
       ktlrv2(r.left,k,rl+1);
        ktlrv2(r.right,k,rl+1);
    }
    public static boolean get(Node r,int n1,ArrayList<Node> a1){
        a1.add(r);
        if(r==null){
            return false;
        }
        if(r.data==n1){
            return true;
        }
        if(get(r.left,n1,a1)||get(r.right,n1,a1)){
            return true;
        }
        a1.remove(a1.size()-1);
        return false;
    }
    public static int lansister(Node r,int n1,int n2){
        ArrayList<Node> a1=new ArrayList<>();
        ArrayList<Node> a2=new ArrayList<>();
        get(r,n1,a1);
        get(r,n2,a2);
        int i=0;
        for(;i<a1.size()&&i<a2.size();i++){
            if(a1.get(i)!=a2.get(i)){
                break;
            }
        }
        return a1.get(i-1).data;
    }
    public static Node las(Node r,int n1,int n2){
        if(r==null||r.data==n1||r.data==n2){
            return r;
        }
        Node lf=las(r.left,n1,n2);
        Node lr=las(r.right,n1,n2);
        if(lf==null){
            return lr;
        }
        if(lr==null){
            return lf;
        }
        return r;
    }
    public static int dist(Node r,int n){
        if (r == null) {
            return -1;
        }
        if(r.data==n){
            return 0;
        }
        int dl=dist(r.left,n);
        int rl=dist(r.right,n);
        if(dl==-1&&rl==-1){
            return -1;
        }
        else if(dl==-1){
            return rl+1;
        }
        else{
            return dl+1;
        }

    }
    public static int mindist(Node r,int n1,int n2){
        Node las=las(r,n1,n2);
        int fn1=dist(r,n1);
        int fn2=dist(r,n2);
        return fn1+fn2;
    }
    public static void main(String args[]){
        int n[]={1,2,4,-1,-1,5,-1,-1,3,6,-1,-1,7,-1,-1};
        BinaryTree tree=new BinaryTree();
        Node f=tree.builttree(n);
//        int n1[]={2,4,-1,-1,5,-1,-1};
//        Node s=tree.builttree(n1);
        Node s=new Node(2);
        s.left=new Node(4);
        s.right=new Node(5);
        //System.out.println(diameter(f).dia);
        //System.out.println(issubtree(f,s));
        //topview(f);
//        kthlev(f,3);
//        ktlrv2(f,3,1);
        //System.out.println(las(f,4,5).data);
        System.out.println(mindist(s,4,5));
    }

}
