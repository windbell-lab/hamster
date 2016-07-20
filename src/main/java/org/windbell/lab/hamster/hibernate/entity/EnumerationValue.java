package org.windbell.lab.hamster.hibernate.entity;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@javax.persistence.Entity
public class EnumerationValue extends Mode {
	private static final long serialVersionUID = 7372533322687647450L;
	/**
	 * 枚举值编号
	 */
	private String vauleCode;
	/**
	 * 枚举值名称
	 */
	private String valueName;
	/**
	 * 枚举值显示名称
	 */
	private String displayName;
	/**
	 * 枚举描述
	 */
	private String description;
	/**
	 * 排序编码
	 */
	private String sortNumber;
	/**
	 * 所属枚举
	 */
	@ManyToOne(cascade=CascadeType.ALL,targetEntity=Enumeration.class)
	@JoinColumn(name="enumeration")
	private Enumeration enumeration;

	public EnumerationValue() {
		super();
	}

	public String getVauleCode() {
		return vauleCode;
	}

	public void setVauleCode(String vauleCode) {
		this.vauleCode = vauleCode;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(String sortNumber) {
		this.sortNumber = sortNumber;
	}

	public Enumeration getEnumeration() {
		return enumeration;
	}

	public void setEnumeration(Enumeration enumeration) {
		this.enumeration = enumeration;
	}

}
