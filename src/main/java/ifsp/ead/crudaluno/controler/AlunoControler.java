package ifsp.ead.crudaluno.controler;

import ifsp.ead.crudaluno.dao.GenericDao;
import ifsp.ead.crudaluno.dao.GenericDaoJpa;
import ifsp.ead.crudaluno.model.Aluno;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class AlunoControler {

    private final GenericDao<Aluno, Integer> dao;
    public AlunoControler() {
        dao = new GenericDaoJpa<>(Aluno.class);
    }
    public void createAluno(String nome, String ra, String email, Double nota1, Double nota2, Double nota3){
        Aluno aluno = new Aluno(nome, ra, email, nota1, nota2, nota3);
        dao.create(aluno);
    }

    public void updateAluno(Integer id, String nome, String ra, String email, Double nota1, Double nota2, Double nota3){
        Aluno aluno = new Aluno(id, nome, ra, email, nota1, nota2, nota3);
        dao.update(aluno);
    }

    public void deleteAluno(Integer id){
        dao.delete(id);
    }

    public Aluno findAluno(Integer id){
        return dao.find(id);
    }

    public List<Aluno> findAllAluno(){
        return dao.findAll();
    }

    public List<Aluno> findAlunoByName(String nome) {
        return dao.findByName(nome);
    }

}
