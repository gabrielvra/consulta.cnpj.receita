package db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Inicializar e manter uma fábrica de {@link EntityManager}
 * @author Gabriel Vieira (gabrielvra@outlook.com)
 */
public class PersistenceUnit {

    private static EntityManagerFactory entityManagerFactory;

    /**
     * Inicializa um {@link EntityManagerFactory} para criação da {@link EntityManager}
     */
    public static void init() {
        if (entityManagerFactory != null) {
            return;
        }
        entityManagerFactory = Persistence.createEntityManagerFactory("consulta_cnpj");
    }
 
    /**
     * Retorna um {@link EntityManager}
     * @return Instância de {@link EntityManager}
     */
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
 
    /**
     * Finalizar a fábrica de {@link EntityManager}
     */
    public static void finalization() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}