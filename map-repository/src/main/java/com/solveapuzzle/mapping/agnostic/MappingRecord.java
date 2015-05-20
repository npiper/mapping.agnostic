package com.solveapuzzle.mapping.agnostic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "MAPPINGRECORD")
public class MappingRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private int id;

	@Column(name = "KEY", nullable = false)
	private String key;
	
	@Column(name = "XMLCONTENT", nullable = false)
	//@Lob
    private String xmlContent;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the xmlContent
	 */
	public String getXmlContent() {
		return xmlContent;
	}

	/**
	 * @param xmlContent the xmlContent to set
	 */
	public void setXmlContent(String xmlContent) {
		this.xmlContent = xmlContent;
	}
}
