package com.yaps.petstore.common.delegate;

import com.yaps.petstore.AbstractTestCase;
import com.yaps.petstore.common.dto.*;
import com.yaps.petstore.common.exception.*;
import com.yaps.petstore.common.rmi.RMIConstant;
import com.yaps.petstore.server.service.order.OrderService;
import junit.framework.TestSuite;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class tests the CatalogService class
 */
public final class OrderDelegateTest extends AbstractTestCase {

    public OrderDelegateTest(final String s) {
        super(s);
    }

    public static TestSuite suite() {
        return new TestSuite(OrderDelegateTest.class);
    }

    //==================================
    //=            Test cases          =
    //==================================
    /**
     * This test tries to find an object with a invalid identifier.
     */
    public void testDelegateFindOrderWithInvalidValues() throws Exception {

        // Binds the RMI service
        bindService();

        // Finds an object with a unknown identifier
        final String id = getUniqueStringId();
        try {
            OrderDelegate.findOrder(id);
            fail("Object with unknonw id should not be found");
        } catch (ObjectNotFoundException e) {
        }

        // Finds an object with an empty identifier
        try {
            OrderDelegate.findOrder(new String());
            fail("Object with empty id should not be found");
        } catch (CheckException e) {
        }

        // Finds an object with a null identifier
        try {
            OrderDelegate.findOrder(null);
            fail("Object with null id should not be found");
        } catch (CheckException e) {
        }
    }

    /**
     * This method ensures that creating an object works. It first finds the object,
     * makes sure it doesn't exist, creates it and checks it then exists.
     */
    public void testDelegateCreateOrder() throws Exception {
        final String id = getUniqueStringId();
        OrderDTO orderDTO = null;

        // Creates an object
        final String orderId = createOrder(id);

        // Ensures that the object exists
        try {
            orderDTO = findOrder(orderId);
        } catch (ObjectNotFoundException e) {
            fail("Object has been created it should be found");
        }

        // Checks that it's the right object
        checkOrder(orderDTO, id);

        // Cleans the test environment
        deleteOrder(orderId);

        try {
            findOrder(orderId);
            fail("Object has been deleted it shouldn't be found");
        } catch (ObjectNotFoundException e) {
        }
    }

    /**
     * This test tries to create an object with a invalid values.
     */
    public void testDelegateCreateOrderWithInvalidValues() throws Exception {
        OrderDTO orderDTO;

        // Binds the RMI service
        bindService();

        // Creates an object with a null parameter
        try {
            OrderDelegate.createOrder(null);
            fail("Object with null parameter should not be created");
        } catch (CreateException e) {
        }

        // Creates an object with empty values
        try {
            orderDTO = new OrderDTO(new String(), new String(), new String(), new String(), new String(), new String());
            OrderDelegate.createOrder(orderDTO);
            fail("Object with empty values should not be created");
        } catch (CheckException e) {
        }

        // Creates an object with null values
        try {
            orderDTO = new OrderDTO(null, null, null, null, null, null);
            OrderDelegate.createOrder(orderDTO);
            fail("Object with null values should not be created");
        } catch (CheckException e) {
        }
    }

    //==================================
    //=          Private Methods       =
    //==================================
    private OrderDTO findOrder(final String id) throws FinderException, CheckException {
        OrderDTO orderDTO = null;

        // Binds the RMI service
        bindService();

        try {
            orderDTO = OrderDelegate.findOrder(id);
        } catch (RemoteException e) {
            fail("Service is bound. Call should have succed");
        }

        // Unbinds the RMI service
        unbindService();

        try {
            orderDTO = OrderDelegate.findOrder(id);
            fail("Service is not bound. Call should have failed");
        } catch (RemoteException e) {
        }

        // Binds the RMI service
        bindService();

        return orderDTO;
    }

