package com.roomplannerBackend.roomplannerBackend.persistence;

import com.roomplannerBackend.roomplannerBackend.domain.Booking;
import com.roomplannerBackend.roomplannerBackend.domain.Customer;
import com.roomplannerBackend.roomplannerBackend.domain.repository.CustomerRepository;
import com.roomplannerBackend.roomplannerBackend.persistence.crud.ClienteCrudRepository;
import com.roomplannerBackend.roomplannerBackend.persistence.entity.Cliente;
import com.roomplannerBackend.roomplannerBackend.persistence.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository implements CustomerRepository {
    @Autowired
    private ClienteCrudRepository clienteCrudRepository;
    @Autowired
    private CustomerMapper mapper;


    @Override
    public List<Customer> getAll() {
        List<Cliente> customers =(List<Cliente>) clienteCrudRepository.findAll();
        return mapper.toCustomers(customers);
    }



    @Override
    public Optional<Customer> getCustomerCredentials(String customerId) {
        return clienteCrudRepository.findById(customerId).map(cliente -> {
            Customer customer = mapper.toCustomer(cliente);
            return customer;
        });
    }

    @Override
    public Customer save(Customer customer) {
        Cliente cliente = mapper.toCliente(customer);
        return mapper.toCustomer(clienteCrudRepository.save(cliente));
    }


    @Override
    public Optional<Customer> findByEmailAndpassword(String email, String password) {
        return clienteCrudRepository.findByCorreoAndContraseña(email, password)
                .map(mapper::toCustomer);
    }

}
