package br.com.lab.repositories;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.lab.models.Cliente;

@Component
public class ClienteRepositoryImpl
    extends Repository<Cliente, Long>
    implements ClienteRepository {

	ClienteRepositoryImpl(Session session) {
		super(session);
	}
}
