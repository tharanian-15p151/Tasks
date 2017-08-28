package tharan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean flag=true;
		Item item1=new Item();
		Item item2=new Item();
		Item item3=new Item();
		Item item4=new Item();
		
		item1.setProductName("Soap");
		item1.setPrice(50);
		item1.setQuantity(5);
		item1.setManufacturingDate("26.07.2007");
		
		item2.setProductName("Sampoo");
		item2.setPrice(10);
		item2.setQuantity(20);
		item2.setManufacturingDate("27.02.2008");
		
		item3.setProductName("Paste");
		item3.setPrice(50);
		item3.setQuantity(10);
		item3.setManufacturingDate("25.06.2002");
		
		
		item4.setProductName("Brush");
		item4.setPrice(25);
		item4.setQuantity(10);
		item4.setManufacturingDate("24.06.2010");
		
		SessionFactory sf=new Configuration().configure().buildSessionFactory();
		Session se=sf.openSession();
		se.beginTransaction();
		System.out.println();
		se.save(item1);
		se.save(item2);
		se.save(item3);
		se.save(item4);
		Login log=new Login();
		System.out.println("enter the username:");
		log.setName(br.readLine());
		System.out.println("Enter the Password");
		log.setPassword(br.readLine());
		if(Authentication.check(log))
		{
		System.out.println("Login success");
		while(flag)
		{
		System.out.println("Press 1.Add 2.Update 3.Delete 4.Retrive 5.View all");
		int ch;
		ch=Integer.parseInt(br.readLine());
		
		switch(ch)
		{
		case 1:
			Item item5=new Item();
			System.out.println("Enter the product_Name");
			item5.setProductName(br.readLine());
			System.out.println("Enter the price");
			item5.setPrice(Integer.parseInt(br.readLine()));
			System.out.println("Enter the Quantity");
			item5.setQuantity(Integer.parseInt(br.readLine()));
			System.out.println("Enter the Manufacturing_Date");
			item5.setManufacturingDate(br.readLine());
			se.save(item5);
			break;
		case 2:
			System.out.println("Enter the Id to update");
			int c=Integer.parseInt(br.readLine());
			Item it=se.get(Item.class, c);
			System.out.println("Enter the product_Name");
			it.setProductName(br.readLine());
			System.out.println("Enter the price");
			it.setPrice(Integer.parseInt(br.readLine()));
			System.out.println("Enter the Quantity");
			it.setQuantity(Integer.parseInt(br.readLine()));
			System.out.println("Enter the Manufacturing_Date");
			it.setManufacturingDate(br.readLine());
			se.update(it);
			break;
			
		case 3:
			System.out.println("Enter the id to delete");
			c=Integer.parseInt(br.readLine());
			Item it1=se.get(Item.class, c);
			se.delete(it1);
			break;
			
		case 4:
			System.out.println("Enter the id to Retrive");
			c=Integer.parseInt(br.readLine());
			Item it2=se.get(Item.class, c);
			System.out.println("PRODUCT_ID"+"  "+"PRODUCT_NAME"+" "+"PRODUCT_PRICE"+" "+"PRODUCT_QUANTITY"+" "+"PRODUCT_MANUFACTURINGDATE");
			System.out.println(it2.getId()+"  "+it2.getProductName()+"  "+it2.getPrice()+"  "+it2.getQuantity()+"  "+it2.getManufacturingDate());
			break;
		case 5:
			Query query=se.createQuery("from Item");
			List<Item> it3=query.getResultList();
			System.out.println("PRODUCT_ID"+"  "+"PRODUCT_NAME"+" "+"PRODUCT_PRICE"+" "+"PRODUCT_QUANTITY"+" "+"PRODUCT_MANUFACTURINGDATE");
			for(Item each:it3)
			{
				System.out.println(each.getId()+"       "+each.getProductName()+"        "+each.getPrice()+"       " +each.getQuantity()+"           "+each.getManufacturingDate());
			}
			break;
		default:
			System.out.println("wrong entery");
			flag=false;
		}
		}
		}
		else
		{
			System.out.println("Invalid");
		}
		se.getTransaction().commit();
		se.close();

	}
}
