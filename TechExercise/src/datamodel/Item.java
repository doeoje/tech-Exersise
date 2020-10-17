package datamodel;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") // specify the column name. Without it, it will use method name
	private Integer id;

	@Column(name = "itemname")
	private String itemname;
	
	@Column(name = "cost")
	private Double cost;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "paid")
	private Boolean paid;
	
	
	public Item(Integer id, String itemname, Double cost, Date date) {
		this.id =id;
		this.itemname = itemname;
		this.cost = cost;
		this.date = date;
		this.paid = false;
	}
	public Item( String itemname, Double cost, Date date) {
		this.itemname = itemname;
		this.cost = cost;
		this.date = date;
		this.paid = false;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Boolean getPaid() {
		return paid;
	}
	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	


}
