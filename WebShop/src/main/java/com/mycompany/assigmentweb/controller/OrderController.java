package com.mycompany.assigmentweb.controller;

import com.mycompany.assigmentweb.model.ItemCart;
import com.mycompany.assigmentweb.model.OrderDetails;
import com.mycompany.assigmentweb.model.OrderPr;
import com.mycompany.assigmentweb.model.Product;
import com.mycompany.assigmentweb.model.StateOrder;
import com.mycompany.assigmentweb.service.OrderDetailsService;
import com.mycompany.assigmentweb.service.OrderPrService;
import com.mycompany.assigmentweb.service.ProductService;
import com.mycompany.assigmentweb.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderController {

    @Autowired
    OrderPrService orderPrService;

    @Autowired
    OrderDetailsService orderDetailsService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    /*
     * This method will list all orders.
     */
    @RequestMapping(value = {"/allorders"}, method = RequestMethod.GET)
    public String listOrderPr(ModelMap model) {

        List<OrderPr> orders = orderPrService.findAllOrder();

        if (orders.isEmpty()) {

            model.addAttribute("loggedinuser", getCurrentUser());
            model.addAttribute("emptyorders", "No orders created.");
            return "orderMessages";

        } else {

            model.addAttribute("orders", orders);
            model.addAttribute("loggedinuser", getCurrentUser());

            return "ordersList";
        }
    }

    /*
     * This method will list all orders connected user.
     */
    @RequestMapping(value = {"/allordersuser"}, method = RequestMethod.GET)
    public String listOrderPrUser(ModelMap model) {

        List<OrderPr> orders = orderPrService.finfdAllUserOrders(getCurrentUser());

        if (orders.isEmpty()) {

            model.addAttribute("loggedinuser", getCurrentUser());
            model.addAttribute("emptyorders", "No orders created.");
            return "orderMessages";

        } else {

            model.addAttribute("orders", orders);
            model.addAttribute("loggedinuser", getCurrentUser());

            return "ordersList";
        }
    }

    /*
     * This method changes the status of the purchase order.
     * If there is a sufficient amount of products, it changes the status to DELIVERED and reduces the quantities of those products. Otherwise it sets the state to REJECTED.
     */
    @RequestMapping(value = {"/orderasccept-{ordernumber}"}, method = RequestMethod.GET)
    public String ascceptOrder(@PathVariable String ordernumber, ModelMap model) {

        OrderPr orderPr = orderPrService.finndById(ordernumber);

        if (orderPr.getState().equals(StateOrder.PROCESS.getState())) {

            List<Integer> quantitys = new ArrayList<Integer>();
            List<Product> products = new ArrayList<Product>();
            List<String> messages = new ArrayList<String>();

            List<OrderDetails> orderDetails = orderDetailsService.findOrderDetails(ordernumber);

            for (OrderDetails orderDetail : orderDetails) {

                if (orderDetail.getQuantity() > orderDetail.getProduct().getQuantity()) {
                    messages.add(orderDetail.getProduct().getName() + ",");
                } else {
                    products.add(orderDetail.getProduct());
                    quantitys.add(orderDetail.getQuantity());
                }
            }

            if (messages.isEmpty()) {

                orderPr.setState(StateOrder.DELIVERED.getState());
                orderPr.setDescription("The order has been accepted. And delivered.");
                orderPrService.updateOrderPr(orderPr);

                for (int i = 0; i < products.size(); i++) {
                    products.get(i).setQuantity(products.get(i).getQuantity() - quantitys.get(i));
                    productService.updateProduct(products.get(i));
                }

            } else {

                StringBuilder str = new StringBuilder();
                for (String string : messages) {
                    str.append(string);

                }
                orderPr.setState(StateOrder.REJECTED.getState());
                orderPr.setDescription("Physical inspection determined that the item: " + str + "  was not in the required quantity. Please make your new purchase order.");
                orderPrService.updateOrderPr(orderPr);

            }

            return "redirect:/allorders";
        } else {

            model.addAttribute("message", "The order has already been processed.");
            model.addAttribute("loggedinuser", getCurrentUser());
            return "orderMessages";
        }
    }

    /*
     * This method changes the status. REJECTED.
     */
    @RequestMapping(value = {"/orderreject-{ordernumber}"}, method = RequestMethod.GET)
    public String rejectedOrder(@PathVariable String ordernumber, ModelMap model) {

        OrderPr orderPr = orderPrService.finndById(ordernumber);

        if (orderPr.getState().equals(StateOrder.PROCESS.getState())) {

            orderPr.setState(StateOrder.REJECTED.getState());
            orderPr.setDescription("Order been rejected. Contact the warehouse.");
            orderPrService.updateOrderPr(orderPr);

            return "redirect:/allorders";
        } else {

            model.addAttribute("message", "The order has already been processed");
            model.addAttribute("loggedinuser", getCurrentUser());
            return "orderMessages";
        }
    }


    /*
     * This method will save order and order details.
     */
    @RequestMapping(value = {"/saveorder"}, method = RequestMethod.GET)
    public String saveOrder(ModelMap model, HttpSession session) {

        List<ItemCart> cart = (List<ItemCart>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {

            model.addAttribute("edit", true);
            return "redirect:/allproducts";
        } else {

            for (ItemCart itemCart : cart) {
                if (itemCart.getQuantity() == 0) {

                    model.addAttribute("quantityemmpty", "Add the desired quantity or remove the product " + itemCart.getProduct().getName() + ", from your cart.");
                    return "redirect:/cart";
                }
                OrderPr orderPr = new OrderPr();
                orderPr.setUser(userService.findByUserName(getCurrentUser()));
                orderPrService.saveOrderPr(orderPr);
                OrderDetails details = new OrderDetails(itemCart.getQuantity(), orderPr, itemCart.getProduct());
                orderDetailsService.saveOrderDetails(details);

            }
            cart.clear();

            return "redirect:/allordersuser";
        }
    }

    /*
     * This method will delete order and order details.
     */
    @RequestMapping(value = {"/deleteorder-{ordernumber}"}, method = RequestMethod.GET)
    public String deleteOrder(@PathVariable String ordernumber, ModelMap model) {

        OrderPr orderPr = orderPrService.finndById(ordernumber);

        if (orderPr.getState().equals(StateOrder.PROCESS.getState())) {

            List<OrderDetails> orderDetails = orderDetailsService.findOrderDetails(ordernumber);
            for (OrderDetails orderDetail : orderDetails) {
                orderDetailsService.deleteOrderDetails(orderDetail);
            }
            orderPrService.deleteOrderPr(orderPr);

            model.addAttribute("messagessucces", "Order " + ordernumber + " has been deleted.");
            //  model.addAttribute("ordernumber", ordernumber);
            model.addAttribute("loggedinuser", getCurrentUser());
            return "orderMessages";

        } else {

            model.addAttribute("message", "The order has already been processed.");
            model.addAttribute("loggedinuser", getCurrentUser());
            return "orderMessages";
        }
    }

    /*
     * This method will list all orders details.
     */
    @RequestMapping(value = {"/alldetailsorders-{ordernumber}"}, method = RequestMethod.GET)
    public String listOrderDetails(@PathVariable String ordernumber, ModelMap model) {

        OrderPr order = orderPrService.finndById(ordernumber);
        List<OrderDetails> orderDetails = orderDetailsService.findOrderDetails(ordernumber);
        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("loggedinuser", getCurrentUser());
        return "ordersDetailsList";
    }

    /*
     * This method will create item in cart.
     */
    @RequestMapping(value = {"/addtocart-{productnumber}"}, method = RequestMethod.GET)
    public String addToCart(@PathVariable String productnumber, ModelMap model, HttpSession session) {

        if (session.getAttribute("cart") == null) {
            List<ItemCart> cart = new ArrayList<ItemCart>();
            cart.add(new ItemCart(productService.finndById(productnumber), 0));
            session.setAttribute("cart", cart);
        } else {
            List<ItemCart> cart = (List<ItemCart>) session.getAttribute("cart");

            // using method isExisting, create new ItemCart if not existing
            int index = isExisting(productnumber, session);
            if (index == -1) {
                cart.add(new ItemCart(productService.finndById(productnumber), 0));
            } else {
                model.addAttribute("loggedinuser", getCurrentUser());
                return "cart";
            }

            session.setAttribute("cart", cart);
        }
        model.addAttribute("loggedinuser", getCurrentUser());
        return "cart";
    }

    /*
     * This method will list all items cart(CART) or messege if cart is empty.
     */
    @RequestMapping(value = {"/cart"}, method = RequestMethod.GET)
    public String viewCart(ModelMap model, HttpSession session) {

        List<ItemCart> cart = (List<ItemCart>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {

            model.addAttribute("edit", true);

            return "redirect:/allproducts";
        } else {

            session.setAttribute("cart", cart);
            model.addAttribute("loggedinuser", getCurrentUser());

            return "cart";
        }

    }

    /*
     * This method will delete an item from cart by it's product numeber value.
     */
    @RequestMapping(value = "/deletefromcart-{productnumber}", method = RequestMethod.GET)
    public String deleteItemCart(@PathVariable String productnumber, HttpSession session, ModelMap model) {

        List<ItemCart> cart = (List<ItemCart>) session.getAttribute("cart");

        int index = isExisting(productnumber, session);
        cart.remove(index);

        session.setAttribute("cart", cart);
        model.addAttribute("loggedinuser", getCurrentUser());

        return "cart";
    }

    /*
     * This method will update quantity an item from cart by it's product numeber value.
     */
    @RequestMapping(value = {"/updatequantity-{productnumber}-{orderquantity}"}, method = RequestMethod.GET)
    public String updateItemCartQu(@PathVariable String productnumber, @PathVariable int orderquantity, HttpSession session, ModelMap model) {

        List<ItemCart> cart = (List<ItemCart>) session.getAttribute("cart");

        int index = isExisting(productnumber, session);
        ItemCart items = cart.get(index);
        items.setQuantity(orderquantity);

        session.setAttribute("cart", cart);
        model.addAttribute("loggedinuser", getCurrentUser());

        return "cart";
    }

    /*
     * This method checks if itemCart existing in list cart.
     */
    private int isExisting(String id, HttpSession session) {

        List<ItemCart> cart = (List<ItemCart>) session.getAttribute("cart");

        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getId().equals(id)) {
                return i;
            }
        }

        return -1;
    }

    /*
     * This method returns the principal[user-name] of logged-in user.
     */
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
