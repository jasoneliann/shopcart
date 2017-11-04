<%@page import="com.google.appengine.api.datastore.Query.SortDirection"%>
<%@page import="com.google.appengine.api.datastore.FetchOptions"%>
<%@page import="com.google.appengine.api.datastore.Query"%>
<%@page import="com.google.appengine.api.datastore.DatastoreServiceFactory"%>
<%@page import="com.google.appengine.api.datastore.DatastoreService"%>
<%@page import="com.google.appengine.api.datastore.Entity"%>
<%@page import="javax.jdo.PersistenceManager"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.stts.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
</head>
<body>
	<form action="/hello" method="post">
		Nama : <input type="text" name="nama"/>
		<br/>
		Harga : <input type="text" name="harga"/>
		<br/>
		Qty : <input type="text" name="qty"/>
		<br/>
		<input type="submit" value="Insert"/>
	</form>
	
	<%
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	Query query = new Query("kode").addSort("time",SortDirection.DESCENDING);
	List<Entity> barang = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(15));
	if(barang.isEmpty()){
		out.println("<h1>Tidak ada data Di Database</h1>");
	} else{
		
		out.println("<table border=1>");
			out.println("<th>Nama</th>");
			out.println("<th>Price</th>");
			out.println("<th>Qty</th>");
			out.println("<th>Subtotal</th>");
			out.println("<th>Button</th>");
		int total = 0;
		for(Entity row : barang){
			int sub = Integer.parseInt(row.getProperty("harga").toString()) * Integer.parseInt(row.getProperty("qty").toString());
			//int sub = 0;
			out.println("<tr id='"+row.getKey().getId()+"'>");
				out.println("<td class='colNama'>"+row.getProperty("nama").toString()+"</td>");
				out.println("<td class='colHarga'>"+row.getProperty("harga").toString()+"</td>");
				out.println("<td class='colQty'>"+row.getProperty("qty").toString()+"</td>");
				out.println("<td>"+sub+"</td>");
				out.println("<td class='action'><button class='edit' onclick='editClick(this.parentNode.parentNode.id);'>Edit</button>"+"<button class='delete'>Delete</button></td>");
			out.println("</tr>");
			total += sub;
			
		}
		out.println("</table>");
		
		out.println("<h2>Total : " + total +"</h2>");
	}
	%>
	<script type="text/javascript">
		function editClick(id){
			alert("id : "+id);
			//Simpan Variabel
			$nama = $("tr#"+id+" td.colNama").text();
			$harga = $("tr#"+id+" td.colHarga").text();
			$qty = $("tr#"+id+" td.colQty").text();
			//clear
			$("tr#"+id+" td.colNama").text("");
			$("tr#"+id+" td.colHarga").text("");
			$("tr#"+id+" td.colQty").text("");
		}
	</script>
</body>
</html>