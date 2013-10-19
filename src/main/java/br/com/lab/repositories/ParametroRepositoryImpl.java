package br.com.lab.repositories;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.lab.models.Parametro;

@Component
public class ParametroRepositoryImpl
    extends Repository<Parametro, Long>
    implements ParametroRepository {

	ParametroRepositoryImpl(Session session) {
		super(session);
	}
}
