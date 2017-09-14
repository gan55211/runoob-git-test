package cn.itcast.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@Table(name = "T_QUYU2")
@XmlRootElement
public class quyu {
	

		@Id
		@Column(name = "C_ID")
		@GeneratedValue
		private Integer id;
		@Column(name = "C_QUYU")
		private String quyu; // 区域
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getQuyu() {
			return quyu;
		}
		public void setQuyu(String quyu) {
			this.quyu = quyu;
		}
	}

