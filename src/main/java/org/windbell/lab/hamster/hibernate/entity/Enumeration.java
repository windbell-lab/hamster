package org.windbell.lab.hamster.hibernate.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.engine.internal.Cascade;

@javax.persistence.Entity
public class Enumeration extends Mode {
	private static final long serialVersionUID = -1452599811639787010L;
	/**
	 * 枚举编码
	 */
	private String enumCode;
	/**
	 * 枚举名
	 */
	private String enumName;
	/**
	 * 枚举显示名
	 */
	private String displayName;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 排序编码
	 */
	private String sortNumber;
	/**
	 * 枚举值
	 */
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,targetEntity=EnumerationValue.class)
	@JoinColumn(name="enumeration")
	private List<EnumerationValue> enumerationValues;
	/**
	 * 枚举类型
	 */
	private Integer enumType = Integer.valueOf(0);

	public Enumeration() {
		super();
	}

	public String getEnumCode() {
		return enumCode;
	}

	public void setEnumCode(String enumCode) {
		this.enumCode = enumCode;
	}

	public String getEnumName() {
		return enumName;
	}

	public void setEnumName(String enumName) {
		this.enumName = enumName;
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

	public List<EnumerationValue> getEnumerationValues() {
		return enumerationValues;
	}

	public void setEnumerationValues(List<EnumerationValue> enumerationValues) {
		this.enumerationValues = enumerationValues;
	}

	public Integer getEnumType() {
		return enumType;
	}

	public void setEnumType(Integer enumType) {
		this.enumType = enumType;
	}

}
