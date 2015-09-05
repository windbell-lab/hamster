package org.windbell.lab.hamster.hibernate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
@MappedSuperclass
public class BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Version
	private Integer version;
	private String creater;
	private String updater;
	@Column(columnDefinition="timestamp default CURRENT_TIMESTAMP",nullable=false,updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@Generated(value = GenerationTime.INSERT)
	private Date createTime;
	@Column(columnDefinition="timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@Generated(value = GenerationTime.ALWAYS)
	private Date updateTime;
	
	public BaseEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	private void setVersion(Integer version) {
		this.version = version;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
