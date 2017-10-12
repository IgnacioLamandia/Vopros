package tk.vopros.backend.dao

import tk.vopros.backend.model.Proyecto
import java.util.List

interface ProyectoDAO {
	def void saveProyecto(Proyecto issue);
	
	def List<Proyecto> getAllProyectos();
}