package com.mycompany.assigmentweb.controller;

import com.mycompany.assigmentweb.model.FileBucket;
import com.mycompany.assigmentweb.model.OrderDetails;
import com.mycompany.assigmentweb.model.Product;
import com.mycompany.assigmentweb.service.ProductService;
import com.mycompany.assigmentweb.util.FileValidator;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController {

    private static String UPLOAD_LOCATION = "/AssigmentWeb/src/main/webapp/static/photo/";

    @Autowired
    ProductService productService;

    @Autowired
    FileValidator fileValidator;

    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    /*
     * This method will list all products.
     */
    @RequestMapping(value = {"/allproducts"}, method = RequestMethod.GET)
    public String listProducts(ModelMap model, HttpSession session) {

        List<Product> products = productService.findAllProducts();
        model.addAttribute("loggedinuser", getCurrentUser());
        model.addAttribute("products", products);
    

        return "productsList";
    }


    /*
     * This method will provide the medium to add a new product.
     */
    @RequestMapping(value = {"/newproduct"}, method = RequestMethod.GET)
    public String newProduct(ModelMap model) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getCurrentUser());
        return "productAdd";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * saving product in database. It also validates the product input
     */
    @RequestMapping(value = {"/newproduct"}, method = RequestMethod.POST)
    public String saveNewProduct(@ModelAttribute("product") @Valid Product product, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {

            return "productAdd";
        }
        productService.saveProduct(product);

        model.addAttribute("success", "Product <strong>" + product.getId() + " " + product.getName() + "</strong> has been add successfully.");
        model.addAttribute("idproduct", product.getId());
        model.addAttribute("loggedinuser", getCurrentUser());

        return "productMessages";
    }

    /*
     * This method will delete an product by it's id value. And product photo by photo name.
     * If the product information is in the purchase order, returns the message in which the purchase orders are.
     */
    @RequestMapping(value = {"/delete-product-{id}"}, method = RequestMethod.GET)
    public String deleteProduct(@PathVariable String id, ModelMap model) {

        Product product = productService.finndById(id);

        List<OrderDetails> orderDetailses = product.getDetailses();
        if (orderDetailses.isEmpty()) {
            File photo = new File(UPLOAD_LOCATION + product.getPhotoName());
            photo.delete();
            productService.deleteById(id);

            return "redirect:/allproducts";
        } else {

            StringBuilder strb = new StringBuilder();
            strb.append("You cannot delete a product because product information is in the purchase order: ");
            for (OrderDetails orderDetails : orderDetailses) {
                strb.append("<strong>");
                strb.append(orderDetails.getOrderNumber().getId());
                strb.append("</strong> ");
            }
            strb.append(".");
            model.addAttribute("failure", strb);
            model.addAttribute("loggedinuser", getCurrentUser());
            return "productMessages";
        }

    }

    /*
     * This method will provide the medium to update an existing product.
     */
    @RequestMapping(value = {"/edit-product-{id}"}, method = RequestMethod.GET)
    public String editProduct(@PathVariable String id, ModelMap model) {

        Product product = productService.finndById(id);
        model.addAttribute("product", product);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getCurrentUser());

        return "productAdd";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating product in database. It also validates the product input
     */
    @RequestMapping(value = {"/edit-product-{id}"}, method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("product") @Valid Product product, BindingResult result, ModelMap model, @PathVariable String id) {

        if (result.hasErrors()) {
            return "productAdd";
        }

        productService.updateProduct(product);

        model.addAttribute("success", "Product <strong>" + product.getId() + " " + product.getName() + "</strong> has been add successfully.");
        model.addAttribute("idproduct", product.getId());
        model.addAttribute("loggedinuser", getCurrentUser());

        return "productMessages";
    }

    /*
     * This method will provide the medium to upload productphoto.
     */
    @RequestMapping(value = {"/singleupload-{productid}"}, method = RequestMethod.GET)
    public String getSingleUploadPage(ModelMap model, @PathVariable String productid) {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        model.addAttribute("loggedinuser", getCurrentUser());
        return "singleFileUploader";
    }

    /*
     * This method will be called on form submission, handling POST request for saving photo produc in file system. It also validates the file input.
     * Save photo in the project area /static/photo . Which is a temporary solution in the absence of a server to host images.
     */
    @RequestMapping(value = {"/singleupload-{productid}"}, method = RequestMethod.POST)
    public String singlePhotoUpload(@Valid FileBucket fileBucket, BindingResult result, ModelMap model, @PathVariable String productid) throws IOException {

        if (result.hasErrors()) {

            return "singleFileUploader";

        } else {

            MultipartFile multipartFile = fileBucket.getFile();
            Product product = productService.finndById(productid);
            String photoName = product.getId() + "_" + multipartFile.getOriginalFilename();

            FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + photoName));

            product.setPhotoName(photoName);
            productService.updateProduct(product);

            return "redirect:/allproducts";
        }
    }
    
    private String getCurrentUser() {
        String userName = null;
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (currentUser instanceof UserDetails) {
            userName = ((UserDetails) currentUser).getUsername();
        } else {
            userName = currentUser.toString();
        }
        return userName;
    }

}
