package br.com.lab.repositories;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.lab.models.QuadroOrcamento;

@Component
public class QuadroOrcamentoRepositoryImpl
    extends Repository<QuadroOrcamento, Long>
    implements QuadroOrcamentoRepository {

	QuadroOrcamentoRepositoryImpl(Session session) {
		super(session);
	}
}
