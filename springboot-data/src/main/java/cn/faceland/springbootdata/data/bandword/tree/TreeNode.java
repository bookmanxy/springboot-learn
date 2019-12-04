package cn.faceland.springbootdata.data.bandword.tree;

/**
 * @author watermelon
 * @Date 2019-12-02
 * @Description
 */

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

public class TreeNode implements Serializable {
    private int isLast;                 //是否是关键字结束节点
    private String nodeName;            //节点名
    private TreeNode parentNode;        //父节点
    private TreeNode brotherNode;       //兄弟节点
    private TreeNode childNode;         //子节点

    public int getIsLast() {
        return isLast;
    }

    public void setIsLast(int isLast) {
        this.isLast = isLast;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public TreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(TreeNode parentNode) {
        this.parentNode = parentNode;
    }

    public TreeNode getBrotherNode() {
        return brotherNode;
    }

    public void setBrotherNode(TreeNode brotherNode) {
        this.brotherNode = brotherNode;
    }

    public TreeNode getChildNode() {
        return childNode;
    }

    public void setChildNode(TreeNode childNode) {
        this.childNode = childNode;
    }
}
