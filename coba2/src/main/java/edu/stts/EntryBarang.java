package edu.stts;

import javax.jdo.annotations.*;

import com.google.apphosting.api.DatastorePb.GetResponse.Entity;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class EntryBarang extends Entity{

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long id;
	
	@Persistent
	private long price;
	
	@Persistent
	private String name;
	
	@Persistent
	private int qty;
	
	public EntryBarang(String name, long price, int qty) {
		this.name = name;
		this.price = price;
		this.qty = qty;
	}
}
