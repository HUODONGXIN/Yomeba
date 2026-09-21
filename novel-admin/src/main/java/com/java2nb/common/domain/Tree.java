package com.java2nb.common.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * tree TODO <br>
 * 
 * @author xiongxy
 * 
 */
public class Tree<T> {
	/**
	 * ノードID
	 */
	private String id;
	/**
	 * ノードに表示するテキスト
	 */
	private String text;
	/**
	 * ノードの状態。open closed
	 */
	private Map<String, Object> state;
	/**
	 * ノードが選択されているかどうか true false
	 */
	private boolean checked = false;
	/**
	 * ノードの属性
	 */
	private Map<String, Object> attributes;

	/**
	 * ノードの子ノード
	 */
	private List<Tree<T>> children = new ArrayList<Tree<T>>();

	/**
	 * 親ID
	 */
	private String parentId;
	/**
	 * 親ノードを持つかどうか
	 */
	private boolean hasParent = false;
	/**
	 * 子ノードを持つかどうか
	 */
	private boolean hasChildren = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Map<String, Object> getState() {
		return state;
	}

	public void setState(Map<String, Object> state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<Tree<T>> getChildren() {
		return children;
	}

	public void setChildren(List<Tree<T>> children) {
		this.children = children;
	}

	public boolean isHasParent() {
		return hasParent;
	}

	public void setHasParent(boolean isParent) {
		this.hasParent = isParent;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setChildren(boolean isChildren) {
		this.hasChildren = isChildren;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Tree(String id, String text, Map<String, Object> state, boolean checked, Map<String, Object> attributes,
			List<Tree<T>> children, boolean isParent, boolean isChildren, String parentID) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.checked = checked;
		this.attributes = attributes;
		this.children = children;
		this.hasParent = isParent;
		this.hasChildren = isChildren;
		this.parentId = parentID;
	}

	public Tree() {
		super();
	}

	@Override
	public String toString() {

		return JSON.toJSONString(this);
	}

}