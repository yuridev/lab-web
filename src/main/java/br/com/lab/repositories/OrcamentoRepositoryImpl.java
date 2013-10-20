package br.com.lab.repositories;

import org.hibernate.Session;

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
