package br.com.lab.repositories;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.lab.models.Cliente;

@Component
public class ClienteRepositoryImpl
    extends Repository<Cliente, Long>
    implements ClienteRepository {

	ClienteRepositoryImpl(Session session) {
		super(session);
	}
	
	
	public boolean cpfCnpjExistente(String cpfCnpj) {
	    Criteria criteria = createCriteria();
	    criteria.add(Restrictions.eq("cpfCnpj", cpfCnpj));
	    criteria.setMaxResults(1);
	    return criteria.list().size() > 0;
	}
}
