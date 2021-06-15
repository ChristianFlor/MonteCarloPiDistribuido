package servicios;

import org.osoa.sca.annotations.Service;

@Service
public interface ServicioAsignarPuntos{

    public long[] asignarPuntos();

}