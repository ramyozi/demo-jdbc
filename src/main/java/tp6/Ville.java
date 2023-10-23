package tp6;

public class Ville {
    private int id;
    private String nom;
    private int population;
    private int idDept;
    private int idRegion;
	/** Constructeur
	 * @param id
	 * @param nom
	 * @param population
	 * @param idDept
	 * @param idRegion
	 */
	public Ville(int id, String nom, int population, int idDept,
			int idRegion) {
		super();
		this.id = id;
		this.nom = nom;
		this.population = population;
		this.idDept = idDept;
		this.idRegion = idRegion;
	}
	/** Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/** Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/** Getter
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/** Setter
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/** Getter
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}
	/** Setter
	 * @param population the population to set
	 */
	public void setPopulation(int population) {
		this.population = population;
	}
	/** Getter
	 * @return the idDept
	 */
	public int getIdDept() {
		return idDept;
	}
	/** Setter
	 * @param idDept the idDept to set
	 */
	public void setIdDept(int idDept) {
		this.idDept = idDept;
	}
	/** Getter
	 * @return the idRegion
	 */
	public int getIdRegion() {
		return idRegion;
	}
	/** Setter
	 * @param idRegion the idRegion to set
	 */
	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}
	@Override
	public String toString() {
		return "Ville [id=" + id + ", nom=" + nom + ", population="
				+ population + ", idDept=" + idDept + ", idRegion="
				+ idRegion + "]";
	}
    
    
    
    
    
}
