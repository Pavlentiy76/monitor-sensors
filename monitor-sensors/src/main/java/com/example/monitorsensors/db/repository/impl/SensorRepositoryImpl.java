package com.example.monitorsensors.db.repository.impl;

import com.example.monitorsensors.config.SensorsSessionFactory;
import com.example.monitorsensors.db.repository.SensorRepository;
import com.example.monitorsensors.entity.Sensor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SensorRepositoryImpl implements SensorRepository {

    @Override
    public void save(Sensor sensor) {
        try (Session session = SensorsSessionFactory.getSession().openSession()) {
            session.save(sensor);
        }
    }

    @Override
    public Sensor getById(Long id) {
        try (Session session = SensorsSessionFactory.getSession().openSession()) {
            String hql = "from Sensor s join fetch s.range where s.id = :id ";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            return (Sensor) query.uniqueResult();
        }
    }

    @Override
    public void deleteSensor(Sensor sensor) {
        try (Session session = SensorsSessionFactory.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(sensor);
            transaction.commit();
            session.clear();
        }
    }

    @Override
    public Sensor updateSensor(Long id, Sensor sensor) {
        try (Session session = SensorsSessionFactory.getSession().openSession()) {
            session.beginTransaction();
            session.update(sensor);
            session.getTransaction().commit();
        }
        return getById(id);
    }

    @Override
    public List<Sensor> getAllSensors() {
        try (Session session = SensorsSessionFactory.getSession().openSession()) {
            String hql = "from Sensor s join fetch s.range";
            Query query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public List<Sensor> searchByPartOfTitleOrModel(String partOfNameOrModel) {

        try (Session session = SensorsSessionFactory.getSession().openSession()) {
            String hql = "from Sensor s join fetch s.range where s.title like :partOfNameOrModel or s.model like :partOfNameOrModel";
            Query query = session.createQuery(hql);
            query.setParameter("partOfNameOrModel", "%" + partOfNameOrModel + "%");
            return query.list();
        }
    }

}
