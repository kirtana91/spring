package app16a.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app15.bean.Product;
import app16a.form.ProductForm;

@WebServlet("/")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();

		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex + 1);
		// execute an action
		if (action.equals("product_input.action")) {
			// no action class, there is nothing to be done
		} else if (action.equals("product_save.action")) {
			// create form
			ProductForm productForm = new ProductForm();
			// populate action properties
			productForm.setName(request.getParameter("name"));
			productForm.setDescription(request.getParameter("description"));
			productForm.setPrice(request.getParameter("price"));

			// create model
			Product product = new Product();
			product.setName(productForm.getName());
			product.setDescription(productForm.getDescription());
			try {
				product.setPrice(Float.parseFloat(productForm.getPrice()));
			} catch (NumberFormatException e) {
			}

			request.setAttribute("product", product);
		}
		String dispatchUrl = null;
		if (action.equals("product_input.action")) {
			dispatchUrl = "/WEB-INF/jsp/ProductForm.jsp";
		} else if (action.equals("product_save.action")) {
			dispatchUrl = "/WEB-INF/jsp/ProductDetails.jsp";
		}
		if (dispatchUrl != null) {
			RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
			rd.forward(request, response);
		}
	}
}