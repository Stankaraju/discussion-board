package com.discussionboard.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.discussionboard.Document;

@Repository("UploadFileDao")
public class UploadFileDaoImpl extends AbstractDao<Integer, Document> implements UploadFileDao {
	@Autowired
    private SessionFactory sessionFactory;

 
	@SuppressWarnings("unchecked")
    public List<Document> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<Document>)crit.list();
    }
 
    public void save(Document document) {
        persist(document);
    }
 
     
    public Document findById(int id) {
        return getByKey(id);
    }
 
    @SuppressWarnings("unchecked")
    public List<Document> findAllByUserId(int userId){
        Criteria crit = createEntityCriteria();
        Criteria userCriteria = crit.createCriteria("user");
        userCriteria.add(Restrictions.eq("id", userId));
        return (List<Document>)crit.list();
    }
 
     
    public void deleteById(int id) {
        Document document =  getByKey(id);
        delete(document);
    }
}
