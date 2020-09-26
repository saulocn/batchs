package br.com.estudo.saulocn.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.estudo.saulocn.dao.OrderDao;
import br.com.estudo.saulocn.model.Order;

@Stateless
public class OrderService {

    @Inject
    private OrderDao orderDao;

    public void buy(final Order order) {
        order.setPaid(false);
        orderDao.save(order);
    }

    public List<Order> list() {
        return orderDao.list();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void sendToPayment(final int id) {
        final Order order = orderDao.getById(id);
        orderDao.sendToPayment(order);
    }

    public void sendToPaymentWithoutTransaction(final int id) {
        final Order order = orderDao.getById(id);
        orderDao.sendToPayment(order);
    }
}
