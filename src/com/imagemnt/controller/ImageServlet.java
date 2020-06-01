package com.imagemnt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.imagemnt.dao.ImageDAO;
import com.imagemnt.model.Image;


@WebServlet("/")
@MultipartConfig
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int thresholdSize = 0;
    private ImageDAO imageDAO;
    private final String homeUrl = "/list";
   
    @Override
    public void init() {
    	imageDAO = new ImageDAO();
    }
   
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			doGet(request, response);
		} catch (Exception e) {
			System.out.println("Exception at doGet: "+e);
		}
	}

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getServletPath();
		
		try { 
				switch (action) {
			        case "/insert":
			            insertImage(request, response);
			            break;
			        case "/delete":
			            deleteImage(request, response);
			            break;
			        case "/edit":
			            showEditImage(request, response);
			            break;
			        case "/update":
			            updateImage(request, response);
			            break;
			       case homeUrl:
			    	   listImage(request, response);
			           break;
			        default:
			            listImage(request, response);
			            break;
				}
				
			} catch (SQLException ex) {
	            System.out.println("Exeption Occurred"+ex);
	        }
	}


	private void deleteImage(HttpServletRequest request, HttpServletResponse response) 
			 throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Image image = imageDAO.getImage(id);
		thresholdSize = thresholdSize - Integer.valueOf(image.getSize());
		imageDAO.deleteImage(id);
		response.sendRedirect(request.getContextPath() + homeUrl);
	}

	private void updateImage(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");

        Part filePart = request.getPart("image");
        String temp = filePart.getHeader("content-disposition");
        String image = temp.substring(temp.lastIndexOf("=")+2, temp.length()-1);
        
        
        String size;
        int imageSize = 0;
        if (image.length() < 1) {
        	Image tempImage = imageDAO.getImage(id);
        	image = tempImage.getImage();
        	size = tempImage.getSize();
        	
        } else {
        	size = String.valueOf(filePart.getSize()/1024);
        	imageSize = Integer.parseInt(size);
        }

        Image updateImage = new Image(id, name, size, image);
        
        
        
        if (imageSize > 1024) {
        	request.setAttribute("alertMsg", "Image Size exceeds 1 MB");
        	
        	RequestDispatcher rd=request.getRequestDispatcher("/error.jsp");  
        	rd.include(request, response);
        	
        } else if (imageSize+thresholdSize > 1024 * 10) {
       	 
	       	request.setAttribute("alertMsg", "Total Images Size exceeds 10 MB");
	       	
	       	RequestDispatcher rd=request.getRequestDispatcher("/error.jsp");  
	       	rd.include(request, response);
       	
       } else {
    	   thresholdSize += imageSize;
        	imageDAO.updateImage(updateImage);
        	response.sendRedirect(request.getContextPath() + homeUrl);
        }
 
	}

	private void insertImage(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        Part filePart = request.getPart("image");
     
        String temp = filePart.getHeader("content-disposition");
        String image = temp.substring(temp.lastIndexOf('=')+2, temp.length()-1);
        String name = temp.substring(temp.lastIndexOf('\\') + 1, temp.lastIndexOf("."));
        
        String size = String.valueOf(filePart.getSize()/1024);
        int imageSize = Integer.parseInt(size);
    
        Image newImage = new Image(name, size, image);

        if (imageSize > 1024) {
    
        	request.setAttribute("alertMsg", "Image Size exceeds 1 MB");
        	RequestDispatcher rd=request.getRequestDispatcher("/error.jsp");  
        	rd.include(request, response);
      	
        } else if (imageSize+thresholdSize > 1024 * 10) {
        	 
        	request.setAttribute("alertMsg", "Total Images Size exceeds 10 MB");
        	
        	RequestDispatcher rd=request.getRequestDispatcher("/error.jsp");  
        	rd.include(request, response);
        	
        } else {
        	thresholdSize += imageSize;
        	imageDAO.saveImage(newImage);
        	response.sendRedirect(request.getContextPath() + homeUrl);
        }
        
        
	}

	private void showEditImage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Image existingImage = imageDAO.getImage(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("image-form.jsp");
        request.setAttribute("image", existingImage);
        dispatcher.forward(request, response);
		
	}

	private void listImage(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		
		List<Image> imageList = imageDAO.getAllImage();
		request.setAttribute("imageList", imageList);
		request.setAttribute("totalSize", thresholdSize);
		RequestDispatcher dispatcher = request.getRequestDispatcher("image-list.jsp");
		dispatcher.forward(request, response);
	}


	
	
}
