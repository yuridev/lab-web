package br.com.lab.repositories;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;

import br.com.caelum.vraptor.ioc.Component;
import br.com.lab.models.SequenciadorOrcamento;

@Component
public class SequenciadorOrcamentoRepositoryImpl extends Repository<SequenciadorOrcamento, Long> implements
        SequenciadorOrcamentoRepository {

    SequenciadorOrcamentoRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public SequenciadorOrcamento getNext() {
        Criteria criteria = createCriteria();
        String anoAtual = new LocalDate().toString("yy");
        criteria.add(Restrictions.eq("data", anoAtual));
        criteria.addOrder(Order.desc("id"));
        criteria.setMaxResults(1);
        SequenciadorOrcamento seq = (SequenciadorOrcamento) criteria.uniqueResult();
        SequenciadorOrcamento retorno = new SequenciadorOrcamento();
        if (seq == null) {
            retorno.setNumero(1);
        } else {
            retorno.setNumero(seq.getNumero() + 1);
        }
        retorno.setData(anoAtual);
        return retorno;
    }
}
