package es.ldrsoftware.fecon.prp.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad PRES - Partidas presupuestadas
 * @author Luis David
 *
 */
@Repository(value = "presDao")
public class PresDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Pres getByAnuaMespCateConc(long inst, int anua, int mesp, long cate, long conc) {
		TypedQuery<Pres> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT P FROM Pres P"
						+ " WHERE P.inst = " + inst + " "
						+ "   AND P.anua = " + anua + " "
						+ "   AND P.mesp = " + mesp + " "
						+ "   AND P.cate = " + cate + " "
						+ "   AND P.conc = " + conc + " "
				         , Pres.class);
		List<Pres> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Pres> getGroupList(long inst) {
		Query query = 
				this.getEntityManager().createQuery(
				          "SELECT P.anua, SUM(P.impo), SUM(P.imto), SUM(P.desv), MIN(P.esta) " 
						+ "     , SUM(CASE WHEN P.imto > 0 THEN P.imto ELSE 0 END) "
				        + "     , SUM(CASE WHEN P.imto < 0 THEN P.imto ELSE 0 END) "
						+ "     , SUM(P.bala) "
				        + "  FROM Pres P"
				        + " WHERE P.inst = " + inst + " "
						+ " GROUP BY P.anua "
				        + " ORDER BY P.anua ASC"
				         );
		
		@SuppressWarnings("unchecked")
		List<Object> resultList = (List<Object>)query.getResultList();
		List<Pres> presList = new ArrayList<Pres>();
		
		if (resultList != null && resultList.size() > 0) {
			ListIterator<Object> it = resultList.listIterator();
			
			while (it.hasNext()) {
				Object[] row = (Object[])it.next();
				int anua = 0;
				if (row[0] != null) { anua = (Integer)row[0];}
				double impo = 0;
				if (row[1] != null) { impo = (Double)row[1];}
				double imto = 0;
				if (row[2] != null) { imto = (Double)row[2];}
				double desv = 0;
				if (row[3] != null) { desv = (Double)row[3];}
				String esta = "";
				if (row[4] != null) { esta = (String)row[4];}
				double ingr = 0;
				if (row[5] != null) { ingr = (Double)row[5];}
				double gast = 0;
				if (row[6] != null) { gast = (Double)row[6];}
				double bala = 0;
				if (row[7] != null) { bala = (Double)row[7];}
				
				Pres pres = new Pres();
				pres.setInst(inst);
				pres.setAnua(anua);
				pres.setImpo(impo);
				pres.setImto(imto);
				pres.setDesv(desv);
				pres.setEsta(esta);
				pres.setToha(ingr);
				pres.setTode(gast);
				pres.setBala(bala);
				presList.add(pres);
			}
		}
		return presList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Pres> getGroupListByAnua(long inst, int anua) {
		
		Query query = 
				this.getEntityManager().createQuery(
				          "SELECT P.mesp, SUM(P.impo), SUM(P.imto), SUM(P.desv), SUM(P.bala), MIN(P.esta) "
						+ "  FROM Pres P " 
						+ " WHERE P.inst = " + inst + " "
						+ "   AND P.anua = " + anua + " "
						+ "   AND P.mesp <> 0 "
						+ " GROUP BY P.mesp "
						+ " ORDER BY P.mesp ASC "
						);
//		if (npre) {
//			query = query + " AND P.impo <> 0 ";
//		}
		
//		Query q = this.getEntityManager().createQuery(query);
		
		@SuppressWarnings("unchecked")
		List<Object> resultList = (List<Object>)query.getResultList();
		List<Pres> presList = new ArrayList<Pres>();
		
		if (resultList != null && resultList.size() > 0) {
			ListIterator<Object> it = resultList.listIterator();
			
			int mesEsperado = 1;
			
			while (it.hasNext()) {
				Object[] row = (Object[])it.next();
				int mesp = 0;
				if (row[0] != null) { mesp = (Integer)row[0];}
				double impo = 0;
				if (row[1] != null) { impo = (Double)row[1];}
				double imto = 0;
				if (row[2] != null) { imto = (Double)row[2];}
				double desv = 0;
				if (row[3] != null) { desv = (Double)row[3];}
				double bala = 0;
				if (row[4] != null) { bala = (Double)row[4];}
				String esta = "";
				if (row[5] != null) { esta = (String)row[5];}
				
				while (mesEsperado < mesp) {
					Pres pres = new Pres();
					pres.setInst(inst);
					pres.setAnua(anua);
					pres.setMesp(mesEsperado);
					pres.setImpo(0);
					pres.setImto(0);
					pres.setDesv(0);
					pres.setBala(0);
					pres.setEsta("A");
					presList.add(pres);
					mesEsperado++;	
				}
				
				Pres pres = new Pres();
				pres.setInst(inst);
				pres.setAnua(anua);
				pres.setMesp(mesp);
				pres.setImpo(impo);
				pres.setImto(imto);
				pres.setDesv(desv);
				pres.setBala(bala);
				pres.setEsta(esta);
				presList.add(pres);
				mesEsperado++;
			}
			while(mesEsperado <= 12) {
				Pres pres = new Pres();
				pres.setInst(inst);
				pres.setAnua(anua);
				pres.setMesp(mesEsperado);
				pres.setImpo(0);
				pres.setImto(0);
				pres.setDesv(0);
				pres.setBala(0);
				pres.setEsta("A");
				presList.add(pres);
				mesEsperado++;
			}
		} else {
			for(int i = 1; i<=12; i++) {
				Pres pres = new Pres();
				pres.setInst(inst);
				pres.setAnua(anua);
				pres.setMesp(i);
				pres.setImpo(0);
				pres.setImto(0);
				pres.setDesv(0);
				pres.setBala(0);
				pres.setEsta("A");
				presList.add(pres);
			}
		}
		return presList;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Pres> getGroupCateListByAnuaMesp(long inst, int anua, int mesp) {
		
		Query query = 
				this.getEntityManager().createQuery(
				          "SELECT P.cate, SUM(P.impo), SUM(P.imto), SUM(P.desv), SUM(P.bala), MIN(P.esta) "
						+ "  FROM Pres P, Cate C " 
						+ " WHERE P.inst = " + inst + " "
						+ "   AND P.anua = " + anua + " "
						+ "   AND P.mesp = " + mesp + " "
						+ "   AND P.inst = C.inst "
						+ "   AND P.cate = C.iden "
						+ " GROUP BY P.cate, C.orde "
						+ " ORDER BY C.orde ASC "
						);
		
		@SuppressWarnings("unchecked")
		List<Object> resultList = (List<Object>)query.getResultList();
		List<Pres> presList = new ArrayList<Pres>();
		if (resultList != null && resultList.size() > 0) {
			ListIterator<Object> it = resultList.listIterator();

			while (it.hasNext()) {
				Object[] row = (Object[])it.next();
				long cate = 0;
				if (row[0] != null) { cate = (Long)row[0];}
				double impo = 0;
				if (row[1] != null) { impo = (Double)row[1];}
				double imto = 0;
				if (row[2] != null) { imto = (Double)row[2];}
				double desv = 0;
				if (row[3] != null) { desv = (Double)row[3];}
				double bala = 0;
				if (row[4] != null) { bala = (Double)row[4];}
				String esta = "";
				if (row[5] != null) { esta = (String)row[5];}
								
				Pres pres = new Pres();
				pres.setInst(inst);
				pres.setAnua(anua);
				pres.setMesp(mesp);
				pres.setCate(cate);
				pres.setImpo(impo);
				pres.setImto(imto);
				pres.setDesv(desv);
				pres.setBala(bala);
				pres.setEsta(esta);
				presList.add(pres);
			}
		} 
		return presList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Pres> getGroupCateListByAnua(long inst, int anua) {
		
		Query query = 
				this.getEntityManager().createQuery(
				          "SELECT P.cate, SUM(P.impo), SUM(P.imto), SUM(P.desv), SUM(P.bala), MIN(P.esta) "
						+ "  FROM Pres P, Cate C " 
						+ " WHERE P.inst = " + inst + " "
						+ "   AND P.anua = " + anua + " "
						+ "   AND P.inst = C.inst "
						+ "   AND P.cate = C.iden "
						+ " GROUP BY P.cate, C.orde "
						+ " ORDER BY C.orde ASC "
						);
		
		@SuppressWarnings("unchecked")
		List<Object> resultList = (List<Object>)query.getResultList();
		List<Pres> presList = new ArrayList<Pres>();
		if (resultList != null && resultList.size() > 0) {
			ListIterator<Object> it = resultList.listIterator();

			while (it.hasNext()) {
				Object[] row = (Object[])it.next();
				long cate = 0;
				if (row[0] != null) { cate = (Long)row[0];}
				double impo = 0;
				if (row[1] != null) { impo = (Double)row[1];}
				double imto = 0;
				if (row[2] != null) { imto = (Double)row[2];}
				double desv = 0;
				if (row[3] != null) { desv = (Double)row[3];}
				double bala = 0;
				if (row[4] != null) { bala = (Double)row[4];}
				String esta = "";
				if (row[5] != null) { esta = (String)row[5];}
								
				Pres pres = new Pres();
				pres.setInst(inst);
				pres.setAnua(anua);
				pres.setCate(cate);
				pres.setImpo(impo);
				pres.setImto(imto);
				pres.setDesv(desv);
				pres.setBala(bala);
				pres.setEsta(esta);
				presList.add(pres);
			}
		} 
		return presList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Pres> getGroupConcListByAnuaCate(long inst, int anua, long cate) {
		
		Query query = 
				this.getEntityManager().createQuery(
				          "SELECT P.conc, SUM(P.impo), SUM(P.imto), SUM(P.desv), SUM(P.bala), MIN(P.esta) "
						+ "  FROM Pres P " 
						+ " WHERE P.inst = " + inst + " "
						+ "   AND P.anua = " + anua + " "
						+ "   AND P.cate = " + cate + " " 
						+ " GROUP BY P.conc "
						);
		
		@SuppressWarnings("unchecked")
		List<Object> resultList = (List<Object>)query.getResultList();
		List<Pres> presList = new ArrayList<Pres>();
		if (resultList != null && resultList.size() > 0) {
			ListIterator<Object> it = resultList.listIterator();

			while (it.hasNext()) {
				Object[] row = (Object[])it.next();
				long conc = 0;
				if (row[0] != null) { conc = (Long)row[0];}
				double impo = 0;
				if (row[1] != null) { impo = (Double)row[1];}
				double imto = 0;
				if (row[2] != null) { imto = (Double)row[2];}
				double desv = 0;
				if (row[3] != null) { desv = (Double)row[3];}
				double bala = 0;
				if (row[4] != null) { bala = (Double)row[4];}
				String esta = "";
				if (row[5] != null) { esta = (String)row[5];}
								
				Pres pres = new Pres();
				pres.setInst(inst);
				pres.setAnua(anua);
				pres.setCate(cate);
				pres.setConc(conc);
				pres.setImpo(impo);
				pres.setImto(imto);
				pres.setDesv(desv);
				pres.setBala(bala);
				pres.setEsta(esta);
				presList.add(pres);
			}
		} 
		return presList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Pres> getListByAnuaMesp(long inst, int anua, int mesp) {
		
		TypedQuery<Pres> query = 
				this.getEntityManager().createQuery(
				          "SELECT P FROM Pres P, Cate C "
						+ " WHERE P.inst = " + inst + " "
						+ "   AND P.anua = " + anua + " "
						+ "   AND P.mesp = " + mesp + " "
						+ "   AND P.inst = C.inst "
						+ "   AND P.cate = C.iden "
						+ " ORDER BY C.orde ASC "
						, Pres.class);
		
		List<Pres> resultList = query.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Pres> getListByAnuaMespPres(long inst, int anua, int mesp) {
		
		TypedQuery<Pres> query = 
				this.getEntityManager().createQuery(
				          "SELECT P FROM Pres P, Cate C "
						+ " WHERE P.inst = " + inst + " "
						+ "   AND P.anua = " + anua + " "
						+ "   AND P.mesp = " + mesp + " "
						+ "   AND P.inst = C.inst "
						+ "   AND P.cate = C.iden "
						+ "   AND P.impo <> 0 "
						+ " ORDER BY C.orde ASC "
						, Pres.class);
		
		List<Pres> resultList = query.getResultList();
		return resultList;
	}

	//FIXME: Incorporar el cruce con los conceptos para ordenar
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Pres> getListByAnuaMespCate(long inst, int anua, int mesp, long cate) {
		
		TypedQuery<Pres> query = 
				this.getEntityManager().createQuery(
				          "SELECT P FROM Pres P "
						+ " WHERE P.inst = " + inst + " "
						+ "   AND P.anua = " + anua + " "
						+ "   AND P.mesp = " + mesp + " "
						+ "   AND P.cate = " + cate + " "
						+ " ORDER BY P.conc ASC "
						, Pres.class);
		
		List<Pres> resultList = query.getResultList();
		return resultList;
	}

	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Pres save(Pres pres) {
		return getEntityManager().merge(pres);
	}
}
