package br.com.lab.repositories;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.caelum.vraptor.ioc.Component;
import br.com.lab.models.Orcamento;

@Component
public class OrcamentoRepositoryImpl
    extends Repository<Orcamento, Long>
    implements OrcamentoRepository {

	OrcamentoRepositoryImpl(Session session) {
		super(session);
	}
}
