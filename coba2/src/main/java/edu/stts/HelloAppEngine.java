package edu.stts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FilePermission;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.datastore.*;
import com.google.cloud.storage.Storage;

import java.util.Date;

public class HelloAppEngine extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		PrintWriter out = resp.getWriter();
		
		String nama = req.getParameter("nama");
		System.out.println("Ini Nama = "+nama);
		String harga = req.getParameter("harga");
		System.out.println("Ini harga = "+harga);
		String qty = req.getParameter("qty");
		System.out.println("Ini qty= "+qty);
		  
		//EntryBarang entry = new EntryBarang(nama.toString(),Long.parseLong(harga),Integer.parseInt(qty));
		//System.out.println("Lolos Entry");
		//Insert Berdasarkan Key = Datetime
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		String tampDateTime = dateFormat.format(date);
		/*
		Key kode = KeyFactory.createKey("Barang", nama);
		Entity entry = new Entity("Barang",kode);
		entry.setProperty("nama",nama);
		entry.setProperty("harga", harga);
		entry.setProperty("qty", qty);
		entry.setProperty("time", tampDateTime);
		*/
		//DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		
		/*Datastore datastore = DatastoreOptions.newBuilder().setProjectId("shoppingcart-182405").
				setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("ShoppingCart-62de2223eed0.json"))).build().getService();*/
		
		Datastore datastore = DatastoreOptions.newBuilder().setProjectId("shoppingcart-182405").
	    		setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("ShoppingCart-c50afc2c491d.json"))).build().getService();
		
		//Query<com.google.cloud.datastore.Entity> query = Query.newEntityQueryBuilder().setKind("Task").build();
		//QueryResults<com.google.cloud.datastore.Entity> results = datastore.run(query);
		Query<Entity> query = Query.newEntityQueryBuilder().setKind("Task").build();
		QueryResults<Entity> results = datastore.run(query);
		
		
		while (results.hasNext()) {
	        Entity entity = results.next();
	        out.println(entity.getKey().getId() + " - " + entity.getString("description") + " - " + (entity.getTimestamp("created").toSqlTimestamp().getYear() + 1900) + "<br/>");        
	    }
		//datastore.put(entry);
		
		System.out.println("Sukses");

		resp.sendRedirect("index.jsp");
  
  }
}