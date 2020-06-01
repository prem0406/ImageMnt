package com.imagemnt.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.imagemnt.model.Image;
import com.imagemnt.util.HibernateUtil;

public class ImageDAO {
	
	public void saveImage(Image image) {
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory(Image.class).openSession()){
			//start a transaction
			transaction = session.beginTransaction();
			
			//save the Image object
			session.save(image);
			
			//commit transaction
			transaction.commit();
		} catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}
	
	public void updateImage(Image image) {
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory(Image.class).openSession()){
			//start a transaction
			transaction = session.beginTransaction();
			
			//update the image object
			session.update(image);
			
			//commit transaction
			transaction.commit();
		} catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}
	
	public void deleteImage(int id) {
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory(Image.class).openSession()){
			//start a transaction
			transaction = session.beginTransaction();
			
			//delete the image object
			Image image = session.get(Image.class, id);
			if (image != null) {
				session.delete(image);
				System.out.println("Image Deleted");
			}
			
			//commit transaction
			transaction.commit();
			
		} catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}
	
	public Image getImage(int id) {
		Transaction transaction = null;
		Image image = null;
		
		try (Session session = HibernateUtil.getSessionFactory(Image.class).openSession()){
			//start a transaction
			transaction = session.beginTransaction();
			
			//getting image 
			image = session.get(Image.class, id);
			
			
			//commit transaction
			transaction.commit();
			
		} catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
		return image;
	}
	
	@SuppressWarnings("unchecked")
	public List<Image> getAllImage(){
		Transaction transaction = null;
		List<Image> imageList = null;
		
		try (Session session = HibernateUtil.getSessionFactory(Image.class).openSession()){
			//start a transaction
			transaction = session.beginTransaction();
			
			//getting image 
			imageList = session.createQuery("from Image").getResultList();
			
			
			//commit transaction
			transaction.commit();
			
		} catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
		return imageList;
	}
	
}