    // Creates a category first, then a product linked to this category and an item linked to the product
    // Creates a Customer and an order linked to the customer
    // Creates an orderLine linked to the order and the item
    private String createOrder(final String id) throws CreateException, CheckException, RemoteException {
        // Binds the RMI service
        bindService();

        // Create Category
        final CategoryDTO categoryDTO = new CategoryDTO("cat" + id, "name" + id, "description" + id);
        CatalogDelegate.createCategory(categoryDTO);
        // Create Product
        final ProductDTO productDTO = new ProductDTO("prod" + id, "name" + id, "description" + id);
        productDTO.setCategoryId("cat" + id);
        CatalogDelegate.createProduct(productDTO);
        // Create Item
        final ItemDTO itemDTO = new ItemDTO("item" + id, "name" + id, Double.parseDouble(id));
        itemDTO.setProductId("prod" + id);
        CatalogDelegate.createItem(itemDTO);

        // Create Customer
        final CustomerDTO customerDTO = new CustomerDTO("custo" + id, "firstname" + id, "lastname" + id);
        CustomerDelegate.createCustomer(customerDTO);

        // Create Order
        final OrderDTO orderDTO = new OrderDTO("firstname" + id, "lastname" + id, "street1" + id, "city" + id, "zip" + id, "country" + id);
        orderDTO.setStreet2("street2" + id);
        orderDTO.setCreditCardExpiryDate("ccexp" + id);
        orderDTO.setCreditCardNumber("ccnum" + id);
        orderDTO.setCreditCardType("cctyp" + id);
        orderDTO.setState("state" + id);
        orderDTO.setCustomerId(customerDTO.getId());

        // Creates two order lines
        final OrderLineDTO oi1 = new OrderLineDTO(Integer.parseInt(id), itemDTO.getUnitCost());
        oi1.setItemId(itemDTO.getId());
        final OrderLineDTO oi2 = new OrderLineDTO(Integer.parseInt(id), itemDTO.getUnitCost());
        oi2.setItemId(itemDTO.getId());
        final Collection orderLines = new ArrayList();
        orderLines.add(oi1);
        orderLines.add(oi2);
        orderDTO.setOrderLines(orderLines);

        final OrderDTO result = OrderDelegate.createOrder(orderDTO);
        return result.getId();
    }

    private void deleteOrder(final String id) throws RemoveException, CheckException, RemoteException {
        // Binds the RMI service
        bindService();

        OrderDelegate.deleteOrder(id);
    }

    private void checkOrder(final OrderDTO orderDTO, final String id) {
        assertEquals("firstname", "firstname" + id, orderDTO.getFirstname());
        assertEquals("lastname", "lastname" + id, orderDTO.getLastname());
        assertEquals("city", "city" + id, orderDTO.getCity());
        assertEquals("country", "country" + id, orderDTO.getCountry());
        assertEquals("state", "state" + id, orderDTO.getState());
        assertEquals("street1", "street1" + id, orderDTO.getStreet1());
        assertEquals("street2", "street2" + id, orderDTO.getStreet2());
        assertEquals("zipcode", "zip" + id, orderDTO.getZipcode());
        assertEquals("CreditCardExpiryDate", "ccexp" + id, orderDTO.getCreditCardExpiryDate());
        assertEquals("CreditCardNumber", "ccnum" + id, orderDTO.getCreditCardNumber());
        assertEquals("CreditCardType", "cctyp" + id, orderDTO.getCreditCardType());
        assertEquals("order items", 2, orderDTO.getOrderLines().size());
        assertEquals("item id", "item" + id, ((OrderLineDTO) orderDTO.getOrderLines().iterator().next()).getItemId());
    }


    private void bindService() {
        try {
            Naming.rebind(RMIConstant.ORDER_SERVICE, new OrderService());
        } catch (Exception e) {
            fail("Could not bind a service");
        }
    }

    private void unbindService() {
        try {
            Naming.unbind(RMIConstant.ORDER_SERVICE);
        } catch (Exception e) {
            fail("Could not unbind a service");
        }
    }
}
